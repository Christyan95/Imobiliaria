package com.example.jeff_imoveis

import android.content.ContentValues
import android.util.Log

class DAO_Proprietario(banco : BancoProprietario)
{
    var banco : BancoProprietario

    init {
        this.banco = banco
    }

    fun inserirProprietario(proprietario : Proprietario){
        val db_insercao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("cpf_prop", proprietario.cpf_prop)
            put("nome", proprietario.nome)
            put("email", proprietario.email)
        }
        val confirmaInsercao = db_insercao?.insert("Proprietario",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
    }

    fun mostrarProprietarios(): List<Proprietario>{
        val listaProprietarios = ArrayList<Proprietario>()
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Proprietario",null)
        with(cursor) {
            while (moveToNext()) {
                val cpf_prop = getString(getColumnIndexOrThrow("cpf_prop"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                Log.i("Teste","CPF proprietário: "+cpf_prop+" - Nome: "+nome+" - Email: "+email)
                listaProprietarios.add(Proprietario(cpf_prop, nome, email))
            }
        }
        cursor.close()
        return(listaProprietarios)
    }

    fun atualizarProprietario(proprietario : Proprietario){
        val db_atualizacao = this.banco.writableDatabase
        var valores = ContentValues().apply {
            put("nome", proprietario.nome)
            put("email", proprietario.email)
        }
        val condicao = "cpf_prop = "+proprietario.cpf_prop
        val confirmaAtualizacao = db_atualizacao.update("Proprietario", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
    }

    fun excluirProprietario(proprietario : Proprietario){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "cpf_prop = "+proprietario.cpf_prop
        val confirmaExclusao = db_exclusao.delete("Proprietario", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }
}