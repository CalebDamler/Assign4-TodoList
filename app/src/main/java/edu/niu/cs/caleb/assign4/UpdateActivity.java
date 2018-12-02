package edu.niu.cs.caleb.assign4;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Caleb on 5/2/2017.
 */
public class UpdateActivity extends AppCompatActivity {

    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        dataBaseManager = new DataBaseManager(this);
        updateView();
    }

    public void updateView(){

        //get all of the items in database
        ArrayList<ToDo> toDos = dataBaseManager.selectAll();
        int numToDos = toDos.size();


        if (numToDos > 0){
            RelativeLayout layout = new RelativeLayout(this); //parent layout

            ScrollView scrollView = new ScrollView(this); //hold data

            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(numToDos);
            gridLayout.setColumnCount(4);

            TextView[] ids = new TextView[numToDos];
            EditText[] name = new EditText[numToDos];
            Button[] buttons = new Button[numToDos];

            //find screen width
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int sub = 0;

            for (ToDo toDo : toDos){
                //fill the textview with id
                ids[sub] = new TextView(this);
                ids[sub].setGravity(Gravity.CENTER);

                ids[sub].setText("" + toDo.getId());

                //fill edit textfield
                name[sub] = new EditText(this);

                name[sub].setText(toDo.getName());




                name[sub].setId(10 * toDo.getId());


                //fill the button fields
                buttons[sub] = new Button(this);
                buttons[sub].setText("Update");
                buttons[sub].setId(toDo.getId());
                buttons[sub].setOnClickListener(buttonHandler);

                gridLayout.addView(ids[sub], width/10, ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(name[sub], (int)(width * .4), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(buttons[sub], (int)(width * .35), ViewGroup.LayoutParams.WRAP_CONTENT);

                sub++;
            }// end for

            Button backButton = new Button(this);
            backButton.setText(R.string.backString);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            // start to build layout
            //add

            scrollView.addView(gridLayout);
            layout.addView(scrollView);

            //button params button at bottom of screen
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.setMargins(0,0,0,50);

            //add the back button
            layout.addView(backButton, params);

            //display the layout

            setContentView(layout);
        }



    }

    //listener
    public  View.OnClickListener buttonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int toDoId = v.getId();

            EditText nameET = (EditText) findViewById(10 * toDoId);
            String nameStr = nameET.getText().toString();

            dataBaseManager.updateByID(toDoId, nameStr);
            Toast.makeText(UpdateActivity.this, "ToDo Updated: " + nameStr, Toast.LENGTH_SHORT).show();
            updateView();

        }
    };

}
