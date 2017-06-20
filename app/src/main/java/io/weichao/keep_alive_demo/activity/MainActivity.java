package io.weichao.keep_alive_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.weichao.keep_alive_demo.R;
import io.weichao.keep_alive_demo.service.JobHandlerService;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openJobService();
    }

    private void openJobService() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, JobHandlerService.class);
        startService(intent);
    }
}