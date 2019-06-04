package solid.ren.accessibilityservicedemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.support.v4.app.ActivityCompat.startActivity
import android.content.pm.PackageManager



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.btn_open_service).setOnClickListener {
            //打开系统设置中辅助功能
            val intent = Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }

        findViewById(R.id.btn_open_gp).setOnClickListener {
            val pm = packageManager
            val launchIntent = pm.getLaunchIntentForPackage("com.android.vending")
            startActivity(launchIntent)

        }
    }
}
