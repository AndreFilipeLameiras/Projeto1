package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDPintura (db: SQLiteDatabase): TabelaBD(db, NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${TabelaBDPintura.CAMPO_NOME} TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "Pintura"
        const val CAMPO_NOME = "nome"

    }
}