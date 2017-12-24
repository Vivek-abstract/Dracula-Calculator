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
    var firstOperand:String?=""
    var secondOperand:String?=""
    var isOperationUndergoing = false
    var isDecimal = false

    fun btnNumberEvent(view:View) {

        val selectedButton = view as Button
        var btnClickValue:String=lowerNumber.text.toString()
        if(btnClickValue.equals("0")) {
            btnClickValue = ""
        }
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
                if(!isDecimal) {
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
        //Now user enters next number so reset isDecimal
        isDecimal = false
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
                    op = "÷"
                }
            }
            firstOperand = lowerNumber.text.toString()
            if(firstOperand.isNullOrBlank()) {
                //When user presses equal then the lowernumber text is in upperNumber
                firstOperand = upperNumber.text.toString()
            }
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
                        op = "÷"
                    }
                }
                isOperationUndergoing = true
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
                        op = "÷"
                    }
                }
                lowerNumber.setText("$op ")
            }
        }
    }

    fun btnDelEvent(view: View) {
        upperNumber.setText("")
        lowerNumber.setText("0")
        firstOperand = ""
        secondOperand = "0"
        op = ""
        isDecimal = false
        isOperationUndergoing = false
    }

    fun btnEqualEvent(view: View) {
        isDecimal = false
        secondOperand = lowerNumber.text.toString()
        isOperationUndergoing = false
        if(secondOperand!!.length > 2) {
            secondOperand = secondOperand!!.substring(2)
            println(secondOperand)
            var answer: Double? = null
            when (op) {
                "+" -> {
                    answer = firstOperand!!.toDouble() + secondOperand!!.toDouble()
                }
                "-" -> {
                    answer = firstOperand!!.toDouble() - secondOperand!!.toDouble()
                }
                "*" -> {
                    answer = firstOperand!!.toDouble() * secondOperand!!.toDouble()
                }
                "÷" -> {
                    answer = firstOperand!!.toDouble() / secondOperand!!.toDouble()
                }
            }
            //Now answer is the first operand
            firstOperand = answer.toString()
            lowerNumber.setText("")
            if(firstOperand!!.length <= 10) {
                upperNumber.setText(firstOperand)
            } else {
                //Only display upto 8 decimal places for decimal numbers
                upperNumber.setText(firstOperand!!.substring(0, 11))
            }
        } else {
            //If user didnt enter number and press enter
            var testUpper = upperNumber.text.toString()
            if (testUpper.isNullOrBlank()) {
                //User presses equal without any input
                lowerNumber.setText("0")
            } else {
                //There is a number in upperNumber
                lowerNumber.setText("")
            }
        }
    }
}
