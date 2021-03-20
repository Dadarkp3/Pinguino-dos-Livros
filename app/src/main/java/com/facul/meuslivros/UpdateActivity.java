package com.facul.meuslivros;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class UpdateActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input, value_input, edition_input;
    Button update_button, delete_button;

    String id, title, author, pages, value, edition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        value_input = findViewById(R.id.value_input2);
        edition_input = findViewById(R.id.edition_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //Precisa ser chamado primeiro
        getAndSetIntentData();

        //Coloca o titulo correto na tela
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DatabaseHelper dbh = new DatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                value = value_input.getText().toString().trim();
                edition = edition_input.getText().toString().trim();

                dbh.updateData(id, title, author, pages, value, edition);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages") && getIntent().hasExtra("value") && getIntent().hasExtra("edition")){
            //Get Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            value = getIntent().getStringExtra("value");
            edition = getIntent().getStringExtra("edition");

            //Set Intent Data
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
            value_input.setText(value);
            edition_input.setText(edition);
            Log.d("stev", title+" "+author+" "+pages+" "+value+" "+edition);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar " + title + " ?");
        builder.setMessage("Tem certeza que deseja deletar " + title + " ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbh = new DatabaseHelper(UpdateActivity.this);
                dbh.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
