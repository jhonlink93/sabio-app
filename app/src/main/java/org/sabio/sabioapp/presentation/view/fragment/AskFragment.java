package org.sabio.sabioapp.presentation.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sabio.sabioapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AskFragment extends BaseFragment {


    public AskFragment() {
        // Required empty public constructor
    }

    public static AskFragment getInstance() {
        return new AskFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask, container, false);
    }


}
