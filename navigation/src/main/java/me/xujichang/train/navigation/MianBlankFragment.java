package me.xujichang.train.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.xujichang.train.navigation.databinding.FragmentMainTwoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MianBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MianBlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MianBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MianBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MianBlankFragment newInstance(String param1, String param2) {
        MianBlankFragment fragment = new MianBlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vView = inflater.inflate(R.layout.fragment_mian_blank, container, false);
        vView.findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle vBundle = new Bundle();
                NavOptions vOptions = new NavOptions
                        .Builder()
                        .build();
                Navigation.findNavController(v).navigate(R.id.action_mianBlankFragment_to_mainTwoFragment, vBundle, vOptions);
            }
        });
        return vView;
    }
}