package be.vdab.cv_hubert_bullen.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.vdab.cv_hubert_bullen.R;

/**
 * Created by jeansmits on 17/09/15.
 */
public class FragmentSixWorkingExp extends Fragment {

    public FragmentSixWorkingExp() {
    }

    public static FragmentSixWorkingExp createNewFragmentSix() {

        return new FragmentSixWorkingExp();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment

        return inflater.inflate(R.layout.frag_six_work, container, false);

    }
}