package br.com.futsalmanager.view;

import androidx.appcompat.app.AppCompatActivity;
import br.com.futsalmanager.R;
import br.com.futsalmanager.controller.HorarioController;
import br.com.futsalmanager.model.Horario;
import br.com.futsalmanager.util.TimePickerUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class HorarioCadView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario_cad_view);
        final Spinner snHorarioCadMes, snHorarioCadDiaSemana;
        snHorarioCadMes = (Spinner) findViewById(R.id.snHorarioCadMes);
        snHorarioCadDiaSemana = (Spinner) findViewById(R.id.snHorarioCadDiaSemana);
        final EditText edtHorarioCadHoraJogo = (EditText) findViewById(R.id.edtHorarioCadHoraJogo);
        Button btHorarioCadSalvar = (Button) findViewById(R.id.btHorarioCadSalvar);
        Button btHorarioCadHora = (Button) findViewById(R.id.btHorarioCadHora);
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
                String msg = new HorarioController(HorarioCadView.this)
                        .insereDado(new Horario(snHorarioCadMes.getSelectedItem().toString(),
                                        snHorarioCadDiaSemana.getSelectedItem().toString(),
                                        edtHorarioCadHoraJogo.getText().toString()));
                finish();
            }
        });
    }
}
