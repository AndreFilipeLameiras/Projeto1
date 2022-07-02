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

    private fun insereEstofo(db: SQLiteDatabase, estofos: Estofos){
        estofos.id = TabelaBDEstofos(db).insert(estofos.toContentValues())
        assertNotEquals(-1, estofos.id)
    }

    private fun insereJante(db: SQLiteDatabase, jante: Jante){
        jante.id = TabelaBDJantes(db).insert(jante.toContentValues())
        assertNotEquals(-1, jante.id)
    }

    private fun insereMotorizacao(db: SQLiteDatabase, motorizacao: Motorizacao){
        motorizacao.id = TabelaBDMotorizacoes(db).insert(motorizacao.toContentValues())
        assertNotEquals(-1, motorizacao.id)
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
            "${TabelaBDMarcas.CAMPO_ID}=?",
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
            "${TabelaBDMarcas.CAMPO_ID}=?",
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
            "${TabelaBDMarcas.CAMPO_ID}=?",
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

        val modelo = Modelo("Serie 1 ", 23574.28, marca)
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

        val modelo = Modelo("Teste", 25444.2, marcaNissam)
        insereModelo(db, modelo)

        modelo.modelo = "Navara"
        modelo.preco = 35444.4
        modelo.marca = marcaNissam

        val registosAlterados = TabelaBDModelo(db).update(
            modelo.toContentValues(),
            "${TabelaBDModelo.CAMPO_ID}=?",
            arrayOf("${modelo.id}"))



        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarModelo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("Opel")
        insereMarca(db, marca)


        val modelo = Modelo("Teste", 25214.2, marca)
        insereModelo(db, modelo)


        val registosEliminados = TabelaBDModelo(db).delete(
            "${TabelaBDModelo.CAMPO_ID}=?",
            arrayOf("${modelo.id}"))



        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerModelo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val marca = Marca("Ferrari")
        insereMarca(db, marca)

        val modelo = Modelo("F8 Tributo", 78451.21, marca)
        insereModelo(db, modelo)

        val cursor = TabelaBDModelo(db).query(
            TabelaBDModelo.TODAS_COLUNAS,
            "${TabelaBDModelo.CAMPO_ID}=?",
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
            "${TabelaBDTransmissoes.CAMPO_ID}=?",
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
            "${TabelaBDTracao.CAMPO_ID}=?",
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
            "${TabelaBDTracao.CAMPO_ID}=?",
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
            "${TabelaBDTracao.CAMPO_ID}=?",
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
            "${TabelaBDCombustivel.CAMPO_ID}=?",
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
            "${TabelaBDCombustivel.CAMPO_ID}=?",
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
            "${TabelaBDCombustivel.CAMPO_ID}=?",
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
            "${TabelaBDCores.CAMPO_ID}=?",
            arrayOf("${cor.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarCores(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val cor = Cores("Teste", 1458.1)
        insereCor(db, cor)

        cor.nome = "Verde"
        cor.preco = 1454.2


        val registosEliminados = TabelaBDCores(db).delete(
            "${TabelaBDCores.CAMPO_ID}=?",
            arrayOf("${cor.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerCores(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val cor = Cores("Azul", 1785.8)
        insereCor(db, cor)

        val cursor = TabelaBDCores(db).query(
            TabelaBDCores.TODAS_COLUNAS,
            "${TabelaBDCores.CAMPO_ID}=?",
            arrayOf("${cor.id}"),
            null,
            null,
            null

        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val corBD = Cores.fromCursor(cursor)

        assertEquals(cor, corBD)

        db.close()
    }

    @Test
    fun consegueInserirEstofo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val estofo = Estofos("Pele Dakota perfurada Preto", 1780.1)

        TabelaBDEstofos(db).insert(estofo.toContentValues())
    }

    @Test
    fun consegueAlterarEstofo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val estofo = Estofos("Teste", 1844.1)
        insereEstofo(db, estofo)

        estofo.nome = "tecido/Sensatec em antracite"
        estofo.preco = 454.2

        val registosAlterados = TabelaBDEstofos(db).update(
            estofo.toContentValues(),
            "${TabelaBDEstofos.CAMPO_ID}=?",
            arrayOf("${estofo.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarEstofo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val estofo = Estofos("Teste", 1475.1)
        insereEstofo(db, estofo)

        estofo.nome = "Tecido Grid Antracite/Preto"
        estofo.preco = 1445.2


        val registosEliminados = TabelaBDEstofos(db).delete(
            "${TabelaBDEstofos.CAMPO_ID}=?",
            arrayOf("${estofo.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerEstofo(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val estofo = Estofos("Pele Dakota perfurada em vermelho Magma", 1245.8)
        insereEstofo(db, estofo)

        val cursor = TabelaBDEstofos(db).query(
            TabelaBDEstofos.TODAS_COLUNAS,
            "${TabelaBDEstofos.CAMPO_ID}=?",
            arrayOf("${estofo.id}"),
            null,
            null,
            null

        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val estoBD = Estofos.fromCursor(cursor)

        assertEquals(estofo, estoBD)

        db.close()
    }

    @Test
    fun consegueInserirJante(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val jante = Jante("raios em estrela cinzento Ferric ", 205, 55 ,16, 450.5 )

        TabelaBDJantes(db).insert(jante.toContentValues())
    }

    @Test
    fun consegueAlterarJante(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val jante = Jante("Teste", 205, 55, 16, 450.5)
        insereJante(db, jante)

        jante.nome = "raios multiplos "
        jante.largura = 225
        jante.altura = 45
        jante.raio = 17
        jante.preco = 454.2

        val registosAlterados = TabelaBDJantes(db).update(
            jante.toContentValues(),
            "${TabelaBDJantes.CAMPO_ID}=?",
            arrayOf("${jante.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarJante(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val jante = Jante("Teste", 225, 45, 16, 755.5)
        insereJante(db, jante)

        jante.nome = "raios duplos com pneus performance"
        jante.largura = 225
        jante.altura = 45
        jante.raio = 17
        jante.preco = 1512.2


        val registosEliminados = TabelaBDJantes(db).delete(
            "${TabelaBDJantes.CAMPO_ID}=?",
            arrayOf("${jante.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerJante(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val jante = Jante("raios duplos bicolores", 225,45,17, 1545.8)
        insereJante(db, jante)

        val cursor = TabelaBDJantes(db).query(
            TabelaBDJantes.TODAS_COLUNAS,
            "${TabelaBDJantes.CAMPO_ID}=?",
            arrayOf("${jante.id}"),
            null,
            null,
            null

        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val jantBD = Jante.fromCursor(cursor)

        assertEquals(jante, jantBD)

        db.close()
    }

    @Test
    fun consegueInserirMotorizacao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val transmissao = Transmissao("Automatica")
        insereTransmissao(db, transmissao)

        val tracao = Tracao("Traseira")
        insereTracao(db, tracao)

        val combustivel = Combustivel("Gasolina")
        insereCombustivel(db, combustivel)

        val motorizacao = Motorizacao(109, 6.5, 140.5 , transmissao, tracao, combustivel)

        TabelaBDMotorizacoes(db).insert(motorizacao.toContentValues())
    }

    @Test
    fun consegueAlterarMotorizacao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val transmissaoAutomatica = Transmissao("Automatica")
        insereTransmissao(db, transmissaoAutomatica)

        val transmissaoManual = Transmissao("Manual")
        insereTransmissao(db, transmissaoManual)

        val tracaoTraseira = Tracao("Traseira")
        insereTracao(db, tracaoTraseira)

        val tracaoDianteira = Tracao("Dianteira")
        insereTracao(db, tracaoDianteira)

        val combustivelGasolina = Combustivel("Gasolina")
        insereCombustivel(db, combustivelGasolina)

        val combustivelDiesel = Combustivel("Diesel")
        insereCombustivel(db, combustivelDiesel)

        val motorizacao = Motorizacao(136, 6.8, 145.5, transmissaoAutomatica, tracaoTraseira, combustivelGasolina )
        insereMotorizacao(db, motorizacao)

        motorizacao.potencia = 190
        motorizacao.consumo = 5.4
        motorizacao.emissoes = 141.1
        motorizacao.transmissao = transmissaoManual
        motorizacao.tracao = tracaoTraseira
        motorizacao.combustivel = combustivelDiesel


        val registosAlterados = TabelaBDMotorizacoes(db).update(
            motorizacao.toContentValues(),
            "${TabelaBDMotorizacoes.CAMPO_ID}=?",
            arrayOf("${motorizacao.id}"))



        assertEquals(1, registosAlterados)

        db.close()
    }


    @Test
    fun consegueEliminarMotorizacao(){
        val db = getBdCarrosOpenHelper().writableDatabase

        val transmissao = Transmissao("Automatica CVT")
        insereTransmissao(db, transmissao)

        val tracao = Tracao("Dianteira")
        insereTracao(db, tracao)

        val combustivel = Combustivel("Hibrido")
        insereCombustivel(db, combustivel)


        val motorizacao = Motorizacao(215, 5.7, 154.3, transmissao, tracao,combustivel )
        insereMotorizacao(db, motorizacao)


        val registosEliminados = TabelaBDMotorizacoes(db).delete(
            "${TabelaBDMotorizacoes.CAMPO_ID}=?",
            arrayOf("${motorizacao.id}"))



        assertEquals(1, registosEliminados)

        db.close()
    }


}