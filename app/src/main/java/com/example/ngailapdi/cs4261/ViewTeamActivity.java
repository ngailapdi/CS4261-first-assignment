package com.example.ngailapdi.cs4261;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewTeamActivity extends AppCompatActivity {
    TextView teamNameView;
    TextView teamDescriptionView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);

        teamNameView = findViewById(R.id.teamName);
        teamDescriptionView = findViewById(R.id.description);
        Intent intent = getIntent();
        String teamName = intent.getStringExtra("teamName");
        teamNameView.setText(teamName);
        teamDescriptionView.setText(intent.getStringExtra("teamDescription"));
    }
}
