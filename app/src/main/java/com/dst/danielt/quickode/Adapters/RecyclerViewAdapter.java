package com.dst.danielt.quickode.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dst.danielt.quickode.Model.RecyclerViewData;
import com.dst.danielt.quickode.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by danielT on 09/08/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    Context context;
    private List<RecyclerViewData> data;
    private LayoutInflater inflater;
    public RecyclerViewAdapter(List<RecyclerViewData> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recyclev_display,parent,false);
        DataViewHolder display = new DataViewHolder(view);
        return display;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        RecyclerViewData rvdata = data.get(position);

        Picasso.with(context).load(rvdata.getImage()).into(holder.img);
        holder.txtVAlbumId.setText(" Album id: "+String.valueOf(rvdata.getAlbumId() ) );
        holder.txtVImgId.setText(String.valueOf(rvdata.getImageID() ) );
        holder.txtVImgTitle.setText(rvdata.getImageTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class DataViewHolder extends RecyclerView.ViewHolder {

        TextView txtVImgId;
        TextView txtVImgTitle;
        TextView txtVAlbumId;
        ImageView img;

        public DataViewHolder(View itemView) {
            super(itemView);
            txtVImgId = (TextView) itemView.findViewById(R.id.txtVImgId);
            txtVImgTitle = (TextView) itemView.findViewById(R.id.txtVImgTitle);
            txtVAlbumId = (TextView) itemView.findViewById(R.id.txtVAlbumId);
            img = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }

}
