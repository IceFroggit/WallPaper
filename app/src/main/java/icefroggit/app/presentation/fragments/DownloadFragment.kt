package icefroggit.app.presentation.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import icefroggit.app.R
import icefroggit.app.databinding.FragmentDownloadBinding
import icefroggit.app.utils.BlurHashDecoder

class DownloadFragment : Fragment() {
    private lateinit var binding: FragmentDownloadBinding
    private val args: DownloadFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDownloadBinding.inflate(inflater)
        loadImage(args.imageData[0])
        addCallBack()
        return binding.root
    }

    private fun loadImage(url:String){
        val blurHash = BlurHashDecoder.decode(args.imageData[1])
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(blurHash?.toDrawable(this.resources))
            .error(blurHash)
            .into(binding.downloadImageView)
        binding.constraintDownload.background = BitmapDrawable(this.resources,blurHash)

    }
    private fun addCallBack(){
        binding.backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }



}