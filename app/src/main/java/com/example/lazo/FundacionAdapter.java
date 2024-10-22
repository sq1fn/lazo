package com.example.lazo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lazo.modelo.Fundacion;

import java.util.ArrayList;
import java.util.List;

public class FundacionAdapter extends RecyclerView.Adapter<FundacionAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private List<Fundacion> fundacionList;
    final List<Fundacion> fundacionListFull;

    public FundacionAdapter(Context context, List<Fundacion> fundacionList) {
        this.context = context;
        this.fundacionList = fundacionList;
        this.fundacionListFull = new ArrayList<>(fundacionList);}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cuentas, parent, false);
        return new ViewHolder(view);}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fundacion fundacion = fundacionList.get(position);
        holder.nombreTextView.setText(fundacion.getNombre());
        holder.categoriaTextView.setText(fundacion.getCategoria());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PerfilConsulta.class);
            intent.putExtra("nombre", fundacion.getNombre());
            intent.putExtra("categoria", fundacion.getCategoria());
            intent.putExtra("direccion", fundacion.getDireccion());
            intent.putExtra("telefono", fundacion.getTelefono());
            intent.putExtra("descripcion", fundacion.getDescripcion());
            context.startActivity(intent);});
    }

    @Override
    public int getItemCount() {return fundacionList.size();}

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Fundacion> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(fundacionListFull);  // Sin filtro, devuelve toda la lista
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Fundacion fundacion : fundacionListFull) {
                        if (fundacion.getNombre().toLowerCase().contains(filterPattern)) {
                            filteredList.add(fundacion);}}}

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;}

            @Override
            @SuppressWarnings("unchecked")
            protected void publishResults(CharSequence constraint, FilterResults results) {
                fundacionList.clear();
                fundacionList.addAll((List<Fundacion>) results.values);
                notifyDataSetChanged();
            }};}

    public void filter(String query) {getFilter().filter(query);}

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView categoriaTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombre_fundation);
            categoriaTextView = itemView.findViewById(R.id.categoria_fundacion);
        }}}
