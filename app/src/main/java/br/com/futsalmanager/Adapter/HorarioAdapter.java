package br.com.futsalmanager.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import br.com.futsalmanager.R;
import br.com.futsalmanager.model.Horario;
import br.com.futsalmanager.util.NumberFormat;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.MyViewHolder> {

    private Context mContext;
    private List<Horario> mData;

    public HorarioAdapter(Context mContext, List<Horario> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public HorarioAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.horario_card,viewGroup,false);
        return new HorarioAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HorarioAdapter.MyViewHolder myViewHolder, final int i) {
        if (new NumberFormat().verificarSePar(i)) {
            myViewHolder.llHorarioList.setBackgroundColor(Color.rgb(255,255,255));
        } else {
            myViewHolder.llHorarioList.setBackgroundColor(Color.rgb(240,240,240));
        }
        myViewHolder.lbHorarioTexto.setText(
                //"Código: " + mData.get(i).getId() +
                "Mês: " + mData.get(i).getMes() +
                "\nDia da Semana: " + mData.get(i).getDiasemana() +
                "\nHorário: " + mData.get(i).getHora()

        );
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView lbHorarioTexto;
        LinearLayout llHorarioList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lbHorarioTexto = (TextView) itemView.findViewById(R.id.lbHorarioTexto);
            cardView = (CardView) itemView.findViewById(R.id.horario_card);
            llHorarioList = (LinearLayout) itemView.findViewById(R.id.llHorarioList);
        }
    }
}
