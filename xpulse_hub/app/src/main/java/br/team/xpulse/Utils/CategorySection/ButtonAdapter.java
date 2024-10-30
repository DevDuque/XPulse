package br.team.xpulse.Utils.CategorySection;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.team.xpulse.R;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

    private final String[] buttonTexts;
    private final List<String> selectedTags = new ArrayList<>();
    private final OnTagSelectionListener listener;

    public ButtonAdapter(String[] buttonTexts, OnTagSelectionListener listener) {
        this.buttonTexts = buttonTexts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_item, parent, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        String buttonText = buttonTexts[position];
        holder.button.setText(buttonText);

        // Define a cor inicial com base no status de seleção
        updateBtnColor(holder.button, selectedTags.contains(buttonText));

        holder.button.setOnClickListener(v -> {
            if (selectedTags.contains(buttonText)) {
                // Se o botão já estiver selecionado, desseleciona-o
                selectedTags.remove(buttonText);
                holder.button.setSelected(false);
            } else {
                // Se o botão não estiver selecionado e já tivermos duas tags, mostra uma mensagem
                if (selectedTags.size() < 2) {
                    selectedTags.add(buttonText);
                    holder.button.setSelected(true);
                } else {
                    // Mostra uma mensagem se já tiver selecionado duas tags
                    Toast.makeText(holder.button.getContext(), "Você pode selecionar no máximo 2 tags.", Toast.LENGTH_SHORT).show();
                    return; // Sai se não for possível selecionar
                }
            }

            // Atualiza a cor do botão com base no status de seleção
            updateBtnColor(holder.button, holder.button.isSelected());
            listener.onTagSelectionChanged(new ArrayList<>(selectedTags));
        });
    }


    // Método auxiliar para atualizar a cor do botão com base no status de seleção
    private void updateBtnColor(Button button, boolean isSelected) {
        int color = isSelected
                ? ContextCompat.getColor(button.getContext(), R.color.btn_end_color)
                : ContextCompat.getColor(button.getContext(), R.color.stroke_color);
        button.setBackgroundTintList(ColorStateList.valueOf(color));
    }



    @Override
    public int getItemCount() {
        return buttonTexts.length;
    }

    static class ButtonViewHolder extends RecyclerView.ViewHolder {
        Button button;

        ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }

    public interface OnTagSelectionListener {
        void onTagSelectionChanged(List<String> selectedTags);
    }
}
