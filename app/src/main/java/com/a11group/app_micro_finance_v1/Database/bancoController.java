package com.a11group.app_micro_finance_v1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

/**
 * Created by Jonathan on 11/04/2017.
 *
 * A classe BancoController será responsável por controlar as manipulações ao banco
 */

public class bancoController {

    private SQLiteDatabase db;
    private criaSQLite banco;

    public bancoController(Context context){
        banco = new criaSQLite(context);
    }

    public String insereDado(String descricao, String tipo, String data_rec){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(criaSQLite.DESCRICAO, descricao);
        valores.put(criaSQLite.TIPO, tipo);
        valores.put(criaSQLite.DATA_REC, data_rec);

        resultado = db.insert(criaSQLite.RECEITA, null, valores);
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
