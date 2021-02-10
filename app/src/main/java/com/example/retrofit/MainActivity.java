package com.example.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisilisation apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void getTodos(View view) {
        Call<List<Todo>> call = apiInterface.getTodos();
        //Enqueue is a async task
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.e(TAG, "onResponse" + response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getLocalizedMessage());
            }
        });
    }

    public void getTodoWithRouteParameter(View view) {

        Call<List<Todo>> todoCall = apiInterface.getTodoWithPar(2);
        todoCall.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.e(TAG, "onResponse" + response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getLocalizedMessage());

            }
        });

    }

    public void getTodoUsingQuery(View view) {
        Call<List<Todo>> listCall = apiInterface.getTodoUsingQuery(2, false);
        listCall.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.e(TAG, "onResponse" + response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getLocalizedMessage());
            }
        });
    }

    public void postTodo(View view) {
        Todo todo=new Todo(3,"Happy Always",false);
        Call<Todo> postTodo=apiInterface.postTodo(todo);
        postTodo.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.e(TAG, "onResponse" + response.body());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getLocalizedMessage());
            }
        });

    }
}