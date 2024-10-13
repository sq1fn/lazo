package com.example.lazo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lazo.modelo.Fundacion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuscarFragment extends Fragment {

    private FundacionAdapter adapter;
    private List<Fundacion> fundacionList;

    public BuscarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para el fragmento
        return inflater.inflate(R.layout.fragment_buscar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview); // Asegúrate de que el ID sea correcto
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar lista de fundaciones y el adapter
        fundacionList = new ArrayList<>();
        adapter = new FundacionAdapter(getContext(), fundacionList);
        recyclerView.setAdapter(adapter);

        // Conectar con Firebase para obtener las fundaciones
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users/fundaciones");

        // Obtener los datos de Firebase
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fundacionList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Fundacion fundacion = dataSnapshot.getValue(Fundacion.class);
                    fundacionList.add(fundacion);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}
