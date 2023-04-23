package dark.suit.owl.nasa.ui.fragment

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import dark.suit.owl.nasa.R
import dark.suit.owl.nasa.base.BaseFragment
import dark.suit.owl.nasa.data.model.MainImageSliderItem
import dark.suit.owl.nasa.databinding.FragmentMainBinding
import dark.suit.owl.nasa.ui.activity.MainActivity
import dark.suit.owl.nasa.ui.adapter.SliderPagerAdapter
import dark.suit.owl.nasa.ui.fragment.custom.SliderItemFragment
import dark.suit.owl.nasa.utils.CarouselEffectTransformer
import dark.suit.owl.nasa.viewmodel.MainFragmentViewModel
import java.lang.Math.abs


class MainFragment : BaseFragment<MainFragmentViewModel, FragmentMainBinding, MainActivity>() {
    lateinit var  handlerSlider : Runnable


    override fun initViews() {

        val sliderVp = viewBinding!!.viewPagerMainFrag

        val fragmentSliderList = listOf(
            SliderItemFragment(MainImageSliderItem(R.drawable.custom_slider, "sky is jhghj kljblk")),
            SliderItemFragment(MainImageSliderItem(R.drawable.rover_image, "blue bind hgvhojhb ljhbjh jhhbvjh")),
            SliderItemFragment(MainImageSliderItem(R.drawable.custom_slider, "galaxy")),
            SliderItemFragment(MainImageSliderItem(R.drawable.rover_image, "sun"))
        )

        with(sliderVp) {
            clipToPadding = false
            offscreenPageLimit = 3
            pageMargin = 50


            setPageTransformer(false,CarouselEffectTransformer(context))
            adapter = SliderPagerAdapter(parentFragmentManager, fragmentSliderList)

        }

    }

    override fun setViewModel() {
        viewModel = MainFragmentViewModel(requireContext(), compositeDisposable!!)
    }

    override fun initGetData() {
//        TODO("Not yet implemented")
    }

    override fun inflateBiding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater!!, container, false)
    }

    override fun onError(error: String?) {
//        TODO("Not yet implemented")
    }

    override fun showLoading(message: String?) {
//        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed() {
        navigateUp()
    }

    override fun showNoConnection() {
//        TODO("Not yet implemented")
    }

    override val appContext: Context?
        get() = requireContext()

}