package com.kostyamuzhik.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.mariuszgromada.math.mxparser.Expression
// import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var expression: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result_text)

        findViewById<Button>(R.id.btn_9).setOnClickListener { addText("9") }
        findViewById<Button>(R.id.btn_8).setOnClickListener { addText("8") }
        findViewById<Button>(R.id.btn_7).setOnClickListener { addText("7") }
        findViewById<Button>(R.id.btn_6).setOnClickListener { addText("6") }
        findViewById<Button>(R.id.btn_5).setOnClickListener { addText("5") }
        findViewById<Button>(R.id.btn_4).setOnClickListener { addText("4") }
        findViewById<Button>(R.id.btn_3).setOnClickListener { addText("3") }
        findViewById<Button>(R.id.btn_2).setOnClickListener { addText("2") }
        findViewById<Button>(R.id.btn_1).setOnClickListener { addText("1") }
        findViewById<Button>(R.id.btn_0).setOnClickListener { addText("0") }

        findViewById<Button>(R.id.btn_left_bracket).setOnClickListener { addText("(") }
        findViewById<Button>(R.id.btn_right_bracket).setOnClickListener { addText(")") }

        findViewById<Button>(R.id.btn_divide).setOnClickListener { addText("/") }
        findViewById<Button>(R.id.btn_multiply).setOnClickListener { addText("*") }
        findViewById<Button>(R.id.btn_subtract).setOnClickListener { addText("-") }
        findViewById<Button>(R.id.btn_add).setOnClickListener { addText("+") }
        //findViewById<Button>(R.id.btn_percent).setOnClickListener { addText("%") }

        findViewById<Button>(R.id.btn_dot).setOnClickListener {
            val cur = expression.text.toString()
            if (cur.isNotEmpty() && cur[cur.length - 1].isDigit() && checkDot(cur))
                addText(".")
            else
                Toast.makeText(this, "Incorrect Expression", Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.btn_C).setOnClickListener {
            expression.text = ""
            result.text = ""
        }

        findViewById<Button>(R.id.btn_back).setOnClickListener {
            val text = expression.text.toString()
            if (text.isNotEmpty())
                expression.text = text.substring(0, text.length - 1)
            result.text = ""
        }

        findViewById<Button>(R.id.btn_equal).setOnClickListener {
            if (result.text.isNotEmpty()) {
                expression.text = result.text
                result.text = ""
            }
            else {
                try {
                    val res = Expression(expression.text.toString()).calculate()
                    if (res.toString() == "NaN")
                        Toast.makeText(this, "Incorrect Expression", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Incorrect Expression", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun addText(str: String) {
        expression.append(str)
        try {
            val res = Expression(expression.text.toString()).calculate()
            result.text = res.toString()
            if (result.text == "NaN")
                result.text = ""
        } catch (e: Exception){
            Toast.makeText(this, "Incorrect Expression", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("expr", expression.text.toString())
            putString("res", result.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        expression.text = savedInstanceState.getString("expr")
        result.text = savedInstanceState.getString("res")
    }

    private fun checkDot(cur: String): Boolean {
        var enable = true
        for (i in cur) {
            if (i == '.') {
                enable = false
            }
            if (i == '+' || i == '-' || i =='*' || i == '/' || i == '(' || i == ')') {
                enable = true
            }
        }
        return enable
    }
}