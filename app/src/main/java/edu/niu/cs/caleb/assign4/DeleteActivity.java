package edu.niu.cs.caleb.assign4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Caleb on 5/2/2017.
 */
public class DeleteActivity extends AppCompatActivity {
    private DataBaseManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        database = new DataBaseManager(this);

        updateView();
    }

    public void updateView(){
        ArrayList<ToDo> toDos = database.selectAll();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);
        //fill radio group with info in database

        for (ToDo toDo : toDos){
            // create radio button to go in group
            RadioButton radioButton = new RadioButton(this);
            //set id and text for the button
            radioButton.setId(toDo.getId());
            radioButton.setText(toDo.toString());

            //add radio button to group
            radioGroup.addView(radioButton);
        }

        //handle selection in radioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                database.deleteByID(checkedId);
                Toast.makeText(DeleteActivity.this, "Deleted ToDo", Toast.LENGTH_SHORT).show();
                updateView();
            }

        });

        Button backBtn = new Button(this);

        backBtn.setText(R.string.backString);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //put radio group in scroll view
        scrollView.addView(radioGroup);

        //put the scrollview into relativelayout
        relativeLayout.addView(scrollView);

        //setup params to make back button at the bottom of screen
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0,50);
        //add the button to relative layout
        relativeLayout.addView(backBtn,params);

        //put the relative layout on screen

        setContentView(relativeLayout);

    }

}
