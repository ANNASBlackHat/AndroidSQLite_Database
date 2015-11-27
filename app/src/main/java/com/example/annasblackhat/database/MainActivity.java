package com.example.annasblackhat.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nim = (EditText)findViewById(R.id.nim);
        final EditText nama = (EditText)findViewById(R.id.txtNama);
        Button save = (Button)findViewById(R.id.btnSave);
        Button del  = (Button)findViewById(R.id.btnDel);
        Button show  = (Button)findViewById(R.id.btnShow);
        Button update = (Button)findViewById(R.id.btnUpdate);
        final TextView result = (TextView)findViewById(R.id.result);

        final DatabaseHelper db = new DatabaseHelper(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xNim = nim.getText().toString();
                String xNama = nama.getText().toString();

                Mahasiswa m = new Mahasiswa();
                m.setNama(xNama);
                m.setNim(xNim);
                db.insert(m);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Mahasiswa> list = db.getData();
                String nama="", nim="";
                for(Mahasiswa m : list){
                    nama += m.getNama()+", ";
                    nim += m.getNim()+", ";
                }
                result.setText("NIM : " + nim + "\n" +
                        "NAMA : " + nama);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(nim.getText().toString());
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xNim = nim.getText().toString();
                String xNama = nama.getText().toString();

                Mahasiswa m = new Mahasiswa();
                m.setNama(xNama);
                m.setNim(xNim);
                db.update(m);
            }
        });
    }
}
