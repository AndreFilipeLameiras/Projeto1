package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCarros(val db: SQLiteDatabase) {
    fun cria(){
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_MODELO_ID TEXT NOT NULL, $CAMPO_MOTORIZACAO_ID TEXT NOT NULL, $CAMPO_PINTURA_ID TEXT NOT NULL, $CAMPO_ESTOFOS_ID TEXT NOT NULL, $CAMPO_JANTES_ID TEXT NOT NULL, FOREIGN KEY ($CAMPO_MODELO_ID) REFERENCES ${TabelaBDModelo.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT, FOREIGN KEY ($CAMPO_MOTORIZACAO_ID) REFERENCES ${TabelaBDMotorizacoes.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_PINTURA_ID) REFERENCES ${TabelaBDPintura.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_ESTOFOS_ID) REFERENCES ${TabelaBDEstofos.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_JANTES_ID) REFERENCES ${TabelaBDJantes.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT ) ")
    }


    companion object{
        const val NOME = "carros"
        const val CAMPO_MODELO_ID = "modeloId"
        const val CAMPO_MOTORIZACAO_ID = "motorizacaoId"
        const val CAMPO_PINTURA_ID = "pinturaId"
        const val CAMPO_ESTOFOS_ID = "estofosId"
        const val CAMPO_JANTES_ID = "jantesId"
    }
}