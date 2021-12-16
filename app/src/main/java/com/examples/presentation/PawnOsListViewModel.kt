package com.examples.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.common.Resource
import com.examples.domain.data.PawnItem
import com.examples.domain.use_case.GetOsPawnUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PawnOsListViewModel @Inject constructor(
    private val getOsPawnUC: GetOsPawnUC
) : ViewModel() {

    private val _state = mutableStateOf(ViewState<PawnItem>())
    val state: State<ViewState<PawnItem>> = _state

    init {
        getOsPawnItems()
    }

    private fun getOsPawnItems() {
        getOsPawnUC().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ViewState(items = result.data ?: emptyList())
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