package com.example.wonjun.testuniton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_next:
                Intent intent = new Intent(CommentActivity.this, mapActivity.class);
                startActivity(intent);
                break;
        }
    }
}
