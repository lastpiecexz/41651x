package com.itfollowme.njcit01;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class LoginActivity extends Activity {

    private EditText mEtUsername;
    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
    }

    public void login(View view) {
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApi.URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

        ZhihuApi zhihuApi = retrofit.create(ZhihuApi.class);

        Call<String> call = zhihuApi.login(username, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zhihu",".........");
                String str = response.body();
                Headers headers = response.headers();
                Iterator iterator = headers.names().iterator();
                while(iterator.hasNext()){
                    String header =  iterator.next().toString();
                    Log.i("zhihu",header+":"+headers.get(header));
                }
                Toast.makeText(LoginActivity.this,str,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("zhihu","===.");
                Log.i("zhihu",call.toString());
                Log.i("zhihu",t.toString());
                Toast.makeText(LoginActivity.this,"错误",Toast.LENGTH_LONG).show();
            }
        });

    }
}
