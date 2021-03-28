package com.example.melearn.gui.deck;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.melearn.MainActivity;
import com.example.melearn.R;
import com.example.melearn.gui.card.AllCardsFragment;
import com.example.melearn.logic.Deck;

import java.util.ArrayList;

public class DeckListViewAdapter extends ArrayAdapter<Deck> {
    private AppCompatActivity activity;

    public DeckListViewAdapter(@NonNull AppCompatActivity context, ArrayList<Deck> deckArrayList) {
        super(context, 0, deckArrayList);
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.deck_list_item, parent, false);
        }
        Deck deck = getItem(position);

        TextView txtVwName = listItemView.findViewById(R.id.txtVwName);
        TextView txtVwDesc = listItemView.findViewById(R.id.txtVwDesc);

        txtVwName.setText(deck.getName());
        txtVwDesc.setText(deck.getDescription());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Item clicked is : " + deck.getDeckid());

                //Wie können wir vin hier das fragment wecheseln, um auf das geklickte deck zu navigieren?
                DeckFragment deckFragment = new DeckFragment(deck.getDeckid());
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, deckFragment);
                fragmentTransaction.commit();
            }
        });
        return listItemView;
    }
}
