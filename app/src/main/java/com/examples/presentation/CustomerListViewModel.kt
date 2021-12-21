package com.examples.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.common.Resource
import com.examples.common.ViewState
import com.examples.domain.data.Customer
import com.examples.domain.use_case.GetCustomerUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private val getCustomerUC: GetCustomerUC
) : ViewModel() {

    val query = mutableStateOf("") //for search persistence
    private val _state = mutableStateOf(ViewState<Customer>())
    val state: State<ViewState<Customer>> = _state
    private var cachedCustomers: List<Customer>? = emptyList()
    private var isSearched = mutableStateOf(false)

    init {
        viewModelScope.launch {
            getCustomers()
        }
    }

    private suspend fun getCustomers() {
        getCustomerUC().onEach { result ->
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
        }
            //.launchIn(viewModelScope)
    }

    fun newSearch(query: String) {

        val trimmedQuery = query.trim() //avoid trimming in multiple places

        //cache customers from Resource for search
        if (!isSearched.value) {
            cachedCustomers = _state.value.items
            isSearched.value = true
        }
        _state.value = ViewState(isLoading = true)
        viewModelScope.launch(Dispatchers.Default) {
            if (trimmedQuery.isEmpty()) {
                _state.value = ViewState(items = cachedCustomers ?: emptyList())
                return@launch
            }
            val results = cachedCustomers?.filter {
                it.Name.contains(trimmedQuery, ignoreCase = true) ||
                        it.MobileNo.startsWith(trimmedQuery, ignoreCase = true) ||
                        it.Place.startsWith(trimmedQuery, ignoreCase = true)
            }
            results?.sortedBy { it.Name }
            _state.value = ViewState(items = results ?: emptyList())
        }
    }
}