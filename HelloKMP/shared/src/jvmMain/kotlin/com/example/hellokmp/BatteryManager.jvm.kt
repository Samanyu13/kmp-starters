package com.example.hellokmp

import oshi.SystemInfo

class BatteryManagerImpl : BatteryManager {
    override fun getBatteryLevel(): Int {
        val systemInfo = SystemInfo()
        val hardware = systemInfo.hardware
        val batteryPowerSources = hardware.powerSources
        if (batteryPowerSources.isEmpty()) {
            return -1
        }
        val battery = batteryPowerSources[0]
        return (battery.remainingCapacityPercent * 100).toInt()
    }
}
