package br.team.xpulse.CategorySection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.team.xpulse.R;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

    private final String[] buttonTexts;

    public ButtonAdapter(String[] buttonTexts) {
        this.buttonTexts = buttonTexts;
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
        holder.button.setText(buttonTexts[position]);
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
}
