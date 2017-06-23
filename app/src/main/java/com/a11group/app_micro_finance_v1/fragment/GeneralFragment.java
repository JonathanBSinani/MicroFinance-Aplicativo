package com.a11group.app_micro_finance_v1.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a11group.app_micro_finance_v1.activity.NewExpensesActivity;
import com.a11group.app_micro_finance_v1.activity.NewIncomeActivity;
import com.a11group.app_micro_finance_v1.R;
import com.github.clans.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFragment extends Fragment {


    private FloatingActionButton menu_fab_receita;
    private FloatingActionButton menu_fab_despesa;

    public GeneralFragment() {
        // Required empty public constructor
    }

    //cria uma nova instância do fragmento
    public static GeneralFragment newInstance(){
        Bundle args = new Bundle();
        GeneralFragment fragment = new GeneralFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_geral, container, false);

        menu_fab_receita = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.menu_receita);
        menu_fab_despesa = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.menu_despesa);

        menu_fab_receita.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Adicionar nova Receita/Despesa", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent tela_receita_nova = new Intent(getContext(), NewIncomeActivity.class);
                startActivity(tela_receita_nova);
            }
        });

        menu_fab_despesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_despesa_nova = new Intent(getContext(), NewExpensesActivity.class);
                startActivity(tela_despesa_nova);
            }
        });
        return view;
    }

}
