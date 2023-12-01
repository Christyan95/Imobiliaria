package com.example.jeff_imoveis

import android.content.ContentValues
import android.util.Log

class DAO_Imovel(banco : BancoImovel)
{
    var banco : BancoImovel

    init {
        this.banco = banco
    }

    fun inserirImovel(imovel : Imovel){
        val db_insercao = this.banco.writableDatabase
        var valores = ContentValues().apply{
            put("matricula", imovel.matricula)
            put("endereco", imovel.endereco)
            put("valor_do_aluguel", imovel.valor_do_aluguel)
        }
        val confirmaInsercao = db_insercao?.insert("imovel",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
    }

    fun mostrarImoveis(): List<Imovel>{
        val listaImoveis = ArrayList<Imovel>()
        val db_leitura = this.banco.readableDatabase
        var cursor = db_leitura.rawQuery("select * from imovel",null)
        with(cursor) {
            while (moveToNext()) {
                val matricula = getString(getColumnIndexOrThrow("matricula"))
                val endereco = getString(getColumnIndexOrThrow("endereco"))
                val valor_do_aluguel = getFloat(getColumnIndexOrThrow("valor_do_aluguel"))
                Log.i("Teste","Matrícula: "+matricula+" - Endereço: "+endereco+" - Valor do aluguel: "+valor_do_aluguel)
                listaImoveis.add(Imovel(matricula,endereco, valor_do_aluguel))
            }
        }
        cursor.close()
        return(listaImoveis)
    }

    fun atualizarImovel(imovel : Imovel){
        val db_atualizacao = this.banco.writableDatabase
        var valores = ContentValues().apply {
            put("endereco", imovel.endereco)
            put("valor_do_aluguel", imovel.valor_do_aluguel)
        }
        val condicao = "matricula = "+imovel.matricula
        val confirmaAtualizacao = db_atualizacao.update("imovel", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
    }

    fun excluirImovel(imovel : Imovel){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "matricula = "+imovel.matricula
        val confirmaExclusao = db_exclusao.delete("imovel", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }
}