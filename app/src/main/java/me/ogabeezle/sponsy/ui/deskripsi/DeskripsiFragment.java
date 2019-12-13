package me.ogabeezle.sponsy.ui.deskripsi;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import me.ogabeezle.sponsy.Model.Account;
import me.ogabeezle.sponsy.R;
import me.ogabeezle.sponsy.ui.signup.SignupFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DeskripsiFragment extends Fragment {

    View v;
    Account account;

    public DeskripsiFragment(Account account){
        super();
        this.account=account;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_desc, container, false);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(account.getImageUrl());
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d(TAG, "onSuccess: "+uri.toString());
                Glide.with(v.getContext()).load(uri).into((ImageView) v.findViewById(R.id.profile_picture));
            }
        });
        ((TextView)v.findViewById(R.id.name)).setText(account.getName());
        ((TextView)v.findViewById(R.id.deskripsi)).setText(account.getDeskripsi());
//        loadButton();
        return v;
    }

    void loadButton(){
        TextView daftarButton = v.findViewById(R.id.daftar_button);
        daftarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SignupFragment());
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
