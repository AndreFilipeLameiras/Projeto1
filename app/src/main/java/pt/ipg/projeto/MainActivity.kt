package pt.ipg.projeto

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
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

    var menu: Menu? = null

    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) 

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navhome, R.id.nav_gallery, R.id.nav_slideshow
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
        } else if (fragment is ListaMarcasFragment) {
            opcaoProcessada = (fragment as ListaMarcasFragment).processaOpcaoMenu(item)
        } else if (fragment is InserirMarcaFragment) {
            opcaoProcessada = (fragment as InserirMarcaFragment).processaOpcaoMenu(item)
        } else if (fragment is ListaModelosfragment) {
            opcaoProcessada = (fragment as ListaModelosfragment).processaOpcaoMenu(item)
        } else if (fragment is InserirModeloFragment) {
            opcaoProcessada = (fragment as InserirModeloFragment).processaOpcaoMenu(item)
        } else if (fragment is EliminarModeloFragment) {
            opcaoProcessada = (fragment as EliminarModeloFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaJantesFragment) {
            opcaoProcessada = (fragment as ListaJantesFragment).processaOpcaoMenu(item)
        } else if (fragment is InserirJantesFragment) {
            opcaoProcessada = (fragment as InserirJantesFragment).processaOpcaoMenu(item)
        } else {
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

}