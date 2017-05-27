package com.a11group.app_micro_finance_v1.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.a11group.app_micro_finance_v1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Geral_Receitas_Fragment extends Fragment {

    ListView listReceitas;

    public Geral_Receitas_Fragment() {
        // Required empty public constructor
    }

    //cria uma nova instância do fragmento
    public static Geral_Receitas_Fragment newInstance(){
        Bundle args = new Bundle();
        Geral_Receitas_Fragment fragment = new Geral_Receitas_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_geral_receitas, container, false);

        //VINCULA A LISTA DA TELA AO LISTVIEW QUE DECLARAMOS
        listReceitas = (ListView) view.findViewById(R.id.lvReceitas);

        this.CarregaOpcoesLista();

        return view;
    }

    //CRIA A OPÇÕES DA NOSSA LISTA E ADICIONA AO LISTVIEW DA NOSSA TELA.
    protected  void CarregaOpcoesLista(){

        String[] itens = new String[2];

        itens[0] = "Receita 1";
        itens[1] = "Receita 2";

        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,itens);


        listReceitas.setAdapter(arrayItens);

    }

}
