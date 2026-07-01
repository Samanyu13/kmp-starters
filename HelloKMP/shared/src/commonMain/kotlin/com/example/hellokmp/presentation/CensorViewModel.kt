package com.example.hellokmp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellokmp.network.InsultCensorClient
import com.example.hellokmp.util.NetworkError
import com.example.hellokmp.util.onError
import com.example.hellokmp.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CensorState(
    val uncensoredText: String = "",
    val censoredText: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: NetworkError? = null
)

class CensorViewModel(
    private val client: InsultCensorClient
) : ViewModel() {

    private val _state = MutableStateFlow(CensorState())
    val state: StateFlow<CensorState> = _state.asStateFlow()

    fun onUncensoredTextChange(text: String) {
        _state.update { it.copy(uncensoredText = text) }
    }

    fun censorText() {
        val currentText = _state.value.uncensoredText
        if (currentText.isBlank()) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null, censoredText = null) }

            client.censorWords(currentText)
                .onSuccess { censored ->
                    _state.update { it.copy(censoredText = censored, isLoading = false) }
                }
                .onError { error ->
                    _state.update { it.copy(errorMessage = error, isLoading = false) }
                }
        }
    }
}
