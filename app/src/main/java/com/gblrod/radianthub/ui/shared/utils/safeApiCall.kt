package com.gblrod.radianthub.ui.shared.utils

import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException

suspend inline fun safeApiCall(
    onHttpError: (Int) -> Unit,
    onIoError: () -> Unit,
    onGenericError: () -> Unit,
    block: suspend () -> Unit
) {
    try {
        block()

    } catch (e: HttpException) {
        onHttpError(e.code())

    } catch (e: IOException) {
        onIoError()

    } catch (e: Exception) {
        if (e is CancellationException) throw e
        onGenericError()
    }
}