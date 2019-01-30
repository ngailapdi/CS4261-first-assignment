package com.example.ngailapdi.cs4261;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText description;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.nameInput);
        description = findViewById(R.id.desInput);
        Button add = findViewById(R.id.addButton);
        user = FirebaseAuth.getInstance().getCurrentUser();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference teamFire = FirebaseDatabase.getInstance().getReference("team/" + user.getUid() + "/");
                addTeam(teamFire);
                Toast.makeText(AddActivity.this,"Add Successful",Toast.LENGTH_LONG).show();
                Intent backIntent = new Intent(AddActivity.this, MainActivity.class);
                startActivityForResult(backIntent, 1);
            }
            });



}
    public void addTeam(DatabaseReference teamFire) {
        String teamName = name.getText().toString();
        String teamDes = description.getText().toString();
        String id = teamFire.push().getKey();
        Team newTeam = new Team(teamName,teamDes,id);
        teamFire.child(id).setValue(newTeam);
        name.setText("Name");
        description.setText("Description");
    }

}