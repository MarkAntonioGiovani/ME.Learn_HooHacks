package com.example.melearn.gui.deck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.melearn.R;
import com.example.melearn.gui.card.AllCardsFragment;
import com.example.melearn.gui.card.NewCardFragment;
import com.example.melearn.logic.Deck;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DeckFragment extends Fragment {
    String id;
    public Deck deck;
    public DocumentSnapshot document;
    FirebaseFirestore db;

    public DeckFragment(String deckid) {
        this.id = deckid;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deck, container, false);
//                this.db = FirebaseFirestore.getInstance();
//        this.db.collection("Decks")
//                .whereEqualTo("deckid", id)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                deck = new Deck(document.getId().toString(),document.getData().get("name").toString(), document.getData().get("desc").toString());
//                            }
//                        } else {
//                            System.out.println("Error getting documents: " + task.getException());
//                        }
//                    }
//                });
        TextView txtVwName = view.findViewById(R.id.txtVwName);
        //txtVwName.setText(deck.getName());
        TextView txtVwDesc = view.findViewById(R.id.txtVwDesc);
        //txtVwDesc.setText(deck.getDescription());
        Button btnNewCard = (Button) view.findViewById(R.id.btnNewCards);
        btnNewCard.setOnClickListener(v -> {
            NewCardFragment newCardFragment = new NewCardFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, newCardFragment);
            fragmentTransaction.commit();
        });

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        AllCardsFragment allCardsFragment = new AllCardsFragment("BQhiYEnSlAGIuoxU59n0");
        fragmentTransaction.replace(R.id.fragment_container_all_cards, allCardsFragment);
        fragmentTransaction.commit();
        return view;
    }

}