package com.example.b1

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listView)
        textViewMessage = findViewById(R.id.textViewMessage)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        val input = editTextNumber.text.toString()
        if (input.isEmpty()) {
            textViewMessage.text = "Vui lòng nhập số nguyên dương!"
            return
        }

        val n: Int
        try {
            n = input.toInt()
            if (n < 0) {
                textViewMessage.text = "Vui lòng nhập số nguyên dương!"
                return
            }
        } catch (e: NumberFormatException) {
            textViewMessage.text = "Vui lòng nhập số nguyên dương!"
            return
        }

        val results = mutableListOf<String>()
        val selectedId = radioGroup.checkedRadioButtonId

        when (selectedId) {
            R.id.radioEven -> {
                for (i in 0..n step 2) {
                    results.add(i.toString())
                }
            }
            R.id.radioOdd -> {
                for (i in 1..n step 2) {
                    results.add(i.toString())
                }
            }
            R.id.radioPerfectSquare -> {
                for (i in 0 until Math.sqrt(n.toDouble()).toInt() + 1) {
                    results.add((i * i).toString())
                }
            }
            else -> {
                textViewMessage.text = "Vui lòng chọn loại số!"
                return
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
        listView.adapter = adapter
        textViewMessage.text = ""
    }
}