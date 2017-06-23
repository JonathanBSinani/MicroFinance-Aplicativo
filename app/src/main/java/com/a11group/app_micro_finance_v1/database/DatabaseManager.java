package com.a11group.app_micro_finance_v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

/**
 * Created by Jonathan on 11/04/2017.
 *
 * A classe BancoController será responsável por controlar as manipulações ao banco
 */

public class DatabaseManager {

    private SQLiteDatabase db;
    private DatabaseGenerator banco;

    public DatabaseManager(Context context){
        banco = new DatabaseGenerator(context);
    }

    public String insereDado(String descricao, String tipo, String data_rec){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DatabaseGenerator.DESCRICAO, descricao);
        valores.put(DatabaseGenerator.TIPO, tipo);
        valores.put(DatabaseGenerator.DATA_REC, data_rec);

        resultado = db.insert(DatabaseGenerator.RECEITA, null, valores);
        db.close();

        if (resultado == 1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }


    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME_BANCO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.RECEITA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
