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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import me.ogabeezle.sponsy.R;

public class HomeFragment extends Fragment {

    RecyclerView adsCarousel,recommendForYouCarousel, nearbyCarousel;
    AdsCarouselAdapter adsCarouselAdapter;
    ItemCarouselAdapter recommendedItemCarouselAdapter, nearbyItemCarouselAdapter;
    ArrayList<AdsCarouselModel> iklan = new ArrayList<>();
    ArrayList<ItemCarouselModel> recommendForYouItem = new ArrayList<>();
    ArrayList<ItemCarouselModel> nearbyItem = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        loadData();
        adsCarousel = (RecyclerView) root.findViewById(R.id.carousel);
        adsCarousel.setLayoutManager(new LinearLayoutManager(root.getContext(), RecyclerView.HORIZONTAL,false));
        adsCarouselAdapter = new AdsCarouselAdapter(iklan,root.getContext());
        adsCarousel.setAdapter(adsCarouselAdapter);


        recommendForYouCarousel = (RecyclerView) root.findViewById(R.id.rekomendasiacaracarousel);
        recommendForYouCarousel.setLayoutManager(new LinearLayoutManager(root.getContext(),RecyclerView.HORIZONTAL,false));
        recommendedItemCarouselAdapter = new ItemCarouselAdapter(recommendForYouItem,root.getContext());
        recommendForYouCarousel.setAdapter(recommendedItemCarouselAdapter);

        recommendForYouCarousel = (RecyclerView) root.findViewById(R.id.rekomendasiacaracarousel);
        recommendForYouCarousel.setLayoutManager(new LinearLayoutManager(root.getContext(),RecyclerView.HORIZONTAL,false));
        recommendedItemCarouselAdapter = new ItemCarouselAdapter(recommendForYouItem,root.getContext());
        recommendForYouCarousel.setAdapter(recommendedItemCarouselAdapter);

        nearbyCarousel = (RecyclerView) root.findViewById(R.id.nearbycarousel);
        nearbyCarousel.setLayoutManager(new LinearLayoutManager(root.getContext(),RecyclerView.HORIZONTAL,false));
        nearbyItemCarouselAdapter = new ItemCarouselAdapter(nearbyItem,root.getContext());
        nearbyCarousel.setAdapter(nearbyItemCarouselAdapter);

        SnapHelper helper = new GravitySnapHelper(Gravity.CENTER);
        helper.attachToRecyclerView(adsCarousel);
        helper.attachToRecyclerView(recommendForYouCarousel);
        return root;
    }

    void loadData(){
        for(int i=0;i<5;i++){
            iklan.add(new AdsCarouselModel(R.drawable.testkotak));
        }
        for(int i=0;i<5;i++){
            try {
                if(i%3==0)
                    recommendForYouItem.add((new ItemCarouselModel(R.drawable.schematics, "Schematics 2019", new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2019"), "Surabaya")));
                else if(i%3==1)
                    recommendForYouItem.add((new ItemCarouselModel(R.drawable.pasar_kreatif, "Pasar Kreatif 2019", new SimpleDateFormat("dd/MM/yyyy").parse("02/11/2019"), "Bandung")));
                else
                    recommendForYouItem.add((new ItemCarouselModel(R.drawable.jak_cloth, "jakCloth 2019", new SimpleDateFormat("dd/MM/yyyy").parse("02/12/2019"), "jakarta")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<5;i++){
            try {
                if(i%3==1)
                    nearbyItem.add((new ItemCarouselModel(R.drawable.schematics, "Schematics 2019", new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2019"), "Surabaya")));
                else if(i%3==2)
                    nearbyItem.add((new ItemCarouselModel(R.drawable.pasar_kreatif, "Pasar Kreatif 2019", new SimpleDateFormat("dd/MM/yyyy").parse("02/11/2019"), "Bandung")));
                else
                    nearbyItem.add((new ItemCarouselModel(R.drawable.jak_cloth, "jakCloth 2019", new SimpleDateFormat("dd/MM/yyyy").parse("02/12/2019"), "jakarta")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}