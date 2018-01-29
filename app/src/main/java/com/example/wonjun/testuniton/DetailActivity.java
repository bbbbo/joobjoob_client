package com.example.wonjun.testuniton;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    BottomSheetDialog bottomSheetDialog;
    int cnt = 1;
    private TextView tv_cnt;
    private ImageButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        floatButton = (ImageButton)findViewById(R.id.btn_buy);
        floatButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(DetailActivity.this);
                View parentView = getLayoutInflater().inflate(R.layout.dialog_item, null);
                bottomSheetDialog.setContentView(parentView);
                tv_cnt = (TextView)parentView.findViewById(R.id.tv_cnt);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300.f, getResources().getDisplayMetrics()));
                bottomSheetDialog.show();
            }
        });


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_up:
                cnt++;
                break;
            case R.id.btn_down:
                cnt--;
                if(cnt == 0){
                    Toast.makeText(DetailActivity.this, "수량은 1이 최소입니다!", Toast.LENGTH_SHORT).show();
                    cnt = 1;
                }
                break;
        }
        Log.v("Detail", String.valueOf(cnt));

        tv_cnt.setText(String.valueOf(cnt));
        bottomSheetDialog.show();

    }

    public void beforeClick(View v){
        switch (v.getId()){
            case R.id.btn_before:
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }



}


