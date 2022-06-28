package pt.ipg.projeto

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ContentProviderModelos : ContentProvider() {
    var dbOpenHelper : BDCarrosOpenHelper? = null



    override fun onCreate(): Boolean {
        dbOpenHelper = BDCarrosOpenHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbOpenHelper!!.readableDatabase

        requireNotNull(projection)
        val colunas = projection as Array<String>

        val argsSeleccao = selectionArgs as Array<String>

        val id = uri.lastPathSegment

        val cursor = when (getUriMatcher().match(uri)){
            URI_MODELOS -> TabelaBDModelo(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_MARCAS -> TabelaBDMarcas(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_MODELO_ESPECIFICO -> TabelaBDModelo(db).query(colunas, "${BaseColumns._ID}=?",arrayOf("${id}"),null, null, null)
            URI_MARCA_ESPECIFICA -> TabelaBDMarcas(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"),null, null, null)
            else -> null
        }

        db.close()

        return cursor

    }

    override fun getType(uri: Uri): String? =
        when(getUriMatcher().match(uri)){
            URI_MODELOS -> "$MULTIPLOS_REGISTOS/${TabelaBDModelo.NOME}"
            URI_MARCAS -> "$MULTIPLOS_REGISTOS/${TabelaBDMarcas.NOME}"
            URI_MODELO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDModelo.NOME}"
            URI_MARCA_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDMarcas.NOME}"
            else -> null
        }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper!!.writableDatabase

        requireNotNull(values)

        val id = when (getUriMatcher().match(uri)){
            URI_MODELOS -> TabelaBDModelo(db).insert(values)
            URI_MARCAS -> TabelaBDMarcas(db).insert(values)
            else -> -1
        }

        db.close()

        if(id == -1L) return null

        return Uri.withAppendedPath(uri, "$id")

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbOpenHelper!!.writableDatabase

        val id = uri.lastPathSegment

        val registosApagados = when (getUriMatcher().match(uri)){
            URI_MODELO_ESPECIFICO -> TabelaBDModelo(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_MARCA_ESPECIFICA -> TabelaBDMarcas(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            else -> 0
        }

        db.close()

        return registosApagados
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        requireNotNull(values)

        val db = dbOpenHelper!!.writableDatabase

        val id = uri.lastPathSegment

        val registosAlterados = when (getUriMatcher().match(uri)){
            URI_MODELO_ESPECIFICO -> TabelaBDModelo(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_MARCA_ESPECIFICA -> TabelaBDMarcas(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            else -> 0
        }

        db.close()

        return registosAlterados

    }

    companion object{
        const val AUTHORITY = "pt.ipg.projeto"

        const val URI_MARCAS = 100
        const val URI_MARCA_ESPECIFICA = 101
        const val URI_MODELOS = 200
        const val URI_MODELO_ESPECIFICO = 201

        const val UNICO_REGISTO = "vnd.android.cursor.item"
        const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

        val ENDERECO_BASE = Uri.parse("content://$AUTHORITY")
        val ENDERECO_MODELOS = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDModelo.NOME)
        val ENDERECO_Marcas = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDMarcas.NOME)



        fun getUriMatcher() : UriMatcher{
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, TabelaBDMarcas.NOME, URI_MARCAS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDMarcas.NOME}/#", URI_MARCA_ESPECIFICA)
            uriMatcher.addURI(AUTHORITY, TabelaBDModelo.NOME, URI_MODELOS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDModelo.NOME}/#", URI_MODELO_ESPECIFICO)

            return uriMatcher
        }


    }

}