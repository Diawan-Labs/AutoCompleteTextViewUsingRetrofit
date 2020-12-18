package com.example.mydemos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final String TAG=MainActivity.class.getSimpleName();
    AutoCompleteTextView autoCompleteTextView;
    String[] countries=new String[]{"India","pakistan","Inodnesia"," America","Africa","Afganisthan"};
    List<String> listOfItems=new ArrayList<>();
    AutoSuggestAdapter autoSuggestAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView=findViewById(R.id.autoCompleteTextView);

        autoSuggestAdapter=new AutoSuggestAdapter(this, android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(autoSuggestAdapter);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showOutput(autoCompleteTextView.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s){
            }
        });
    }

    private void showOutput(String s) {
        Log.d(TAG,"inside showOutput:"+s);
        ApiNames apiNames=ApiClass.getRetrofit().create(ApiNames.class);
        Call<List<String>> request=apiNames.getNames(s);
        request.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.body() != null) {
                    listOfItems=response.body();
                    Log.d(TAG, "onResponse: "+response.body());
                    autoSuggestAdapter.setData(listOfItems);
                    autoSuggestAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });

    }


}