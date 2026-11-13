package com.example.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc.ui.theme.IMCTheme
import com.example.imc.calcularIMC
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaIMC(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaIMC( modifier: Modifier = Modifier) {

    var altura by remember {
        mutableStateOf("")
    }

    var peso by remember {
        mutableStateOf("")
    }

    var imc: Double by remember {
        mutableStateOf(0.0)
    }


    val limiteMaximo = 30





    fun determinarcategoria (imc: Double): String{
        return if (imc < 18.5){
            "Abaixo do peso"
        } else if(imc >= 18.5 && imc < 25.0){
            "Peso ideal"
        } else if(imc >= 25.0 && imc < 30.0){
            "Levemente acima do peso"
        } else if(imc == 30.0 && imc < 35.0){
            "Obesidade grau I"
        } else if(imc >= 35.0 && imc < 40.0){
            "besidade grau II"
        } else {
            "besidade grau III"
        }
    }


    var result = determinarcategoria(imc)


    var categoriaImc = result

    Column(modifier = modifier.fillMaxSize().background(Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize() ){
            Column(modifier = Modifier.fillMaxWidth()) {


                Column(
                    modifier = Modifier.fillMaxWidth()
                        .height(160.dp)
                        .background(color = colorResource(id = R.color.cor_app)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.bmi),
                        contentDescription = "Logo app",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(vertical = 16.dp)
                    )

                    Text(
                        text = "Calcular IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(horizontal = 32.dp)
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .offset(y = (-20).dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF9F6F6)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {


                        Text(
                            text = "Seus dados",
                            fontSize = 24.sp,
                            color = Color(0xff4CA6D5),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                        )



                        Column(modifier = Modifier
                            .padding(top = 20.dp, start = 15.dp, end = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {

                            OutlinedTextField(
                                value = altura,
                                onValueChange = { novoTexto ->
                                    val formatado = novoTexto.replace(',', '.')

                                    if (formatado.length <= limiteMaximo) {
                                        altura = formatado
                                    }
                                },
                                placeholder = { Text("Altura")},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

                        }


                        Column(modifier = Modifier
                            .padding(top = 14.dp, start = 15.dp, end = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {

                            OutlinedTextField(
                                value = peso,
                                onValueChange = { novoTexto ->
                                    val formatado = novoTexto.replace(',', '.')

                                    if (formatado.length <= limiteMaximo) {
                                        peso = formatado
                                    }
                                },
                                placeholder = { Text("Peso")},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Button(
                                onClick =
                                    {
                                    imc = calcularIMC(
                                        altura = altura.toDouble(),
                                        peso = peso.toDouble()
                                    )

                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(60.dp)

                            ) {
                                Text("Calcular")
                            }

                        }



                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .offset(y = 20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF9F6F6)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {


                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .height(160.dp)
                                .background(color = colorResource(id = R.color.cor_app)),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = String.format(Locale.US,"%.1f", imc) ,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(top = 20.dp)
                            )

                            Text(
                                text = "$categoriaImc",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 20.dp)
                            )


                        }



                    }
                }



            }
        }
    }



}



