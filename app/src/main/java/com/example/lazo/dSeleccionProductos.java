package com.example.lazo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import com.example.lazo.modelo.Producto;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class dSeleccionProductos extends Fragment {

    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private List<Producto> productos;
    private FirebaseFirestore db;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout que contiene el SearchView y el RecyclerView
        View view = inflater.inflate(R.layout.fragment_d_seleccion_productos, container, false);

        // Inicializar el SearchView y configurar el listener de búsqueda
        searchView = view.findViewById(R.id.buscar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Filtro de búsqueda cuando el usuario hace clic en el botón de buscar
                filtrarProductos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar en tiempo real mientras el usuario escribe
                filtrarProductos(newText);
                return true;
            }
        });

        // Inicializar el RecyclerView y su adaptador
        recyclerView = view.findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productos = new ArrayList<>();
        productoAdapter = new ProductoAdapter(productos);
        recyclerView.setAdapter(productoAdapter);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Cargar los productos desde Firestore
        cargarProductos();

        return view;
    }

    // Método para cargar productos desde Firestore
    private void cargarProductos() {
        db.collection("productos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        productos.clear();  // Limpiar la lista antes de agregar nuevos datos
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String nombre = document.getString("nombre");
                            List<String> categorias = (List<String>) document.get("categorias");

                            Producto producto = new Producto(nombre, categorias);
                            productos.add(producto);
                        }
                        productoAdapter.notifyDataSetChanged(); // Notificar al adaptador
                    } else {
                        Log.e("Firestore", "Error al obtener productos: ", task.getException());
                    }
                });
    }

    // Método para filtrar productos según el texto de búsqueda
    private void filtrarProductos(String texto) {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                productosFiltrados.add(producto);
            }
        }
        productoAdapter.equals(productosFiltrados);
    }
}
