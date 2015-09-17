package be.vdab.cv_hubert_bullen.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.vdab.cv_hubert_bullen.R;


public class FragmentOneGeneralInfo extends Fragment{

    public FragmentOneGeneralInfo() {
    }

    public static FragmentOneGeneralInfo createNewFragmentOne() {

        return new FragmentOneGeneralInfo();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment

        return inflater.inflate(R.layout.frag_one_general, container, false);

    }
}
