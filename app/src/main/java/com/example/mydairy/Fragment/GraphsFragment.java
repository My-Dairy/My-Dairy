package com.example.mydairy.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mydairy.R;
import com.example.mydairy.adapter.GraphAdapter;
import com.example.mydairy.specific.GraphFat;
import com.example.mydairy.specific.GraphQty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraphsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView listView;
    private String GraphTitle[] = {
            "Fat",
            "Quantity",
            "Amount",
            "Graph4 with average",
            "Graph5 with average",
            "Graph6 with average",
            "Graph7 with average",
    };

    private Integer GraphImage[] = {
            R.drawable.cow1,
            R.drawable.cow1,
            R.drawable.cow1,
            R.drawable.cow1,
            R.drawable.cow1,
            R.drawable.cow1,
            R.drawable.cow1
    };

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GraphsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraphsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphsFragment newInstance(String param1, String param2) {
        GraphsFragment fragment = new GraphsFragment();
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
        View root = inflater.inflate(R.layout.fragment_graphs, container, false);

        ListView listView=(ListView)root.findViewById(R.id.list_graph);

        GraphAdapter customCountryList = new GraphAdapter(getActivity(), GraphTitle, GraphImage);
        listView.setAdapter(customCountryList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==0)
                {
                    Intent intent = new Intent(getActivity(), GraphFat.class);
                    startActivity(intent);
                }
                else if(position==1)
                {
                    Intent intent = new Intent(getActivity(), GraphQty.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getActivity(), GraphFat.class);
                    startActivity(intent);
                }
            }
        });

        return root;
    }
}