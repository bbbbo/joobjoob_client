package com.example.wonjun.testuniton;

import android.os.AsyncTask;
import android.support.v4.app.*;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity
{

    boolean isPageOpen = false;
    EditText editText;
    //    LinearLayout topLayout;
    RelativeLayout searchLayout;
    FrameLayout filterLayout;
    LinearLayout categoryLayout;
    FrameLayout listLayout;
    LinearLayout slidingPage01;

    private RecyclerView rv;
    private NoticeAdapter noticeAdapter;
//    SeekBar seekBar;

    ImageView searchButton;
    ImageButton sideButton,sideButton2;

//    Animation translateLeftAnim;
    //슬라이드 닫기 애니메이션
//    Animation translateRightAnim;


    String baseUrl = "http://52.79.41.31:3000/api/v1g1";
    private static final String TAG = "test";

    int progressValue;

    ListView sideList;
    //MyListAdapter myListAdapter;
    String[] names = {"줍줍 방만들기" , "My Page" , "Logout" , "FAQ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


//        editText = (EditText) findViewById(R.id.editText);
//        topLayout = (LinearLayout) findViewById(R.id.topLayout);
        searchLayout = (RelativeLayout) findViewById(R.id.searchLayout);
//        filterLayout = (FrameLayout) findViewById(R.id.filterLayout);
        categoryLayout = (LinearLayout) findViewById(R.id.categoryLayout);
        listLayout = (FrameLayout) findViewById(R.id.listLayout);
//        slidingPage01 = (RelativeLayout)findViewById(R.id.slidingPage01);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });


        rv = (RecyclerView) findViewById(R.id.rv);
//        noticeAdapter = new NoticeAdapter(getBaseContext());
        rv.setAdapter(noticeAdapter);



        FrameLayout fl1 = (FrameLayout) findViewById(R.id.fl1);
        FrameLayout fl2 = (FrameLayout) findViewById(R.id.fl2);
        FrameLayout fl3 = (FrameLayout) findViewById(R.id.fl3);
        fl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
                Log.e("fl","ff");
            }
        });

        fl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("fl","ff");
            }
        });

        fl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("fl","ff");
            }
        });

//        getData(baseUrl);
//        seekBar = (SeekBar) findViewById(R.id.seekBar);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                //textView.setText("설정된 값: " + progress);
//                progressValue = progress;
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

        searchButton = (ImageView)findViewById(R.id.searchButton);
//        sideButton = (ImageButton)findViewById(R.id.sideButton);
//        sideButton2 = (ImageButton)findViewById(R.id.sideButton2);

//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.listLayout, new ListFragment())
//                .commit();


        //애니메이션
//        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
//        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        //애니메이션 리스너 설정
//        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
//        translateLeftAnim.setAnimationListener(animationListener);
//        translateRightAnim.setAnimationListener(animationListener);

//        sideList = (ListView)findViewById(R.id.sideList);
        //myListAdapter = new MyListAdapter(MainActivity.this,list_itemArrayList);
        // sideList.setAdapter(myListAdapter);

//        sideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    public void onSearchButtonClicked(View v) {

    }

    public void onSideButtonClicked(View v) {
        //if(isPageOpen){
        //애니메이션 시작
        //slidingPage01.startAnimation(translateLeftAnim);
        // }
        //열기
        // else{
        slidingPage01.setVisibility(View.VISIBLE);
//           slidingPage01.startAnimation(translateRightAnim);
        //  }

    }

    public void onSideButton2Clicked(View v) {
//        slidingPage01.startAnimation(translateLeftAnim);
    }


//    public void getData(String url) {
//        class GetDataJSON extends AsyncTask<Object, Object, Void> {
//            @Override
//            protected Void doInBackground(Object... params) {
//                try {
//
//                    final String urlResource = "/users_test";
//                    String pathParam = "strutive07@gmail.com";
//                    HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + urlResource).newBuilder();
////                    urlBuilder.addPathSegment(pathParam);
//                    urlBuilder.addQueryParameter("email", "strutive07@gmail.com");
//                    String url = urlBuilder.build().toString();
//
//                    Request request = new Request.Builder()
//                            .url(url)
//                            .build();
//                    OkHttpClient client = new OkHttpClient();
//                    Response response = client.newCall(request).execute();
//
//                    Gson gson = new Gson();
//                    String resData = response.body().string();
//                    Log.d("response", resData);
//                    return null;
//
//
//                } catch (UnknownHostException | UnsupportedEncodingException e) {
//                    Log.e(TAG, "Error: " + e.getLocalizedMessage());
//                } catch (Exception e) {
//                    Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
//                }
//                return null;
//            }
//        }
//    }

    //애니메이션 리스너
//    private class SlidingPageAnimationListener implements Animation.AnimationListener {
//        @Override
//        public void onAnimationEnd(Animation animation) {
//            //슬라이드 열기->닫기
//            if(isPageOpen){
//                slidingPage01.setVisibility(View.GONE);
//                //sideButton.setText("Open");
//                isPageOpen = false;
//                sideButton2.setEnabled(false);
//
//            }
//            //슬라이드 닫기->열기
//            else{
//                //sideButton.setText("Close");
//                isPageOpen = true;
//                sideButton2.setEnabled(true);
//            }
//        }
//        @Override
//        public void onAnimationRepeat(Animation animation) {
//
//        }
//        @Override
//        public void onAnimationStart(Animation animation) {
//
//        }
//    }


}