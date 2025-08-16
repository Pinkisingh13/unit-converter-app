package com.example.unitconverterapp
import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Unit Converter App", color = Color.White)
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xff6200EE)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize(),

                    )
                     { innerPadding ->

                        UnitConverter(innerPadding)
                }


            }
        }
    }
}



@Composable
fun UnitConverter(innerPadding: PaddingValues){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember {mutableStateOf(false)}
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val outputConversionFactor = remember { mutableDoubleStateOf(1.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        color = Color.Black
    )

    fun convertUnit(){
        // ?: -> elvis operator
    val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
    val result = (inputValueDouble * conversionFactor.doubleValue*100/ outputConversionFactor.doubleValue ).roundToInt() / 100.0
    outputValue = result.toString()

    }



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize())
    {
     OutlinedTextField(
         value = inputValue,
         onValueChange = {inputValue = it},
         label = { Text("Enter value", style = customTextStyle.copy(color = Color.White)) },
         modifier = Modifier.padding(16.dp)
     )

        Spacer(modifier = Modifier.height(16.dp))
        Row {

//            val context = LocalContext.current
//            Button(onClick = {}) {
//
//                Text("my Button")
//            }
//
//            Button(onClick = {}) {
//                Text("my second button")
//            }


           //   Input Box
            Box{

                //Input Button
                Button(onClick = {inputExpanded = true}){
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Drop Down")
                }

                DropdownMenu(expanded = inputExpanded , {inputExpanded = false} ) {
                    DropdownMenuItem(
                        text = {
                            Text("Centimeter")
                        },
                        onClick = {

                            inputExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Meters")
                        },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnit()}
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Feet")
                        },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.03048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Millimeter")
                        },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Millimeter"
                            conversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }

             // output box
           Spacer(modifier = Modifier.width(16.dp))
        Box{
            Button(onClick = {outputExpanded = true}){
                Text(outputUnit)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Drop Down")
            }
            DropdownMenu(expanded = outputExpanded , {outputExpanded = false} ) {
                DropdownMenuItem(
                    text = {
                        Text("Centimeter")
                    },
                    onClick = {

                        outputExpanded = false
                        outputUnit = "Centimeters"
                        outputConversionFactor.doubleValue = 0.01
                        convertUnit()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Meters")
                    },
                    onClick = {
                        outputExpanded = false
                        outputUnit = "Meters"
                        outputConversionFactor.doubleValue = 1.00
                        convertUnit()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Feet")
                    },
                    onClick = {
                        outputExpanded = false
                        outputUnit = "Feet"
                        outputConversionFactor.doubleValue = 0.3048
                        convertUnit()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Millimeter")
                    },
                    onClick = {
                        outputExpanded = false
                        outputUnit = "Millimeters"
                        outputConversionFactor.doubleValue = 0.001
                        convertUnit()
                    }
                )
            }

            }
        }



        Spacer(modifier = Modifier.height(22.dp))
        // Result Text
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}





@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterAppTheme {
        UnitConverter(innerPadding = PaddingValues())
    }
}