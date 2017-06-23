package com.a11group.app_micro_finance_v1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a11group.app_micro_finance_v1.R;
import com.a11group.app_micro_finance_v1.api.MicrofinanceAPI;
import com.a11group.app_micro_finance_v1.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    /*DECLARAÇÕES DE OBJETOS/*
     */

    private EditText txtemail;
    private EditText txtsenha;
    private Button btnlogin;
    private TextView linkInscrever;

    private MicrofinanceAPI microfinanceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        btnlogin = (Button) findViewById(R.id.btn_login);
        linkInscrever = (TextView) findViewById(R.id.cria_conta_link);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        linkInscrever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_criaConta = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(tela_criaConta);
            }
        });

        microfinanceAPI = new MicrofinanceAPI(this);
    }

    //função login
    public void login() {
        Log.d(TAG, "LoginActivity");

        txtemail = (EditText) findViewById(R.id.edt_email);
        txtsenha = (EditText) findViewById(R.id.edt_password);
        btnlogin = (Button) findViewById(R.id.btn_login);

        microfinanceAPI
                .login(txtemail.getText().toString(), txtsenha.getText().toString())
                .enqueue(loginCallback());

        if (!validate()) {
            onLoginFailed();
            return;
        }


        //desabilitando botão login
        btnlogin.setEnabled(false);

        //criando o ProgressDialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        String email = txtemail.getText().toString();
        String password = txtsenha.getText().toString();

          /*
        *
        * aqui vai o código para verificar os dados de LoginActivity
        *
        * */


        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        //chama onLoginSucess ou a função onLoginFailed
                        onLoginSucess();
                        //função onLoginFailed
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                //acaba finalizando a atividade por padrão e vai acaba logando automaticamente
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //desabilita o voltar ao MainActivity
        moveTaskToBack(true);
    }

    private Callback<Login> loginCallback() {

        return new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response.body() != null) {
                    Login login = response.body();
                    Toast.makeText(getApplicationContext(), "Login ID is " + login.getId(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "call formatted json from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "username or password not found", Toast.LENGTH_LONG).show();
            }
        };
    }

    //função Sucesso no LoginActivity
    public void onLoginSucess() {
        btnlogin = (Button) findViewById(R.id.btn_login);
        btnlogin.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
        startActivity(intent);
//        finish();
    }

    //função Falha no LoginActivity
    public void onLoginFailed() {
        btnlogin = (Button) findViewById(R.id.btn_login);
        Toast.makeText(getBaseContext(), "Falha ao Logar", Toast.LENGTH_LONG).show();
        btnlogin.setEnabled(true);
    }

    //validação dos campos email e senha da tela LoginActivity
    public boolean validate() {
        boolean valid = true;

        txtemail = (EditText) findViewById(R.id.edt_email);
        txtsenha = (EditText) findViewById(R.id.edt_password);

        String email = txtemail.getText().toString();
        String senha = txtsenha.getText().toString();

        //validação de email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtemail.setError("Entre com endereço de email válido");
            valid = false;
        } else {
            txtemail.setError(null);
        }

        //validação de senha
        if (senha.isEmpty() || senha.length() < 4 || senha.length() > 10) {
            txtsenha.setError("Entre 4 e 10 caracteres alfanuméricos");
            valid = false;
        } else {
            txtsenha.setError(null);
        }

        return valid;
    }
}
