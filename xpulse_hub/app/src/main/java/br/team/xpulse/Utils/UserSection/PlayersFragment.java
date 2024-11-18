package br.team.xpulse.Utils.UserSection;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.team.xpulse.Model.User;
import br.team.xpulse.R;


import java.util.ArrayList;
import java.util.List;

public class PlayersFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    public PlayersFragment() {
    }

    public static PlayersFragment newInstance() {
        return new PlayersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);


        recyclerView = view.findViewById(R.id.recycler_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adicionar mock de usuários
        List<User> users = getMockUsers();
        userAdapter = new UserAdapter(getContext(), users);
        recyclerView.setAdapter(userAdapter);

        return view;
    }

    // Método para gerar dados de usuários de mock
    private List<User> getMockUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("DevDuque", "Busy", "ADMIN"));
        users.add(new User("PedroHNO", "Available", "VIP"));
        users.add(new User("VicenteCSS", "Busy", "GUEST"));
        return users;
    }
}
