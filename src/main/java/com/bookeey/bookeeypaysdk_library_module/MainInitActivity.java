package com.bookeey.bookeeypaysdk_library_module;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Bundle;
import android.widget.Toast;
import com.google.gson.Gson;
import com.myfatoorah.demo.Do_Appinfo;
import com.myfatoorah.demo.Do_MerchDtl;
import com.myfatoorah.demo.Do_MoreDtl;
import com.myfatoorah.demo.Do_PyrDtl;
import com.myfatoorah.demo.Do_TxnDtl;
import com.myfatoorah.demo.Do_TxnHdr;
import com.myfatoorah.demo.Do_WholeKnet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainInitActivity extends AppCompatActivity {


    public static final String  KEY_AMOUNT = "KEY_AMOUNT";
    public static final String  KEY_MERCH_UID = "KEY_MERCH_UID";
    public static final String  KEY_PAY_METHOD = "KEY_PAY_METHOD";
    public static final String  KEY_TRACK_TXN_UID = "KEY_TRACK_TXN_UID";
    public static final String  KEY_MERCH_TITLE = "KEY_MERCH_TITLE";

    private String merchantTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);



        getSupportActionBar().hide();


//        String amount = "0.100";
//        String merchUID = "mer160009";
//        String payMethod = "KNET";
//        String tarck_txn_UID = "3"+System.currentTimeMillis();
//        String merchantTitle = "Talabat";




        String  amount = getIntent().getStringExtra(KEY_AMOUNT);

        String  merchUID = getIntent().getStringExtra(KEY_MERCH_UID);

        String payMethod = getIntent().getStringExtra(KEY_PAY_METHOD);

        String track_txn_UID = getIntent().getStringExtra(KEY_TRACK_TXN_UID);

        merchantTitle = getIntent().getStringExtra(KEY_MERCH_TITLE);


        initiateKnet_CC_AMEX_Transaction(amount,merchUID,payMethod,track_txn_UID,merchantTitle);

    }



    public void initiateKnet_CC_AMEX_Transaction(String amount,String merchUID, String payMethod, String tarck_txn_UID, String merchantTitle){

        try {




            Do_TxnDtl Do_TxnDtl = new Do_TxnDtl();
            Do_TxnDtl.setSubMerchUID(merchUID);
            Do_TxnDtl.setTxn_AMT(amount);

            Do_TxnHdr Do_TxnHdr = new Do_TxnHdr();
            Do_TxnHdr.setPayFor("ECom");
            Do_TxnHdr.setMerch_TrackUID(tarck_txn_UID);

            Do_TxnHdr.setPayMethod(payMethod);

            Do_TxnHdr.setBKY_Txn_UID("");

            Do_TxnHdr.setMerch_Txn_UID(tarck_txn_UID);

            Do_Appinfo Do_Appinfo = new Do_Appinfo();
            Do_Appinfo.setAPPTyp( "");
            Do_Appinfo.setOS( "");
            Do_Appinfo.setDevcType("");
            Do_Appinfo.setIPAddrs("");
            Do_Appinfo.setCountry("");
            Do_Appinfo.setAPIVer("");
            Do_Appinfo.setUsrSessID("");
            Do_Appinfo.setAPIVer("");

            Do_PyrDtl Do_PyrDtl = new Do_PyrDtl();
            Do_PyrDtl.setPyr_MPhone("");
            Do_PyrDtl.setPyr_Name("");

            Do_MerchDtl Do_MerchDtl= new Do_MerchDtl();
            Do_MerchDtl.setBKY_PRDENUM("ECom");
            Do_MerchDtl.setFURL("https://demo.bookeey.com/portal/paymentfailure");
            Do_MerchDtl.setMerchUID("mer160009");
            Do_MerchDtl.setSURL("https://demo.bookeey.com/portal/paymentSuccess");




            Do_MoreDtl Do_MoreDtl = new Do_MoreDtl();
            Do_MoreDtl.setCust_Data1("");
            Do_MoreDtl.setCust_Data2("");
            Do_MoreDtl.setCust_Data3("");



            Do_WholeKnet whole_json = new Do_WholeKnet();

            whole_json.setDo_TxnDtl(Do_TxnDtl);
            whole_json.setDo_TxnHdr(Do_TxnHdr);
            whole_json.setDo_Appinfo( Do_Appinfo);
            whole_json.setDo_PyrDtl( Do_PyrDtl);

            whole_json.setDo_MerchDtl(Do_MerchDtl);

            whole_json.setDBRqst("PY_ECom");
            whole_json.setDo_MoreDtl(Do_MoreDtl);

            String whole_json_str = new Gson().toJson(whole_json);


            new AsyncTaskForKnet_CC_AMEX(whole_json_str).execute();



        }catch(Exception e){



            Toast.makeText(MainInitActivity.this,"Exception: "+e.getMessage(),Toast.LENGTH_LONG).show();

        }

    }




    private class AsyncTaskForKnet_CC_AMEX extends AsyncTask<String, String, String> {

        private String resp, bodyInput;


        public AsyncTaskForKnet_CC_AMEX(String bodyInput) {
            this.bodyInput = bodyInput;
        }

        @Override
        protected String doInBackground(String... params) {


            try {
                URL url = new URL("https://apps.bookeey.com/pgapi/api/payment/requestLink");

                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Content-Type", "application/json; utf-8");
                httpCon.setRequestProperty("Accept", "application/json");
                httpCon.setRequestProperty("x-api-key",
                        "9d6faae816329d7ee295305768802829ee5e1a4f28fd41e5a3d7b885c6864f7105ee227b67eefca1c4c86c69834acbf567b2bc1cb3be52058b976c3cdbd30473");
                httpCon.setDoOutput(true);
                OutputStream os = httpCon.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

                byte[] input = bodyInput.getBytes("utf-8");
                os.write(input, 0, input.length);


                osw.flush();
                osw.close();
                os.close();
                httpCon.connect();

                BufferedInputStream bis = new BufferedInputStream(httpCon.getInputStream());
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                int result2 = bis.read();
                while (result2 != -1) {
                    buf.write((byte) result2);
                    result2 = bis.read();
                }
                resp = buf.toString();


            } catch (Exception e) {

                Looper.prepare();

                Toast.makeText(MainInitActivity.this, "Exception: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {

            Intent i = new Intent(MainInitActivity.this, WebViewActivity.class);
            i.putExtra("KEY_RESULT", result);
            i.putExtra("KEY_MERCH_TITLE", merchantTitle);
            startActivityForResult(i, 10001);


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            if(data!=null) {
                String output_str = data.getStringExtra("OUTPUT");

                Toast.makeText(MainInitActivity.this, " Intermediate Screen\n\n " + output_str, Toast.LENGTH_LONG).show();


                Intent output = new Intent();
                output.putExtra("OUTPUT", data.getStringExtra("OUTPUT"));
                setResult(RESULT_OK, output);
                finish();
            }


        }else{

            if(data!=null) {

                String output_str = data.getStringExtra("OUTPUT");

                Toast.makeText(MainInitActivity.this, " Intermediate Screen\n\n  " + output_str, Toast.LENGTH_LONG).show();


                Intent output = new Intent();
                output.putExtra("OUTPUT", data.getStringExtra("OUTPUT"));
                setResult(RESULT_OK, output);
                finish();
            }

        }


    }

}
