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

        binding = ActivityPhotoProxyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_photo_proxy);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        Intent sourceIntent = this.getIntent();
        Log.i(TAG, "Received intent:" + sourceIntent.toUri(0));
        sourceIntent.setPackage("");
        Log.i(TAG, "Modified intent:" + sourceIntent.toUri(0));
        //this.startActivity(intent);
        Intent targetIntent = new Intent();
        targetIntent.setData(sourceIntent.getData());
        targetIntent.setAction(sourceIntent.getAction());
        targetIntent.putExtras(sourceIntent);
        targetIntent.setFlags(sourceIntent.getFlags());
        Log.i(TAG, "Own intent:" + targetIntent.toUri(0));
        this.startActivity(targetIntent);
        this.finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_photo_proxy);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}