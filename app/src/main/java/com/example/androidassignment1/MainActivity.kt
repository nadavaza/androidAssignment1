package com.example.androidassignment1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Array<Button>>
    private var currentPlayer = "X"
    private var board = Array(3) { arrayOfNulls<String>(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        buttons = Array(3) { row ->
            Array(3) { col ->
                val buttonId = resources.getIdentifier("button_$row$col", "id", packageName)
                findViewById<Button>(buttonId).apply {
                    setOnClickListener { onButtonClick(this, row, col) }
                }
            }
        }

        val turnStatus: TextView = findViewById(R.id.turn_status)
        turnStatus.text = "$currentPlayer's turn"


        val restartButton : Button = findViewById(R.id.button_restart)
        restartButton.setOnClickListener{ restartGame() }
    }

    private fun onButtonClick(button: Button, row: Int, col: Int) {
        if (button.text.isNotEmpty() || board[row][col] != null) return
        button.text = currentPlayer
        board[row][col] = currentPlayer
    }

    private fun restartGame() {
        currentPlayer = "X"
        board = Array(3) { arrayOfNulls<String>(3) }

        for (row in buttons) {
            for (button in row) {
                button.text = ""
            }
        }
        val turnStatus: TextView = findViewById(R.id.turn_status)
        turnStatus.text = "$currentPlayer's turn"
    }
}