package com.example.jeff_imoveis

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoImovel(context: Context) : SQLiteOpenHelper(context, "BancoImovel", null, 1)
{
    override fun onCreate(db: SQLiteDatabase)
    {
        val nomeTabela = "Imovel"
        val matricula = "matricula"
        val endereco = "endereco"
        val valor_do_aluguel = "valor_do_aluguel"
        val SQL_criacao =
            "CREATE TABLE ${nomeTabela} (" +
                    "${matricula} TEXT PRIMARY KEY," +
                    "${endereco} TEXT," +
                    "${valor_do_aluguel} FLOAT)"
        db.execSQL(SQL_criacao)
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int) {
        val SQL_exclusao = "DROP TABLE IF EXISTS Imovel"
        db.execSQL(SQL_exclusao)
        onCreate(db)
    }
}