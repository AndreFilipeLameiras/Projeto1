package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDMotorizacoes (val db: SQLiteDatabase) {
    fun cria(){
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_POTENCIA TEXT NOT NULL, " +
                "$CAMPO_TIPO_DE_CAIXA TEXT NOT NULL, " +
                "$CAMPO_TIPO_DE_TRACAO TEXT NOT NULL, " +
                "$CAMPO_TIPO_DE_COMBUSTIVEL TEXT NOT NULL, " +
                "$CAMPO_CONSUMO TEXT NOT NULL " +
                "$CAMPO_EMISSOES TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "motorizacoes"
        const val CAMPO_POTENCIA = "potencia"
        const val CAMPO_TIPO_DE_CAIXA = "tipo de caixa"
        const val CAMPO_TIPO_DE_TRACAO = "tipo de tracao"
        const val CAMPO_TIPO_DE_COMBUSTIVEL = "tipo de combustivel"
        const val CAMPO_CONSUMO = "consumo"
        const val CAMPO_EMISSOES = "emissoes"
    }
}