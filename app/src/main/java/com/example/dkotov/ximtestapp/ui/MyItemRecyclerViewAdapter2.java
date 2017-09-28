package com.example.dkotov.ximtestapp.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dkotov.ximtestapp.R;
import com.example.dkotov.ximtestapp.model.DataItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.example.dkotov.ximtestapp.model.DataItem} and makes a call to the
 * specified {@link DogsFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter2 extends RecyclerView.Adapter<MyItemRecyclerViewAdapter2.ViewHolder> {

    private static final String TAG = "XimTestApp: "+  MyItemRecyclerViewAdapter2.class.getSimpleName();

    private final List<DataItem> mValues;
    private final DogsFragment.OnListFragmentInteractionListener mListener;
    private final Fragment mFragment;

    public MyItemRecyclerViewAdapter2(List<DataItem> items,
                                      DogsFragment.OnListFragmentInteractionListener listener,
                                      Fragment fragment) {
        mValues = items;
        mListener = listener;
        mFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Picasso.with(mFragment.getContext()).load(holder.mItem.getUrl()).into(holder.mImageView);
        holder.mContentView.setText(mValues.get(position).getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }

                Intent intent = new Intent(mFragment.getContext(), DetailsActivity.class);
                intent.putExtra(MainActivity.IMG_URL, holder.mItem.getUrl());
                intent.putExtra(MainActivity.TEXT_CONTENT, holder.mItem.getTitle());
                Log.d(TAG, "Starting details activity: " +
                        intent.getStringExtra(MainActivity.IMG_URL + " "
                                + intent.getStringExtra(MainActivity.TEXT_CONTENT)));
                mFragment.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mContentView;
        public DataItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.item_image);
            mContentView = (TextView) view.findViewById(R.id.item_description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
