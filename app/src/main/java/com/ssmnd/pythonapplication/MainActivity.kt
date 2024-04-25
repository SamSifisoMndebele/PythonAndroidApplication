package com.ssmnd.pythonapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.ssmnd.pythonapplication.ui.theme.PythonApplicationTheme
import com.ssmnd.terms.PolTerm
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PythonApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val term0 = PolTerm(5, 'x', 2)
                    val term1 = PolTerm(6, 'x', 2)

                    Column {
                        TableFromPython()
                        Text(text = (term0+term1).toString())
                    }
                }
            }
        }
    }

    @Composable
    fun TableFromPython() {
        if (!Python.isStarted()) Python.start(AndroidPlatform(this))
        val python = Python.getInstance()

        val pyPlot = python.getModule("python_plot")
        val data = Array(25) { Array(3) { Random.nextDouble().roundToInt() } }
        val bytes = pyPlot.callAttr("plot_table", data).toJava(ByteArray::class.java)

        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Plot")
    }
}

