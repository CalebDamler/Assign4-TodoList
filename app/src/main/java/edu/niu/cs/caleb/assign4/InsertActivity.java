package edu.niu.cs.caleb.assign4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Caleb on 5/2/2017.
 */
public class InsertActivity extends AppCompatActivity {
    private DataBaseManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        database = new DataBaseManager(this);
    }//oncreate

    public void Insert (View view){
        EditText nameET = (EditText)findViewById(R.id.NameEdit);


        String nameStr = nameET.getText().toString();


        try{
            ToDo toDo = new ToDo(0, nameStr);
            database.insert(toDo);
            Toast.makeText(this, "ToDo Added: " + nameStr, Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

        nameET.setText("");

    }

    public void goBack(View view){
        finish();
    }
}
