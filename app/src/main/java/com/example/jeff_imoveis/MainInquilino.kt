package com.example.jeff_imoveis

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainInquilino : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            minhaTelaInquilino()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun minhaTelaInquilino() {

    val banco_inquilino = BancoInquilino(LocalContext.current)
    val operacoesBanco2 = DAO_Inquilino(banco_inquilino)

    var cpf_inq_estado by remember { mutableStateOf(TextFieldValue()) }
    var nome_estado by remember { mutableStateOf(TextFieldValue()) }
    var valor_caucao_depositado_estado by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(android.graphics.Color.parseColor("#FFE4C2"))),
    ) {
        Text("Inquilinos | JeffImóveis",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        TextField(
            value = cpf_inq_estado,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onValueChange = {
                cpf_inq_estado = it
            },
            label =  { Text("cpf inquilino.." ) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = (Color(android.graphics.Color.parseColor("#211B17"))).copy(.2f),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = (Color(android.graphics.Color.parseColor("#211B17"))),
                focusedLabelColor = (Color(android.graphics.Color.parseColor("#C2812E"))),
                cursorColor = (Color(android.graphics.Color.parseColor("#C2812E")))
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nome_estado,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onValueChange = {
                nome_estado = it
            },
            label =  { Text("nome inquilino.." ) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = (Color(android.graphics.Color.parseColor("#211B17"))).copy(.2f),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = (Color(android.graphics.Color.parseColor("#211B17"))),
                focusedLabelColor = (Color(android.graphics.Color.parseColor("#C2812E"))),
                cursorColor = (Color(android.graphics.Color.parseColor("#C2812E")))
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = valor_caucao_depositado_estado,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onValueChange = {
                valor_caucao_depositado_estado = it
            },
            label =  { Text("valor caução depositado inquilino.." ) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = (Color(android.graphics.Color.parseColor("#211B17"))).copy(.2f),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = (Color(android.graphics.Color.parseColor("#211B17"))),
                focusedLabelColor = (Color(android.graphics.Color.parseColor("#C2812E"))),
                cursorColor = (Color(android.graphics.Color.parseColor("#C2812E")))
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val novoInquilino = Inquilino(
                    cpf_inq_estado.text,
                    nome_estado.text,
                    valor_caucao_depositado_estado.text.toFloat()
                )

                operacoesBanco2.inserirInquilino(novoInquilino)

                cpf_inq_estado = TextFieldValue()
                nome_estado = TextFieldValue()
                valor_caucao_depositado_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Adicionar Inquilino",color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val valores = Inquilino(
                    cpf_inq_estado.text,
                    nome_estado.text,
                    valor_caucao_depositado_estado.text.toFloat()
                )

                operacoesBanco2.atualizarInquilino(valores)

                cpf_inq_estado = TextFieldValue()
                nome_estado = TextFieldValue()
                valor_caucao_depositado_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Atualizar Inquilino",color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val valores = Inquilino(
                    cpf_inq_estado.text,
                    nome_estado.text,
                    valor_caucao_depositado_estado.text.toFloat()
                )

                operacoesBanco2.excluirInquilino(valores)

                cpf_inq_estado = TextFieldValue()
                nome_estado = TextFieldValue()
                valor_caucao_depositado_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Remover Inquilino",color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            //Busca dos elementos do banco de dados.
            val listaDeInquilinos = operacoesBanco2.mostrarInquilinos()
            for(inquilino in listaDeInquilinos){
                Log.i("Teste","-> "+inquilino)
            }
            //Inserção dos elementos do banco de dados no LazyColumn.
            items(listaDeInquilinos) { inquilino ->
                ItemDaListaInquilino(inquilino)
            }
        }
    }
}

@Composable
fun ItemDaListaInquilino(inquilino: Inquilino) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        colors = CardDefaults.cardColors(containerColor = (Color(android.graphics.Color.parseColor("#211B17"))).copy(.3f))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                Text(text = "CPF do inquilino:                                "+inquilino.cpf_inq)
                Text(text = "Nome do inquilino:                             "+inquilino.nome)
                Text(text = "Valor caulção dep do inquilino:        "+inquilino.valor_caucao_depositado.toString())
            }
        }
    }
}