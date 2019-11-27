package me.ogabeezle.sponsy.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.ogabeezle.sponsy.R;

public class ItemCarouselAdapter extends RecyclerView.Adapter<ItemCarouselAdapter.CarouselHolder> {
    private ArrayList<ItemCarouselModel> arr;
    private Context context;

    ItemCarouselAdapter(ArrayList<ItemCarouselModel> arr, Context context){
        this.context=context;
        this.arr=arr;
    }

    @NonNull
    @Override
    public CarouselHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarouselHolder(LayoutInflater.from(context).inflate(R.layout.item_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselHolder holder, int position) {
        final ItemCarouselModel model=arr.get(position);
        holder.itemTitle.setText(model.getTitle());
        holder.itemPic.setImageResource(model.getPicture());
        holder.itemDate.setText(model.getDateString());
        holder.itemLocation.setText(model.getLocation());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class CarouselHolder extends RecyclerView.ViewHolder{
        ImageView itemPic;
        TextView itemTitle;
        TextView itemDate;
        TextView itemLocation;
        public CarouselHolder(@NonNull View itemView) {
            super(itemView);
            itemPic = (ImageView) itemView.findViewById(R.id.itempic);
            itemTitle = (TextView) itemView.findViewById(R.id.itemtitle);
            itemDate = (TextView) itemView.findViewById(R.id.itemdate);
            itemLocation = (TextView) itemView.findViewById(R.id.itemlocation);
        }
    }
}
