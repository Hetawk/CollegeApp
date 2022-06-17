package com.ekd.college.ui.gallery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.ekd.college.R;


public class GalleryFragment extends Fragment {
    private WebView mWebView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mWebView = (WebView) view.findViewById(R.id.web_view);
//        initView();
        initData();
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initData() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://www.youtube.com");
    }

//    private void initView() {
//        mWebView = (WebView) find
//    }
}