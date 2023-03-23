package dark.suit.owl.nasa.ui.fragment.custom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import dark.suit.owl.nasa.R
import dark.suit.owl.nasa.data.model.MainImageSliderItem
import dark.suit.owl.nasa.databinding.FragmentMainBinding
import dark.suit.owl.nasa.databinding.FragmentSliderItemBinding

class SliderItemFragment (val sliderData: MainImageSliderItem): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_slider_item, container, false)
        val image = v.findViewById<ShapeableImageView>(R.id.rounded_imageslide_iv)
        val text = v.findViewById<TextView>(R.id.text_slide_tv)

        image.setImageResource(sliderData.image)
        text.text = sliderData.text
        // Inflate the layout for this fragment
        return v
    }

}