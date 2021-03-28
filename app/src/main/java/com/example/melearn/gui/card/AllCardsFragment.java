package com.example.melearn.gui.card;

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
import com.example.melearn.logic.Card;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;


public class AllCardsFragment extends Fragment {
    ListView cardsListView;
    ArrayList<Card> cardArrayList;
    FirebaseFirestore db;
    String deckId;

    public AllCardsFragment(String deckid) {
        this.deckId = deckid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_cards, container, false);
        cardsListView = view.findViewById(R.id.listViewCard);
        cardArrayList = new ArrayList<Card>();

        this.db = FirebaseFirestore.getInstance();

        //Methode zum Laden der Decks aus der Datenbank. Aktuell zum Testen harte Decks.
        loadDecksIntoListView();

        return view;
    }

    private void loadDecksIntoListView() {
        this.db.collection("Cards")
                .whereEqualTo("deckid", this.deckId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId() + " => " + document.getData());
                                Card d = new Card(
                                        document.getData().get("front").toString(),
                                        document.getData().get("back").toString(),
                                        Integer.parseInt(document.getData().get("score").toString()));
                                cardArrayList.add(d);
                            }
                            CardListViewAdapter adapter = new CardListViewAdapter((AppCompatActivity) getContext(), cardArrayList);
                            cardsListView.setAdapter(adapter);
                        } else {
                            System.out.println("Error getting documents: " + task.getException());
                        }
                    }
                });
    }
}
