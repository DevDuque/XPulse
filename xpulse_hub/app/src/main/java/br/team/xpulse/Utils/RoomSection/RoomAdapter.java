package br.team.xpulse.Utils.RoomSection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.team.xpulse.Model.Room;
import br.team.xpulse.R;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private final List<Room> rooms;
    private final OnRoomClickListener onRoomClickListener;

    public RoomAdapter(List<Room> rooms, OnRoomClickListener onRoomClickListener) {
        this.rooms = rooms;
        this.onRoomClickListener = onRoomClickListener; // Inicializando o listener
    }

    // Interface para escutar cliques em um item
    public interface OnRoomClickListener {
        void onRoomClick(Room room); // Método a ser chamado quando um item for clicado
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = rooms.get(position);
        holder.bind(room, onRoomClickListener); // Passando o listener para o ViewHolder
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblTitle, lblType, lblMax, lblDate;

        ImageView imgActivity;

        public ViewHolder(View itemView) {
            super(itemView);
            lblTitle = itemView.findViewById(R.id.lblTitle);
            lblType = itemView.findViewById(R.id.lblType);
            lblMax = itemView.findViewById(R.id.lblMax);
            lblDate = itemView.findViewById(R.id.lblDate);
            imgActivity = itemView.findViewById(R.id.img_activity);
        }

        public void bind(Room room, OnRoomClickListener listener) {
            lblTitle.setText(room.getName());
            lblType.setText(room.getTags().get(0));
            lblMax.setText("Max. " + room.getActivity().getMaxPlayers());
            SimpleDateFormat formatter = new SimpleDateFormat("EEE - dd/MM - HH:mm", Locale.getDefault());
            lblDate.setText(formatter.format(room.getDateTime()));

            imgActivity.setImageResource(room.getActivity().getPhotoID());

            lblType.setText(String.join(", ", room.getTags()));

            // Configurando o clique no item
            itemView.setOnClickListener(v -> listener.onRoomClick(room));
        }
    }
}
