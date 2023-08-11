package com.cleanarchitecturenotesapp.feature_note.domain.util

sealed class NoteOrderBy(val orderType: OrderType){
    class Title(orderType: OrderType): NoteOrderBy(orderType)
    class Date(orderType: OrderType): NoteOrderBy(orderType)
    class Color(orderType: OrderType): NoteOrderBy(orderType)

    fun copy(orderType: OrderType): NoteOrderBy{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
