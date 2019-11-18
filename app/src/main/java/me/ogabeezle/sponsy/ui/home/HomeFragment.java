package me.ogabeezle.sponsy.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;

import me.ogabeezle.sponsy.R;

public class HomeFragment extends Fragment {

    RecyclerView adsCarousel;
    AdsCarouselAdapter adsCarouselAdapter;
    ArrayList<AdsCarouselModel> iklan = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        loadData();
        adsCarousel = (RecyclerView) root.findViewById(R.id.carousel);
        adsCarousel.setLayoutManager(new LinearLayoutManager(root.getContext(), RecyclerView.HORIZONTAL,false));
        adsCarouselAdapter = new AdsCarouselAdapter(iklan,root.getContext());
        adsCarousel.setAdapter(adsCarouselAdapter);
        SnapHelper helper = new GravitySnapHelper(Gravity.CENTER);
        helper.attachToRecyclerView(adsCarousel);

        return root;
    }

    void loadData(){
        for(int i=0;i<5;i++){
            iklan.add(new AdsCarouselModel(R.drawable.testkotak));
        }
    }
}