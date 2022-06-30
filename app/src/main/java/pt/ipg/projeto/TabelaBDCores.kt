package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCores (db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,  $CAMPO_COR TEXT NOT NULL, $PRECO DOUBLE NOT NULL) ")
    }

    companion object{
        const val NOME = "cores"
        //const val CAMPO_PINTURA_ID = "pinturaId"
        const val CAMPO_COR = "nome"
        const val PRECO = "preco"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_COR, PRECO)
    }

}