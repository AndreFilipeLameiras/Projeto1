package pt.ipg.projeto

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ContentProviderCarros : ContentProvider() {
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
            URI_JANTES -> TabelaBDJantes(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_MOTORIZACOES -> TabelaBDMotorizacoes(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_COMBUSTIVEIS -> TabelaBDCombustivel(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_CORES -> TabelaBDCores(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_ESTILO -> TabelaBDEstilo(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_ESTOFOS -> TabelaBDEstofos(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_TRACOES -> TabelaBDTracao(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)
            URI_TRASNMISSOES -> TabelaBDTransmissoes(db).query(colunas, selection, argsSeleccao, null, null, sortOrder)

            URI_MODELO_ESPECIFICO -> TabelaBDModelo(db).query(colunas, "${BaseColumns._ID}=?",arrayOf("${id}"),null, null, null)
            URI_MARCA_ESPECIFICA -> TabelaBDMarcas(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"),null, null, null)
            URI_JANTE_ESPECIFICA -> TabelaBDJantes(db).query(colunas, "${BaseColumns._ID}=?",arrayOf("${id}"),null, null, null)
            URI_MOTORIZACAO_ESPECIFICA -> TabelaBDMotorizacoes(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"),null, null, null)
            URI_COMBUSTIVEL_ESPECIFICO -> TabelaBDCombustivel(db).query(colunas, "${BaseColumns._ID}=?",arrayOf("${id}"),null, null, null)
            URI_COR_ESPECIFICA -> TabelaBDCores(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"),null, null, null)
            URI_ESTILO_ESPECIFICO -> TabelaBDEstilo(db).query(colunas, "${BaseColumns._ID}=?",arrayOf("${id}"),null, null, null)
            URI_ESTOFO_ESPECIFICO -> TabelaBDEstofos(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"),null, null, null)
            URI_TRACAO_ESPECIFICA -> TabelaBDTracao(db).query(colunas, "${BaseColumns._ID}=?",arrayOf("${id}"),null, null, null)
            URI_TRANSMISSAO_ESPECIFICA -> TabelaBDTransmissoes(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"),null, null, null)



            else -> null
        }

        db.close()

        return cursor

    }

    override fun getType(uri: Uri): String? =
        when(getUriMatcher().match(uri)){
            URI_MODELOS -> "$MULTIPLOS_REGISTOS/${TabelaBDModelo.NOME}"
            URI_MARCAS -> "$MULTIPLOS_REGISTOS/${TabelaBDMarcas.NOME}"
            URI_JANTES -> "$MULTIPLOS_REGISTOS/${TabelaBDJantes.NOME}"
            URI_MOTORIZACOES -> "$MULTIPLOS_REGISTOS/${TabelaBDMotorizacoes.NOME}"
            URI_COMBUSTIVEIS -> "$MULTIPLOS_REGISTOS/${TabelaBDCombustivel.NOME}"
            URI_CORES -> "$MULTIPLOS_REGISTOS/${TabelaBDCores.NOME}"
            URI_ESTILO -> "$MULTIPLOS_REGISTOS/${TabelaBDEstilo.NOME}"
            URI_ESTOFOS -> "$MULTIPLOS_REGISTOS/${TabelaBDEstofos.NOME}"
            URI_TRACOES -> "$MULTIPLOS_REGISTOS/${TabelaBDTracao.NOME}"
            URI_TRASNMISSOES -> "$MULTIPLOS_REGISTOS/${TabelaBDTransmissoes.NOME}"

            URI_MODELO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDModelo.NOME}"
            URI_MARCA_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDMarcas.NOME}"
            URI_JANTE_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDJantes.NOME}"
            URI_MOTORIZACAO_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDMotorizacoes.NOME}"
            URI_COMBUSTIVEL_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDCombustivel.NOME}"
            URI_COR_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDCores.NOME}"
            URI_ESTILO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDEstilo.NOME}"
            URI_ESTOFO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDEstofos.NOME}"
            URI_TRACAO_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDTracao.NOME}"
            URI_TRANSMISSAO_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDTransmissoes.NOME}"


            else -> null
        }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper!!.writableDatabase

        requireNotNull(values)

        val id = when (getUriMatcher().match(uri)){
            URI_MODELOS -> TabelaBDModelo(db).insert(values)
            URI_MARCAS -> TabelaBDMarcas(db).insert(values)
            URI_JANTES -> TabelaBDJantes(db).insert(values)
            URI_MOTORIZACOES -> TabelaBDMotorizacoes(db).insert(values)
            URI_COMBUSTIVEIS -> TabelaBDCombustivel(db).insert(values)
            URI_CORES -> TabelaBDCores(db).insert(values)
            URI_ESTILO -> TabelaBDEstilo(db).insert(values)
            URI_ESTOFOS -> TabelaBDEstofos(db).insert(values)
            URI_TRACOES -> TabelaBDTracao(db).insert(values)
            URI_TRASNMISSOES -> TabelaBDTransmissoes(db).insert(values)
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
            URI_JANTE_ESPECIFICA -> TabelaBDJantes(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_MOTORIZACAO_ESPECIFICA -> TabelaBDMotorizacoes(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_COMBUSTIVEIS -> TabelaBDCombustivel(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_COR_ESPECIFICA -> TabelaBDCores(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_ESTILO_ESPECIFICO -> TabelaBDEstilo(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_ESTOFO_ESPECIFICO -> TabelaBDEstofos(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_TRACAO_ESPECIFICA -> TabelaBDTracao(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            URI_TRANSMISSAO_ESPECIFICA -> TabelaBDTransmissoes(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))

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

        const val URI_JANTES = 300
        const val URI_JANTE_ESPECIFICA = 301

        const val URI_MOTORIZACOES = 400
        const val URI_MOTORIZACAO_ESPECIFICA = 401

        const val URI_COMBUSTIVEIS = 500
        const val URI_COMBUSTIVEL_ESPECIFICO = 501

        const val URI_CORES = 600
        const val URI_COR_ESPECIFICA = 601

        const val URI_ESTILO = 700
        const val URI_ESTILO_ESPECIFICO = 701

        const val URI_ESTOFOS = 800
        const val URI_ESTOFO_ESPECIFICO = 801

        const val URI_TRACOES = 900
        const val URI_TRACAO_ESPECIFICA = 901

        const val URI_TRASNMISSOES = 1000
        const val URI_TRANSMISSAO_ESPECIFICA = 1001

        const val UNICO_REGISTO = "vnd.android.cursor.item"
        const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

        val ENDERECO_BASE = Uri.parse("content://$AUTHORITY")
        val ENDERECO_MODELOS = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDModelo.NOME)
        val ENDERECO_MARCAS = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDMarcas.NOME)
        val ENDERECO_JANTES = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDJantes.NOME)
        val ENDERECO_MOTORIZACOES = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDMotorizacoes.NOME)
        val ENDERECO_COMBUSTIVEIS = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDCombustivel.NOME)
        val ENDERECO_CORES = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDCores.NOME)
        val ENDERECO_ESTILO = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDEstilo.NOME)
        val ENDERECO_ESTOFOS = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDEstofos.NOME)
        val ENDERECO_TRACOES = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDTracao.NOME)
        val ENDERECO_TRASNMISSOES = Uri.withAppendedPath(ENDERECO_BASE, TabelaBDTransmissoes.NOME)





        fun getUriMatcher() : UriMatcher{
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, TabelaBDMarcas.NOME, URI_MARCAS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDMarcas.NOME}/#", URI_MARCA_ESPECIFICA)

            uriMatcher.addURI(AUTHORITY, TabelaBDModelo.NOME, URI_MODELOS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDModelo.NOME}/#", URI_MODELO_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDJantes.NOME, URI_JANTES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDJantes.NOME}/#", URI_JANTE_ESPECIFICA)

            uriMatcher.addURI(AUTHORITY, TabelaBDMotorizacoes.NOME, URI_MOTORIZACOES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDMotorizacoes.NOME}/#", URI_MOTORIZACAO_ESPECIFICA)

            uriMatcher.addURI(AUTHORITY, TabelaBDCombustivel.NOME, URI_COMBUSTIVEIS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDCombustivel.NOME}/#", URI_COMBUSTIVEL_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDCores.NOME, URI_CORES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDCores.NOME}/#", URI_COR_ESPECIFICA)

            uriMatcher.addURI(AUTHORITY, TabelaBDEstilo.NOME, URI_ESTILO)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDEstilo.NOME}/#", URI_ESTILO_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDEstofos.NOME, URI_ESTOFOS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDEstofos.NOME}/#", URI_ESTOFO_ESPECIFICO)

            uriMatcher.addURI(AUTHORITY, TabelaBDTracao.NOME, URI_TRACOES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDTracao.NOME}/#", URI_TRACAO_ESPECIFICA)

            uriMatcher.addURI(AUTHORITY, TabelaBDTransmissoes.NOME, URI_TRASNMISSOES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDTransmissoes.NOME}/#", URI_TRANSMISSAO_ESPECIFICA)




            return uriMatcher
        }


    }

}