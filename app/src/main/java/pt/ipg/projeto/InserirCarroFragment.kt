package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.ipg.projeto.databinding.FragmentInserirCarroBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InserirCarroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirCarroFragment : Fragment() {
    private var _binding: FragmentInserirCarroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInserirCarroBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserir_carro, container, false)
    }


}