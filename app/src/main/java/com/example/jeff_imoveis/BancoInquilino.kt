package com.example.jeff_imoveis

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoInquilino(context: Context) : SQLiteOpenHelper(context, "BancoInquilino", null, 1)
{
    override fun onCreate(db: SQLiteDatabase)
    {
        val nomeTabela = "Inquilino"
        val cpf_inq = "cpf_inq"
        val nome = "nome"
        val valor_caucao_depositado = "valor_caucao_depositado"
        val SQL_criacao =
            "CREATE TABLE ${nomeTabela} (" +
                    "${cpf_inq} TEXT PRIMARY KEY," +
                    "${nome} TEXT," +
                    "${valor_caucao_depositado} FLOAT)"
        db.execSQL(SQL_criacao)
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int) {
        val SQL_exclusao = "DROP TABLE IF EXISTS Inquilino"
        db.execSQL(SQL_exclusao)
        onCreate(db)
    }
}