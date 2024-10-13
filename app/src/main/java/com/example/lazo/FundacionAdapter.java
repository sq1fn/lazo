package com.example.lazo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lazo.modelo.Fundacion;

import java.util.List;

public class FundacionAdapter extends RecyclerView.Adapter<FundacionAdapter.FundacionViewHolder> {

    private Context context;
    private List<Fundacion> fundacionList;

    public FundacionAdapter(Context context, List<Fundacion> fundacionList) {
        this.context = context;
        this.fundacionList = fundacionList;
    }

    @NonNull
    @Override
    public FundacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cuentas, parent, false);
        return new FundacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FundacionViewHolder holder, int position) {
        Fundacion fundacion = fundacionList.get(position);
        holder.nombre.setText(fundacion.getNombre());
        holder.categoria.setText(fundacion.getCategoria());
    }

    @Override
    public int getItemCount() {
        return fundacionList.size();
    }

    public static class FundacionViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, categoria;

        public FundacionViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.recTitle);
            categoria = itemView.findViewById(R.id.categoria_fundacion);
        }
    }
}
