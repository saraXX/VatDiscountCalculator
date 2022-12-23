package android.guide.vatdiscountcalculator.ui

import android.guide.vatdiscountcalculator.R
import android.guide.vatdiscountcalculator.ResultViewModel
import android.guide.vatdiscountcalculator.databinding.FragmentCalculatorBinding
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar

class CalculatorFragment : Fragment() {
    val TAG = "CalculatorFragment"

    val args: CalculatorFragmentArgs by navArgs()

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()

        binding.enterBtn.setOnClickListener {
            if (isEmptyEntry())
                Snackbar.make(binding.root, R.string.snack_bar, Snackbar.LENGTH_LONG).show()
            else
                showResult()
        }

        viewModel.result.observe(this.viewLifecycleOwner) { result ->
            binding.resultTextView.text =
                String.Companion.format("%.2f\n%s", result, getString(R.string.currency))
            binding.detailTextView.text = String.Companion.format(
                "%.2f %s",
                viewModel.percentageResult.value,
                getString(R.string.currency)
            )
        }
    }


    fun showResult() {
        val amount = binding.numberEditText.text.toString().toFloat()
        val percent = binding.percentageEditText.text.toString().toFloat()

        if (args.option == "VAT")
            viewModel.calculateVAT(amount, percent)
        else
            viewModel.calculateDISCOUNT(amount, percent)
    }

    fun bindViews() {
        binding.apply {
            if (args.option == "VAT") { // VAT UI
                amountAfterResult.text = getString(R.string.amount_after_vat)
                detailOfResult.text = getString(R.string.increase_amount)
                calculatorInfoTextView.text = getString(R.string.fragmentVatText)
            } else { // discount UI
                amountAfterResult.text = getString(R.string.amount_after_discount)
                detailOfResult.text = getString(R.string.discount_amount)
                calculatorInfoTextView.text = getString(R.string.fragmentDiscountText)
            }
        }
    }

    fun isEmptyEntry(): Boolean {
        return TextUtils.isEmpty(binding.numberEditText.text) && TextUtils.isEmpty(binding.percentageEditText.text)
    }
}