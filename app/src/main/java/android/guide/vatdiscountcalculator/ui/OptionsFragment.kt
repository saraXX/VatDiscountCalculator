package android.guide.vatdiscountcalculator.ui

import android.guide.vatdiscountcalculator.databinding.FragmentOptionsBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class OptionsFragment : Fragment() {

    private var _binding : FragmentOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vatBtn.setOnClickListener {
            val action = OptionsFragmentDirections
                .actionOptionsFragmentToCalculatorFragment("VAT")
            findNavController().navigate(action)
        }

        binding.discountBtn.setOnClickListener {
            val action = OptionsFragmentDirections
                .actionOptionsFragmentToCalculatorFragment("DISCOUNT")
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}