package com.dei.ceo;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class PermissionActivity extends AppCompatActivity {
    ViewPager pager;
    SharedPreferences pmsp;
    SharedPreferences.Editor pmedit;
    SharedPreferences losp;
    SharedPreferences.Editor loedit;

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(PermissionActivity.this, "권한이 허가되었습니다.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PermissionActivity.this, MainActivity.class));
            finish();
        LoginActivity MA = (LoginActivity)LoginActivity.AActivity;
        MA.finish();

    }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(PermissionActivity.this, "권한이 거부되었습니다.\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_permission);



    }



    public void commit(View v) {

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("모든기능을 사용하기 위한 권한동의입니다.")
                .setDeniedMessage("거부되었습니다. \n [설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                .setPermissions(Manifest.permission.CALL_PHONE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE )
                .check();



    }

}





