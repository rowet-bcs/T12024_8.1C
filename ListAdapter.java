package com.example.task81c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Dictionary;

public class ListAdapter  extends ArrayAdapter<Dictionary> {

    // Custom list adapter class to display chat history
    public ListAdapter(@NonNull Context context, ArrayList<Dictionary> advertArrayList){
        super(context, R.layout.chat_layout, advertArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        // Retrieve chat history for given position
        Dictionary dict = getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.chat_layout, parent, false);
        }

        String userMessage = (String) dict.get("User");
        String llamaMessage = (String) dict.get("Llama");


        // Populate display with advert type, lost/found date and title
        TextView userText = view.findViewById(R.id.userText);
        TextView llamaText = view.findViewById(R.id.llamaText);

        userText.setText(userMessage);
        llamaText.setText(llamaMessage);

        return view;
    }


}
