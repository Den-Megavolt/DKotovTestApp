package com.example.dkotov.ximtestapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dkotov.ximtestapp.R;
import com.example.dkotov.ximtestapp.XimTestApp;
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
    private static final String STATE = "state";

    private OnListFragmentInteractionListener mListener;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private Context context;

    private List<DataItem> cats = new ArrayList<>();

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
//            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(STATE);
//            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.request_progress);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        // Set the adapter
        context = rootView.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
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
        Log.d(TAG, "OnStart: Cats = " + cats.toString());
        if (cats != null && !cats.isEmpty()) {
            mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(cats, mListener, this));
        } else {
            mRecyclerView.setVisibility(View.GONE);
            showProgress(true);
            DataReceiver.receiveData(getString(R.string.query_cat));
        }

        if (XimTestApp.getCatsPosition() != 0) {
            ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .scrollToPositionWithOffset(XimTestApp.getCatsPosition(), 0);
            XimTestApp.setCatsPosition(0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        XimTestApp.setCatsPosition(((LinearLayoutManager)
                mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.d(TAG, "Event received: " + event.getCode() + " " + event.getType() + " " +
                event.getDataItems().toString());
        showProgress(false);
        mRecyclerView.setVisibility(View.VISIBLE);
        if (event.getCode() == 0 && event.getType().equalsIgnoreCase(getString(R.string.query_cat))) {
            cats = event.getDataItems();
            mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(cats, mListener, this));
        }
    }

    private void showProgress(final boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
//        state.putParcelable(STATE, mRecyclerView.getLayoutManager().onSaveInstanceState());
    }

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
