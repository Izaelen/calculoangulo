package br.com.rafaelleme.senai.calculoangulo;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtValorAngulo;
    RadioButton rbSen, rbCos, rbTan;
    Button btnCalcular;
    int opcao = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtValorAngulo = findViewById(R.id.edtValorAngulo);
        rbSen = findViewById(R.id.rbSen);
        rbCos = findViewById(R.id.rbCos);
        rbTan = findViewById(R.id.rbTan);
        btnCalcular = findViewById(R.id.btnCalcular);

        rbSen.setOnClickListener(this);
        rbCos.setOnClickListener(this);
        rbTan.setOnClickListener(this);
        btnCalcular.setOnClickListener(this);

        Log.i("SENAI","Esse é o meu primeiro LOG");

       
    }
    public double calcularSeno(double angulo){
        return Math.sin(Math.toRadians(angulo));
    }public double calcularCosseno(double angulo){
        return Math.cos(Math.toRadians(angulo));
    }public double calcuarTangente(double angulo){
        return Math.tan(Math.toRadians(angulo));
    }

    public void calcular(){
        AlertDialog dlgAlerta;
        double angulo, valorCalculado = 0;
        String titulo ="";
        angulo = Double.valueOf(edtValorAngulo.getText().toString());

        if(opcao == 1){
            titulo = "Cálculo de Seno";
            valorCalculado = calcularSeno(angulo);
        }else if(opcao == 2){
            titulo = "Cálculo de Cosseno";
            valorCalculado = calcularCosseno(angulo);
        }else if(opcao == 3){
            titulo = "Cálculo de Tangente";
            valorCalculado = calcuarTangente(angulo);
        }

        dlgAlerta = new AlertDialog.Builder(this).create();
        dlgAlerta.setTitle(titulo);
        //String valorFormatado = String.format("%.2f",valorCalculado);
        DecimalFormat format = new DecimalFormat("0.02");
        String valorFormatado = format.format(valorCalculado);
        dlgAlerta.setMessage(String.valueOf(valorFormatado));
        dlgAlerta.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbSen:
                opcao = 1;
                rbCos.setChecked(false);
                rbTan.setChecked(false);
                break;

            case R.id.rbCos:
                opcao = 2;
                rbSen.setChecked(false);
                rbTan.setChecked(false);
                break;

            case R.id.rbTan:
                opcao = 3;
                rbSen.setChecked(false);
                rbCos.setChecked(false);
                break;

            case R.id.btnCalcular:
                if(validar()) {
                    calcular();
                }

                break;
        }
    }

    private boolean validar(){
    if(edtValorAngulo.getText().toString().equals("")){
        edtValorAngulo.setError("Campo Obrigatório");
        return false;
    }else {
        double valorAngulo = Double.valueOf(edtValorAngulo.getText().toString());
        if (valorAngulo >= 0 && valorAngulo <= 360) {
            return true;
        } else {
            edtValorAngulo.setError("O valor deve estar entre  0 e 360");
            return false;

        }
    }

    }

}
