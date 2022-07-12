package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.ipg.projeto.databinding.FragmentInserirCombustivelBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InserirCombustivelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirCombustivelFragment : Fragment() {
    private var _binding: FragmentInserirCombustivelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirCombustivelBinding.inflate(inflater, container, false)

        return inflater.inflate(R.layout.fragment_inserir_combustivel, container, false)
    }


}