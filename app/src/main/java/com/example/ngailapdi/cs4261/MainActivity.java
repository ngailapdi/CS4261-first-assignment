package com.example.ngailapdi.cs4261;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewTeams;
    private ArrayList<String> teamsList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve list from database
        // hardcode
        teamsList = new ArrayList<>();
        teamsList.add("ABC");
        teamsList.add("CDE");

        // Display
        listViewTeams = findViewById(R.id.team_list);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamsList);
        listViewTeams.setAdapter(arrayAdapter);

        listViewTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent teamInfo = new Intent(getApplicationContext(), ViewTeamActivity.class);
                teamInfo.putExtra("index", teamsList.get(i));
                startActivity(teamInfo);
            }
        });
    }
}
