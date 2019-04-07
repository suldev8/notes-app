package com.example.notes_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {

    private EditText edTitle;
    private EditText edNote;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edTitle = (EditText) findViewById(R.id.edTitle);
        edNote = (EditText) findViewById(R.id.edNote);

        dbHandler = new DBHandler(this);
    }

    public void addNote(View view) {
        String title = edTitle.getText().toString();
        if(title.isEmpty()){
            Toast.makeText(getApplicationContext(), "Sorry, you must write a title", Toast.LENGTH_SHORT).show();
            return;
        }
        String note = edNote.getText().toString();
        dbHandler.addNote(title, note);
        finish();
    }
}
