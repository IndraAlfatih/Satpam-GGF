package com.ggf.qcpp.k_historylocation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ggf.qcpp.R;

import java.io.IOException;

public class HistoryLocation_Form extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1000;

    private LinearLayout stateBefore, stateUploading, stateComplete;
    private ProgressBar progressBar;
    private TextView txtProgress, fileName, clearUpload;
    private ImageView imagePreview;

    private Uri imageUri;
    private boolean isUploading = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_location_form);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupActions();
        showBeforeUpload();
    }

    private void initViews() {
        stateBefore = findViewById(R.id.stateBefore);
        stateUploading = findViewById(R.id.stateUploading);
        stateComplete = findViewById(R.id.stateComplete);

        progressBar = findViewById(R.id.progressBar);
        txtProgress = findViewById(R.id.txtProgress);
        fileName = findViewById(R.id.fileName);
        clearUpload = findViewById(R.id.clearUpload);

        imagePreview = findViewById(R.id.previewImage);
    }

    private void setupActions() {
        findViewById(R.id.uploadBox).setOnClickListener(v -> openPicker());
        clearUpload.setOnClickListener(v -> showBeforeUpload());
    }

    private void openPicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            if (imageUri != null) {
                startDemoUpload();
            }
        }
    }

    private void startDemoUpload() {
        isUploading = true;
        showUploadingState();

        progressBar.setProgress(0);
        txtProgress.setText("Uploading...");

        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                int progress = i;
                handler.post(() -> {
                    progressBar.setProgress(progress);
                    txtProgress.setText(progress + "%");
                });

                try { Thread.sleep(20); } catch (Exception ignored) {}

                if (progress == 100) {
                    handler.post(this::uploadCompleted);
                }

                if (!isUploading) return;
            }
        }).start();
    }

    private void uploadCompleted() {
        showCompleteUploadState();
        fileName.setText("image_uploaded.jpg");

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            imagePreview.setImageBitmap(bitmap);
            imagePreview.setVisibility(View.VISIBLE); // ✅ Tampilkan Preview setelah upload selesai
        } catch (IOException e) {
            e.printStackTrace();
        }

        isUploading = false;
    }

    private void showBeforeUpload() {
        isUploading = false;

        stateBefore.setVisibility(View.VISIBLE);
        stateUploading.setVisibility(View.GONE);
        stateComplete.setVisibility(View.GONE);

        imagePreview.setVisibility(View.GONE); // ✅ Sembunyikan ketika reset
    }

    private void showUploadingState() {
        stateBefore.setVisibility(View.GONE);
        stateUploading.setVisibility(View.VISIBLE);
        stateComplete.setVisibility(View.GONE);

        imagePreview.setVisibility(View.GONE); // ✅ Pastikan preview tidak muncul saat upload
    }

    private void showCompleteUploadState() {
        stateBefore.setVisibility(View.GONE);
        stateUploading.setVisibility(View.GONE);
        stateComplete.setVisibility(View.VISIBLE);

        imagePreview.setVisibility(View.VISIBLE); // ✅ Saat finish, tampilkan image
    }

    @Override
    public void onBackPressed() {
        if (isUploading || stateComplete.getVisibility() == View.VISIBLE) {
            showBeforeUpload();
        } else {
            super.onBackPressed();
        }
    }
}
