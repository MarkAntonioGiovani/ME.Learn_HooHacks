package com.example.melearn.gui.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.melearn.R;
import com.example.melearn.gui.deck.DeckFragment;
import com.example.melearn.logic.Card;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class CardListViewAdapter extends ArrayAdapter<Card> {
    private final AppCompatActivity activity;

    public CardListViewAdapter(@NonNull AppCompatActivity context, ArrayList<Card> cardArrayList) {
        super(context, 0, cardArrayList);
        activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_list_item, parent, false);
        }
        Card card = getItem(position);

        TextView txtVwName = listItemView.findViewById(R.id.txtVwFront);
        TextView txtVwDesc = listItemView.findViewById(R.id.txtVwBack);

        txtVwName.setText(card.getFront());
        txtVwDesc.setText(card.getBack());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Item clicked is : " + card.getFront());

                //Wie können wir vin hier das fragment wecheseln, um auf die geklickte card zu navigieren?
                CardFragment cardFragment = new CardFragment();
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, cardFragment);
                fragmentTransaction.commit();
            }
        });
        return listItemView;
    }
}
