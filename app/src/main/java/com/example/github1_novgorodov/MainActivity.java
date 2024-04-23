package com.example.github1_novgorodov;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<User> mContributors;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mContributors = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerV);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        ContributorAdapter adapter = new ContributorAdapter(mContributors);
        mRecyclerView.setAdapter(adapter);

        GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
        final Call<List<User>> call = gitHubService.getData("square", "picasso");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful())
                {
                    mContributors.addAll(response.body());

                    mRecyclerView.getAdapter().notifyDataSetChanged();
                }
                else
                {
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();

            }
        });



    }


}