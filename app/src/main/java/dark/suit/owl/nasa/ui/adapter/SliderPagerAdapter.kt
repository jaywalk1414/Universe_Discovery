package dark.suit.owl.nasa.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import dark.suit.owl.nasa.ui.fragment.custom.SliderItemFragment

class SliderPagerAdapter(fm: FragmentManager,  val itemFragment:List<SliderItemFragment> ) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val MAX_COUNT = itemFragment.size


    override fun getCount(): Int = MAX_COUNT

    override fun getItem(position: Int): Fragment {
        return itemFragment[position]
    }

    override fun getPageWidth(position: Int): Float {
        return  0.9f
    }




//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val virtualPosition: Int = position % MAX_COUNT
//        return super.instantiateItem(container, virtualPosition)
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        val virtualPosition: Int = position % MAX_COUNT
//        super.destroyItem(container, virtualPosition, `object`)
//    }

}