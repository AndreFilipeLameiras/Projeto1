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

    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase{
        val openHelper = BDCarrosOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereMarca(db: SQLiteDatabase, marca: Marca) {
        marca.id = TabelaBDMarcas(db).insert(marca.toContentValues())
        assertNotEquals(-1, marca.id)
    }

    private fun insereModelo(db: SQLiteDatabase, modelo: Modelo){
        modelo.id = TabelaBDModelo(db).insert(modelo.toContentValues())
        assertNotEquals(-1, modelo.id)
    }


    @Before
    fun apagaBaseDados(){
        appContext().deleteDatabase(BDCarrosOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val openHelper = BDCarrosOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirMarca(){
        val db = getWritableDatabase()

        val marca = Marca("BMW")

        TabelaBDModelo(db).insert(marca.toContentValues())

        db.close()
    }


    @Test
    fun consegueInserirModelo(){
        val db = getWritableDatabase()

        val marca = Marca("Audi")
        insereMarca(db, marca)

        val modelo = Modelo("Serie 1 ", 23574.28, marca.id)
        insereModelo(db, modelo)

        db.close()
    }

    @Test
    fun consegueAlterarMarca(){
        val db = getWritableDatabase()

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
    fun consegueAlterarModelos(){
        val db = getWritableDatabase()

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

}