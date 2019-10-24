package com.alapshin.hyperion.strictmode

import android.content.Context
import android.os.StrictMode
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import java.lang.NullPointerException

@AutoService(Plugin::class)
class StrictModePlugin : Plugin() {
    lateinit var vmPolicy: StrictMode.VmPolicy
    lateinit var threadPolicy: StrictMode.ThreadPolicy

    override fun createPluginModule(): PluginModule? {
        return StrictModeModule(vmPolicy, threadPolicy)
    }

    override fun onApplicationCreated(context: Context) {
        super.onApplicationCreated(context)
        vmPolicy = StrictMode.getVmPolicy()
        threadPolicy = StrictMode.getThreadPolicy()
    }

}
