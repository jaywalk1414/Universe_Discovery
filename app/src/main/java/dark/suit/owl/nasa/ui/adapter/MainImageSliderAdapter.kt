package dark.suit.owl.nasa.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.imageview.ShapeableImageView
import dark.suit.owl.nasa.R
import dark.suit.owl.nasa.data.model.MainImageSliderItem

class MainImageSliderAdapter(private val sliderDataList: List<MainImageSliderItem>, private val fragmentActivity: FragmentActivity) :
    RecyclerView.Adapter<MainImageSliderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(fragmentActivity).inflate(R.layout.fragment_slider_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sliderDataList[position])
    }

    override fun getItemCount(): Int {
        return sliderDataList.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val image = v.findViewById<ShapeableImageView>(R.id.rounded_imageslide_iv)
        val text = v.findViewById<TextView>(R.id.text_slide_tv)


        fun bind(sliderData: MainImageSliderItem) {

            image.setImageResource(sliderData.image)
            text.text = sliderData.text

        }
    }
}


