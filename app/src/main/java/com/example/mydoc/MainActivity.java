package com.example.mydoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Idoc_clicked_callback{


    RecyclerView recycler;
    FloatingActionButton floatingActionButton;
    Button delete_all_button;

    static DocViewModel viewModel;
    Intent i_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInitializer(this);
    }

    private void viewInitializer(Context context){

        recycler = findViewById(R.id.recycler);
        recycler_adapter adapter = new recycler_adapter(this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(DocViewModel.class);
        viewModel.getAllData().observe(this, new Observer<List<Docs>>() {
            @Override
            public void onChanged(List<Docs> docs) {
                adapter.onUpdate(docs);
            }
        });

        i_intent = new Intent(this, EditActivity.class);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditActivity.fabClicked = true;
                EditActivity.docClicked = false;
                i_intent.putExtra(EditActivity.DATA, "");
                startActivity(i_intent);

            }
        });

        delete_all_button = findViewById(R.id.delete_all_button);
        delete_all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert = new AlertDialog.Builder(context)
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setTitle("Are you sure?")
                        .setMessage("This will delete every doc.")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                viewModel.deleteAll();
                                Toast.makeText(context, "Everything is deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Nothing is deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

    }

    public static void insert(String s){

        Docs doc = new Docs(s);
        viewModel.insert(doc);

    }


    @Override
    public void onDocClick(Docs docs) {

        EditActivity.docClicked = true;
        EditActivity.fabClicked = false;
        i_intent.putExtra(EditActivity.DATA, docs.getDoc());
        startActivity(i_intent);

    }

    @Override
    public void onDeleteButtonClick(Docs docs) {

        viewModel.delete(docs);

    }
}