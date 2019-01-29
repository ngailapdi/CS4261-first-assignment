package com.example.ngailapdi.cs4261;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.nameInput);
        description = findViewById(R.id.desInput);
        Button add = findViewById(R.id.addButton);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase team =  FirebaseDatabase.getInstance();
                DatabaseReference myRef = team.getReference("team");
                addTeam(myRef);
            }

});

}
public void addTeam(DatabaseReference myRef)
{
    String teamName = name.getText().toString();
    String teamDes = description.getText().toString();
    String id = myRef.push().getKey();
    Team newTeam = new Team(teamName,teamDes,id);
    myRef.child(id).setValue(newTeam);


}
}