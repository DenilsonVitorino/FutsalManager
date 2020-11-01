package br.com.futsalmanager.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import br.com.futsalmanager.R;
import br.com.futsalmanager.controller.HorarioController;
import br.com.futsalmanager.model.Horario;
import br.com.futsalmanager.util.NumberFormat;
import br.com.futsalmanager.view.HorarioCadView;
import br.com.futsalmanager.view.HorarioView;

public class JogadorAdapter extends RecyclerView.Adapter<JogadorAdapter.MyViewHolder> {

    private Context mContext;
    private List<Horario> mData;

    public JogadorAdapter(Context mContext, List<Horario> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public JogadorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.horario_card,viewGroup,false);
        return new JogadorAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final JogadorAdapter.MyViewHolder myViewHolder, final int i) {
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
        myViewHolder.btHorarioCardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HorarioCadView.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id",mData.get(i).getId());
                intent.putExtras(bundle);
                mContext. startActivity(intent);
            }
        });

        myViewHolder.btHorarioCardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Deseja remover horário?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new HorarioController(mContext).deletaDado(mData.get(i).getId());
                                HorarioView.alimentarLista();
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
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
        Button btHorarioCardEdit,
                btHorarioCardDelete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lbHorarioTexto = (TextView) itemView.findViewById(R.id.lbHorarioTexto);
            cardView = (CardView) itemView.findViewById(R.id.jogador_card);
            llHorarioList = (LinearLayout) itemView.findViewById(R.id.llHorarioList);
            btHorarioCardEdit = (Button) itemView.findViewById(R.id.btHorarioCardEdit);
            btHorarioCardDelete = (Button) itemView.findViewById(R.id.btHorarioCardDelete);
        }
    }
}