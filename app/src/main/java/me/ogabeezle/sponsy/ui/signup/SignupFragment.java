package me.ogabeezle.sponsy.ui.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import me.ogabeezle.sponsy.MainActivity;
import me.ogabeezle.sponsy.R;
import me.ogabeezle.sponsy.Rest.ApiClient;
import me.ogabeezle.sponsy.Rest.ApiInterface;
import me.ogabeezle.sponsy.ui.home.HomeFragment;
import me.ogabeezle.sponsy.ui.login.LoginFragment;
import me.ogabeezle.sponsy.Model.SignUpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class SignupFragment extends Fragment {
    @Nullable
    private final int PICK_IMAGE_REQUEST=1902;
    private Uri filePath;
    private String uri;
    private String BASE_URL="gs://sponsy-e385e.appspot.com/";
    private ImageView insertPicture;
    FirebaseStorage storage;
    StorageReference storageReference;

    View v;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.signup_fragment, container, false);
        storage=FirebaseStorage.getInstance();
        uri = "";
        storageReference =storage.getReference();
        loadButton();
        return v;
    }

    void loadButton(){
        TextView daftarButton = v.findViewById(R.id.masuk_button);
        daftarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new LoginFragment());
            }
        });

        insertPicture = v.findViewById(R.id.insert_picture);
        insertPicture.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              chooseImage();
          }
        });

        ConstraintLayout signUpButton = (ConstraintLayout) v.findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vv) {
                EditText namaEvent = v.findViewById(R.id.nama_event);
                EditText alamatEvent = v.findViewById(R.id.alamat_event);
                EditText narahubungEvent = v.findViewById(R.id.narahubung_event);
                EditText alamatEmail = v.findViewById(R.id.alamat_email);
                EditText password = v.findViewById(R.id.password);

                if(namaEvent.getText().toString()==""||
                        alamatEvent.getText().toString()==""||
                        narahubungEvent.getText().toString()==""||
                        alamatEmail.getText().toString()==""||
                        password.getText().toString()==""||
                        uri==""){
                    return;
                }
                HashMap<String,String> datajson;
                datajson = new HashMap<>();
                datajson.put("name",namaEvent.getText().toString());
                datajson.put("email",alamatEmail.getText().toString());
                datajson.put("contactPerson",narahubungEvent.getText().toString());
                datajson.put("address",alamatEvent.getText().toString());
                datajson.put("password",password.getText().toString());
                datajson.put("imageUrl",uri);
                datajson.put("accountType","1");
                datajson.put("deskripsi","asdasda");
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<SignUpResponse> reg=mApiInterface.register(datajson);
                reg.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        Log.d(TAG, "onResponse: "+response.body().getCode()+response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage().toString());
                    }
                });
                loadFragment(new HomeFragment());
            }
        });
    }
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this.getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            String path="images/"+ UUID.randomUUID().toString();
            StorageReference ref = storageReference.child(path);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
            uri=path;
            Log.d(TAG, BASE_URL+path);
        }
    }

    private void chooseImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(MainActivity.getContextOfApplication().getContentResolver(), filePath);
                insertPicture.setImageBitmap(bitmap);
                uploadImage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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
