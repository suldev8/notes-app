package com.example.notes_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler;
    String[][] notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DBHandler(this);


        FloatingActionButton addNoteBtn = findViewById(R.id.addNoteBtn);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddNote.class));
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        notes = dbHandler.getNotes();
        String[] titles = new String[notes.length];

        for(int i =0 ; i<notes.length; i++ ) {
            titles[i] = notes[i][1];
        }

        ListAdapter namesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        ListView titleList = (ListView) findViewById(R.id.notesList);
        titleList.setAdapter(namesAdapter);

        titleList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, ShowNote.class);
                        int index = 0;
                        i.putExtra("id", notes[position][index++]);
                        i.putExtra("title", notes[position][index++]);
                        i.putExtra("note", notes[position][index]);
                        startActivity(i);
                    }
                }
        );

    }
}
