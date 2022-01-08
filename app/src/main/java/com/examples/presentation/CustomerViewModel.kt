package com.examples.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.common.Resource
import com.examples.common.ViewState
import com.examples.domain.data.Customer
import com.examples.domain.use_case.GetCustomerUC
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val getCustomerUC: GetCustomerUC
) : ViewModel() {

    //private val _myUiState = MutableStateFlow<Result<UiState>>(Result.Loading)
    //val myUiState: StateFlow<Result<UiState>> = _myUiState

    val query = mutableStateOf("") //for search persistence

    private val _state = mutableStateOf(ViewState<Customer>())
    val state: State<ViewState<Customer>> = _state

    private var cachedCustomers: List<Customer>? = emptyList()
    private var isSearched = mutableStateOf(false)

    init {
        //lifecycleScope.launch{}
       // viewModelScope.launch {
        //repeatOnLifeCycle(Lifecycle.State.STARTED)
            getCustomers()
       // }
    }





/*
private fun getCust(){
    val result: StateFlow<Result<UiState>> = flow {
        emit(repository.fetchItem())
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000), // Or Lazily because it's a one-shot
        initialValue = Result.Loading
    )
}
*/
    private fun getCustomers() {
        //lifecycleScope

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
                    //CircularProgressIndicator(/* modifier = Modifier.align(Alignment.CenterHorizontally) */)

                }
            }
        }.launchIn(viewModelScope)
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