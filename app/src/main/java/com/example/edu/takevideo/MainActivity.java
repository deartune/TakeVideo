package com.example.edu.takevideo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button videoViewFromGalleryButton = (Button) findViewById(R.id.videoViewFromGalleryButton);
        Button takeVideo = (Button) findViewById(R.id.takeVideo);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoViewFromGalleryButton.setOnClickListener(this);
        takeVideo.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.videoViewFromGalleryButton:
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 102);
        break;

        case R.id.takeVideo:
                if(getPackageManager()

                        .hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){

                    Intent intent1 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(intent1, 201);
                }
                break;
        }
        }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 102:
                if (data != null) {
                    String videoPath = data.getData().toString();
                    videoView.setVideoPath(videoPath);
                    videoView.start();
                }
            break;
            case 201:
                if (resultCode == RESULT_OK) {
                    Uri videoUri = data.getData();
                    Toast.makeText(this, "비디오가 나타납니다" + videoUri, Toast.LENGTH_SHORT).show();
                    videoView.setVideoURI(videoUri);
                    videoView.start();
                }
           break;
        }
}}