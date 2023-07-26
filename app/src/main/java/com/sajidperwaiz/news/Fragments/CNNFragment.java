package com.sajidperwaiz.news.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.sajidperwaiz.news.R;

public class CNNFragment extends Fragment implements OnBackPressedListener {

    WebView webView;
    ProgressBar pgBar;

    public CNNFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c_n_n, container, false);

        webView = view.findViewById(R.id.webView);
        pgBar = view.findViewById(R.id.pgBar);

        getActivity().setTitle("CNN");

        webView.loadUrl("https://edition.cnn.com/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pgBar.setVisibility(view.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pgBar.setVisibility(view.GONE);
                super.onPageFinished(view, url);
            }
        });

        return view;
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            // If the WebView can go back, navigate back in the WebView's history
            webView.goBack();
        } else {
            // If the WebView cannot go back further, perform the default back press action
            requireActivity().onBackPressed();
        }
    }
}