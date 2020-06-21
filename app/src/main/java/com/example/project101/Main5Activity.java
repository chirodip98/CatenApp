package com.example.project101;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Main5Activity extends AppCompatActivity {
    ImageView imageView;
    Button bt1 , bt2;
    EditText ed1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        imageView=(ImageView)findViewById(R.id.img1);
        bt1=(Button) findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        ed1=(EditText)findViewById(R.id.ed1);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt=ed1.getText().toString();
                if(txt!=null && !txt.isEmpty())
                {
                    try {
                        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                        BitMatrix bitMatrix=multiFormatWriter.encode(txt,BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(Main5Activity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        final IntentResult intentResult=IntentIntegrator.parseActivityResult(requestcode,resultcode,data);
        if(intentResult!=null && intentResult.getContents()!=null)
        {
            new AlertDialog.Builder(Main5Activity.this).setTitle("Scan Results").setMessage(intentResult.getContents()).setPositiveButton("copy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ClipboardManager manager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                    ClipData clipData=ClipData.newPlainText("result",intentResult.getContents());
                    manager.setPrimaryClip(clipData);
                }
            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
        super.onActivityResult(requestcode, resultcode, data);
    }

}
