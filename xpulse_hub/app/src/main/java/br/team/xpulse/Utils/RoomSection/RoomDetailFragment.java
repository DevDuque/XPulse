//package br.team.xpulse.Utils.RoomSection;
//
//import android.os.Bundle;
//import androidx.fragment.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import br.team.xpulse.Model.Room;
//import br.team.xpulse.R;
//
//public class RoomDetailFragment extends Fragment {
//
//    private static final String ARG_ROOM = "selectedRoom";
//
//    public RoomDetailFragment() {

//    }
//
//    public static RoomDetailFragment newInstance(Room room) {
//        RoomDetailFragment fragment = new RoomDetailFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_ROOM, room);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_room_detail, container, false);
//
//        // Obter a sala selecionada
//        if (getArguments() != null) {
//            Room room = (Room) getArguments().getSerializable(ARG_ROOM);
//
//            // Inicializar os componentes de visualização
//            ImageView imageRoom = view.findViewById(R.id.imageRoom);
//            TextView txtRoomName = view.findViewById(R.id.txtRoomName);
//            TextView txtServerLink = view.findViewById(R.id.txtServerLink);
//            TextView txtDescription = view.findViewById(R.id.txtDescription);
//
//            // Definir os dados nos componentes
//            txtRoomName.setText(room.getName());
//            txtServerLink.setText(room.getServerLink()); // Assumindo que você tenha um método para obter o link
//            txtDescription.setText(room.getDescription()); // Assumindo que você tenha um método para obter a descrição
//
//            // Aqui você deve carregar a imagem da sala usando Glide ou Picasso
//            // Glide.with(this).load(room.getImageUrl()).into(imageRoom);
//        }
//
//        return view;
//    }
//}
