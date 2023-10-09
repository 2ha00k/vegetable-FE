package com.example.vegetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnPhoto;
    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 각 버튼을 레이아웃 XML의 ID와 연결
        btnLogin = findViewById(R.id.btn_login);
        btnPhoto = findViewById(R.id.btn_photo);
        btnInfo = findViewById(R.id.btn_info);

        // 로그인 버튼 클릭 시 LoginActivity로 이동
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // 사진 인식 버튼 클릭 시 PhotoActivity로 이동
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(intent);
            }
        });

        // 공지사항 버튼 클릭 시 InfoActivity로 이동
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
    }
}