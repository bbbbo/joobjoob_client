package com.example.wonjun.testuniton.fragment;

/**
 * Created by wonjun on 2018. 1. 26..
 */

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.wonjun.testuniton.network.NetworkUtil;
import com.example.wonjun.testuniton.R;
import com.example.wonjun.testuniton.models.Response;
import com.example.wonjun.testuniton.models.User;
import com.example.wonjun.testuniton.utils.Validation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
public class RegisterFragmet extends Fragment {
    public static final String TAG = RegisterFragmet.class.getSimpleName();

    private EditText mEtName;
    private EditText mEtEmail;
    private EditText mEtPassword;
    private Button   mBtRegister;
    private TextView mTvLogin;
    private TextInputLayout mTiName;
    private TextInputLayout mTiEmail;
    private TextInputLayout mTiPassword;
    private ProgressBar mProgressbar;

    private CompositeSubscription mSubscriptions;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        mSubscriptions = new CompositeSubscription();
        initViews(view);
        return view;
    }

    private void initViews(View v){
        mEtName = (EditText) v.findViewById(R.id.et_name);
        mEtEmail = (EditText) v.findViewById(R.id.et_email);
        mEtPassword = (EditText) v.findViewById(R.id.et_password);
        mBtRegister = (Button) v.findViewById(R.id.btn_register);
        mTvLogin = (TextView) v.findViewById(R.id.tv_login);
        mTiName = (TextInputLayout) v.findViewById(R.id.ti_name);
        mTiEmail = (TextInputLayout) v.findViewById(R.id.ti_email);
        mTiPassword = (TextInputLayout) v.findViewById(R.id.ti_password);
        mProgressbar = (ProgressBar) v.findViewById(R.id.progress);

        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
    }

    private void register() {
        setError();
        String name = mEtName.getText().toString();
        String email = mEtEmail.getText().toString();
        String password = mEtPassword.getText().toString();

        int err = 0;
        if(!Validation.validateFields(name)){
            err++;
            mTiName.setError("Name should not be empty !");
        }

        if(!Validation.validateEmail(email)){
            err++;
            mTiName.setError("Email should not be empty !");
        }

        if(!Validation.validateFields(password)){
            err++;
            mTiName.setError("Password should not be empty !");
        }
        if(err == 0){
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);

            mProgressbar.setVisibility(View.VISIBLE);
            registerProcess(user);
        }
    }

    private void registerProcess(User user) {
        mSubscriptions.add(NetworkUtil.getRetrofit().register(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> handleResponse(response), error -> handleError(error)));
    }
    private void handleError(Throwable error) {
        mProgressbar.setVisibility(View.GONE);

        if (error instanceof HttpException) {

            Gson gson = new GsonBuilder().create();

            try {

                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody,Response.class);
                showSnackBarMessage(response.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            showSnackBarMessage("Network Error !");
        }
    }



    private void handleResponse(Response response) {
        mProgressbar.setVisibility(View.GONE);
        showSnackBarMessage(response.getMessage());
    }
    private void showSnackBarMessage(String message) {
        if(getView() != null){
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void goToLogin() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        LoginFragment fragment = new LoginFragment();
        ft.replace(R.id.fragmentFrame, fragment, LoginFragment.TAG);
        ft.commit();

    }

    private void setError() {

        mTiName.setError(null);
        mTiEmail.setError(null);
        mTiPassword.setError(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }
}
