package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDMotorizacoes (db: SQLiteDatabase) : TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_POTENCIA TEXT NOT NULL, $CAMPO_TIPO_DE_CAIXA TEXT NOT NULL, $CAMPO_TIPO_DE_TRACAO TEXT NOT NULL, $CAMPO_TIPO_DE_COMBUSTIVEL TEXT NOT NULL, $CAMPO_CONSUMO INTEGER NOT NULL, $CAMPO_EMISSOES INTEGER NOT NULL)")


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