package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BaseDadosTest {

    private fun getAppContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getBdCarrosOpenHelper() = BDCarrosOpenHelper(getAppContext())


    /*private fun getWritableDatabase(): SQLiteDatabase{
        val openHelper = BDCarrosOpenHelper(appContext())
        return openHelper.writableDatabase
    }*/

    private fun insereMarca(db: SQLiteDatabase, marca: Marca) {
        marca.id = TabelaBDMarcas(db).insert(marca.toContentValues())
        assertNotEquals(-1, marca.id)
    }



    private fun insereModelo(db: SQLiteDatabase, modelo: Modelo){
        modelo.id = TabelaBDModelo(db).insert(modelo.toContentValues())
        assertNotEquals(-1, modelo.id)
    }

    private fun insereTransmissao(db: SQLiteDatabase, transmissao: Transmissao){
        transmissao.id = TabelaBDTransmissoes(db).insert(transmissao.toContentValues())
        assertNotEquals(-1, transmissao.id)
    }

    private fun insereTracao(db: SQLiteDatabase, tracao: Tracao){
        tracao.id = TabelaBDTracao(db).insert(tracao.toContentValues())
        assertNotEquals(-1, tracao.id)
    }

    private fun insereCombustivel(db: SQLiteDatabase, combustivel: Combustivel){
        combustivel.id = TabelaBDCombustivel(db).insert(combustivel.toContentValues())
        assertNotEquals(-1, combustivel.id)
    }

    private fun insereCor(db: SQLiteDatabase, cor: Cores){
        cor.id = TabelaBDCores(db).insert(cor.toContentValues())
        assertNotEquals(-1, cor.id)
    }

    @Before
    fun apagaBaseDados(){
        //appContext().deleteDatabase(BDCarrosOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val openHelper = BDCarrosOpenHelper(getAppContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirMarca(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("BMW")

        TabelaBDModelo(db).insert(marca.toContentValues())

        db.close()
    }

    @Test
    fun consegueAlterarMarca(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("Teste")
        insereMarca(db, marca)

        marca.nome = "Mercedes"

        val registosAlterados = TabelaBDMarcas(db).update(
            marca.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${marca.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarMarca(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("Teste")
        insereMarca(db, marca)

        marca.nome = "Mercedes"

        val registosEliminados = TabelaBDMarcas(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${marca.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerMarca(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("Audi")
        insereMarca(db, marca)

        val cursor = TabelaBDMarcas(db).query(
            TabelaBDMarcas.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${marca.id}"),
            null,
            null,
            null

        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val marcBD = Marca.fromCursor(cursor)

        assertEquals(marca, marcBD)

        db.close()
    }


    @Test
    fun consegueInserirModelo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("Audi")
        insereMarca(db, marca)

        val modelo = Modelo("Serie 1 ", 23574.28, marca.id)
        insereModelo(db, modelo)

        db.close()
    }

    @Test
    fun consegueAlterarModelo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marcaNissam = Marca("Nissan")
        insereMarca(db, marcaNissam)

        val marcaSeat = Marca("Seat")
        insereMarca(db, marcaSeat)

        val modelo = Modelo("Teste", 25444.2, marcaNissam.id)
        insereModelo(db, modelo)

        modelo.modelo = "Navara"
        modelo.preco = 35444.4
        modelo.idMarca = marcaNissam.id

        val registosAlterados = TabelaBDModelo(db).update(
            modelo.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${modelo.id}"))



        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarModelo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("Opel")
        insereMarca(db, marca)


        val modelo = Modelo("Teste", 25214.2, marca.id)
        insereModelo(db, modelo)


        val registosEliminados = TabelaBDModelo(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${modelo.id}"))



        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerModelo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("Ferrari")
        insereMarca(db, marca)

        val modelo = Modelo("F8 Tributo", 78451.21, marca.id)
        insereModelo(db, modelo)

        val cursor = TabelaBDModelo(db).query(
            TabelaBDModelo.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${marca.id}"),
            null,
            null,
            null

        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val modeBD = Modelo.fromCursor(cursor)

        assertEquals(marca, modeBD)

        db.close()
    }

    @Test
    fun consegueInserirTransmissao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val transmissao = Transmissao("Automatica")

        TabelaBDTransmissoes(db).insert(transmissao.toContentValues())
    }

    @Test
    fun consegueAlterarTransmissao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val transmissao = Transmissao("Automatica CVT")
        insereTransmissao(db, transmissao)

        transmissao.nome = "Automatica"

        val registosAlterados = TabelaBDTransmissoes(db).update(
            transmissao.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${transmissao.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueInserirTracao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val tracao = Tracao("Integral")

        TabelaBDTracao(db).insert(tracao.toContentValues())
    }

    @Test
    fun consegueAlterarTracao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val tracao = Tracao("Teste")
        insereTracao(db, tracao)

        tracao.nome = "Dianteira"

        val registosAlterados = TabelaBDTracao(db).update(
            tracao.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${tracao.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarTracao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val tracao = Tracao("Teste")
        insereTracao(db, tracao)

        tracao.nome = "Dianteira"

        val registosEliminados = TabelaBDTracao(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${tracao.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }



    @Test
    fun consegueLerTracao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val tracao = Tracao("Traseira")
        insereTracao(db, tracao)

        val cursor = TabelaBDTracao(db).query(
            TabelaBDTracao.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${tracao.id}"),
            null,
            null,
            null

        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val tracBD = Tracao.fromCursor(cursor)

        assertEquals(tracao, tracBD)

        db.close()
    }

    @Test
    fun consegueInserirCombustivel(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val combustivel = Combustivel("Gasolina")

        TabelaBDCombustivel(db).insert(combustivel.toContentValues())
    }

    @Test
    fun consegueAlterarCombustivel(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val combustivel = Combustivel("Teste")
        insereCombustivel(db, combustivel)

        combustivel.nome = "Diesel"

        val registosAlterados = TabelaBDCombustivel(db).update(
            combustivel.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${combustivel.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarCombustivel(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val combustivel = Combustivel("Teste")
        insereCombustivel(db, combustivel)

        combustivel.nome = "Eletrico"

        val registosEliminados = TabelaBDCombustivel(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${combustivel.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerCombustivel(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val combustivel = Combustivel("Hibrido")
        insereCombustivel(db, combustivel)

        val cursor = TabelaBDCombustivel(db).query(
            TabelaBDCombustivel.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${combustivel.id}"),
            null,
            null,
            null

        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val combBD = Combustivel.fromCursor(cursor)

        assertEquals(combustivel, combBD)

        db.close()
    }

    @Test
    fun consegueInserirCores(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val cor = Cores("Vermelho", 1472.1)

        TabelaBDCores(db).insert(cor.toContentValues())
    }

    @Test
    fun consegueAlterarCores(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val cor = Cores("Teste", 1614.1)
        insereCor(db, cor)

        cor.nome = "Verde"
        cor.preco = 1454.2

        val registosAlterados = TabelaBDCores(db).update(
            cor.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${cor.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }












}