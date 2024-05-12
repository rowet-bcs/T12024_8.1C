package com.example.task81c;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class ChatBot extends AppCompatActivity {
    // Initialise variables
    Chat chat = new Chat("");
    ChatResponse responseFromAPI;
    String responseString;
    ListAdapter listAdapter;
    ListView chatList;
    EditText enterMessage;
    TextView welcomeText;
    String currentUser;

    // Create retrofit interface for chat POST call
    interface RetrofitAPI {
        @POST("chat")
        Call<ChatResponse> sendChat(@Body Chat chat);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_bot);

        // Link variables to UI elements
        enterMessage = findViewById(R.id.enterMessage);
        chatList = findViewById(R.id.chatList);
        welcomeText = findViewById(R.id.welcomeText);

        // Get username passed from login
        currentUser = getIntent().getStringExtra("username");

        // Set welcome text
        welcomeText.setText("Welcome " + currentUser + "!");

        // Set layout to adjust around onscreen keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Set list view to display chat history
        setAdapter();
    }

    public void sendMessage(View view){
        // Retrieve entered message
        String newMessage = enterMessage.getText().toString();

        // Check if a new message has been entered
        if (!newMessage.isEmpty()) {
            // Set message in chat object
            chat.setUserMessage(newMessage);

            // Sent http request
            sendRequest();

            // Clear text box
            enterMessage.setText("");
        }
    }

    private void setAdapter() {
        // Set list adapter to display chat history
        listAdapter = new ListAdapter(ChatBot.this, chat.getChatHistory());
        chatList.setAdapter(listAdapter);
        chatList.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        chatList.setStackFromBottom(true);
    }

    public void sendRequest() {
        // Create retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().readTimeout(10, java.util.concurrent.TimeUnit.MINUTES).build()) // this will set the read timeout for 10mins (IMPORTANT: If not your request will exceed the default read timeout)
                .build();

        // Create call
        ChatBot.RetrofitAPI retrofitAPI = retrofit.create(ChatBot.RetrofitAPI.class);
        Call<ChatResponse> call = retrofitAPI.sendChat(chat);

        // Send call
        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {

                // Retrieve response body
                responseFromAPI = response.body();

                // Retrieve response message
                responseString = responseFromAPI.message;

                // Add message and response to chat history
                chat.addChatHistory(chat.getUserMessage(), responseString);

                // Update list view
                setAdapter();
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                // Display error message on request failure
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void closeChat(View view){
        // Close app
        finish();
    }
}