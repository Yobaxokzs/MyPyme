package com.inacap.proveedores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private DAOHelperUsuario d;


    EditText etuser, etpass;
    Spinner etTL;
    Button btnAcceder, btnRegistrar, btnCRUD;
    String uso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        etuser = (EditText) findViewById(R.id.etId);
        etpass = (EditText) findViewById(R.id.etPass);
        btnAcceder = (Button) findViewById(R.id.btnAcceder);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnAcceder.setOnClickListener(this);
        d = new DAOHelperUsuario(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAcceder:
                String u = etuser.getText().toString();
                String p = etpass.getText().toString();
                if (u.equals("") && p.equals("")) {
                    Toast.makeText(this, "ERROR CAMPOS VACIOS", Toast.LENGTH_SHORT).show();
                } else if (d.login(u, p) == 1) {
                    Toast.makeText(this, "Acceso de Administrador", Toast.LENGTH_SHORT).show();
                    Intent inicio = new Intent(LoginActivity.this, Inicio.class);
                    startActivity(inicio);
                }
        }
    }

    public void Registrar(View view) {
        Intent Registrar = new Intent(this, Registrar.class);
        startActivity(Registrar);
    }
}