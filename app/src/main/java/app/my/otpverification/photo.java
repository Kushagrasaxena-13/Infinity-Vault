package app.my.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import app.my.otpverification.databinding.ActivityPhotoBinding;

public class photo extends AppCompatActivity {

//    private ActivityPhotoBinding binding;
    private DatabaseReference firebaseDatabase;
    private String firebaseAuth;
    public Context mcontext;


    ImageView photo_,back;
    Uri uri;



//    public photo(Context mcontext) {
//        this.mcontext = mcontext;
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        photo_ = findViewById(R.id.photo);
        back = findViewById(R.id.imageView3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(photo.this,MainActivity.class));
            }
        });
//        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        String str = getIntent().getStringExtra("image_url");
        firebaseAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Image").child(firebaseAuth);
        mcontext = getApplicationContext();

//        uri = Uri.parse(str);

        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Model post = snapshot.getValue(Model.class);


                    if(post.getImageUrl().equals(str)){
                        try {
//                            Toast.makeText(getApplicationContext(),,Toast.LENGTH_LONG).show();
                            Glide.with(mcontext).load(post.getImageUrl()).into(photo_);
//                            photo_.setImageURI(uri);



                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });









    }
}