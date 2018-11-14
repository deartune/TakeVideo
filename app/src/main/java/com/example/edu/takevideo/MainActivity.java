package com.example.edu.takevideo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button videoViewFromGalleryButton = (Button) findViewById(R.id.videoViewFromGalleryButton);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoViewFromGalleryButton.setOnClickListener(this);

    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 102);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String videoPath = data.getData().toString();
            videoView.setVideoPath(videoPath);
            videoView.start();
        }
    }
}