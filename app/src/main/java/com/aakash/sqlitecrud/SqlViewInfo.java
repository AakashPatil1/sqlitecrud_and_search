package com.aakash.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class SqlViewInfo extends AppCompatActivity {


    TextView textView;

    myDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_view_info);



        //View Data
        textView=(TextView) findViewById(R.id.textView);
        db= new myDataBase(this);
        String Data=db.getData();
        textView.setText(Data);



    }


}