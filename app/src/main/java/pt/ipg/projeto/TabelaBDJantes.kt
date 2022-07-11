package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDJantes (db: SQLiteDatabase): TabelaBD(db, NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL, $LARGURA INTEGER NOT NULL, $ALTURA INTEGER NOT NULL, $RAIO INTEGER NOT NULL, $PRECO INTEGER NOT NULL )")
    }

    companion object{
        const val NOME = "jantes"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_NOME = "nome"
        const val LARGURA = "largura"
        const val ALTURA = "altura"
        const val RAIO = "raio"
        const val PRECO = "preco"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_NOME, LARGURA, ALTURA, RAIO, PRECO)

    }

}