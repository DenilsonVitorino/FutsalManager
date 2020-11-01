package br.com.futsalmanager.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.futsalmanager.dao.CriaBanco;
import br.com.futsalmanager.model.Horario;
import br.com.futsalmanager.model.JogadorXHorario;

public class JogadorXHorarioController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public JogadorXHorarioController(Context context){
        banco = new CriaBanco(context);
    }

    public List<JogadorXHorario> carregaDados(){
        List<JogadorXHorario> list = new ArrayList<>();
        Cursor cursor;
        String[] campos =  {"id","idhorario","idjogador","nomejogador","camiseta"};
        db = banco.getReadableDatabase();
        cursor = db.query("jogadorxhorario", campos, null, null, null, null, null, null);
        if(cursor!=null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    list.add(new JogadorXHorario(cursor.getLong(0),cursor.getLong(1),cursor.getLong(2),cursor.getString(3),cursor.getInt(4)));
                } while (cursor.moveToNext());
            }
        }
        db.close();
        return list;
    }

    public JogadorXHorario carregaDadoById(Long id){
        JogadorXHorario jogadorXHorario = new JogadorXHorario();
        Cursor cursor;
        String[] campos =  {"id","idhorario","idjogador","nomejogador","camiseta"};
        String where = "id=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("jogadorxhorario",campos,where, null, null, null, null, null);
        if(cursor!=null) cursor.moveToFirst();
        jogadorXHorario.setId(cursor.getLong(0));
        jogadorXHorario.setIdHorario(cursor.getLong(1));
        jogadorXHorario.setIdJogador(cursor.getLong(2));
        jogadorXHorario.setNomeJogador(cursor.getString(3));
        jogadorXHorario.setCamiseta(cursor.getInt(4));
        db.close();
        return jogadorXHorario;
    }

    public String insereDado(JogadorXHorario jogadorXHorario){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("idhorario", jogadorXHorario.getIdHorario());
        valores.put("idjogador", jogadorXHorario.getIdJogador());
        valores.put("nomejogador", jogadorXHorario.getNomeJogador());
        valores.put("camiseta", jogadorXHorario.getCamiseta());
        resultado = db.insert("jogadorxhorario", null, valores);
        db.close();
        if (resultado ==-1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }

    public void alteraRegistro(JogadorXHorario jogadorXHorario){
        ContentValues valores;
        String where;
        db = banco.getWritableDatabase();
        where = "id=" + jogadorXHorario.getId();
        valores = new ContentValues();
        valores.put("idhorario", jogadorXHorario.getIdHorario());
        valores.put("idjogador", jogadorXHorario.getIdJogador());
        valores.put("nomejogador", jogadorXHorario.getNomeJogador());
        valores.put("camiseta", jogadorXHorario.getCamiseta());
        db.update("jogadorxhorario",valores,where,null);
        db.close();
    }

    public void deletaDado(Long id){
        String where =  "id=" + id;
        db = banco.getReadableDatabase();
        db.delete("jogadorxhorario",where,null);
        db.close();
    }
}
