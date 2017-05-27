package com.a11group.app_micro_finance_v1.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.a11group.app_micro_finance_v1.Model.ReceitaModel;
import com.a11group.app_micro_finance_v1.Uteis.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 25/05/2017.
 */

public class ReceitaRepository {
    DatabaseUtil databaseUtil;

    public ReceitaRepository(Context context){
        databaseUtil = new DatabaseUtil(context);
    }

    /*
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param receitaModel
     */

    public void Salvar(ReceitaModel receitaModel){
        ContentValues contentValues = new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("ds_receita", receitaModel.getDescricaoReceita());
        contentValues.put("tp_receita", receitaModel.getTipoReceita());
        contentValues.put("dt_receita", receitaModel.getDataReceita());
        contentValues.put("vl_receita", receitaModel.getValorReceita());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_receita", null, contentValues);
    }

    /*
    * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
    * @param receitaModel
    * */
    public void Atualizar(ReceitaModel receitaModel){
        ContentValues contentValues = new ContentValues();

        /*MONTA OS PARAMETROS PARA REALIZAR UM UPGRADE NOS CAMPOS*/
        contentValues.put("ds_receita", receitaModel.getDescricaoReceita());
        contentValues.put("tp_receita", receitaModel.getTipoReceita());
        contentValues.put("dt_receita", receitaModel.getDataReceita());
        contentValues.put("vl_receita", receitaModel.getValorReceita());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_receita", contentValues, "id_receita = ?", new String[]{Integer.toString(receitaModel.getCodigo())});
    }

    /*
    * EXCLUI UM REGISTRO PELO CÓDIGO
    * @param codigo
    * @return
    * */
    public Integer Excluir(int codigo){
        /*EXCLUINDO REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS*/
        return databaseUtil.GetConexaoDataBase().delete("tb_receita", "id_receita = ?", new String[]{Integer.toString(codigo)});
    }

    /*
    * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
    * @param codigo
    * @return
    * */
    public ReceitaModel GetReceita(int codigo){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_receita WHERE id_receita = " + codigo, null);

        cursor.moveToFirst();

        /*CRIANDO UMA NOVA RECEITA*/
        ReceitaModel receitaModel = new ReceitaModel();

        /*ADICIONANDO OS DADOS DA RECEITA*/
        receitaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_receita")));
        receitaModel.setDescricaoReceita(cursor.getString(cursor.getColumnIndex("ds_receita")));
        receitaModel.setTipoReceita(cursor.getString(cursor.getColumnIndex("tp_receita")));
        receitaModel.setDataReceita(cursor.getString(cursor.getColumnIndex("dt_receita")));
        receitaModel.setValorReceita(cursor.getDouble(cursor.getColumnIndex("vl_receita")));

        /*RETORNANDO A RECEITA*/
        return receitaModel;
    }

    /*
    * CONSULTA TODAS AS RECEITAS CADASTRADAS NA BASE
    * @return
    * */
    public List<ReceitaModel> SelecionarTodos(){
        List<ReceitaModel> receitas = new ArrayList<ReceitaModel>();

        /*MONTA A QUERY A SER EXECUTADA*/
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT   id_receita,");
        stringBuilderQuery.append("         ds_receita,");
        stringBuilderQuery.append("         tp_receita,");
        stringBuilderQuery.append("         dt_receita,");
        stringBuilderQuery.append("         vl_receita ");
        stringBuilderQuery.append(" FROM tb_receita ");
        stringBuilderQuery.append(" ORDER BY dt_receita");

        /* CONSULTANDO OS REGISTROS CADASTRADOS*/
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /* POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();

        ReceitaModel receitaModel;

        /* REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR*/
        while (!cursor.isAfterLast()){
            /* CRIANDO UMA NOVA RECEITAS*/
            receitaModel = new ReceitaModel();

            /* ADICIONANDO OS DADOS DA RECEITA*/
            receitaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_receita")));
            receitaModel.setDescricaoReceita(cursor.getString(cursor.getColumnIndex("ds_receita")));
            receitaModel.setTipoReceita(cursor.getString(cursor.getColumnIndex("tp_receita")));
            receitaModel.setDataReceita(cursor.getString(cursor.getColumnIndex("dt_receita")));
            receitaModel.setValorReceita(cursor.getDouble(cursor.getColumnIndex("vl_receita")));

            /* ADICIONANDO UMA PESSOA NA LISTA*/
            receitas.add(receitaModel);

            /* VAI PARA O PRÓXIMO REGISTRO*/
            cursor.moveToNext();
        }

        /*RETORNANDO A LISTA DE RECEITAS*/
        return receitas;
    }
}
