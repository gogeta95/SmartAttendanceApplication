package com.example.saurabh.smartattendanceapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Saurabh on 10-10-2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<StudentHolder> {
    private Context context;
    private List<StudentModel> studentList;
    private RecyclerView mRecyclerView;
    public RecyclerAdapter(Context context, List<StudentModel> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudentHolder(LayoutInflater.from(context).inflate(R.layout.attendance_item,parent,false));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }

    @Override
    public void onBindViewHolder(final StudentHolder holder, final int position) {
        final StudentModel current= studentList.get(position);
        holder.roll_no.setText(current.roll_no);
        holder.present.setChecked(current.present);
        holder.name.setText(current.name);
        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.present = holder.present.isChecked();
//                Some Awesome Stuff!
                mRecyclerView.smoothScrollToPosition(position+1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList==null?0:studentList.size();
    }
}

class StudentHolder extends RecyclerView.ViewHolder{
    TextView roll_no;
    CheckBox present;
    TextView name;
    public StudentHolder(View itemView) {
        super(itemView);
        roll_no= (TextView) itemView.findViewById(R.id.roll_no);
        present= (CheckBox) itemView.findViewById(R.id.present);
        name= (TextView) itemView.findViewById(R.id.name);
    }
}
