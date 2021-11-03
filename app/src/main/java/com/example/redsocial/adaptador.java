package com.example.redsocial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class adaptador extends RecyclerView.Adapter<adaptador.ViewHolder> {
    private List<ListaElementos> mData;
    private LayoutInflater mInflater;
    private Context context;
    public adaptador ( List<ListaElementos> itemList, Context context){
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData = itemList;
    }

    @Override
    public adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= mInflater.inflate(R.layout.lista_elementos,null);
       return new adaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptador.ViewHolder holder, int position) {
    holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void setItems(List<ListaElementos> items){ mData =items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name,comment;
        ViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImageView);
            name= itemView.findViewById(R.id.nameTextView);
            comment= itemView.findViewById(R.id.commentTextView);
        }

        void bindData(final ListaElementos item){
           name.setText(item.getName());
           comment.setText(item.getComment());
        }
    }

}
