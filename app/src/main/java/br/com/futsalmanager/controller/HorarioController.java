package br.com.futsalmanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.futsalmanager.dao.CriaBanco;
import br.com.futsalmanager.model.Horario;

public class HorarioController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public HorarioController(Context context){
        banco = new CriaBanco(context);
    }

    public List<Horario> carregaDados(){
        List<Horario> list = new ArrayList<>();
        Cursor cursor;
        String[] campos =  {"id","mes","diasemana","hora"};
        db = banco.getReadableDatabase();
        cursor = db.query("horarios", campos, null, null, null, null, null, null);
        if(cursor!=null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    list.add(new Horario(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                } while (cursor.moveToNext());
            }
        }
        db.close();
        return list;
    }

    public Horario carregaDadoById(Long id){
        Horario horario = new Horario();
        Cursor cursor;
        String[] campos =  {"id","mes","diasemana","hora"};
        String where = "id=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("horarios",campos,where, null, null, null, null, null);
        if(cursor!=null) cursor.moveToFirst();
        horario.setId(cursor.getLong(0));
        horario.setMes(cursor.getString(1));
        horario.setDiasemana(cursor.getString(2));
        horario.setHora(cursor.getString(3));
        db.close();
        return horario;
    }

    public String insereDado(Horario horario){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("mes", horario.getMes());
        valores.put("diasemana", horario.getDiasemana());
        valores.put("hora", horario.getHora());
        resultado = db.insert("horarios", null, valores);
        db.close();
        if (resultado ==-1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }

    public void alteraRegistro(Horario horario){
        ContentValues valores;
        String where;
        db = banco.getWritableDatabase();
        where = "id=" + horario.getId();
        valores = new ContentValues();
        valores.put("mes", horario.getMes());
        valores.put("diasemana", horario.getDiasemana());
        valores.put("hora", horario.getHora());
        db.update("horarios",valores,where,null);
        db.close();
    }

    public void deletaDado(Long id){
        String where =  "id=" + id;
        db = banco.getReadableDatabase();
        db.delete("horarios",where,null);
        db.close();
    }
}
