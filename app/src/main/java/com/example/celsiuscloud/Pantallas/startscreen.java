package com.example.celsiuscloud.Pantallas;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.celsiuscloud.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class startscreen extends AppCompatActivity {


    CarouselView customCarouselView;
    int NUMBER_OF_PAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreen);

        customCarouselView = (CarouselView) findViewById(R.id.carouselView);
        customCarouselView.setPageCount(NUMBER_OF_PAGES);
        // set ViewListener for custom view
        customCarouselView.setViewListener(viewListener);
    }

    public void openLogin(View v){
        Intent i = new Intent(this,loginscreen.class);
        startActivity(i);
    }

    public void openRegister(View v){
        Intent i = new Intent(this, registerscreen.class);
        startActivity(i);
    }

    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            int[] Pantallas = {R.layout.carousel_view1, R.layout.carousel_view2, R.layout.carousel_view3, R.layout.carousel_view4};
            View customView = getLayoutInflater().inflate(Pantallas[position], null);
            return customView;
        }
    };



}
