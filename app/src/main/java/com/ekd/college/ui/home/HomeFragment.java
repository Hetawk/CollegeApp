package com.ekd.college.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.ekd.college.R;

import java.net.URI;

public class HomeFragment extends Fragment {
//    private SliderLayout sliderLayout;
    private ImageView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        return inflater.inflate(R.layout.fragment_home, container, false);

        //        sliderLayout = view.findViewById(R.id.slider);
//        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
//        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderLayout.setScrollTimeInSec(1);



//        setSliderViews();
        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;


    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=65 Florence St, Worcester, MA 01610, USA");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

//    private void setSliderViews() {
//        for (int i = 0; i < 5; i++) {
//            DefaultSliderView sliderView = new DefaultSliderView(getContext());
//
//            switch (i) {
//                case 0:
//                    sliderView.setImageUrl(String.valueOf(R.drawable.bday));
////                    sliderView.setImageUrl("https://mancunion.com/wp-content/uploads/sites/6/Oxford_university_The_Queens_College_by_Fenlio.jpg");
//                    break;
//                case 1:
//                    sliderView.setImageUrl(String.valueOf(R.drawable.cel_college_profile));
//                    break;
//                case 2:
//                    sliderView.setImageUrl(String.valueOf(R.drawable.ic_about_one));
////                    sliderView.setImageUrl("https://briancaustin.com/wp-content/uploads/2017/05/tlu-07.jpg");
//                    break;
//                case 3:
//                    sliderView.setImageUrl(String.valueOf(R.drawable.ic_developer_one));
////                    sliderView.setImageUrl("data:image/jpeg;base64,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");
//                    break;
//                case 4:
//                    sliderView.setImageUrl(String.valueOf(R.drawable.ic_about_two));
////                    sliderView.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTn4AFFKr1xoyChVMaZJH5rMMu4NwzjpOOzsQ&usqp=CAU");
//                    break;
//
//            } //todo: end of switch
//
//            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//            sliderLayout.addSliderView(sliderView);
//        }
//    }
}