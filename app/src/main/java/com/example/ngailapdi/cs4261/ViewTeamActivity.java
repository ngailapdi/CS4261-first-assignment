package com.example.ngailapdi.cs4261;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewTeamActivity extends AppCompatActivity {
    TextView teamNameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);

        teamNameView = findViewById(R.id.teamName);
        Intent intent = getIntent();
        String teamName = intent.getStringExtra("index");
        teamNameView.setText(teamName);
    }
}
