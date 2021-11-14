package com.car105.yendondeesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class dbActivity extends AppCompatActivity {

    private EditText et_codigo, et_nombre, et_disponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        et_codigo = (EditText) findViewById(R.id.codigo);
        et_nombre = (EditText) findViewById(R.id.nombre);
        et_disponible = (EditText) findViewById(R.id.disponible);
    }

    // Methods

    public void Salvar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();
        String disponible = et_disponible.getText().toString();

        if (!codigo.isEmpty() && !nombre.isEmpty() && !disponible.isEmpty()) {
            ContentValues salvado =new ContentValues();
            salvado.put("codigo", codigo);
            salvado.put("nombre", nombre);
            salvado.put("disponible", disponible);

            BaseDeDatos.insert("usuario", null, salvado);
            BaseDeDatos.close();
            et_codigo.setText("");
            et_nombre.setText("");
            et_disponible.setText("");

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Disponible(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, disponible from usuario where codigo =" + codigo, null);

            if (fila.moveToFirst()){
                et_nombre.setText(fila.getString(0));
                et_disponible.setText(fila.getString(1));
                BaseDeDatos.close();
            }
            else{
                Toast.makeText(this, "Usuarios No Existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }

        else {
            Toast.makeText(this, "Debes Introducir el codigo", Toast.LENGTH_SHORT).show();
        }
    }

}