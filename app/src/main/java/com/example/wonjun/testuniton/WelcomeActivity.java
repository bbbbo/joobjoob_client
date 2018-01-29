package com.example.wonjun.testuniton;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_next:
                Intent intent = new Intent(WelcomeActivity.this, CommentActivity.class);
                startActivity(intent);
                break;

        }
    }
}
