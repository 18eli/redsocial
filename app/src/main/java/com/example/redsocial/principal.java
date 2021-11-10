package com.example.redsocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class principal extends AppCompatActivity {
    List<ListaElementos> elements=new ArrayList<>();
    String nombre,dni;
    EditText ETcomentario;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        nombre = getIntent().getStringExtra("nombre");

        dni=getIntent().getStringExtra("dni");

        init();
    }
    public void Anterior (View view){
        Intent anterior = new Intent(this,MainActivity.class);
        startActivity(anterior);
    }

    public void init(){


        db.collection("alumnos")
                .whereEqualTo("dni",dni)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().isEmpty()){
                                Toast.makeText(getApplicationContext(),"La cuenta no tiene comentarios",Toast.LENGTH_SHORT).show();
                            } else{

                                for(QueryDocumentSnapshot documents : task.getResult()){
                                    List<Map<String, Object>> group =  (List<Map<String, Object>>) documents.get("comentarios");
                                    if (group.size()>0){
                                        Toast.makeText(getApplicationContext(),"el tamaño es mayor a 0",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"el tamaño es menor a 0",Toast.LENGTH_SHORT).show();
                                    }
//                                    for (int i=0;i<group.size();i++)
//                                    {
////                                        Log.d("TAG",group.get(i).get("comentario").toString());
//                                        elements.add(new ListaElementos("#775447",nombre,group.get(i).get("comentario").toString()));
//                                    }
                                }
                                adaptador listAdapter = new adaptador(elements,principal.this);
                                RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(principal.this));
                                recyclerView.setAdapter(listAdapter);
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "paso por aqui", Toast.LENGTH_SHORT).show();
                        }
                    }

                });



    }

    public void comentar(View view){
        ETcomentario=findViewById(R.id.idComentar);
        String Scomentario = ETcomentario.getText().toString();

        List<Map> hola=new ArrayList<>();
        Map<String, Object> comentario = new HashMap<>();
        comentario.put("comentario",Scomentario);
        hola.add(comentario);
        db.collection("alumnos").document(dni).update("comentarios",FieldValue.arrayUnion(comentario));
        elements.clear();
        init();
    }
}