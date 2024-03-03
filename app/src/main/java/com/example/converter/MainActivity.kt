package com.example.converter



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.converter.ui.theme.ConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Converter()
                }
            }
        }
    }
}

@Composable
fun Converter(){

    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Kilowatt") }
    var outputUnit by remember { mutableStateOf("Kilowatt") }
    var inputConversion by remember { mutableStateOf(1.00) }
    var outputConversion by remember { mutableStateOf(1.00) }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }

    fun Conversion(){
        val inputDouble = input.toDoubleOrNull() ?: 0.0
        val result = (inputDouble * inputConversion * 100.0 / outputConversion).roundToInt() / 100.0
        output = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Converter")
        Spacer(modifier = Modifier.height(16.dp) )
        OutlinedTextField(value = input, onValueChange ={
            input = it
            Conversion()
        } )
        Spacer(modifier = Modifier.height(16.dp) )
        Row {
            Box {
                Button(onClick = { iExpand = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select here")
                    DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand= false }) {
                        DropdownMenuItem(text =  { Text("KiloWatt")}, onClick = {
                            inputUnit = "Kilowatt"
                            iExpand = false
                            inputConversion = 1.00
                            Conversion()
                        })
                        DropdownMenuItem(text =  { Text("Watt") }, onClick = {
                            inputUnit = "Watt"
                            iExpand = false
                            inputConversion = 0.001
                            Conversion()
                        })
                        DropdownMenuItem(text =  { Text("HorsePower" ) }, onClick = {
                            inputUnit= "HorsePower"
                            iExpand = false
                            inputConversion = 0.7457
                            Conversion()
                        })
                    }

                }
            }
            Spacer(modifier = Modifier.width(8.dp) )
            Box {
                Button(onClick = { oExpand = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select here")
                    DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {
                        DropdownMenuItem(text =  { Text("KiloWatt")}, onClick = {
                            outputUnit = "KiloWatt"
                            oExpand = false
                            outputConversion = 1.00
                            Conversion()
                        })
                        DropdownMenuItem(text =  { Text("Watt") }, onClick = {
                            outputUnit = "Watt"
                            oExpand = false
                            outputConversion = 0.001
                            Conversion()
                        })
                        DropdownMenuItem(text =  { Text("HorsePower") }, onClick = {
                            outputUnit = "HorsePower"
                            oExpand = false
                            outputConversion = 0.7457
                            Conversion()
                        })
                    }
                }
            }
        }
        Text(text = "Result: $output  $outputUnit", Modifier.padding(all = 16.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun ConverterPreview() {
    Converter()

 }
