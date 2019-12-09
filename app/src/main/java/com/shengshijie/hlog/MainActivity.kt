package com.shengshijie.hlog

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shengshijie.log.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
import pub.devrel.easypermissions.EasyPermissions.RationaleCallbacks
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), PermissionCallbacks,
    RationaleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HLog.setLogImpl(LogbackImpl().apply {
            file = true
            db = true
            socket = true
            socketHost = ""
            socketPort = 0
        })
        HLog.init(application, getExternalFilesDir(null)?.absolutePath, "RFT")
        logTask()
    }

    fun test(view: View) {
        thread {
            for (i in 1..11) {
                HLog.verbose("FFF",DVC)
                HLog.debug("DDD",CRS)
                HLog.error("EEE",DBG)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults, this
        )
    }

    override fun onDestroy() {
        HLog.destroy()
        super.onDestroy()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onRationaleAccepted(requestCode: Int) {

    }

    @AfterPermissionGranted(RC_STORAGE_PERM)
    fun logTask() {
        if (EasyPermissions.hasPermissions(this, *EXTERNAL_STORAGE)) {
            Toast.makeText(this, "TODO: Storage things", Toast.LENGTH_LONG).show()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "应用需要存储权限写入日志",
                RC_STORAGE_PERM,
                *EXTERNAL_STORAGE
            )
        }
    }

}

const val RC_STORAGE_PERM = 123
val EXTERNAL_STORAGE = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)