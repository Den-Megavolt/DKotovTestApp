package com.example.dkotov.ximtestapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dkotov.ximtestapp.R;
import com.example.dkotov.ximtestapp.model.DataItem;
import com.example.dkotov.ximtestapp.model.MessageEvent;
import com.example.dkotov.ximtestapp.util.DataReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CatsFragment extends Fragment {

    private static final String TAG = "XimTestApp: " + CatsFragment.class.getSimpleName();
    private static final String CAT_POSITION = "cat position";

    private OnListFragmentInteractionListener mListener;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private Context mContext;

    private List<DataItem> mCats = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CatsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CatsFragment newInstance() {
        CatsFragment fragment = new CatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//
//        if (savedInstanceState != null) {
//            ((LinearLayoutManager) mRecyclerView.getLayoutManager())
//                    .scrollToPositionWithOffset(savedInstanceState.getInt(CAT_POSITION), 0);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.request_progress);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        // Set the adapter
        mContext = rootView.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d(TAG, "OnStart: Cats = " + mCats.toString());
        if (mCats != null && !mCats.isEmpty()) {
            mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(mCats, mListener, this));
        } else {
            mRecyclerView.setVisibility(View.GONE);
            showProgress(true);
            DataReceiver.receiveData(getString(R.string.query_cat));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called, data to be restored: "
                + (PreferenceManager.getDefaultSharedPreferences(getContext())
                .getInt(CAT_POSITION, 0)));
        ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                .scrollToPositionWithOffset(PreferenceManager.getDefaultSharedPreferences(getContext())
                        .getInt(CAT_POSITION, 0), 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .edit().putInt(CAT_POSITION, ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                .findFirstCompletelyVisibleItemPosition()).apply();
        Log.d(TAG, "SharedPrefs are to be saved: " +
                (PreferenceManager.getDefaultSharedPreferences(getContext())
                        .getInt(CAT_POSITION, 0)));
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.d(TAG, "Event received: " + event.getmCode() + " " + event.getmType() + " " +
                event.getmDataItems().toString());
        showProgress(false);
        mRecyclerView.setVisibility(View.VISIBLE);
        if (event.getmCode() == 0 && event.getmType().equalsIgnoreCase(getString(R.string.query_cat))) {
            mCats = event.getmDataItems();
            mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(mCats, mListener, this));
        }
    }

    private void showProgress(final boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

//    @Override
//    public void onSaveInstanceState(Bundle state) {
//        super.onSaveInstanceState(state);
//        state.putInt(CAT_POSITION, ((LinearLayoutManager) mRecyclerView.getLayoutManager())
//                .findFirstCompletelyVisibleItemPosition());
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DataItem item);
    }
}
