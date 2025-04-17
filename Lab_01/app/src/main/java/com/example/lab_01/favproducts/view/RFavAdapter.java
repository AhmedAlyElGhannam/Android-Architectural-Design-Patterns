package com.example.lab_01.favproducts.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lab_01.R;
import com.example.lab_01.allproducts.view.OnFavouriteClickListener;
import com.example.lab_01.model.pojo.Product;

import java.util.List;

public class RFavAdapter extends RecyclerView.Adapter<RFavAdapter.ViewHolder>{

    private final Context context;
    private List<Product> products;
    private static final String TAG = "RADAPTER";
    private OnDeleteClickListener delListener;

    public RFavAdapter(Context context, List<Product> products, OnDeleteClickListener __listener) {
        this.context = context;
        this.products = products;
        this.delListener = __listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private TextView txtViewTitle;
        private TextView txtViewDesc;
        private TextView txtViewPrice;
        private RatingBar ratingBar;
        private ImageView imgView;
        public ConstraintLayout productLayout;
        private Button delBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            txtViewTitle = layout.findViewById(R.id.txtViewTitle);
            txtViewDesc = layout.findViewById(R.id.txtViewDesc);
            txtViewPrice = layout.findViewById(R.id.txtViewPrice);
            ratingBar = layout.findViewById(R.id.ratingBar);
            imgView = layout.findViewById(R.id.imgView);
            productLayout = layout.findViewById(R.id.productLayout);
            delBtn = layout.findViewById(R.id.delBtn);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.fav_product, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder: ");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtViewTitle.setText(products.get(position).getTitle());
        holder.txtViewDesc.setText(products.get(position).getDescription());
        holder.txtViewPrice.setText(products.get(position).getPrice());

        holder.ratingBar.setRating(products.get(position).getRating());

//        new DownloadTask(holder.imgView).execute(products.get(position).getThumbnail());

        Glide.with(context).load(products.get(position).getThumbnail())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.imgView);

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delListener.onDeleteProductClick(products.get(position));
            }
        });

        Log.i(TAG, "onBindViewHolder: ");
    }

    @NonNull
    @Override
    public int getItemCount() {
        return products.size();
    }

}
