package pt.ipg.projeto

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class ContentProviderModelos : ContentProvider() {
    var db : BDCarrosOpenHelper? = null



    override fun onCreate(): Boolean {
        db = BDCarrosOpenHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    companion object{
        const val AUTHORITY = "pt.ipg.projeto"

        const val URI_MARCAS = 100
        const val URI_MARCAS_ESPECIFICA = 101
        const val URI_MODELOS = 200
        const val URI_MODELOS_ESPECIFICOS = 201


        fun getUriMatcher() : UriMatcher{
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, TabelaBDMarcas.NOME, URI_MARCAS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDMarcas.NOME}/#", URI_MARCAS_ESPECIFICA)
            uriMatcher.addURI(AUTHORITY, TabelaBDModelo.NOME, URI_MODELOS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDModelo.NOME}/#", URI_MODELOS_ESPECIFICOS)
            
            return uriMatcher
        }


    }

}