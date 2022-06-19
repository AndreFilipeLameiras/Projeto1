package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDModelo (db: SQLiteDatabase): TabelaBD(db, NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_MODELO TEXT NOT NULL, $CAMPO_PRECO , $CAMPO_MARCA_ID INTEGER NOT NULL, FOREIGN KEY($CAMPO_MARCA_ID) REFERENCES ${TabelaBDMarcas.NOME})")
    }


    companion object{
        const val NOME = "modelos"
        const val CAMPO_MODELO = "nome"
        const val CAMPO_MARCA_ID = "marcaID"
        const val CAMPO_PRECO = "preco"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_MODELO, CAMPO_PRECO, CAMPO_MARCA_ID)
    }
}