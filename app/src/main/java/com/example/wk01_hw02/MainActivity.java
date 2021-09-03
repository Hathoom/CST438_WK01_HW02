package com.example.wk01_hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button button;

    private EditText usernameInput;
    private EditText passwordInput;
    private TextView mainText;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private List<String> usernames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        mainText = findViewById(R.id.main_Text);

        usernames.add("Bret");
        usernames.add("Antonette");
        usernames.add("Samantha");
        usernames.add("Karianne");
        usernames.add("Kamren");
        usernames.add("Leopoldo_Corkery");
        usernames.add("Elwyn.Skiles");
        usernames.add("Maxime_Nienow");
        usernames.add("Delphine");
        usernames.add("Moriah.Stanton");

        // will prevent Patch from ignoring null
        Gson gson = new GsonBuilder().serializeNulls().create();

        //if you add v3 to baseurl has a backslash: /v3/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity();
            }
        });

        //getUsers();
    }

    public void loginActivity() {
        Intent intent = new Intent(this, LandingActivity.class);
        String input_username = usernameInput.getText().toString();

        String input_password = passwordInput.getText().toString();

        boolean user_matching = false;
        boolean password_matching = input_password.equals("password");
        int index = -1;

        usernameInput.setTextColor(Color.rgb(0, 0, 0));
        passwordInput.setTextColor(Color.rgb(0, 0, 0));

        for (String username : usernames)
        {
            if (username.equals(input_username))
            {
                user_matching = true;
                index = usernames.indexOf(username);
            }
        }

        if (!user_matching)
        {
            usernameInput.setTextColor(Color.rgb(200, 0, 0));
        }
        if (!password_matching)
        {
            passwordInput.setTextColor(Color.rgb(200, 0, 0));
        }

        if (user_matching && password_matching)
        {
            index = index + 1;
            intent.putExtra("userId", index);

            Toast.makeText(this, "Welcome " + input_username, Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Incorrect Username or Password, please check red text", Toast.LENGTH_LONG).show();
        }

    }

    public void getUsers() {

        Call<List<User>> call = jsonPlaceHolderApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    mainText.setText("Code: " + response.code());
                    return;
                }

                List<User> users = response.body();

                //Log.d("test", response.body().toString());

//                for (User user : users)
//                {
//                    usernames.add(user.getuserName());
//                    userIds.add(user.getId());
//                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mainText.setText(t.getMessage());
            }
        });
    }
}