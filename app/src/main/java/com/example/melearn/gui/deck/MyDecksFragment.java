package com.example.melearn.gui.deck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.melearn.R;
import com.example.melearn.gui.deck.DeckListViewAdapter;
import com.example.melearn.logic.Deck;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class MyDecksFragment extends Fragment {
    ListView decksListView;
    ArrayList<Deck> decksArrayList;
    FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_decks, container, false);
        decksListView = view.findViewById(R.id.listViewDeck);
        decksArrayList = new ArrayList<Deck>();

        this.db = FirebaseFirestore.getInstance();

        //Methode zum Laden der Decks aus der Datenbank. Aktuell zum Testen harte Decks.
        loadDecksIntoListView();

        return view;
    }

    private void loadDecksIntoListView() {
        this.db.collection("Decks")
                .whereEqualTo("uid", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId() + " => " + document.getData());
                                Deck d = new Deck(document.getId().toString(),document.getData().get("name").toString(), document.getData().get("desc").toString());
                                decksArrayList.add(d);
                            }
                            DeckListViewAdapter adapter = new DeckListViewAdapter((AppCompatActivity) getActivity(), decksArrayList);
                            decksListView.setAdapter(adapter);
                        } else {
                            System.out.println("Error getting documents: " + task.getException());
                        }
                    }
                });
    }
}

