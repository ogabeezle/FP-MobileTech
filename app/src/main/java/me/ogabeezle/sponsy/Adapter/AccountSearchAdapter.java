package me.ogabeezle.sponsy.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import me.ogabeezle.sponsy.Model.Account;
import me.ogabeezle.sponsy.R;
import me.ogabeezle.sponsy.ui.deskripsi.DeskripsiFragment;
import me.ogabeezle.sponsy.ui.search.SearchFragment;

public class AccountSearchAdapter extends RecyclerView.Adapter<AccountSearchAdapter.CarouselHolder> {
    private List<Account> arr;
    private Context context;
    private SearchFragment searchFragment;

    public AccountSearchAdapter(List<Account> arr, Context context,SearchFragment searchFragment) {
        this.context = context;
        this.arr = arr;
        this.searchFragment=searchFragment;

    }

    @NonNull
    @Override
    public CarouselHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarouselHolder(LayoutInflater.from(context).inflate(R.layout.item_card_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CarouselHolder holder, int position) {
        final Account model = arr.get(position);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(model.getImageUrl());
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.itemPic);
            }
        });
        holder.itemTitle.setText(model.getName());
        holder.itemDate.setText(model.getContactName());
        holder.itemLocation.setText(model.getAddress());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFragment.loadFragment(new DeskripsiFragment(model));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class CarouselHolder extends RecyclerView.ViewHolder {
        ImageView itemPic;
        TextView itemTitle;
        TextView itemDate;
        TextView itemLocation;
        LinearLayout card;

        public CarouselHolder(@NonNull View itemView) {
            super(itemView);
            card=(LinearLayout) itemView.findViewById(R.id.card);
            itemPic = (ImageView) itemView.findViewById(R.id.itempic);
            itemTitle = (TextView) itemView.findViewById(R.id.itemtitle);
            itemDate = (TextView) itemView.findViewById(R.id.itemdate);
            itemLocation = (TextView) itemView.findViewById(R.id.itemlocation);
        }
    }
}
