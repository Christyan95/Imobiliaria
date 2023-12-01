package com.example.jeff_imoveis

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainSalvarDados : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            minhaTelaSalvarDados()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun minhaTelaSalvarDados() {

    val contexto = LocalContext.current

    val banco_imovel = BancoImovel(contexto)
    val operacoesBancoImo = DAO_Imovel(banco_imovel)

    val banco_proprietario = BancoProprietario(contexto)
    val operacoesBancoProp = DAO_Proprietario(banco_proprietario)

    val banco_inquilino = BancoInquilino(contexto)
    val operacoesBancoInq = DAO_Inquilino(banco_inquilino)

    val listaDeImoveis = operacoesBancoImo.mostrarImoveis()
    val listaDeProprietarios = operacoesBancoProp.mostrarProprietarios()
    val listaDeInquilinos = operacoesBancoInq.mostrarInquilinos()

    var textoExibidoImo by remember { mutableStateOf("") }
    var textoExibidoProp by remember { mutableStateOf("") }
    var textoExibidoInq by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(android.graphics.Color.parseColor("#FFE4C2"))),
    ) {
        Text("Salvar Dados | JeffImóveis",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        Button(
            onClick = {
                Toast.makeText(contexto,"Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Salvar Dados",color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                textoExibidoImo = listaDeImoveis.toString()
                textoExibidoProp = listaDeProprietarios.toString()
                textoExibidoInq = listaDeInquilinos.toString()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Mostrar Dados Salvos",color = Color.White)
        }

        Spacer(modifier = Modifier.height(75.dp))

        Text(
            "Imóveis salvos",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
            )
        Text(text = textoExibidoImo,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Proprietários salvos",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = textoExibidoProp,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Inquilinos salvos",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = textoExibidoInq,
            textAlign = TextAlign.Center)
    }
}
