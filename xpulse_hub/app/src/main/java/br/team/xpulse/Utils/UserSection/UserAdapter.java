package br.team.xpulse.Utils.UserSection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.team.xpulse.Model.User;
import br.team.xpulse.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.nameTextView.setText(user.getName());
        holder.availabilityTextView.setText(user.getAvailability());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    // ViewHolder que mantém as views de cada item
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, availabilityTextView;
        ImageView imgActivity;

        public UserViewHolder(View itemView) {
            super(itemView);
            imgActivity = itemView.findViewById(R.id.img_activity);
            nameTextView = itemView.findViewById(R.id.lblName);
            availabilityTextView = itemView.findViewById(R.id.lblType);
        }
    }
}
