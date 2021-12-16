package com.examples.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.common.Resource
import com.examples.domain.data.PawnItem
import com.examples.domain.use_case.GetTodaysRenewalUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PawnTodaysRenewalViewModel @Inject constructor(
    private val getTodaysRenewalUC: GetTodaysRenewalUC
) : ViewModel() {

    private val _state = mutableStateOf(ViewState<PawnItem>())
    val state: State<ViewState<PawnItem>> = _state

    init {
        getTodaysRenewalList()
    }
    
    private fun getTodaysRenewalList() {
        getTodaysRenewalUC().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ViewState(items = result.data ?: emptyList())
                    Log.d("Rj pi today",result.data.toString())

                }
                is Resource.Error -> {
                    _state.value = ViewState(
                        error = result.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ViewState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}