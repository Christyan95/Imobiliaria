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

class MainImovel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            minhaTelaImovel()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun minhaTelaImovel() {

    val banco_imovel = BancoImovel(LocalContext.current)
    val operacoesBanco = DAO_Imovel(banco_imovel)

    var matricula_estado by remember { mutableStateOf(TextFieldValue()) }
    var endereco_estado by remember { mutableStateOf(TextFieldValue()) }
    var valor_do_aluguel_estado by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(android.graphics.Color.parseColor("#FFE4C2"))),
    ) {
        Text("Imóveis | JeffImóveis",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        TextField(
            value = matricula_estado,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onValueChange = {
                matricula_estado = it
            },
            label =  { Text("matricula imóvel.." ) },
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
            value = endereco_estado,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onValueChange = {
                endereco_estado = it
            },
            label =  { Text("endereço imóvel.." ) },
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
            value = valor_do_aluguel_estado,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onValueChange = {
                valor_do_aluguel_estado = it
            },
            label =  { Text("valor do aluguel imóvel.." ) },
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
                val novoImovel = Imovel(
                    matricula_estado.text,
                    endereco_estado.text,
                    valor_do_aluguel_estado.text.toFloat()
                )

                operacoesBanco.inserirImovel(novoImovel)

                matricula_estado = TextFieldValue()
                endereco_estado = TextFieldValue()
                valor_do_aluguel_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Adicionar Imóvel",color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val valores = Imovel(
                    matricula_estado.text,
                    endereco_estado.text,
                    valor_do_aluguel_estado.text.toFloat()
                )

                operacoesBanco.atualizarImovel(valores)

                matricula_estado = TextFieldValue()
                endereco_estado = TextFieldValue()
                valor_do_aluguel_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Atualizar Imóvel",color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val valores = Imovel(
                    matricula_estado.text,
                    endereco_estado.text,
                    valor_do_aluguel_estado.text.toFloat()
                )

                operacoesBanco.excluirImovel(valores)

                matricula_estado = TextFieldValue()
                endereco_estado = TextFieldValue()
                valor_do_aluguel_estado = TextFieldValue()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors((Color(android.graphics.Color.parseColor("#923A0B"))))
        ) {
            Text(text = "Remover Imóvel",color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            //Busca dos elementos do banco de dados.
            val listaDeImoveis = operacoesBanco.mostrarImoveis()
            for(imovel in listaDeImoveis){
                Log.i("Teste","-> "+imovel)
            }
            //Inserção dos elementos do banco de dados no LazyColumn.
            items(listaDeImoveis) { imovel ->
                ItemDaListaImovel(imovel)
            }
        }
    }
}

@Composable
fun ItemDaListaImovel(imovel: Imovel) {
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
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                ) {
                Text(text = "Matrícula do imóvel:               "+imovel.matricula)
                Text(text = "Endereço do imóvel:                "+imovel.endereco)
                Text(text = "Valor do aluguel do imóvel:    "+imovel.valor_do_aluguel.toString())
            }
        }
    }
}