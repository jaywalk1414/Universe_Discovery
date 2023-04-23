package dark.suit.owl.nasa.base

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import dark.suit.owl.nasa.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

abstract class BaseFragment<T : BaseViewModel<*>?, S : ViewBinding?, A : Activity?> : Fragment(),
    BaseView {
    private val PERMISSION_REQUEST_CODE = 4586
    protected var compositeDisposable: CompositeDisposable? = null
    protected var viewModel: T? = null
    protected var viewBinding: S? = null
    protected var activity: A? = null
    protected var lifecycleOwner: LifecycleOwner? = null
    protected var handlerPostDelayed = Handler()
    protected var postRunnable: Runnable? = null
    protected abstract fun onBackPressed()
    protected abstract fun initViews()
    protected abstract fun setViewModel()
    protected abstract fun initGetData()
    protected abstract fun inflateBiding(inflater: LayoutInflater?, container: ViewGroup?): S
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                onBackPressed()
                //                navigateUp();
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as A?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflateBiding(inflater, container)
        return viewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleOwner = viewLifecycleOwner
        compositeDisposable = CompositeDisposable()
        setViewModel()
        initGetData()
        initViews()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    fun setFragmentResult(key: String?, value: Any?) {
        NavHostFragment.findNavController(this).previousBackStackEntry!!
            .savedStateHandle.set(key!!, value)
        navigateUp()
    }

    fun getFragmentResult(key: String?): Any? {
        return NavHostFragment.findNavController(this).currentBackStackEntry!!.savedStateHandle.get(
            key!!
        )
    }

    fun getFragmentResultObserver(key: String?): MutableLiveData<Any>? {
        return Objects.requireNonNull(
            Navigation.findNavController(
                viewBinding!!.root
            ).currentBackStackEntry
        )?.savedStateHandle?.getLiveData(
            key!!
        )
    }

    fun addDisposable(disposable: Disposable?) {
        compositeDisposable!!.add(disposable!!)
    }

    protected fun onPermission(basePermissionModels: ArrayList<BasePermissionModel>?) {}
    fun checkPermissions(permissions: Array<String?>) {
        val permissionArray = ArrayList<BasePermissionModel>()
        for (permission in permissions) {
            permissionArray.add(BasePermissionModel(permission!!, true))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE)
        }
        onPermission(permissionArray)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val permissionResults = ArrayList<BasePermissionModel>()
            for (i in permissions.indices) {
                permissionResults.add(
                    BasePermissionModel(
                        permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED
                    )
                )
            }
            onPermission(permissionResults)
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun navigate(action: Int?) {
        Navigation.findNavController(requireView()).navigate(action!!)
    }

    fun navigate(action: Int?, bundle: Bundle?) {
        Navigation.findNavController(requireView()).navigate(action!!, bundle)
    }

    fun navigateWithOptions(action: NavDirections, navOptions: NavOptions?) {
        Log.d(TAG, "navigate: $action")
        try {
            Navigation.findNavController(requireView()).navigate(action, navOptions)
        } catch (e: Exception) {
            Log.d(TAG, "navigate: " + e.message)
        }
    }

    fun navigate(action: NavDirections) {
        val option = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.fade_in)
            .setExitAnim(R.anim.fade_out)
            .setPopExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .build()
        Log.d(TAG, "navigate: $action")
        try {
            Navigation.findNavController(requireView()).navigate(action, option)
        } catch (e: Exception) {
            Log.d(TAG, "navigate: " + e.message)
        }
    }

    fun navigateUp() {
        NavHostFragment.findNavController(this).navigateUp()
    }

    override fun onDestroy() {
        handlerPostDelayed.removeCallbacks(postRunnable!!)
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //        presenter.onDestroy();
        viewBinding = null
    }

    companion object {
        val TAG = BaseFragment::class.java.simpleName
    }
}