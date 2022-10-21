package com.example.myfirstappinkotlin.presentation.light

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myfirstappinkotlin.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LightFragment : Fragment(R.layout.light_fragment) {

    private val vm: LightViewModel by viewModel<LightViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.light_fragment, container, false)

        val userData = view.findViewById<TextView>(R.id.userData)
        val topicED = view.findViewById<EditText>(R.id.topicEditText)
        val messageED = view.findViewById<EditText>(R.id.messageEditText)
        val publishButton = view.findViewById<Button>(R.id.publishButton)
        val subscribeButton = view.findViewById<Button>(R.id.subscribeButton)

        publishButton.setOnClickListener {
            val topic = topicED.text.toString()
            val message = messageED.text.toString()
            vm.publish(topic = topic, message = message)
        }

        subscribeButton.setOnClickListener {
            val topic = topicED.text.toString()
            vm.subscribe(topic)
        }

        vm.lightLive.observe(viewLifecycleOwner){
            userData.text = it
        }
        return view
    }

}