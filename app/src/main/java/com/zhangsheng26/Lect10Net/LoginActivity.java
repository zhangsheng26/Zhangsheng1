package com.zhangsheng26.Lect10Net;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.zhangsheng26.Lect10Net.list.NetListActivity;
import com.zs26app.R;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_EDT=10;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private CustomVideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        videoview = findViewById(R.id.videoview);
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.mipmap.weixin));
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });
        Button button1 = (Button) findViewById(R.id.edt_commit);
        initView();
        Map<String,String> userInfo = NetFileSave.getUserInfo(this);
        if (userInfo != null){
            etUsername.setText(userInfo.get("Username"));
            etPassword.setText(userInfo.get("password"));
        }
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.edt_name);
        etPassword = (EditText) findViewById(R.id.edt_pwd);
        btnLogin = (Button) findViewById(R.id.edt_commit);
        btnLogin.setOnClickListener(this);
    }
    @Override
    public void onClick (View v) {
        String  username= etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //否则登录成功
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        //保存信息
        boolean isSaveSuccess = NetFileSave.saveUserInfo(this, username, password);
        if (isSaveSuccess) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(LoginActivity.this, NetListActivity.class);
        startActivityForResult(intent, REQUEST_CODE_EDT);
        intent.putExtra("name", username);
        intent.putExtra("pwd", Integer.valueOf(password));
        setResult(RESULT_OK, intent);
        finish();
    }
}
