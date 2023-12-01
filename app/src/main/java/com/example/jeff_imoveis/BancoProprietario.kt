package com.example.jeff_imoveis

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoProprietario(context: Context) : SQLiteOpenHelper(context, "BancoProprietario", null, 1)
{
    override fun onCreate(db: SQLiteDatabase)
    {
        val nomeTabela = "Proprietario"
        val cpf_prop = "cpf_prop"
        val nome = "nome"
        val email = "email"
        val SQL_criacao =
            "CREATE TABLE ${nomeTabela} (" +
                    "${cpf_prop} TEXT PRIMARY KEY," +
                    "${nome} TEXT," +
                    "${email} TEXT)"
        db.execSQL(SQL_criacao)
    }

    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int) {
        val SQL_exclusao = "DROP TABLE IF EXISTS Proprietario"
        db.execSQL(SQL_exclusao)
        onCreate(db)
    }
}