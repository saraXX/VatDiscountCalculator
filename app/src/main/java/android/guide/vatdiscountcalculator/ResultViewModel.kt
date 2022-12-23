package android.guide.vatdiscountcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * amount -> input1
 * percent -> input2
 * _result -> result of calculation (either form VAT fun or discount fun)
 * _percentageResult -> either the VAT amount or Discount Amount
 *
 * these are the four variable i need in this project for now
 */
class ResultViewModel : ViewModel() {
    private val _result = MutableLiveData<Float>()
    val result: LiveData<Float> = _result

    private val _percentageResult = MutableLiveData<Float>()
    val percentageResult: LiveData<Float> = _percentageResult


    //    pass (200, 15)
    fun calculateVAT(amount: Float, percent: Float){
        _percentageResult.value = percentageAmount(amount, percent) // 30
        _result.value = amount + percentageAmount(amount, percent) // 230
    }

    //    pass (200, 15)
    fun calculateDISCOUNT(amount :Float, percent:Float){
        _percentageResult.value = percentageAmount(amount, percent) // 30
        _result.value = amount - percentageAmount(amount, percent) // 170
    }

    /**
     * pass 15
     * return 0.15
     */
    private fun toDecimal(percent: Float): Float{
        return percent/100
    }

    /**
     * pass (200, 15)
     * return 30.0
     */
    private fun percentageAmount(amount: Float, percent: Float): Float{
        return toDecimal(percent) * amount
    }


}