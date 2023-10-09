package com.example.vegetableapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;

    private ImageView imageView;
    private Button btnYes;
    private Button btnNo;
    private Button btnCamera, btnAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView = findViewById(R.id.imageView);
        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);
        btnCamera = findViewById(R.id.btn_camera);
        btnAlbum = findViewById(R.id.btn_album);

        // 초기에는 imageView와 btnYes, btnNo를 숨김
        imageView.setVisibility(View.GONE);
        btnYes.setVisibility(View.GONE);
        btnNo.setVisibility(View.GONE);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePhoto(v);
                // 버튼 숨기기
                btnCamera.setVisibility(View.GONE);
                btnAlbum.setVisibility(View.GONE);
                // 이미지뷰와 "예" 및 "아니요" 버튼 표시
                imageView.setVisibility(View.VISIBLE);
                btnYes.setVisibility(View.VISIBLE);
                btnNo.setVisibility(View.VISIBLE);
            }
        });

        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhotoFromGallery(v);
                // 버튼 숨기기
                btnCamera.setVisibility(View.GONE);
                btnAlbum.setVisibility(View.GONE);
                // 이미지뷰와 "예" 및 "아니요" 버튼 표시
                imageView.setVisibility(View.VISIBLE);
                btnYes.setVisibility(View.VISIBLE);
                btnNo.setVisibility(View.VISIBLE);
            }
        });


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // "예" 버튼을 눌렀을 때의 동작 구현
                // 선택한 사진을 서버로 업로드하거나 처리
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                btnYes.setVisibility(View.GONE);
                btnNo.setVisibility(View.GONE);
                btnCamera.setVisibility(View.VISIBLE);
                btnAlbum.setVisibility(View.VISIBLE);
            }
        });
    }

    public void capturePhoto(View view) {
        // 카메라 앱 호출하여 사진 촬영
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void pickPhotoFromGallery(View view) {
        // 갤러리 앱 호출하여 사진 선택
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA && data != null) {
                // 카메라로 촬영한 사진 처리
                Bundle extras = data.getExtras();
                Bitmap photo = (Bitmap) extras.get("data");
                imageView.setImageBitmap(photo);

                // 이미지뷰 및 "예" 및 "아니요" 버튼 표시
                imageView.setVisibility(View.VISIBLE);
                btnYes.setVisibility(View.VISIBLE);
                btnNo.setVisibility(View.VISIBLE);
            } else if (requestCode == REQUEST_GALLERY && data != null) {
                // 갤러리에서 선택한 사진 처리
                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);

                // 이미지뷰 및 "예" 및 "아니요" 버튼 표시
                imageView.setVisibility(View.VISIBLE);
                btnYes.setVisibility(View.VISIBLE);
                btnNo.setVisibility(View.VISIBLE);
            }
        }
    }
}

