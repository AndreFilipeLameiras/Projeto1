package pt.ipg.projeto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDCarrosOpenHelper( context: Context? ) : SQLiteOpenHelper(context, NOME, null, VERSAO) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)

        TabelaBDEstofos(db).cria()
        TabelaBDMotorizacoes(db).cria()
        TabelaBDCores(db).cria()
        TabelaBDTracao(db).cria()
        TabelaBDTransmissoes(db).cria()
        TabelaBDCombustivel(db).cria()
        TabelaBDPintura(db).cria()
        TabelaBDJantes(db).cria()
        TabelaBDMarcas(db).cria()
        TabelaBDModelo(db).cria()
        TabelaBDEstilo(db).cria()
        //TabelaBDCoresPintura(db).cria()
        //TabelaBDEstofosEstilo(db).cria()

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }



    companion object{
        const val NOME = "carros.db"
        private const val VERSAO = 1
    }
}