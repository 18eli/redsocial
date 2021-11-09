package com.example.redsocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Spinner spinner = (Spinner) findViewById(R.id.facultad);
        String[] facultades = new String[] {"Ingenieria", "Educacion", "Ciencias Contables", "Medicina", "Derecho"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, facultades);
        spinner.setAdapter(adapter);
    }
    public void registrar (View view){
        EditText eTDni= (EditText)findViewById(R.id.idDni);
        String dni = eTDni.getText().toString();
        EditText eTcodigoU = (EditText)findViewById(R.id.idCodigoU);
        String codigoU = eTcodigoU.getText().toString();
        EditText eTclave = (EditText)findViewById(R.id.idClave);
        String clave = eTclave.getText().toString();
        EditText eTnomape = (EditText)findViewById(R.id.idNomApe);
        String nomape = eTnomape.getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.facultad);
        String text = spinner.getSelectedItem().toString();

        Map<String, Object> user = new HashMap<>();
        user.put("dni", dni);
        user.put("codigoUniversitario", codigoU);
        user.put("clave", clave);
        user.put("nombreApellido", nomape);
        user.put("facultad",text);

        db.collection("alumnos")
                .whereEqualTo("dni",dni)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().isEmpty()){
                        db.collection("alumnos").document(dni).set(
                                  user
                        );
                        Bundle extras = new Bundle();
                        extras.putString("nombre",nomape);
                        Intent salir = new Intent (Registro.this,principal.class);
                        salir.putExtras(extras);
                        startActivity(salir);
                    } else{
                        Toast.makeText(getApplicationContext(), "si existe el dni", Toast.LENGTH_SHORT).show();
                    }
                      for(QueryDocumentSnapshot documents : task.getResult()){

                      }
                }else{
                    Toast.makeText(getApplicationContext(), "paso por aqui", Toast.LENGTH_SHORT).show();

//                    db.collection("alumnos").document(dni).set(
//                                     user
//                             );

                }
            }

        });

    }

    public void validacion (){


//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//
//                            }
//                        } else {
//
//                        }
//                    }
//                });

    }
}