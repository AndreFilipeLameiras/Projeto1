package pt.ipg.projeto

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pt.ipg.projeto.databinding.ActivityMainBinding
import pt.ipg.projeto.ui.gallery.ListaCoresFragment
import pt.ipg.projeto.ui.home.MenuPrincipalFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    var idMenuAtual = R.menu.main
        get() = field
        set(value) {
            if (value != field) {
                field = value
                invalidateOptionsMenu()
            }
        }

    var janteSeleccionada : Jante? = null

    var menu: Menu? = null

    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) 

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        /*binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navhome, R.id.listaCoresFragment, R.id.listaJantesFragment, R.id.listaCombustivelFragment, R.id.listaTracaoFragment, R.id.listaTransmissoesFragment, R.id.listaEstofosFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(idMenuAtual, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val opcaoProcessada: Boolean

        if (fragment is MenuPrincipalFragment) {
            opcaoProcessada = (fragment as MenuPrincipalFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaCarrosFragment) {
            opcaoProcessada = (fragment as ListaCarrosFragment).processaOpcaoMenu(item)
        }else if (fragment is EditarCarroFragment) {
            opcaoProcessada = (fragment as EditarCarroFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarCarroFragment) {
            opcaoProcessada = (fragment as EliminarCarroFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaMarcasFragment) {
            opcaoProcessada = (fragment as ListaMarcasFragment).processaOpcaoMenu(item)
        } else if (fragment is EditarMarcaFragment) {
            opcaoProcessada = (fragment as EditarMarcaFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarMarcaFragment) {
            opcaoProcessada = (fragment as EliminarMarcaFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaModelosfragment) {
            opcaoProcessada = (fragment as ListaModelosfragment).processaOpcaoMenu(item)
        } else if (fragment is InserirModeloFragment) {
            opcaoProcessada = (fragment as InserirModeloFragment).processaOpcaoMenu(item)
        } else if (fragment is EliminarModeloFragment) {
            opcaoProcessada = (fragment as EliminarModeloFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaJantesFragment) {
            opcaoProcessada = (fragment as ListaJantesFragment).processaOpcaoMenu(item)
        } else if (fragment is EditarJantesFragment) {
            opcaoProcessada = (fragment as EditarJantesFragment).processaOpcaoMenu(item)
        } else if (fragment is EliminaJanteFragment) {
            opcaoProcessada = (fragment as EliminaJanteFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaEstofosFragment) {
            opcaoProcessada = (fragment as ListaEstofosFragment).processaOpcaoMenu(item)
        }else if (fragment is EditarEstofoFragment) {
            opcaoProcessada = (fragment as EditarEstofoFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarEstofoFragment) {
            opcaoProcessada = (fragment as EliminarEstofoFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaTracaoFragment) {
            opcaoProcessada = (fragment as ListaTracaoFragment).processaOpcaoMenu(item)
        }else if (fragment is EditarTracaoFragment) {
            opcaoProcessada = (fragment as EditarTracaoFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarTracaoFragment) {
            opcaoProcessada = (fragment as EliminarTracaoFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaTransmissoesFragment) {
            opcaoProcessada = (fragment as ListaTransmissoesFragment).processaOpcaoMenu(item)
        }else if (fragment is EditarTransmissaoFragment) {
            opcaoProcessada = (fragment as EditarTransmissaoFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarTransmisssaoFragment) {
            opcaoProcessada = (fragment as EliminarTransmisssaoFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaCombustivelFragment) {
            opcaoProcessada = (fragment as ListaCombustivelFragment).processaOpcaoMenu(item)
        }else if (fragment is EditarCombustivelFragment) {
            opcaoProcessada = (fragment as EditarCombustivelFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarCombustivelFragment) {
            opcaoProcessada = (fragment as EliminarCombustivelFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaCoresFragment) {
            opcaoProcessada = (fragment as ListaCoresFragment).processaOpcaoMenu(item)
        }else if (fragment is EditarCorFragment) {
            opcaoProcessada = (fragment as EditarCorFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarCorFragment) {
            opcaoProcessada = (fragment as EliminarCorFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaMotorizacoesFragment) {
            opcaoProcessada = (fragment as ListaMotorizacoesFragment).processaOpcaoMenu(item)
        }else if (fragment is InserirMotorizacaoFragment) {
            opcaoProcessada = (fragment as InserirMotorizacaoFragment).processaOpcaoMenu(item)
        }


        else {
            opcaoProcessada = false
        }

        if (opcaoProcessada) return true

        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun mostraOpcoesAlterarEliminar(mostra: Boolean) {
        menu!!.findItem(R.id.action_alterar).setVisible(mostra)
        menu!!.findItem(R.id.action_eliminar).setVisible(mostra)
    }

    fun atualizaTitulo(id_sting_titulo: Int){
        binding.appBarMain.toolbar.setTitle(id_sting_titulo)
    }

}