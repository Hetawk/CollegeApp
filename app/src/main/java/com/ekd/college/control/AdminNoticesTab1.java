package com.ekd.college.control;

import static android.widget.Toast.makeText;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekd.college.R;
import com.ekd.college.control.DBHandlers.NoticesModel;
import com.ekd.college.control.main.RecyclerViewAdapterNotices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminNoticesTab1 extends Fragment {

    RecyclerView noticeList;
    private DatabaseReference reference;
    ArrayList<NoticesModel> list;
    RecyclerViewAdapterNotices adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.fragment_admin_notices,container,false);

        noticeList=(RecyclerView)view.findViewById(R.id.noticeRecyclerView);
        noticeList.setHasFixedSize(true);
        noticeList.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<NoticesModel>();

        reference=FirebaseDatabase.getInstance().getReference("NoticesModel");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    list.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        NoticesModel model = dataSnapshot1.getValue(NoticesModel.class);
                        list.add(model);
                    }

                    adapter=new RecyclerViewAdapterNotices(getContext(),list);
                    noticeList.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                makeText(getContext(),"Something went wrong....",Toast.LENGTH_LONG).show();
            }
        });




        return view;


    }



}













