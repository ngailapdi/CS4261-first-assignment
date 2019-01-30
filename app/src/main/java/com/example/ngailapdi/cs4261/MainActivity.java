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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewTeams;
    private ArrayList<Team> teamsList = new ArrayList<>();
    private ArrayList<String> teamsListName = new ArrayList<>();

    private ArrayAdapter<String> arrayAdapterName;
    private TextView usernameView;
    private FirebaseUser user;
    private Button addButton;
    private DatabaseReference databaseTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameView = findViewById(R.id.username);
        user = FirebaseAuth.getInstance().getCurrentUser();
        usernameView.setText(user.getEmail());

        // Retrieve list from database
        databaseTeam = FirebaseDatabase.getInstance().getReference();
        databaseTeam = databaseTeam.child("team/" + user.getUid());

        databaseTeam.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Team team = dataSnapshot.getValue(Team.class);
                teamsList.add(team);

                // Notify the ArrayAdapter that there was a change
                arrayAdapterName.add(team.getName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // hardcode
//        teamsList = new ArrayList<>();
//        teamsList.add("ABC");
//        teamsList.add("CDE");

        // Display
        listViewTeams = findViewById(R.id.team_list);
        arrayAdapterName = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamsListName);
        listViewTeams.setAdapter(arrayAdapterName);

        listViewTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent teamInfo = new Intent(getApplicationContext(), ViewTeamActivity.class);
                teamInfo.putExtra("teamName", teamsList.get(i).getName());
                teamInfo.putExtra("teamDescription", teamsList.get(i).getDescription());
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
