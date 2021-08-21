package com.example.mydairy.entry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mydairy.R;

public class EntryDashboardActivity extends AppCompatActivity {

    private CardView[] Card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_dashboard);

        Card = new CardView[8];
        Card[0].findViewById(R.id.card_0);

        Card[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryDashboardActivity.this, DailyEntryActivity.class);
                startActivity(intent);
            }
        });
    }
}