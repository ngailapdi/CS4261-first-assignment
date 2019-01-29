package com.example.ngailapdi.cs4261;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewTeams;
    private ArrayList<String> teamsList;
    private ArrayAdapter<String> arrayAdapter;
    private TextView usernameView;
    private FirebaseUser user;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameView = findViewById(R.id.username);
        user = FirebaseAuth.getInstance().getCurrentUser();
        usernameView.setText(user.getEmail());

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

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(addIntent, 1);
            }
        });

    }
}
