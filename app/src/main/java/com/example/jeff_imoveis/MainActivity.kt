package com.example.jeff_imoveis

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            chamandoOutraTela()
        }
    }
}

@Composable
fun chamandoOutraTela(){
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(android.graphics.Color.parseColor("#FFE4C2"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Jeff Imóveis - PV2",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(150.dp))

        Button(
            onClick = {
                //Isso é uma ação.
                chamarTelaImovel(contexto)
            },
            //Estas são características gráficas.
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#923A0B")))) {
            Text(text = "Chamar a tela Imóvel",color = Color.White)
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                //Isso é uma ação.
                chamarTelaProprietario(contexto)
            },
            //Estas são características gráficas.
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#923A0B")))) {
            Text(text = "Chamar a tela Proprietário",color = Color.White)
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                //Isso é uma ação.
                chamarTelaInquilino(contexto)
            },
            //Estas são características gráficas.
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#923A0B")))) {
            Text(text = "Chamar a tela Inquilino",color = Color.White)
        }

        Spacer(modifier = Modifier.height(200.dp))

        Button(
            onClick = {
                //Isso é uma ação.
                chamarTelaSalvarDados(contexto)
            },
            //Estas são características gráficas.
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#923A0B")))) {
            Text(text = "Chamar a tela Salvar Dados",color = Color.White)
        }
    }
}

fun chamarTelaImovel(contexto : Context){
    contexto.startActivity(Intent(contexto, MainImovel::class.java))
}

fun chamarTelaProprietario(contexto : Context){
    contexto.startActivity(Intent(contexto, MainProprietario::class.java))
}

fun chamarTelaInquilino(contexto : Context){
    contexto.startActivity(Intent(contexto, MainInquilino::class.java))
}

fun chamarTelaSalvarDados(contexto : Context){
    contexto.startActivity(Intent(contexto, MainSalvarDados::class.java))
}