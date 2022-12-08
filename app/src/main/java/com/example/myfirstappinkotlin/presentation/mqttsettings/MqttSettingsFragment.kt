package com.example.myfirstappinkotlin.presentation.mqttsettings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.domain.models.LightName
import com.example.myfirstappinkotlin.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MqttSettingsFragment : Fragment(R.layout.fragment_mqtt_settings) {

    private val vm: MqttSettingsViewModel by viewModel<MqttSettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mqtt_settings, container, false)
        val lightNameEditText = view.findViewById<EditText>(R.id.lightNameED)
        val topicEditText = view.findViewById<EditText>(R.id.topicEditText)
        val lightData = view.findViewById<TextView>(R.id.lightData)
        val saveDataButton = view.findViewById<Button>(R.id.sendButton)
        val receiveButton = view.findViewById<Button>(R.id.receiveButton)

        vm.lightNamesLive.observe(viewLifecycleOwner){
            lightData.text = it.toString()
        }


        saveDataButton.setOnClickListener{
            vm.insert(LightName(
                lightNameEditText.toString(),
                topicEditText.toString()
            ))
        }

        receiveButton.setOnClickListener{
            vm.load()
        }

        return view
    }
}