package dark.suit.owl.nasa.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<T>(protected var compositeDisposable: CompositeDisposable) :
    ViewModel() {
    protected var mNavigator: WeakReference<T>? = null
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val navigator: T?
        get() = mNavigator!!.get()

    fun setNavigator(navigator: T) {
        mNavigator = WeakReference(navigator)
    }
}