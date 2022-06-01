package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDPinturas (val db: SQLiteDatabase) {
    fun cria(){
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $TIPO_PINTURA TEXT NOT NULL, $NOME_PINTURA TEXT NOT NULL) ")
    }

    companion object{
        const val NOME = "pinturas"
        const val TIPO_PINTURA = "tipo de pintura"
        const val NOME_PINTURA = "nome da pintura"
    }

}