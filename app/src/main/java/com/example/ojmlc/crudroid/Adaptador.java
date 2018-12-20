package com.example.ojmlc.crudroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    ArrayList<Planeta> lista;
    DBPlaneta DBP;
    Planeta p;
    Activity act;
    int id  = 0;

    public Adaptador(ArrayList<Planeta> lista, Activity a, DBPlaneta dbp) {
        this.lista = lista;
        this.act = a;
        this.DBP = dbp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Planeta getItem(int position) {
        p = lista.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        p = lista.get(position);
        return p.getId();
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater li = (LayoutInflater)act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }

        p = lista.get(position);

        TextView nombre = v.findViewById(R.id.viewNameItem);
        TextView tipo = v.findViewById(R.id.viewTypeItem);
        TextView fecha = v.findViewById(R.id.viewDateItem);
        TextView diametro = v.findViewById(R.id.viewDiametroItem);
        Button editar = v.findViewById(R.id.btnEditItem);
        Button eliminar = v.findViewById(R.id.btnDeletetItem);

        nombre.setText(p.getNombre());
        tipo.setText(p.getTipo());
        fecha.setText(p.getFecha());
        diametro.setText("" + p.getDiametro());

        editar.setTag(position);
        eliminar.setTag(position);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                final Dialog dialogo = new Dialog(act);
                dialogo.setTitle("Editar Planeta");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();

                final EditText nombre = dialogo.findViewById(R.id.txtNombre);
                final EditText tipo = dialogo.findViewById(R.id.txtTipo);
                final EditText fecha = dialogo.findViewById(R.id.txtFecha);
                final EditText diametro = dialogo.findViewById(R.id.txtDiametro);
                Button crear = dialogo.findViewById(R.id.btnCreateAdd);
                //crear.setText("Editar");
                Button cancelar = dialogo.findViewById(R.id.btnCancelAdd);

                p = lista.get(pos);
                setId(p.getId());
                nombre.setText(p.getNombre());
                tipo.setText(p.getTipo());
                fecha.setText(p.getFecha());
                diametro.setText("" + p.getDiametro());

                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            p = new Planeta(getId(), nombre.getText().toString(), tipo.getText().toString(), fecha.getText().toString(), Integer.parseInt(diametro.getText().toString()));
                            DBP.Editar(p);
                            lista = DBP.verTodo();
                            notifyDataSetChanged();
                            dialogo.dismiss();

                        } catch(Exception e) {
                            Toast.makeText(act, "Error en la base de datos", Toast.LENGTH_LONG).show();
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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                p = lista.get(pos);
                setId(p.getId());
                AlertDialog.Builder delete = new AlertDialog.Builder(act);
                delete.setMessage("¿Seguro que deseas ELIMINAR este planeta?");
                delete.setCancelable(false);
                delete.setPositiveButton("Simon", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBP.Eliminar(getId());
                        lista = DBP.verTodo();
                        notifyDataSetChanged();
                    }
                });
                delete.setNegativeButton("Nel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                delete.show();
            }
        });

        return v;
    }
}
