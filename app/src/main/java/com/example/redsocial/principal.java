package com.example.redsocial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class principal extends AppCompatActivity {
    List<ListaElementos> elements;
    String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        valor = getIntent().getStringExtra("nombre");
        init();
    }
    public void Anterior (View view){
        Intent anterior = new Intent(this,MainActivity.class);
        startActivity(anterior);
    }

    public void init(){
        elements=new ArrayList<>();
        elements.add(new ListaElementos("#775447",valor,"Esto es un suculento comentario"));
        elements.add(new ListaElementos("#775447","Pedro","Esto es un suculento comentario"));
        elements.add(new ListaElementos("#775447","Maria","Esto es un suculento comentario"));
        elements.add(new ListaElementos("#775447","Juan","Esto es un suculento comentario"));
        elements.add(new ListaElementos("#775447","Pablo","Esto es un suculento comentario"));
        adaptador listAdapter = new adaptador(elements,this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }
}