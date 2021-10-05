package com.example.mydoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    public static final String DATA = "data";

    public static boolean fabClicked = false;
    public static boolean docClicked = false;

    EditText editText;
    Button save;
    Button share;

    Intent e_intent;
    Bundle bundle;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        viewInitializer(this);

    }

    private void viewInitializer(Context context){

        bundle = getIntent().getExtras();
        if(bundle != null){
            data = bundle.getString(DATA);
        }


        editText = findViewById(R.id.edit);
        editText.setText(data);

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fabClicked && !docClicked){

                    MainActivity.insert(editText.getText().toString());
                    fabClicked = false;
                    finish();

                }
                else if(!fabClicked && docClicked){

                    String ms = editText.getText().toString();
                    MainActivity.insert(ms);
                    docClicked = false;
                    Toast.makeText(context, "The doc is updated", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });

        share = findViewById(R.id.Share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((editText.getText() != null) && (!(editText.getText().toString().equals("")))){
                    shareDoc(editText.getText().toString());
                }
                else{
                    Toast.makeText(context, "The doc is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void shareDoc(String s){

        e_intent = new Intent(Intent.ACTION_SEND);
        e_intent.setType("text/plain");
        e_intent.putExtra(Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(e_intent, "Share this doc with..."));

    }

}