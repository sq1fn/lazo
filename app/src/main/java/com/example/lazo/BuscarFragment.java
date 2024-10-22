package com.example.lazo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lazo.modelo.Fundacion;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BuscarFragment extends Fragment {

    private FundacionAdapter adapter;
    private List<Fundacion> fundacionList;
    private FirebaseFirestore db;

    public BuscarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buscar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fundacionList = new ArrayList<>();
        adapter = new FundacionAdapter(getContext(), fundacionList);
        recyclerView.setAdapter(adapter);

        // Referencia a la colección de fundaciones en Firestore
        CollectionReference fundacionesRef = db.collection("fundaciones");

        // Obtener datos en tiempo real desde Firestore
        fundacionesRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    // Manejar el error
                    return;
                }

                fundacionList.clear();
                if (value != null) {
                    for (com.google.firebase.firestore.DocumentSnapshot snapshot : value.getDocuments()) {
                        Fundacion fundacion = snapshot.toObject(Fundacion.class);
                        fundacionList.add(fundacion);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // Configuración de la barra de búsqueda
        SearchView searchView = view.findViewById(R.id.buscar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });
    }
}
