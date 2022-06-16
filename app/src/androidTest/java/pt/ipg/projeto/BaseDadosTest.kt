package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
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

    fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase{
        val openHelper = BDCarrosOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereMarca(db: SQLiteDatabase, marca: Marca) {
        marca.id = TabelaBDMarcas(db).insert(marca.toContentValues())
        assertNotEquals(-1, marca.id)
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
        modelo.id = TabelaBDModelo(db).insert(modelo.toContentValues())

        assertNotEquals(-1, modelo.id)

        db.close()
    }

}