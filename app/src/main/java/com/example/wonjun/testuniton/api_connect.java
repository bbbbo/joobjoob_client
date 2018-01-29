package com.example.wonjun.testuniton;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



/**
 * Created by wonjun on 2018. 1. 28..
 */

public class api_connect {
//          예시
//    getData("strutive@gmail.com");
//    getAllProducts();
//    createRoom("5a6c926b53fc024018d7809a", "strutive07@gmail.com" , "의점", "20", "30",  a);
    String baseUrl = "http://52.79.41.31:3000/api/v1g1";
    String TAG = api_connect.class.getSimpleName();
    //email 로 가입되어있는 회왼 정보 리턴
    @SuppressLint("StaticFieldLeak")
    public  void getData() {
        final String[] resData = new String[1];
                new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground (Void...params){
                try {

                    final String urlResource = "/users_test";
                    String pathParam = "strutive07@gmail.com";
                    HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + urlResource).newBuilder();
//                    urlBuilder.addPathSegment(pathParam);
                    String email = ""; //설정
                    urlBuilder.addQueryParameter("email", email);
                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();

                    Gson gson = new Gson();
                    resData[0] = response.body().string();
                    Log.d("response", resData[0]);
                } catch (UnknownHostException | UnsupportedEncodingException e) {
                    Log.e(TAG, "Error: " + e.getLocalizedMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
                }
                return null;
            }
        }.execute();
    }



    //모든 Products 정보
    @SuppressLint("StaticFieldLeak")
    public void getAllProducts() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground (Void...params){
                try {

                    final String urlResource = "/product/getall";
                    HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + urlResource).newBuilder();
                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();

                    Gson gson = new Gson();
                    String resData = response.body().string();
                    Log.d("response", resData);

                } catch (UnknownHostException | UnsupportedEncodingException e) {
                    Log.e(TAG, "Error: " + e.getLocalizedMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
                }
                return null;
            }
        }.execute();
    }


    //하나의 Products 정보
    @SuppressLint("StaticFieldLeak")
    public void getOneProducts() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground (Void...params){
                try {
                    final String urlResource = "/product/getone";
                    HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + urlResource).newBuilder();
                    String product_id = "";  //초기화
                    urlBuilder.addQueryParameter("product_id", product_id);
                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();

                    Gson gson = new Gson();
                    String resData = response.body().string();
                    Log.d("response", resData);

                } catch (UnknownHostException | UnsupportedEncodingException e) {
                    Log.e(TAG, "Error: " + e.getLocalizedMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
                }
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getOneRoom() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground (Void...params){
                try {
                    final String urlResource = "/room/getone";
                    HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + urlResource).newBuilder();
                    String product_id = "";  //초기화
                    urlBuilder.addQueryParameter("product_id", product_id);
                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();

                    Gson gson = new Gson();
                    String resData = response.body().string();
                    Log.d("response", resData);

                } catch (UnknownHostException | UnsupportedEncodingException e) {
                    Log.e(TAG, "Error: " + e.getLocalizedMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
                }
                return null;
            }
        }.execute();
    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    String post(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    @SuppressLint("StaticFieldLeak")
    public void joinRoom(String product_id, String room_id, String email, ArrayList<Integer> arr) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final String urlResource = "/room/join";

                    HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + urlResource).newBuilder();

                    String url = urlBuilder.build().toString();

                    RequestBody body = new FormBody.Builder()
                            .add("product_id", product_id)
                            .add("room_id", room_id)
                            .add("email", email)
                            .add("g_gcount", arr.toString()).build();

                    Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();

                    Gson gson = new Gson();
                    String resData = response.body().string();
                    Log.d("response", resData);

                } catch (UnknownHostException | UnsupportedEncodingException e) {
                    Log.e(TAG, "Error: " + e.getLocalizedMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
                }
                return null;
            }
        }.execute();
    }
    @SuppressLint("StaticFieldLeak")
    public void createRoom(String product_id, String email, String convenience_name, String convenience_lat, String convenience_lng, ArrayList<Integer> arr) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground (Void...params){
                try {
                    final String urlResource = "/room/create";

                    HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + urlResource).newBuilder();

                    String url = urlBuilder.build().toString();

                    RequestBody body = new FormBody.Builder()
                            .add("product_id", product_id)
                            .add("email", email)
                            .add("convenience_name", convenience_name)
                            .add("convenience_lat", convenience_lat)
                            .add("convenience_lng", convenience_lng)
                            .add("g_gcount" , arr.toString()).build();

                    Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();

                    Gson gson = new Gson();
                    String resData = response.body().string();
                    Log.d("response", resData);

                } catch (UnknownHostException | UnsupportedEncodingException e) {
                    Log.e(TAG, "Error: " + e.getLocalizedMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
                }
                return null;
            }
        }.execute();

    }
}
