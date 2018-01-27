package com.example.wonjun.testuniton.fragment;

/**
 * Created by wonjun on 2018. 1. 26..
 */

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.wonjun.testuniton.MainActivity;
import com.example.wonjun.testuniton.R;
import com.example.wonjun.testuniton.models.User;
import com.example.wonjun.testuniton.models.Response;
import com.example.wonjun.testuniton.network.NetworkUtil;
import com.example.wonjun.testuniton.utils.Validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ResetPasswordDialog extends DialogFragment {
    public interface Listener{
        void onPasswordReset(String message);
    }
    public static final String TAG = ResetPasswordDialog.class.getSimpleName();

    private EditText mEtEmail;
    private EditText mEtToken;
    private EditText mEtPassword;
    private Button mBtResetPassword;
    private TextView mTvMessage;
    private TextInputLayout mTiEmail;
    private TextInputLayout mTiToken;
    private TextInputLayout mTiPassword;
    private ProgressBar mProgressBar;

    private CompositeSubscription mSubscriptions;

    private String mEmail;

    private boolean isInit = true;

    private Listener mListner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reset_password,container,false);
        mSubscriptions = new CompositeSubscription();
        initViews(view);
        return view;
    }

    private void initViews(View v) {
        mEtEmail = (EditText) v.findViewById(R.id.et_email);
        mEtToken = (EditText) v.findViewById(R.id.et_token);
        mEtPassword = (EditText) v.findViewById(R.id.et_password);
        mBtResetPassword = (Button) v.findViewById(R.id.btn_reset_password);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress);
        mTvMessage = (TextView) v.findViewById(R.id.tv_message);
        mTiEmail = (TextInputLayout) v.findViewById(R.id.ti_email);
        mTiToken = (TextInputLayout) v.findViewById(R.id.ti_token);
        mTiPassword = (TextInputLayout) v.findViewById(R.id.ti_password);
        mBtResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInit)
                    resetPasswordInit();
                else
                    resetPasswordFinish();
            }
        });
    }
    public void setToken(String token) {

        mEtToken.setText(token);
    }

    private void resetPasswordFinish() {

        setEmptyFields();

        String token = mEtToken.getText().toString();
        String password = mEtPassword.getText().toString();
        int err = 0;
        if(!Validation.validateFields(token)){
            err++;
            mTiToken.setError("Token Should not be empty !");
        }
        if(!Validation.validateFields(password)){
            err++;
            mTiEmail.setError("Password Should not be empty !");
        }
        if(err == 0){
            mProgressBar.setVisibility(View.VISIBLE);
            User user = new User();
            user.setPassword(password);
            user.setToken(token);
            resetPasswordFinishProgress(user);
        }
    }

    private void resetPasswordFinishProgress(User user) {
        mSubscriptions.add(NetworkUtil.getRetrofit().resetPasswordFinish(mEmail, user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> handleResponse(response), error -> handleError(error)));
    }

    private void resetPasswordInit() {
        setEmptyFields();
        mEmail = mEtEmail.getText().toString();
        int err = 0;
        if(!Validation.validateEmail(mEmail)){
            err++;
            mTiEmail.setError("Email Should be Valid !");
        }
        if(err == 0){
            mProgressBar.setVisibility(View.VISIBLE);
            resetPasswordInitProgress(mEmail);
        }
    }

    private void resetPasswordInitProgress(String Email) {
        mSubscriptions.add(NetworkUtil.getRetrofit().resetPasswordInit(Email)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> handleResponse(response), error -> handleError(error)));
    }

    private void handleError(Throwable error) {
        mProgressBar.setVisibility(View.GONE);

        if (error instanceof HttpException) {

            Gson gson = new GsonBuilder().create();

            try {

                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody,Response.class);
                showMessage(response.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            showMessage("Network Error !");
        }
    }

    private void handleResponse(Response response) {
        mProgressBar.setVisibility(View.GONE);
        if(isInit){
            isInit = false;
            showMessage(response.getMessage());
            mTiEmail.setVisibility(View.GONE);
            mTiToken.setVisibility(View.VISIBLE);
            mTiPassword.setVisibility(View.VISIBLE);
        }else{
            mListner.onPasswordReset(response.getMessage());
            dismiss();
        }
    }

    private void showMessage(String message) {
        mTvMessage.setVisibility(View.VISIBLE);
        mTvMessage.setText(message);
    }

    private void setEmptyFields() {
        mTiEmail.setError(null);
        mTiToken.setError(null);
        mTiPassword.setError(null);
        mTvMessage.setText(null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListner = (MainActivity)context;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }
}
