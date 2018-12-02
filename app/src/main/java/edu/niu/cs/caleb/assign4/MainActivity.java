package edu.niu.cs.caleb.assign4;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataBaseManager dataBaseManager;

    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataBaseManager = new DataBaseManager(this);
        scrollView = (ScrollView)findViewById(R.id.ToDoScrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x / 2;
        updateView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    public void updateView(){
        ArrayList<ToDo> toDos = dataBaseManager.selectAll();
        int numToDos = toDos.size();

        if (numToDos > 0){
            scrollView.removeAllViewsInLayout();
            GridLayout gridLayout = new GridLayout(this);

            gridLayout.setRowCount((numToDos + 1 ) / 2);
            gridLayout.setColumnCount(2);

            toDoButton[] buttons = new toDoButton[numToDos];

            int sub = 0;

            for (ToDo toDo : toDos){
                buttons[sub] = new toDoButton(this, toDo);
                buttons[sub].setText(toDo.getName());
                buttons[sub].setOnClickListener(buttonHandler);

                gridLayout.addView(buttons[sub], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

                sub++;

            }

            //add gridlayout to scroll view
            scrollView.addView(gridLayout);

        }
    }

    public View.OnClickListener buttonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {




        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Toast.makeText(this,"Add",Toast.LENGTH_SHORT).show();
            Log.d("onOptionItemSelected","ADD was selected");

            Intent insertIntent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(insertIntent);

            return true;
        }
        else if (id == R.id.action_delete) {
            Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();
            Log.d("onOptionItemSelected","DELETE was selected");

            Intent deleteIntent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(deleteIntent);
            return true;
        }
        if (id == R.id.action_update) {
            Toast.makeText(this,"Update",Toast.LENGTH_SHORT).show();
            Log.d("onOptionItemSelected","UPDATE was selected");

            Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(updateIntent);
            return true;
        }
        if (id == R.id.action_reset) {
            Toast.makeText(this,"Reset",Toast.LENGTH_SHORT).show();
            Log.d("onOptionItemSelected","Reset was selected");



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
