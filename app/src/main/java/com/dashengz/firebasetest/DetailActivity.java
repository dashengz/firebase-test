package com.dashengz.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class DetailActivity extends AppCompatActivity {

    Firebase userRef;
    EditText name;
    RadioGroup gender;
    EditText age;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        description = (EditText) findViewById(R.id.description);
        gender = (RadioGroup) findViewById(R.id.gender);

        Firebase myFirebaseRef = new Firebase(getResources().getString(R.string.firebase));

        Intent intent = getIntent();
        String myUid = intent.getStringExtra(Keys.INTENT_UID);

        userRef = myFirebaseRef.child("users").child(myUid);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue(User.class) != null) {
                    User user = snapshot.getValue(User.class);
                    name.setText(user.getName());
                    age.setText(String.valueOf(user.getAge()));
                    description.setText(user.getDescription());
                    ((RadioButton) findViewById(translateGender(user.getGender()))).setChecked(true);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            // save profile
            String n = name.getText().toString();
            String a = age.getText().toString();
            int g = gender.getCheckedRadioButtonId();
            String d = description.getText().toString();

            if (n.length() != 0 && g != -1 && a.length() != 0 && d.length() != 0) {
                User user = new User(n, translateGender(g), Integer.valueOf(a), d);
                userRef.setValue(user);
                Toast.makeText(DetailActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(DetailActivity.this,
                        "Please make sure you've entered all info!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_logout) {
            // logout
            userRef.unauth();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Translate resource id to gender type (or the other way around)
     * 0 Male, 1 Female, 2 Secret
     *
     * @param target Clicked RadioButton resource Id (or the gender int)
     * @return gender type int (or resId)
     */
    private int translateGender(int target) {
        switch (target) {
            case R.id.male:
                return 0;
            case R.id.female:
                return 1;
            case R.id.secret:
                return 2;
            case 0:
                return R.id.male;
            case 1:
                return R.id.female;
            case 2:
                return R.id.secret;
            default:
                throw new UnsupportedOperationException("Unsupported resId or gender type!");
        }
    }
}
