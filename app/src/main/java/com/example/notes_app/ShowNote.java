package com.example.notes_app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ShowNote extends AppCompatActivity {

    String id;
    String title;
    String note;
    private EditText edTitle;
    private EditText edNote;

    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        edTitle = (EditText) findViewById(R.id.edTitle);
        edNote = (EditText) findViewById(R.id.edNote);

        title = getIntent().getStringExtra("title");
        note = getIntent().getStringExtra("note");

        edTitle.setText(title);
        edNote.setText(note);
    }

    public void delNote(View view) {
        dbHandler = new DBHandler(this);
        id = getIntent().getStringExtra("id");

        dbHandler.delNote(id);
        dbHandler.close();
        finish();

    }

    public void updNote(View view) {
        dbHandler = new DBHandler(this);

        id = getIntent().getStringExtra("id");
        title = edTitle.getText().toString();
        note = edNote.getText().toString();
        System.out.println(title);

        dbHandler.updNote(id, title, note);
        dbHandler.close();
        finish();
    }


}
