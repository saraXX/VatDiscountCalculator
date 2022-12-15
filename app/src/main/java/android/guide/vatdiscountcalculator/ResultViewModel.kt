package android.guide.vatdiscountcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResultViewModel : ViewModel() {
    private val _result = MutableLiveData<Float>()
    val result: LiveData<Float> = _result

    private val _discountAmount = MutableLiveData<Float>()
    val discountAmount: LiveData<Float> = _discountAmount

    private val _vatAmount = MutableLiveData<Float>()
    val vatAmount: LiveData<Float> = _vatAmount

    fun calculateVAT(number:Float, percentage:Float){
        val temp = toPercentage(number, percentage)
        _result.value = number+temp
    }


    fun calculateDISCOUNT(number:Float, percentage:Float){
        val temp = toPercentage(number, percentage)
        _result.value = number-temp
    }

    fun toPercentage(n : Float, p : Float): Float{
        val temp = p/100
        val temp2 =   temp * n
        _vatAmount.value = temp2 //
        _discountAmount.value = temp2
        return temp2 // return 0.15 to calculate
    }

}