package com.a11group.app_micro_finance_v1.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.a11group.app_micro_finance_v1.model.Income;
import com.a11group.app_micro_finance_v1.utils.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 25/05/2017.
 */

public class IncomeRepository {
    DatabaseUtil databaseUtil;

    public IncomeRepository(Context context){
        databaseUtil = new DatabaseUtil(context);
    }

    /*
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param receitaModel
     */

    public void Salvar(Income receitaModel){
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
    * @param income
    * */
    public void Atualizar(Income income){
        ContentValues contentValues = new ContentValues();

        /*MONTA OS PARAMETROS PARA REALIZAR UM UPGRADE NOS CAMPOS*/
        contentValues.put("ds_receita", income.getDescricaoReceita());
        contentValues.put("tp_receita", income.getTipoReceita());
        contentValues.put("dt_receita", income.getDataReceita());
        contentValues.put("vl_receita", income.getValorReceita());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_receita", contentValues, "id_receita = ?", new String[]{Integer.toString(income.getCodigo())});
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
    public Income GetReceita(int codigo){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_receita WHERE id_receita = " + codigo, null);

        cursor.moveToFirst();

        /*CRIANDO UMA NOVA RECEITA*/
        Income income = new Income();

        /*ADICIONANDO OS DADOS DA RECEITA*/
        income.setCodigo(cursor.getInt(cursor.getColumnIndex("id_receita")));
        income.setDescricaoReceita(cursor.getString(cursor.getColumnIndex("ds_receita")));
        income.setTipoReceita(cursor.getString(cursor.getColumnIndex("tp_receita")));
        income.setDataReceita(cursor.getString(cursor.getColumnIndex("dt_receita")));
        income.setValorReceita(cursor.getDouble(cursor.getColumnIndex("vl_receita")));

        /*RETORNANDO A RECEITA*/
        return income;
    }

    /*
    * CONSULTA TODAS AS RECEITAS CADASTRADAS NA BASE
    * @return
    * */
    public List<Income> SelecionarTodos(){
        List<Income> receitas = new ArrayList<Income>();

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

        Income income;

        /* REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR*/
        while (!cursor.isAfterLast()){
            /* CRIANDO UMA NOVA RECEITAS*/
            income = new Income();

            /* ADICIONANDO OS DADOS DA RECEITA*/
            income.setCodigo(cursor.getInt(cursor.getColumnIndex("id_receita")));
            income.setDescricaoReceita(cursor.getString(cursor.getColumnIndex("ds_receita")));
            income.setTipoReceita(cursor.getString(cursor.getColumnIndex("tp_receita")));
            income.setDataReceita(cursor.getString(cursor.getColumnIndex("dt_receita")));
            income.setValorReceita(cursor.getDouble(cursor.getColumnIndex("vl_receita")));

            /* ADICIONANDO UMA PESSOA NA LISTA*/
            receitas.add(income);

            /* VAI PARA O PRÓXIMO REGISTRO*/
            cursor.moveToNext();
        }

        /*RETORNANDO A LISTA DE RECEITAS*/
        return receitas;
    }
}
