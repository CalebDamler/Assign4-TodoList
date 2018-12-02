package edu.niu.cs.caleb.assign4;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Caleb on 5/2/2017.
 */
public class toDoButton extends Button {
    private ToDo toDo;

    public toDoButton(Context context, ToDo toDo) {
        super(context);
        this.toDo = toDo;
    }


}
