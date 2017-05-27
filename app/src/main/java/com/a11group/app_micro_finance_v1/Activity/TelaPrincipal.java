package com.a11group.app_micro_finance_v1.Activity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.a11group.app_micro_finance_v1.Fragments.Geral_Receitas_Fragment;
import com.a11group.app_micro_finance_v1.Fragments.Geral_Fragment;
import com.a11group.app_micro_finance_v1.R;
import com.github.clans.fab.FloatingActionButton;

public class TelaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    //variáveis de instância
    //private FloatingActionButton menu_fab_receita;
    //private FloatingActionButton menu_fab_despesa;
    private TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_tela_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle("Geral");
        setSupportActionBar(toolbar);

        //inicia o fragmento "Geral" na tela inicial
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, Geral_Fragment.newInstance(),"Geral").commit();

        /*menu_fab_receita = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_receita);
        menu_fab_despesa = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_despesa);*/

        /*menu_fab_receita.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Adicionar nova Receita/Despesa", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                Intent tela_receita_nova = new Intent(getApplicationContext(), NovaReceita.class);
                startActivity(tela_receita_nova);
            }
        });

        menu_fab_despesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_despesa_nova = new Intent(getApplicationContext(), NovaDespesa.class);
                startActivity(tela_despesa_nova);
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean retorno = false;

        Limpar();

        if (id == R.id.nav_geral){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, Geral_Fragment.newInstance(), "Geral").commit();
            toolbar.setSubtitle("Geral");
            retorno = true;
        }else if (id == R.id.nav_receitas){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, Geral_Receitas_Fragment.newInstance(),"Receitas").commit();
            toolbar.setSubtitle("Receitas");
            retorno = true;
        }else if (id == R.id.nav_despesas){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, Geral_Receitas_Fragment.newInstance(),"Despesas").commit();
            toolbar.setSubtitle("Despesas");
            retorno = true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return retorno;
    }

    private int Limpar(){
        Fragment f1 = getSupportFragmentManager().findFragmentByTag("Geral");
        Fragment f2 = getSupportFragmentManager().findFragmentByTag("Receitas");
        Fragment f3 = getSupportFragmentManager().findFragmentByTag("Despesas");
        if (f1 != null) getSupportFragmentManager().beginTransaction().remove(f1).commit();
        if (f2 != null) getSupportFragmentManager().beginTransaction().remove(f2).commit();
        if (f3 != null) getSupportFragmentManager().beginTransaction().remove(f3).commit();
        return 1;
    }
}
