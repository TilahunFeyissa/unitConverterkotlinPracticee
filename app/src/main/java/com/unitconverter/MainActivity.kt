package com.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.0) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.0) }

val customTextStyle=TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 16.sp,
    color = Color.Blue

)

fun convertUnits(){
    val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
    val result=(inputValueDouble*conversionFactor.doubleValue*100.0/oConversionFactor.doubleValue).roundToInt()/100.0
     outputValue=result.toString()

}




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Unit Converter",style=customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue=it
        }, label = { Text(text = "Enter Value")} )
        Spacer(modifier = Modifier.height(16.dp))
//        Row {
//            val context= LocalContext.current
//            Button(onClick = { Toast
//                .makeText(context,"Thanks for clicking",
//                    Toast.LENGTH_LONG).show() }) {
//            Text(text = "click me")
//        }
        Row{
            Box{
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Drop down action")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded=false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        iExpanded=false
                        inputUnit="Centimeters"
                        conversionFactor.doubleValue=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.doubleValue = 1.0
                        convertUnits()
                    })

                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.doubleValue = 0.3048
                        convertUnits()
                    })

                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.doubleValue = 0.01
                        convertUnits()
                    })

                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box{
                Button(onClick = { oExpanded=true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Drop down action")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        oExpanded=false
                        outputUnit="Centimeters"
                        oConversionFactor.doubleValue=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        oConversionFactor.doubleValue = 1.0
                        convertUnits()
                    })

                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor.doubleValue = 0.3048
                        convertUnits()
                    })

                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        oConversionFactor.doubleValue = 0.01
                        convertUnits()
                    })

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result:  $outputValue $outputUnit",
            style=MaterialTheme.typography.headlineMedium)

    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}

