package com.example.jeff_imoveis

import android.content.ContentValues
import android.util.Log

class DAO_Inquilino(banco : BancoInquilino)
{
    var banco : BancoInquilino

    init {
        this.banco = banco
    }

    fun inserirInquilino(inquilino : Inquilino){
        val db_insercao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("cpf_inq", inquilino.cpf_inq)
            put("nome", inquilino.nome)
            put("valor_caucao_depositado", inquilino.valor_caucao_depositado)
        }
        val confirmaInsercao = db_insercao?.insert("Inquilino",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
    }

    fun mostrarInquilinos(): List<Inquilino>{
        val listaInquilinos = ArrayList<Inquilino>()
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from Inquilino",null)
        with(cursor) {
            while (moveToNext()) {
                val cpf_inq = getString(getColumnIndexOrThrow("cpf_inq"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val valor_caucao_depositado = getFloat(getColumnIndexOrThrow("valor_caucao_depositado"))
                Log.i("Teste","CPF inquilino: "+cpf_inq+" - Nome: "+nome+" - Valor: "+valor_caucao_depositado)
                listaInquilinos.add(Inquilino(cpf_inq, nome, valor_caucao_depositado))
            }
        }
        cursor.close()
        return(listaInquilinos)
    }

    fun atualizarInquilino(inquilino : Inquilino){
        val db_atualizacao = this.banco.writableDatabase
        var valores = ContentValues().apply {
            put("nome", inquilino.nome)
            put("valor_caucao_depositado", inquilino.valor_caucao_depositado)
        }
        val condicao = "cpf_inq = "+inquilino.cpf_inq
        val confirmaAtualizacao = db_atualizacao.update("Inquilino", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
    }

    fun excluirInquilino(inquilino : Inquilino){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "cpf_inq = "+inquilino.cpf_inq
        val confirmaExclusao = db_exclusao.delete("Inquilino", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }
}