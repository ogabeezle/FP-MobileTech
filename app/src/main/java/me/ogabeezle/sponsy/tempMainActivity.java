package me.ogabeezle.sponsy;

import android.os.Bundle;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;

public class tempMainActivity extends AppCompatActivity {
    RecyclerView adsCarousel;
    AdsCarouselAdapter adsCarouselAdapter;
    ArrayList<AdsCarouselModel> iklan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_fragment);

        loadData();

        adsCarousel = (RecyclerView) findViewById(R.id.carousel);
        adsCarousel.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.HORIZONTAL,false));
        adsCarouselAdapter = new AdsCarouselAdapter(iklan,this);
        adsCarousel.setAdapter(adsCarouselAdapter);
        SnapHelper helper = new GravitySnapHelper(Gravity.CENTER);
        helper.attachToRecyclerView(adsCarousel);
    }

    void loadData(){
        for(int i=0;i<5;i++){
            iklan.add(new AdsCarouselModel(R.drawable.testkotak));
        }
    }
}
