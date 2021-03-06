package pt.ipg.projeto.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pt.ipg.projeto.MainActivity
import pt.ipg.projeto.R
import pt.ipg.projeto.databinding.FragmentMenuPrincipalBinding

class MenuPrincipalFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentMenuPrincipalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
*/
        _binding = FragmentMenuPrincipalBinding.inflate(inflater, container, false)
        return binding.root

        /*val textView: TextView = binding.textView2
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonModelos.setOnClickListener {
            findNavController().navigate(R.id.action_navhome_to_listaModelosfragment)
        }
        binding.buttonMarcas.setOnClickListener {
            findNavController().navigate(R.id.action_navhome_to_listaMarcasFragment)
        }

        binding.buttonCarros.setOnClickListener{
            findNavController().navigate(R.id.action_navhome_to_listaCarrosFragment)
        }

        binding.buttonMotorizacoes.setOnClickListener{
            findNavController().navigate(R.id.action_navhome_to_listaMotorizacoesFragment)
        }

/*
        binding.buttonJantes.setOnClickListener {
            findNavController().navigate(R.id.action_navhome_to_listaJantesFragment)
        }

        binding.buttonEstofos.setOnClickListener{
            findNavController().navigate(R.id.action_navhome_to_listaEstofosFragment)
        }

        binding.buttonTransmissao.setOnClickListener{
            findNavController().navigate(R.id.action_navhome_to_listaTransmissoesFragment)
        }

        binding.buttonTracao.setOnClickListener{
            findNavController().navigate(R.id.action_navhome_to_listaTracaoFragment)
        }

        binding.buttonCombustivel.setOnClickListener{
            findNavController().navigate(R.id.action_navhome_to_listaCombustivelFragment)
        }*/

        /*binding.buttonCor.setOnClickListener{
            findNavController().navigate(R.id.action_navhome_to_listaCoresFragment)
        }*/

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.main
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when (item.itemId){
            R.id.action_settings -> true
            else -> false
        }
}