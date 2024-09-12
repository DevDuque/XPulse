package br.team.xpulse.ActivitySection;

import static android.app.PendingIntent.getActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import br.team.xpulse.Model.Activity;
import br.team.xpulse.Model.Room;
import br.team.xpulse.R;

public class ActivitySelectionFragment extends BottomSheetDialogFragment {

    private OnActivitySelectedListener listener;
    private List<Activity> activities;
    private List<Room> rooms; // Your list of rooms

    public interface OnActivitySelectedListener {
        void onActivitySelected(Activity activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_selection, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        activities = getActivities();

        ActivityAdapter adapter = new ActivityAdapter(getContext(), activities, new ActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Activity activity) {
                if (listener != null) {
                    listener.onActivitySelected(activity);
                }
                dismiss();
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void setOnActivitySelectedListener(OnActivitySelectedListener listener) {
        this.listener = listener;
    }

    private List<Activity> getActivities() {
        // Placeholder data
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("League of Legends", "MOBA", 5, R.drawable.ic_league));
        activities.add(new Activity("Valorant", "FPS", 5, R.drawable.ic_valorant));
        activities.add(new Activity("Warzone", "FPS", 5, R.drawable.ic_warzone));

        // Add more activities as needed
        return activities;
    }
}