package com.car105.yendondeesta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.car105.yendondeesta.model.Persona;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FireBaseActivity extends AppCompatActivity {

    private List<Persona> listPerson =new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    EditText nomP, celP;
    ListView listV_personas;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);

        nomP = findViewById(R.id.txt_nombrePersona);
        celP = findViewById(R.id.txt_celular);

        listV_personas = findViewById(R.id.lv_datosPersonas);
        inicilizarFirebase();
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Persona) parent.getItemAtPosition(position);
                nomP.setText(personaSelected.getNombre());
                celP.setText(personaSelected.getCelular());
            }
        });


    }

    private void listarDatos() {
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               listPerson.clear()
;
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Persona p = objSnapshot.getValue(Persona.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>
                            (FireBaseActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicilizarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_firebase,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String  nombre = nomP.getText().toString();
        String celular = celP.getText().toString();

        switch (item.getItemId()) {
            case R.id.icon_add:{
                if (nombre.equals("") || celular.equals("")) {
                    validacion();
                }
                else {
                    Persona p = new Persona();
                    p.setId(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setCelular(celular);
                    databaseReference.child("Persona").child(p.getId()).setValue(p);
                    Toast.makeText(this, "Agregado Exitosamente", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }

            case R.id.icon_save:{
                if (nombre.equals("") || celular.equals("")) {
                    validacion();
                }
                else {
                    Persona p = new Persona();
                    p.setId(personaSelected.getId());
                    p.setNombre(nomP.getText().toString().trim());
                    p.setCelular(celP.getText().toString().trim());
                    databaseReference.child("Persona").child(p.getId()).setValue(p);
                    Toast.makeText(this,"Actualizado Exitosamente",Toast.LENGTH_LONG).show();
                    limpiarCajas();
                    break;
                }
                break;
            }

            case R.id.icon_delete:{
                if (nombre.equals("") || celular.equals("")) {
                    validacion();
                }
                else {
                    Toast.makeText(this,"Eliminado Exitosamente",Toast.LENGTH_LONG).show();
                    limpiarCajas();
                    break;
                }
                break;
            }

            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        nomP.setText("");
        celP.setText("");
    }


    public void validacion () {
        String nombre = nomP.getText().toString();
        String celular = celP.getText().toString();

        if (nombre.equals("")) {
            nomP.setError("Requiere Nombre");
        }
        else if (celular.equals("")){
            celP.setError("Requiere No. Celular");
        }
    }
}