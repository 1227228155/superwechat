package com.hyphenate.chatuidemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hyphenate.chatuidemo.R;

public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        super.onCreate(savedInstanceState);
    }

    public void register(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }
}
