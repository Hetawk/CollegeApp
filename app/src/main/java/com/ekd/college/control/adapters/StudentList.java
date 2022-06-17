package com.ekd.college.control.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ekd.college.R;
import com.ekd.college.control.DBHandlers.StudentModel;


import java.util.List;

public class StudentList extends ArrayAdapter<StudentModel> {
    private Activity context;
    private List<StudentModel> studentList;

    public StudentList(Activity context, List<StudentModel> studentList){
            super(context, R.layout.student_list, studentList);
            this.context = context;
            this.studentList = studentList;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.student_list,null,true);

        TextView studentId= (TextView) listViewItem.findViewById(R.id.studentId1);
        TextView studentName= (TextView) listViewItem.findViewById(R.id.StudentName1);
        TextView password= (TextView) listViewItem.findViewById(R.id.password1);

        StudentModel studentModel = studentList.get(position);

        studentId.setText(studentModel.getStudentID());
        studentName.setText(studentModel.getStudentName());
        password.setText(studentModel.getPassword());

        return listViewItem;
    }
}
