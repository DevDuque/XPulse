package br.team.xpulse.Utils.ActivitySection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.team.xpulse.Model.Activity;
import br.team.xpulse.R;

public class ActivityFragment extends Fragment {

    private String activityName;
    private String type;
    private Integer photoID;
    private Integer maxPlayers;

    public ActivityFragment() {

    }

    public static ActivityFragment newInstance(String activityName, String type, Integer photoID, Integer maxPlayers) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        args.putString("activityName", activityName);
        args.putString("type", type);
        args.putInt("photoID", photoID);
        args.putInt("maxPlayers", maxPlayers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            activityName = getArguments().getString("activityName");
            type = getArguments().getString("type");
            photoID = getArguments().getInt("photoID");
            maxPlayers = getArguments().getInt("maxPlayers");
        }
    }

    // Interface para comunicar cliques no fragment
    public interface OnFragmentClickListener {
        void onFragmentClick(Activity activity);
    }

    private OnFragmentClickListener listener;

    // Método para configurar o listener
    public void setOnFragmentClickListener(OnFragmentClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details, container, false);

        ImageView imgActivity = view.findViewById(R.id.img_activity);
        TextView lblTitle = view.findViewById(R.id.lblTitle);
        TextView lblType = view.findViewById(R.id.lblType);
        TextView lblMax = view.findViewById(R.id.lblMax);

        imgActivity.setImageResource(photoID);
        lblTitle.setText(activityName);
        lblType.setText(type);
        lblMax.setText("Max. " + maxPlayers);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Activity activity = new Activity(activityName, type, maxPlayers, photoID);
                    listener.onFragmentClick(activity);
                }
            }
        });

        return view;
    }
}