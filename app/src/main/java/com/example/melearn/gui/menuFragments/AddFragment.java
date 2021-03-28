package com.example.melearn.gui.menuFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.melearn.MainActivity;
import com.example.melearn.R;
import com.example.melearn.gui.deck.DeckFragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class AddFragment extends Fragment {
    String deckName;
    String deckDescription;
    private EditText deckNameInput;
    private EditText deckDescriptionInput;
    private Button buttonAdd;
    FirebaseFirestore db;
    Map<String, Object> deck = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        buttonAdd = view.findViewById(R.id.btnAdd);
        deckNameInput = view.findViewById(R.id.edTxtName);
        deckDescriptionInput = view.findViewById(R.id.edTxtDescription);
        db = FirebaseFirestore.getInstance();
        buttonAdd.setOnClickListener(v -> {
            deck.put("name", deckNameInput.getText().toString());
            deck.put("desc", deckDescriptionInput.getText().toString());
            deck.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());

            DocumentReference cDeck = db.collection("Decks").add(deck).getResult();

            DeckFragment deckFragment = new DeckFragment(cDeck.getId());
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, deckFragment);
            fragmentTransaction.commit();
        });

        return view;
    }
}
