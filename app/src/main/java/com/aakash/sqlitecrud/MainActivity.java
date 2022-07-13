package com.aakash.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtFirstName,edtLastNAme,edtID,edtRollNo;
    Button btnSave,btnDelete,btnSearch,btnShow,btnUpdate;
    myDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtRollNo = (EditText) findViewById(R.id.edtRollNo);
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastNAme = (EditText) findViewById(R.id.edtLastName);
        edtID = (EditText) findViewById(R.id.edtID);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnSave.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        db= new myDataBase(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                boolean fine=true;
                try {
                    String roll=edtRollNo.getText().toString();
                    String first=edtFirstName.getText().toString();
                    String last=edtLastNAme.getText().toString();
                    db.SaveData(roll,first,last);
                }
                catch (Exception e){
                    fine=false;
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                finally {
                    if(fine)
                        Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDelete:
                boolean fine3 = true;
                try {
                    String ID = edtID.getText().toString();
                    Integer id = Integer.parseInt(ID);
                    db.deleteData(id);
                }
                catch (Exception ex){
                    fine3=false;
                    Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
                finally {
                    if(fine3){
                        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnSearch:
                boolean fine1=true;
                try{
                    String ID = edtID.getText().toString();
                    long id = Long.parseLong(ID);
                    String ro=db.getRollNo(id);
                    String fn=db.getFirstName(id);
                    String ln=db.getLastName(id);
                    edtRollNo.setText(ro);
                    edtFirstName.setText(fn);
                    edtLastNAme.setText(ln);
                }
                catch (Exception ex)
                {
                    fine1=false;
                    Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
                finally {
                    if(fine1) {
                        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.btnShow:
                Intent intent = new Intent(this,SqlViewInfo.class);
                startActivity(intent);
                //Toast.makeText(this, "Show", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnUpdate:
                boolean fine2 = true;
                try {
                    String roll = edtRollNo.getText().toString();
                    String first = edtFirstName.getText().toString();
                    String last = edtLastNAme.getText().toString();
                    //db.SaveData(first,last);
                    String ID = edtID.getText().toString();
                    long id = Long.parseLong(ID);
                    db.UpdateDetail(id,roll, first, last);
                }
                catch (Exception ex){
                    fine2=false;
                    Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
                finally {
                    if(fine2){
                        Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }


    }
}