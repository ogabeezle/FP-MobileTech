package me.ogabeezle.sponsy.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.ogabeezle.sponsy.R;

public class AdsCarouselAdapter extends RecyclerView.Adapter<AdsCarouselAdapter.CarouselHolder> {
    private ArrayList<AdsCarouselModel> arr;
    private Context context;

    AdsCarouselAdapter(ArrayList<AdsCarouselModel> arr, Context context){
        this.context=context;
        this.arr=arr;
    }

    @NonNull
    @Override
    public CarouselHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarouselHolder(LayoutInflater.from(context).inflate(R.layout.carousel_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselHolder holder, int position) {
        final AdsCarouselModel model=arr.get(position);
//        int imageResource = context.getResources().getIdentifier("@drawable/testkotak", null, context.getPackageName());
//        Drawable res = context.getResources().getDrawable(imageResource);
        holder.iklan.setImageResource(model.getAdsPicture());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class CarouselHolder extends RecyclerView.ViewHolder{
        ImageView iklan;
        public CarouselHolder(@NonNull View itemView) {
            super(itemView);
            iklan = (ImageView) itemView.findViewById(R.id.carouselcard);
        }
    }
}
