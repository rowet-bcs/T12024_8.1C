// This task was completed with assistance from
// https://github.com/sit3057082024/T-8.1C
// https://square.github.io/retrofit/
// https://square.github.io/okhttp/
// https://www.geeksforgeeks.org/how-to-post-data-to-api-using-retrofit-in-android/
// https://www.digitalocean.com/community/tutorials/retrofit-android-example-tutorial
// https://stackoverflow.com/questions/3606530/listview-scroll-to-the-end-of-the-list-after-updating-the-list
// https://stackoverflow.com/questions/1964789/move-layouts-up-when-soft-keyboard-is-shown
// https://developer.android.com/reference/java/util/Dictionary
// https://www.geeksforgeeks.org/how-to-add-a-textview-with-rounded-corner-in-android/

package com.example.task81c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Initialise variables
    Intent chatBot;
    EditText enterUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Link variables to UI elements
        enterUsername = findViewById(R.id.enterUsername);
    }

    public void login(View view){
        // Retrieve values of entry fields
        String username = enterUsername.getText().toString();

        // Check if a name is been entered
        if (username.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_LONG).show();
        } else {
            // Launch chatbot activity
            chatBot = new Intent(this, ChatBot.class);
            chatBot.putExtra("username", username);
            startActivity(chatBot);
        }
    }

    public void closeApp(View view){
        // Close app
        finish();
    }
}