package app.my.otpverification;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class My_postAdapter extends RecyclerView.Adapter<My_postAdapter.ViewHolder>{

    public Context mcontext;

    public static final int PICK_IMAGE = 1 ;
    Bitmap yourBitmap ;

    public List<Model> mpost;

    public My_postAdapter(Context mcontext, List<Model> mpost) {
        this.mcontext = mcontext;
        this.mpost = mpost;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.post_item,parent,false);
        return new My_postAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull My_postAdapter.ViewHolder holder, int position) {
        Model post = mpost.get(position);
        Glide.with(mcontext).load(post.getImageUrl()).into(holder.image_profile);

        holder.image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {




//                    Bitmap resized = Bitmap. createScaledBitmap ( yourBitmap , 400 , 400 , true ) ;
//                    holder.image_profile .setImageBitmap(resized) ;




                    Intent intent = new Intent(mcontext, photo.class);
                    intent.putExtra("image_url", post.getImageUrl());
                    mcontext.startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(mcontext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mpost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public com.makeramen.roundedimageview.RoundedImageView image_profile;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_profile = itemView.findViewById(R.id.postimage);





        }

    }
}
