package com.rr.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Variables to hold the operands and type of calculation
    private var operand1: Double? = null
    private var pedingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }

        //Set listener to the buttons
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try{
                val value = newNumber.text.toString().toDouble()
                performOperation(value, op)
            }catch (e:NumberFormatException){
                newNumber.setText("")
            }
            pedingOperation = op
        }
        operation.text = pedingOperation

        buttonEquals.setOnClickListener(opListener)
        buttonDevide.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonTimes.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)

        buttonNeg.setOnClickListener { view ->
            val value = newNumber.text.toString()
            if (value.isEmpty()) {
                newNumber.setText("-")
            } else {
                try {
                    var doubleValue = value.toDouble()
                    doubleValue *= -1
                    newNumber.setText(doubleValue.toString())
                } catch (e: java.lang.NumberFormatException) {
                    // newNumber was "-" or "." so must be cleared
                    newNumber.setText("")
                }
            }
        }


    }

    private fun performOperation(value:Double, operation:String){
       if (operand1 == null){
           operand1 = value
       } else{
           if (pedingOperation == "="){
               pedingOperation = operation
           }
           when (pedingOperation){
               "=" -> operand1 = value
               "/" -> {
                   operand1 = if (value == 0.0){
                       Double.NaN
                   } else {
                       operand1!! / value
                   }
               }
               "*" -> operand1 = operand1!! * value
               "-" -> operand1 = operand1!! - value
               "+" -> operand1 = operand1!! + value
           }
       }
        result.setText(operand1.toString())
        newNumber.setText("")
    }
}
