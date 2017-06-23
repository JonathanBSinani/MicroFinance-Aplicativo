package com.a11group.app_micro_finance_v1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonathan on 11/04/2017.
 * esta classe cria somente o banco de dados
 */

public class DatabaseGenerator extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "bancoInterno.db";
    public static final String RECEITA = "receita";
    public static final String ID = "_id";
    public static final String DESCRICAO = "descricao";
    public static final String TIPO = "autor";
    public static final String DATA_REC = "editora";
    public static final int VERSAO = 1;

    public DatabaseGenerator(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + RECEITA +
                "(" +
                    ID + "integer primary key autoincrement" +
                    DESCRICAO + "text" +
                    TIPO + "text" +
                    DATA_REC + "text" +
                ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exist" + RECEITA);
        onCreate(sqLiteDatabase);
    }
}
