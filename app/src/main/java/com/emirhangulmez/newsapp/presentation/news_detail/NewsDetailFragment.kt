package com.emirhangulmez.newsapp.presentation.news_detail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.emirhangulmez.newsapp.R
import com.emirhangulmez.newsapp.common.Extensions.findNavControllerSafely
import com.emirhangulmez.newsapp.common.Extensions.getLoadingDialog
import com.emirhangulmez.newsapp.common.viewBinding
import com.emirhangulmez.newsapp.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val binding by viewBinding(FragmentNewsDetailBinding::bind)
    private val args by navArgs<NewsDetailFragmentArgs>()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loadingDialog = getLoadingDialog(requireContext())
        with(binding) {
            loadingDialog.show()

            getBackIv.setOnClickListener {
                findNavControllerSafely()?.popBackStack()
            }
            Uri.parse(args.newsArg.url).let { uri ->
                sourceUrlTv.text = uri.host
            }
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(args.newsArg.url)
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    request?.url?.let { uri ->
                        view?.loadUrl(uri.toString())
                        sourceUrlTv.text = uri.host
                    }
                    return true
                }
            }
            webView.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    if (newProgress == 100) {
                        loadingDialog.dismiss()
                    }
                }

            }
        }
    }
}