package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDMotorizacoes (db: SQLiteDatabase) : TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_POTENCIA TEXT NOT NULL, $CAMPO_TRANSMISSOES_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_TRANSMISSOES_ID) REFERENCES ${TabelaBDTransmissoes.NOME}, $CAMPO_TRACAO_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_TRACAO_ID) REFERENCES ${TabelaBDTracao.NOME}, $CAMPO_COMBUSTIVEL_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_COMBUSTIVEL_ID) REFERENCES ${TabelaBDCombustivel.NOME}, $CAMPO_CONSUMO DOUBLE NOT NULL, $CAMPO_EMISSOES DOUBLE NOT NULL)")


    }

    companion object{
        const val NOME = "motorizacoes"
        const val CAMPO_POTENCIA = "potencia"
        const val CAMPO_TRANSMISSOES_ID = "transmissoesId"
        const val CAMPO_TRACAO_ID = "tracaoId"
        const val CAMPO_COMBUSTIVEL_ID = "combustivelID"
        const val CAMPO_CONSUMO = "consumo"
        const val CAMPO_EMISSOES = "emissoes"
    }
}