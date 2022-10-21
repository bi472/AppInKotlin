package com.example.myfirstappinkotlin.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myfirstappinkotlin.R
import com.example.myfirstappinkotlin.presentation.light.LightFragment
import com.example.myfirstappinkotlin.presentation.mqttsettings.MqttSettingsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.commit{
                setReorderingAllowed(true)
                replace<MainFragment>(R.id.container)
            }
        }
    }
}