package com.nativeteams.common.data.util

sealed class Resource<out O> {
    data class Success<out O>( val result: O) : Resource<O>()
    data class Failure( val errorMessage: String ) : Resource<Nothing>()
    data class Loading( val loading: Boolean ): Resource<Nothing>()
}