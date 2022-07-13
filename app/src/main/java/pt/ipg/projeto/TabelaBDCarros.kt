package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDCarros(db: SQLiteDatabase) : TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_MODELO_ID TEXT NOT NULL, $CAMPO_MOTORIZACAO_ID TEXT NOT NULL, $CAMPO_COR_ID TEXT NOT NULL, $CAMPO_ESTOFOS_ID TEXT NOT NULL, $CAMPO_JANTES_ID TEXT NOT NULL, FOREIGN KEY ($CAMPO_MODELO_ID) REFERENCES ${TabelaBDModelo.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT, FOREIGN KEY ($CAMPO_MOTORIZACAO_ID) REFERENCES ${TabelaBDMotorizacoes.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_COR_ID) REFERENCES ${TabelaBDCores.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_ESTOFOS_ID) REFERENCES ${TabelaBDEstofos.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_JANTES_ID) REFERENCES ${TabelaBDJantes.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT ) ")
    }


    companion object{
        const val NOME = "carros"
        const val CAMPO_MODELO_ID = "modeloId"
        const val CAMPO_MOTORIZACAO_ID = "motorizacaoId"
        const val CAMPO_COR_ID = "corId"
        const val CAMPO_ESTOFOS_ID = "estofosId"
        const val CAMPO_JANTES_ID = "jantesId"
    }
}