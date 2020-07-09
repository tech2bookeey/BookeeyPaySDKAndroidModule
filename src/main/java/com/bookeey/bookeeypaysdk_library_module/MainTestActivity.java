package com.bookeey.bookeeypaysdk_library_module;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        String amount = "0.100";
        String merchUID = "mer160009";


//        String payMethod = "KNET";
//        String payMethod = "CCARD";
        String payMethod = "AEXP";

        String tarck_txn_UID = "3" + System.currentTimeMillis();
        String merchantTitle = "Talabat";

        Intent i = new Intent(MainTestActivity.this, MainInitActivity.class);
        i.putExtra("KEY_AMOUNT", amount);
        i.putExtra("KEY_MERCH_UID", merchUID);
        i.putExtra("KEY_PAY_METHOD", payMethod);
        i.putExtra("KEY_TRACK_TXN_UID", tarck_txn_UID);
        i.putExtra("KEY_MERCH_TITLE", merchantTitle);
        startActivityForResult(i, 10001);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Toast.makeText(MainTestActivity.this, " Main Screen\n\n" + data.getStringExtra("OUTPUT"), Toast.LENGTH_LONG).show();
        }else{


            Toast.makeText(MainTestActivity.this, " Main Screen\n\n" + data.getStringExtra("OUTPUT"), Toast.LENGTH_LONG).show();

        }


    }
}
