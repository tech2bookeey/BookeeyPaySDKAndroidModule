package com.bookeey.bookeeypaysdk_library_module;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        getSupportActionBar().hide();


        try {

            TextView merchantTitle = (TextView)findViewById(R.id.merchantTitle);

            String merch_title = getIntent().getStringExtra("KEY_MERCH_TITLE");
            merchantTitle.setText(merch_title +" Pay");

            String extras = getIntent().getStringExtra("KEY_RESULT");
            JSONObject extraxJo = new JSONObject(extras);
            String url = extraxJo.getString("PayUrl");
            WebView mWebView = (WebView) findViewById(R.id.payuwebpage);
            progressBar = (ProgressBar) findViewById(R.id.pbar);
            progressBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.knet));
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setSupportZoom(true);
            mWebView.setBackgroundDrawable(getResources().getDrawable(R.drawable.knet));
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.setWebViewClient(new WebViewSampleClient());
            mWebView.loadUrl(url);
            progress = new ProgressDialog(this);
            progress.setCancelable(true);

        }catch(Exception e){

            Toast.makeText(WebViewActivity.this, " Error! "+e.getMessage(),Toast.LENGTH_LONG).show();

        }


        Button payuwebclose =  (Button)findViewById(R.id.payuwebclose);
        payuwebclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private class WebViewSampleClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        public Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
            Map<String, String> query_pairs = new LinkedHashMap<String, String>();
            String query = url.getQuery();
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            return query_pairs;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            final String _url = url;

            https://demo.bookeey.com/portal/paymentSuccess?txnId=222335998&merchantTxnId=31594021026221



            if (_url.contains("paymentSuccess")) {


                try {

                    Map<String,String> mp =  splitQuery(new URL(_url));

                    Intent output = new Intent();
                    output.putExtra("OUTPUT", new Gson().toJson(mp));
                    setResult(RESULT_OK, output);
                    finish();



                }catch(Exception e){
                    Toast.makeText(WebViewActivity.this, "Success case Error! "+e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }else if(_url.contains("paymentfailure")){

                try {


                Map<String,String> mp =  splitQuery(new URL(_url));
                Intent output = new Intent();
                output.putExtra("OUTPUT", new Gson().toJson(mp));
                setResult(RESULT_OK, output);
                finish();

                }catch(Exception e){
                    Toast.makeText(WebViewActivity.this, " Failure case Error! "+e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }



//            if (_url.contains("paymentSuccess")) {
//                ((LinearLayout)findViewById(R.id.close_button_liear_layout)).setVisibility(View.VISIBLE);
//                ((Button)findViewById(R.id.payuwebclose)).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent= new Intent(getBaseContext(),MainActivityJava.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }else if(_url.contains("paymentfailure")){
//                ((LinearLayout)findViewById(R.id.close_button_liear_layout)).setVisibility(View.VISIBLE);
//                ((Button)findViewById(R.id.payuwebclose)).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent= new Intent(getBaseContext(),MainActivityJava.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }






            if (progress.isShowing()) {
                progress.dismiss();
            }

        }
        private void hideIfVisible() {
            if (progress.isShowing()) {
                progress.hide();
            }
        }
        private void showIfNotVisible(String title, String message) {
            if (!progress.isShowing()) {
                progress.setTitle(title);
                progress.setMessage(message);
                progress.show();
                progress.isShowing();
            } else {
                progress.setTitle(title);
                progress.setMessage(message);
                progress.show();
                progress.isShowing();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

}
