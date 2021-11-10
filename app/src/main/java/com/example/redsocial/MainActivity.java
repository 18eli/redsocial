package com.example.redsocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void siguiente(View view){
        EditText dni= (EditText)findViewById(R.id.idDniMain);
        String strDni = dni.getText().toString();
        EditText clave = (EditText)findViewById(R.id.idClaveMain);
        String strClave =clave.getText().toString();
        db.collection("alumnos")
                .whereEqualTo("dni",strDni)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().isEmpty()){
                                Toast.makeText(getApplicationContext(),"La cuenta no existe",Toast.LENGTH_SHORT).show();
                            } else{
                                for(QueryDocumentSnapshot documents : task.getResult()){
                                    if (strClave.equals(documents.getData().get("clave").toString()))
                                    {
                                        Bundle extras = new Bundle();
                                        extras.putString("nombre",documents.getData().get("nombreApellido").toString());
                                        extras.putString("dni",strDni);
                                        Intent siguiente = new Intent (MainActivity.this,principal.class);
                                        siguiente.putExtras(extras);
                                        startActivity(siguiente);
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Clave incorrecta", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "paso por aqui", Toast.LENGTH_SHORT).show();
                        }
                    }

                });


    }

    public void registro(View view){
      Intent registro = new Intent (this,Registro.class);
        startActivity(registro);
    }

}