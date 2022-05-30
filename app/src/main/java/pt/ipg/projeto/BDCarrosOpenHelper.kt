package pt.ipg.projeto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDCarrosOpenHelper(
    context: Context?
) : SQLiteOpenHelper(context, NOME, null, VERSAO) {
    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }



    companion object{
        private const val NOME = "carros.db"
        private const val VERSAO = 1
    }
}