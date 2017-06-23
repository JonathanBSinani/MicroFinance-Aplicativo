package com.a11group.app_micro_finance_v1.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a11group.app_micro_finance_v1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialSimulatorFragment extends Fragment {


    public SocialSimulatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simular_previdencia, container, false);
    }

}
