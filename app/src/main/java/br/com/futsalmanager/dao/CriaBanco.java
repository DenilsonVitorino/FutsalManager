package br.com.futsalmanager.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    public CriaBanco(Context context){
        super(context, "banco.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabelaHorario = "CREATE TABLE horarios("
                + "id integer primary key autoincrement,"
                + "mes text,"
                + "hora text,"
                + "diasemana text)";
        String tabelaJogador = "CREATE TABLE jogadores("
                + "id integer primary key autoincrement,"
                + "nome text)";
        db.execSQL(tabelaHorario);
        db.execSQL(tabelaJogador);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS horarios");
        db.execSQL("DROP TABLE IF EXISTS jogadores");
        onCreate(db);
    }
}
