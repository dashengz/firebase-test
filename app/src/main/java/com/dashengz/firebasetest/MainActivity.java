package com.dashengz.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase(getResources().getString(R.string.firebase));
    }

    public void signUp(View v) {
        myFirebaseRef.createUser(getEmail(), getPassword(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Toast.makeText(MainActivity.this,
                        "Successfully created user account with uid: " + result.get("uid"),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(Keys.INTENT_UID, String.valueOf(result.get("uid")));
                startActivity(intent);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    public void logIn(View v) {
        myFirebaseRef.authWithPassword(getEmail(), getPassword(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(MainActivity.this,
                        "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(Keys.INTENT_UID, authData.getUid());
                startActivity(intent);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    private String getEmail() {
        EditText email = (EditText) findViewById(R.id.email);
        return email != null ? email.getText().toString() : "";
    }

    private String getPassword() {
        EditText password = (EditText) findViewById(R.id.password);
        return password != null ? password.getText().toString() : "";
    }
}
