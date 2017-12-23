package com.example.whiter0se.startuptemplate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var op = ""
    var firstOperand = ""
    var secondOperand = ""
    var isOperationUndergoing = false
    var isDecimal = false

    fun btnNumberEvent(view:View) {

        val selectedButton = view as Button
        var btnClickValue:String=lowerNumber.text.toString()
        when(selectedButton.id) {
            btn0.id -> btnClickValue+="0"
            btn1.id -> btnClickValue+="1"
            btn2.id -> btnClickValue+="2"
            btn3.id -> btnClickValue+="3"
            btn4.id -> btnClickValue+="4"
            btn5.id -> btnClickValue+="5"
            btn6.id -> btnClickValue+="6"
            btn7.id -> btnClickValue+="7"
            btn8.id -> btnClickValue+="8"
            btn9.id -> btnClickValue+="9"
            btnDecimal.id -> {
                if(isDecimal == false) {
                    btnClickValue += "."
                    isDecimal = true
                } else {
                    return
                }
            }
        }
        lowerNumber.setText(btnClickValue)
    }

    fun btnOpEvent(view: View) {
        val selectedButton = view as Button
        if(isOperationUndergoing==false) {
            isOperationUndergoing = true
            when (selectedButton.id) {
                btnAdd.id -> {
                    op = "+"
                }
                btnSub.id -> {
                    op = "-"
                }
                btnMul.id -> {
                    op = "*"
                }
                btnDiv.id -> {
                    op = "/"
                }
            }
            firstOperand = lowerNumber.text.toString()
            upperNumber.setText(firstOperand)
            lowerNumber.setText("$op ")
        } else {
            //Check if user typed number before pressing second operation
            var input = lowerNumber.text.toString()
            if(input.length > 2) {
                //By default input will be "+ " so length > 2 will mean user has
                // entered a number
                btnEqualEvent(view)
                when (selectedButton.id) {
                    btnAdd.id -> {
                        op = "+"
                    }
                    btnSub.id -> {
                        op = "-"
                    }
                    btnMul.id -> {
                        op = "*"
                    }
                    btnDiv.id -> {
                        op = "/"
                    }
                }
                firstOperand = upperNumber.text.toString()
                lowerNumber.setText("$op ")
            } else {
                //Override existing operation ex: user pressed + but meant * so he pressed *
                when (selectedButton.id) {
                    btnAdd.id -> {
                        op = "+"
                    }
                    btnSub.id -> {
                        op = "-"
                    }
                    btnMul.id -> {
                        op = "*"
                    }
                    btnDiv.id -> {
                        op = "/"
                    }
                }
                lowerNumber.setText("$op ")
            }
        }
    }

    fun btnDelEvent(view: View) {
        upperNumber.setText("")
        lowerNumber.setText("")
        firstOperand = ""
        secondOperand = ""
        op = ""
        isOperationUndergoing = false
    }

    fun btnEqualEvent(view: View) {
        secondOperand = lowerNumber.text.toString()
        if(secondOperand.length > 2) {
            secondOperand = secondOperand.substring(2)
            println(secondOperand)
            var answer: Double? = null
            when (op) {
                "+" -> {
                    answer = firstOperand.toDouble() + secondOperand.toDouble()
                }
                "-" -> {
                    answer = firstOperand.toDouble() - secondOperand.toDouble()
                }
                "*" -> {
                    answer = firstOperand.toDouble() * secondOperand.toDouble()
                }
                "/" -> {
                    answer = firstOperand.toDouble() / secondOperand.toDouble()
                }
            }
            //Now answer is the first operand
            firstOperand = answer.toString()
            lowerNumber.setText("")
            upperNumber.setText(answer.toString())
        } else {
            //If user didnt enter number and press enter
            lowerNumber.setText("")
        }
    }
}
