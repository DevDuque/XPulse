package br.team.xpulse.Utils.ActivitySection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.team.xpulse.Model.Activity;
import br.team.xpulse.R;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private final List<Activity> activities;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Activity activity);
    }

    public ActivityAdapter(Context context, List<Activity> activities, OnItemClickListener listener) {
        this.activities = activities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Activity activity = activities.get(position);

        // Aqui você cria o fragmento correspondente
        ActivityFragment fragment = ActivityFragment.newInstance(activity.getActivityName(), activity.getType(), activity.getPhotoID(), activity.getMaxPlayers());

        // Configurando o listener para o fragmento
        fragment.setOnFragmentClickListener(listener::onItemClick);

        holder.bind(activity, listener);
    }


    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgActivity;
        TextView lblTitle, lblType, lblMax;
        ImageButton imgForward;

        public ViewHolder(View itemView) {
            super(itemView);
            imgActivity = itemView.findViewById(R.id.img_activity);
            lblTitle = itemView.findViewById(R.id.lblTitle);
            lblType = itemView.findViewById(R.id.lblType);
            lblMax = itemView.findViewById(R.id.lblMax);
            imgForward = itemView.findViewById(R.id.imgForward);
        }

        public void bind(final Activity activity, final OnItemClickListener listener) {
            imgActivity.setImageResource(activity.getPhotoID());
            lblTitle.setText(activity.getActivityName());
            lblType.setText(activity.getType());
            lblMax.setText(String.valueOf(activity.getMaxPlayers()));

            itemView.setOnClickListener(v -> listener.onItemClick(activity));

            imgForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(activity);
                }
            });
        }
    }
}

