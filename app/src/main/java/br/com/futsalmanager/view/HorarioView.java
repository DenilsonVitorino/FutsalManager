package br.com.futsalmanager.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.futsalmanager.Adapter.HorarioAdapter;
import br.com.futsalmanager.R;
import br.com.futsalmanager.controller.HorarioController;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HorarioView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario_view);
        alimentarLista();
    }

    private void alimentarLista() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerHorarios);
        HorarioAdapter horarioAdapter = new HorarioAdapter(HorarioView.this, new HorarioController(HorarioView.this).carregaDados());
        recyclerView.setLayoutManager(new GridLayoutManager(HorarioView.this,1));
        recyclerView.setAdapter(horarioAdapter);
    }

    private void chamarCadastro() {
        Intent intent = new Intent(this, HorarioCadView.class);
        Bundle bundle = new Bundle();
        bundle.putLong("id",new Long(0));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        alimentarLista();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.horario_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: chamarCadastro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}