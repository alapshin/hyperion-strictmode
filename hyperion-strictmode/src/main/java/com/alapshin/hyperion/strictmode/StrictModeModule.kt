package com.alapshin.hyperion.strictmode

import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.willowtreeapps.hyperion.plugin.v1.PluginModule

class StrictModeModule(private val vmPolicy: StrictMode.VmPolicy, private val threadPolicy: StrictMode.ThreadPolicy) :
    PluginModule(), View.OnClickListener {
    lateinit var view: View

    companion object {
        @JvmStatic
        var enabled: Boolean = true
    }

    private fun enableStrictMode() {
        StrictMode.setVmPolicy(vmPolicy)
        StrictMode.setThreadPolicy(threadPolicy)
    }

    private fun disableStrictMode() {
        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX)
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX)
    }

    private fun updateView(strictModeEnabled: Boolean) {
        view.isSelected = strictModeEnabled
    }

    override fun onClick(v: View) {
        if (!enabled) {
            enabled = true
            enableStrictMode()
        } else {
            enabled = false
            disableStrictMode()
        }
        updateView(enabled)
    }

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View? {
        return layoutInflater.inflate(R.layout.hyperion_strictmode_plugin_item, parent, false).also { v ->
            this.view = v
            updateView(enabled)
            v.setOnClickListener(this)
        }
    }
}
