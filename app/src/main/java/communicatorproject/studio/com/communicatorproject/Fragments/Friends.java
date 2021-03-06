package communicatorproject.studio.com.communicatorproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import communicatorproject.studio.com.communicatorproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Friends extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public Friends() {
        // Required empty public constructor
    }

    public static Friends newInstancenewInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Friends fragment = new Friends();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

}
