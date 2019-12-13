package me.ogabeezle.sponsy.ui.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.ogabeezle.sponsy.Adapter.AccountSearchAdapter;
import me.ogabeezle.sponsy.Model.GetAccount;
import me.ogabeezle.sponsy.R;
import me.ogabeezle.sponsy.Rest.ApiClient;
import me.ogabeezle.sponsy.Rest.ApiInterface;
import me.ogabeezle.sponsy.ui.search.SearchFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CategoryFragment extends SearchFragment {

    View v;
    EditText editText;
    String category;
    SearchFragment temp;

    public CategoryFragment(String category){
        super();
        this.category=category;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.acitivity_eventgrid, container, false);
        temp=this;
        loadButton();
        getContent();
        return v;
    }

    void getContent(){
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetAccount> accountCall = mApiInterface.byCategory(category);
        accountCall.enqueue(new Callback<GetAccount>() {
            @Override
            public void onResponse(Call<GetAccount> call, Response<GetAccount> response) {
                Log.d(TAG, "onResponse: "+editText.getText()+response.body().getData().size());
                if(response.body().getData().size()<1) return ;
                RecyclerView listItem = (RecyclerView) v.findViewById(R.id.listItem);
                listItem.setLayoutManager(new LinearLayoutManager(v.getContext(),RecyclerView.HORIZONTAL,false));
                listItem.setAdapter(new AccountSearchAdapter(response.body().getData(),v.getContext(),temp));
            }

            @Override
            public void onFailure(Call<GetAccount> call, Throwable t) {

            }
        });
    }

    void loadButton(){
        editText = v.findViewById(R.id.search_bar1);
        View searchIcon = v.findViewById(R.id.search_icon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View vv) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetAccount> accountCall = mApiInterface.search(editText.getText().toString());
                accountCall.enqueue(new Callback<GetAccount>() {
                    @Override
                    public void onResponse(Call<GetAccount> call, Response<GetAccount> response) {
                        Log.d(TAG, "onResponse: "+editText.getText()+response.body().getData().size());
                        RecyclerView listItem = (RecyclerView) v.findViewById(R.id.listItem);
                        listItem.setLayoutManager(new LinearLayoutManager(v.getContext(),RecyclerView.HORIZONTAL,false));
                        listItem.setAdapter(new AccountSearchAdapter(response.body().getData(),v.getContext(),temp));
                    }

                    @Override
                    public void onFailure(Call<GetAccount> call, Throwable t) {

                    }
                });
            }
        });
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
