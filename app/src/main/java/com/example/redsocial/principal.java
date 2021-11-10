package com.example.redsocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class principal extends AppCompatActivity {
    List<ListaElementos> elements=new ArrayList<>();
    String nombre,dni;
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
                                Toast.makeText(getApplicationContext(),"La cuenta no existe",Toast.LENGTH_SHORT).show();
                            } else{

                                for(QueryDocumentSnapshot documents : task.getResult()){
                                    List<Map<String, Object>> group =  (List<Map<String, Object>>) documents.get("comentarios");
                                    for (int i=0;i<group.size();i++)
                                    {
//                                        Log.d("TAG",group.get(i).get("comentario").toString());
                                        elements.add(new ListaElementos("#775447",nombre,group.get(i).get("comentario").toString()));
//                                        Toast.makeText(getApplicationContext(),  group.get(0).get("comentario").toString(), Toast.LENGTH_LONG).show();
//                                        elements.add(new ListaElementos("#775447","zzz","Esto es un suculento comentario"));
                                    }

                                }
                                adaptador listAdapter = new adaptador(elements,principal.this);
                                RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(principal.this));
                                recyclerView.setAdapter(listAdapter);
//                                Toast.makeText(getApplicationContext(), "paso por aqui", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "paso por aqui", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
//        Log.d("TAG","pase por aqui");
//        elements.add(new ListaElementos("#775447",nombre,"Esto es un suculento comentario"));
//        elements.add(new ListaElementos("#775447","Pedro","Esto es un suculento comentario"));
//        elements.add(new ListaElementos("#775447","Pedro","Esto es un suculento comentario"));
//        elements.add(new ListaElementos("#775447","Pedro","Esto es un suculento comentario"));
//        elements.add(new ListaElementos("#775447","Pedro","Esto es un suculento comentario"));
//        elements.add(new ListaElementos("#775447","Maria","Esto es un suculento comentario"));
//        elements.add(new ListaElementos("#775447","Juan","Esto es un suculento comentario"));
//        elements.add(new ListaElementos("#775447","Pablo","Esto es un suculento comentario"));


    }
}