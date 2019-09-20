package com.example.certifyscanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.txtContent);

        isCameraPermissionGranted();

    }
    public void setScanBarcode(View view){
        Intent intent=new Intent(this,BarcodeCaptureActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==0){
            if (requestCode== CommonStatusCodes.SUCCESS){
                if (data!=null){
                    Barcode barcode=data.getParcelableExtra("barcode");
                    String bhai_value = barcode.displayValue;
                  textView.setText("Barcode Value:"+bhai_value);
                    Toast.makeText(this,bhai_value,Toast.LENGTH_SHORT).show();
                }else{
                   textView.setText("No barcode captured");

                }
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    private boolean isCameraPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(this, "PERMISSION RE_GRANTED", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
                return false;
            }
        }
            else{
                Toast.makeText(this, "OS is not supported", Toast.LENGTH_SHORT).show();
                return true;

            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){

        }
    }

}
