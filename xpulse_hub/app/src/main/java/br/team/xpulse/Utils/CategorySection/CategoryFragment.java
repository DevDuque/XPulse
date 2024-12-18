package br.team.xpulse.Utils.CategorySection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.team.xpulse.R;

public class CategoryFragment extends Fragment implements ButtonAdapter.OnTagSelectionListener {

    private List<String> selectedTags;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carousel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        String[] buttonTexts = getResources().getStringArray(R.array.button_texts);
        ButtonAdapter adapter = new ButtonAdapter(buttonTexts, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTagSelectionChanged(List<String> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public List<String> getSelectedTags() {
        return selectedTags;
    }
}
