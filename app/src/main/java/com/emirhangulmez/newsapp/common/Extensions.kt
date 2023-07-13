package com.emirhangulmez.newsapp.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.emirhangulmez.newsapp.databinding.DialogLoadingBinding
import org.json.JSONObject.NULL

object Extensions {
    fun Fragment.findNavControllerSafely(): NavController? {
        return if (isAdded) {
            findNavController()
        } else {
            null
        }
    }

    fun String?.getOrWriteNull() =
        this ?: NULL.toString()

    fun View.makeVisible() {
        visibility = View.VISIBLE
    }

    fun View.makeGone() {
        visibility = View.GONE
    }

    fun getLoadingDialog(context: Context): AlertDialog =
        DialogLoadingBinding.inflate(
            LayoutInflater.from(context)
        ).let { binding ->
            AlertDialog.Builder(context).let { builder ->
                builder.create().apply {
                    setCanceledOnTouchOutside(false)
                    setCancelable(false)
                    setView(binding.root)

                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
            }
        }


}