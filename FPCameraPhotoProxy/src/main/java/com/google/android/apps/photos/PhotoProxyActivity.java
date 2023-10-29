package com.google.android.apps.photos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.apps.photos.databinding.ActivityPhotoProxyBinding;

public class PhotoProxyActivity extends AppCompatActivity {

    private static final String TAG = PhotoProxyActivity.class.getCanonicalName();
    private AppBarConfiguration appBarConfiguration;
    private ActivityPhotoProxyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent sourceIntent = this.getIntent();
        if (!sourceIntent.getAction().equals("android.intent.action.MAIN")) {
            Log.i(TAG, "Received intent:" + sourceIntent.toUri(0));
            Intent targetIntent = new Intent();
            targetIntent.setData(sourceIntent.getData());
            // FP stock camera uses action 'com.android.camera.action.REVIEW' which seems to be wrong.
            // Hence, we change it to the default.
            // Source: https://forum.fairphone.com/t/stock-camera-app-crashes-when-clicking-on-gallery-button-with-google-photos-uninstalled/83281/2
            targetIntent.setAction(Intent.ACTION_VIEW);
            targetIntent.putExtras(sourceIntent);
            targetIntent.setFlags(sourceIntent.getFlags());
            Log.i(TAG, "Own intent:" + targetIntent.toUri(0));
            this.startActivity(targetIntent);
            this.finish();
        } else {
            Log.e(TAG, "Starting this app directly is current not supported.");
            this.finish();

            binding = ActivityPhotoProxyBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            setSupportActionBar(binding.toolbar);

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_photo_proxy);
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_photo_proxy);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}