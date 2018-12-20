package com.example.ojmlc.crudroid;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBPlaneta db;
    Adaptador adapter;
    ArrayList<Planeta> list;
    Planeta p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBPlaneta(this);
        list = db.verTodo();
        adapter = new Adaptador(list, this, db);
        ListView lv = findViewById(R.id.lista);
        Button crear = findViewById(R.id.btnCreate);

        if(list != null && list.size() > 0) {
            lv.setAdapter(adapter);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogo = new Dialog(MainActivity.this);
                dialogo.setTitle("Nuevo Planeta");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();

                final EditText nombre = dialogo.findViewById(R.id.txtNombre);
                final EditText tipo = dialogo.findViewById(R.id.txtTipo);
                final EditText fecha = dialogo.findViewById(R.id.txtFecha);
                final EditText diametro = dialogo.findViewById(R.id.txtDiametro);
                final Button crear = dialogo.findViewById(R.id.btnCreateAdd);
                Button cancelar = dialogo.findViewById(R.id.btnCancelAdd);

                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            p = new Planeta(nombre.getText().toString(), tipo.getText().toString(), fecha.getText().toString(), Integer.parseInt(diametro.getText().toString()));
                            db.Insertar(p);
                            list = db.verTodo();
                            adapter.notifyDataSetChanged();
                            dialogo.dismiss();

                        } catch(Exception e) {
                            Toast.makeText(getApplicationContext(), "Error en la base de datos", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogo.dismiss();
                    }
                });
            }
        });
    }
}
