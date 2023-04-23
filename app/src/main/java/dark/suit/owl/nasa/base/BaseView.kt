package dark.suit.owl.nasa.base

import android.content.Context

interface BaseView {
    fun onError(error: String?)
    fun showLoading(message: String?)
    fun dismissLoading()
    fun showNoConnection()
    val appContext: Context?
}