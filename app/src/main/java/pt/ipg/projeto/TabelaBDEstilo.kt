package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDEstilo (db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER KEY AUTOINCREMENT $CAMPO_NOME TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "estilo"
        const val CAMPO_NOME = "nome"
    }

}