package android.guide.vatdiscountcalculator.ui

import android.guide.vatdiscountcalculator.R
import android.guide.vatdiscountcalculator.ResultViewModel
import android.guide.vatdiscountcalculator.databinding.FragmentCalculatorBinding
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar

class CalculatorFragment : Fragment() {
    val args: CalculatorFragmentArgs by navArgs()
    val TAG = "CalculatorFragment"
    private var _binding : FragmentCalculatorBinding? = null
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

        if (args.option =="VAT"){
            setVatUI()
        }else{
            setDiscountUI()
        }

        binding.enterBtn.setOnClickListener {
            if (TextUtils.isEmpty(binding.numberEditText.text) && TextUtils.isEmpty(binding.percentageEditText.text)){
                Snackbar.make(binding.root, R.string.snack_bar, Snackbar.LENGTH_LONG)
                    .show()
            }else{
                showResult()
            }
        }
    }


    fun showResult(){
        val str1 = binding.numberEditText.text.toString()
        val str2 = binding.percentageEditText.text.toString()
        val str1ToF = str1.toFloat()
        val str2ToF = str2.toFloat()

        if (args.option == "VAT"){
            viewModel.calculateVAT(str1ToF, str2ToF)
            Log.d(TAG, "showResult: get currency"+ R.string.currency)
            Log.d(TAG, "showResult: get currency"+ R.string.currency.toString())
            Log.d(TAG, "showResult: get currency"+ getString(R.string.currency))
            binding.resultTextView.text = String.Companion.format("%.2f\n%s",viewModel.result.value, getString(R.string.currency))
            binding.detailTextView.text = String.Companion.format("%.2f %s",viewModel.vatAmount.value, getString(R.string.currency))
        }
        else{
            viewModel.calculateDISCOUNT(str1ToF, str2ToF)
            binding.resultTextView.text = String.Companion.format("%.2f\n%s",viewModel.result.value, getString(R.string.currency))
            binding.detailTextView.text = String.Companion.format("%.2f %s",viewModel.discountAmount.value, getString(R.string.currency))
        }
    }



    fun setVatUI(){
        binding.amountAfterResult.text = getString(R.string.amount_after_vat)
        binding.detailOfResult.text = getString(R.string.increase_amount)
        binding.calculatorInfoTextView.text = getString(R.string.fragmentVatText)
    }
    fun setDiscountUI(){
        binding.amountAfterResult.text = getString(R.string.amount_after_discount)
        binding.detailOfResult.text = getString(R.string.discount_amount)
        binding.calculatorInfoTextView.text = getString(R.string.fragmentDiscountText)
    }


}