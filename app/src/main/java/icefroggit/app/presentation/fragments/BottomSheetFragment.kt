package icefroggit.app.presentation.fragments

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.imageview.ShapeableImageView
import icefroggit.app.R
import icefroggit.app.databinding.BottomSheetBinding
import icefroggit.app.utils.Constants
import java.io.File
import java.io.IOException

class BottomSheetFragment(private val wallUrl: String) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomSheetBinding.inflate(inflater)
        initButtons()
        return binding.root
    }

    private fun initButtons() {
        binding.downLoadFromNet.setOnClickListener { downloadImageFromNet(wallUrl) }
        binding.setAsBackground.setOnClickListener { setAsBackground(Constants.BackGroundState.backGround) }
        binding.setAsLockscreen.setOnClickListener { setAsBackground(Constants.BackGroundState.lockScreen) }
    }

    private fun downloadImageFromNet(url: String) {
        try {
            Toast.makeText(context, "Downloading image", Toast.LENGTH_LONG).show()
            val downloadManager =
                context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val imageUrl = Uri.parse(url)
            val request = DownloadManager.Request(imageUrl).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setMimeType("image/*")
                    .setAllowedOverRoaming(false)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setTitle("wool")
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                        File.separator + "wool" + ".jpg")
            }
            downloadManager.enqueue(request)
        } catch (e: Exception) {
        }
    }

    private fun setAsBackground(LockOrBackground: Int) {
        try {
            val wallpaperManager = WallpaperManager.getInstance(context)
            val image = activity?.findViewById<ShapeableImageView>(R.id.download_image_view)
            if (LockOrBackground == Constants.BackGroundState.backGround)
                Toast.makeText(context, "Wallpaper set as background", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"Wallpaper set as lockScreen",Toast.LENGTH_LONG).show()
            if (image?.drawable != null) {
                val bitmap = (image.drawable as BitmapDrawable).bitmap
                wallpaperManager.setBitmap(bitmap, null, true, LockOrBackground)
            } else {
                Toast.makeText(context, "wait to download", Toast.LENGTH_LONG).show()
            }
        } catch (e: IOException) {
        }
    }
}