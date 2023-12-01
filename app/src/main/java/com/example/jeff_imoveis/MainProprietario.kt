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

class MainProprietario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            minhaTelaProprietario()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun minhaTelaProprietario() {

    val banco_proprietario = BancoProprietario(LocalContext.current)
    val operacoesBanco = DAO_Proprietario(banco_proprietario)

    var cpf_prop_estado by remember { mutableStateOf(TextFieldValue()) }
    var nome_estado by remember { mutableStateOf(TextFieldValue()) }
    var email_estado by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(android.graphics.Color.parseColor("#FFE4C2"))),
    ) {
        Text("Proprietários | JeffImóveis",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        TextField(
            value = cpf_prop_estado,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onValueChange = {
                cpf_prop_estado = it
            },
            label =  { Text("cpf proprietário.." ) },
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
            label =  { Text("nome proprietário.." ) },
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
            value = email_estado,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onValueChange = {
                email_estado = it
            },
            label =  { Text("email proprietario.." ) },
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
                val novoProprietario = Proprietario(
                    cpf_prop_estado.text,
                    nome_estado.text,
                    email_estado.text
                )

                operacoesBanco.inserirProprietario(novoProprietario)

                cpf_prop_estado = TextFieldValue()
                nome_estado = TextFieldValue()
                email_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Adicionar Proprietário",color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val valores = Proprietario(
                    cpf_prop_estado.text,
                    nome_estado.text,
                    email_estado.text
                )

                operacoesBanco.atualizarProprietario(valores)

                cpf_prop_estado = TextFieldValue()
                nome_estado = TextFieldValue()
                email_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Atualizar Proprietário",color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val valores = Proprietario(
                    cpf_prop_estado.text,
                    nome_estado.text,
                    email_estado.text
                )

                operacoesBanco.excluirProprietario(valores)

                cpf_prop_estado = TextFieldValue()
                nome_estado = TextFieldValue()
                email_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Remover Proprietário",color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            //Busca dos elementos do banco de dados.
            val listaDeProprietarios = operacoesBanco.mostrarProprietarios()
            for(proprietario in listaDeProprietarios){
                Log.i("Teste","-> "+proprietario)
            }
            //Inserção dos elementos do banco de dados no LazyColumn.
            items(listaDeProprietarios) { proprietario ->
                ItemDaListaProprietario(proprietario)
            }
        }
    }
}

@Composable
fun ItemDaListaProprietario(proprietario: Proprietario) {
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
                Text(text = "CPF do proprietario:               "+proprietario.cpf_prop)
                Text(text = "Nome do proprietario:            "+proprietario.nome)
                Text(text = "Email do proprietario:             "+proprietario.email)
            }
        }
    }
}