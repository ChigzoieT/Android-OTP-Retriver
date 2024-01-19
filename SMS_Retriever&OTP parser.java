package com.aud.opustester;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.aud.opustester.databinding.SmsRetrieverBinding;



public class SMS_Retriever extends AppCompatActivity{
    private final static int PERMISSION_REQUEST_CODE = 123;
    TextView updatesms;
    Button grabsms;
    public void onCreate(Bundle SavedInstances) {

        super.onCreate(SavedInstances);
        com.aud.opustester.databinding.SmsRetrieverBinding binding = SmsRetrieverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!checkpermission()){
            requestSMSpermission();
        }
        updatesms = binding.smsdata;
         grabsms= binding.click;

        grabsms.setOnClickListener(getclick);
    }

    public final View.OnClickListener getclick = v -> updatesms.setText(getsms());

    public String getsms(){
        String xyz = null;
        String nodata = "no sms found";
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
       if(cursor!=null && cursor.moveToFirst()){
           cursor.moveToFirst();
           if(cursor.getString(12)!=null){
             xyz = cursor.getString(12);
             String combo = parseotp(xyz);
             cursor.close();
             return combo;
           }
       }else{  return nodata;}
       return xyz;
    }

    private boolean checkpermission(){
        int  smschecker = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_SMS);
        return smschecker == PackageManager.PERMISSION_GRANTED;
    }

    private void requestSMSpermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                boolean smschecker = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if(smschecker){
                    Toast.makeText(this, "sms permission given", Toast.LENGTH_LONG).show();
                }else { Toast.makeText(this, "sms permission not given", Toast.LENGTH_LONG).show();}
        }
    }

    public String parseotp(String data){
        int[] myArray= {0,1,2 ,3,4,5,6,7,8,9,};
        char[] datastore = new char[4];

        for(int i = 0; i<data.length();i++){
            char chr = data.charAt(i);
            for (int j=0; i<10;i++){
                if(chr == myArray[j]){
                    for(int k= 0; k<4; k++){
                        myArray[j] = datastore[k];
                        if(k == 3){
                            datastore[k] = '\0';
                            break;
                        }
                    }
                }
            }
        }
    return new String(datastore);}
}