package dongeulcomapny.com.myandroidarchitecture.data.network

sealed class Result<out R> {

    data class CallBackSuccess<out T>(val data: T) : Result<T>()
    data class CallBackError(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is CallBackSuccess<*> ->" CallBackSuccess[data = $data]"
            is CallBackError ->"CallBackError [exception = $exception]"
            Loading ->"Loading"
        }
    }
}

