package br.com.futsalmanager.view;

import androidx.appcompat.app.AppCompatActivity;
import br.com.futsalmanager.R;
import br.com.futsalmanager.controller.HorarioController;
import br.com.futsalmanager.model.Horario;
import br.com.futsalmanager.util.TimePickerUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HorarioCadView extends AppCompatActivity {

    private Long mId;
    private HorarioController horarioController;
    private Spinner snHorarioCadMes, snHorarioCadDiaSemana;
    private EditText edtHorarioCadHoraJogo;
    private List<String> listaMeses = new ArrayList<>();
    private List<String> listaDiasSemana = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario_cad_view);
        snHorarioCadMes = (Spinner) findViewById(R.id.snHorarioCadMes);
        snHorarioCadDiaSemana = (Spinner) findViewById(R.id.snHorarioCadDiaSemana);
        edtHorarioCadHoraJogo = (EditText) findViewById(R.id.edtHorarioCadHoraJogo);
        Button btHorarioCadSalvar = (Button) findViewById(R.id.btHorarioCadSalvar);
        Button btHorarioCadHora = (Button) findViewById(R.id.btHorarioCadHora);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mId = bundle.getLong("id");
        horarioController = new HorarioController(HorarioCadView.this);
        listaMeses = Arrays.asList(getResources().getStringArray(R.array.lista_meses));
        listaDiasSemana = Arrays.asList(getResources().getStringArray(R.array.lista_dias_semana));
        ArrayAdapter<CharSequence> adapterMes = ArrayAdapter.createFromResource(this,
                R.array.lista_meses,R.layout.snipper_item);
        adapterMes.setDropDownViewResource(R.layout.spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterDiaSemana = ArrayAdapter.createFromResource(this,
                R.array.lista_dias_semana,R.layout.snipper_item);
        adapterDiaSemana.setDropDownViewResource(R.layout.spinner_dropdown_item);
        snHorarioCadMes.setAdapter(adapterMes);
        snHorarioCadDiaSemana.setAdapter(adapterDiaSemana);
        btHorarioCadHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerUtil().
                        setDateTimeField(HorarioCadView.this, edtHorarioCadHoraJogo);
            }
        });
        btHorarioCadSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarInformacoes();
                finish();
            }
        });
        carregarInformacoes();
    }

    private void carregarInformacoes() {
        if (mId > 0) {
            Horario horario = horarioController.carregaDadoById(mId);
            snHorarioCadMes.setSelection(listaMeses.indexOf(horario.getMes()));
            snHorarioCadDiaSemana.setSelection(listaDiasSemana.indexOf(horario.getDiasemana()));
            edtHorarioCadHoraJogo.setText(horario.getHora());
        }
    }

    private void salvarInformacoes() {
        if (mId > 0) {
            horarioController
                    .alteraRegistro(new Horario(mId,snHorarioCadMes.getSelectedItem().toString(),
                            snHorarioCadDiaSemana.getSelectedItem().toString(),
                            edtHorarioCadHoraJogo.getText().toString()));
        } else {
            horarioController
                    .insereDado(new Horario(snHorarioCadMes.getSelectedItem().toString(),
                            snHorarioCadDiaSemana.getSelectedItem().toString(),
                            edtHorarioCadHoraJogo.getText().toString()));
        }
    }


}
