package dark.suit.owl.nasa.ui.fragment

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
import dark.suit.owl.nasa.data.model.MainImageSliderItem
import dark.suit.owl.nasa.ui.adapter.SliderPagerAdapter
import dark.suit.owl.nasa.ui.fragment.custom.SliderItemFragment
import dark.suit.owl.nasa.utils.CarouselEffectTransformer
import java.lang.Math.abs


class MainFragment : Fragment() {
    lateinit var  handlerSlider : Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sliderVp = view.findViewById<ViewPager>(R.id.view_pager_mainFrag)


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
}