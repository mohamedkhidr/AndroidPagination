package com.khidrew.pagination.model

sealed class CatListItem {
    data class CatItem(val cat: Cat) : CatListItem()
    data class SeparatorItem(val letter: String) : CatListItem()
}