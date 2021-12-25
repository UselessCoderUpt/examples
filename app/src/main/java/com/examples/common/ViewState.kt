package com.examples.common

//data class PawnListState(
//    val isLoading: Boolean = false,
//    val pawnItems: List<PawnItem> = emptyList(),
//    val error: String = ""
//)

data class ViewState<T>(
    val isLoading: Boolean = false,
    val items: List<T> = emptyList(),
    val error: String = ""
)

