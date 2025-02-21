package me.ogabeezle.sponsy.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.ogabeezle.sponsy.R;
import me.ogabeezle.sponsy.ui.signup.SignupFragment;

public class ProfileFragment extends Fragment {

    View v;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.login_fragment, container, false);
        loadButton();
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