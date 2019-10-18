package com.example.trabalhochato;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Calculus yea = new Calculus();
    private final int REQ_CODE_SPEAKER_INPUT = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //Mostra entrada de dialogo do google speaker
    private void promptSpeechInput() {

        //Passar o parametro para onde será chamado
        Intent it = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        it.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getAvailableLocales());
        it.putExtra(RecognizerIntent.EXTRA_PROMPT, "!");


        try {

            startActivityForResult(it, REQ_CODE_SPEAKER_INPUT);

        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();

        }

    }

    //Recebe a entrada do Google speaker
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SPEAKER_INPUT && data != null) {

                {
                    //recebe a mensagem do intent e adiciona no array
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    TextView potencia = findViewById(R.id.p);
                    TextView resistencia = findViewById(R.id.r);
                    TextView corrente = findViewById(R.id.c);
                    TextView tensao = findViewById(R.id.t);
                    yea.reset();
                    potencia.setText("");
                    resistencia.setText("");
                    corrente.setText("");
                    tensao.setText("");
                    //passa a mensagem captada no array e passa para a String
                    //mostrar a mensagem

                    //while (true) {
                        String mensagem = "";
                        Log.i("Falar o calculo", result.get(0));
                    int porn = 0;
                    String[] dammit = result.get(0).split(" ");

                        for (int i = 0; porn <= 3 && i < dammit.length; i++) {
                            switch (dammit[i].toLowerCase()){
                                case "potência": {
                                if(porn<2 && i+1 < dammit.length) {
                                    Double valor = Double.parseDouble(dammit[i+1]);
                                    System.out.println(valor);
                                    yea.setPotencia(valor);
                                    potencia.setText(dammit[i+1]);
                                    porn++;
                                }
                                break;}
                            case "tensão": {
                                if(porn<2 && i+1 < dammit.length){
                                Double valor = Double.parseDouble(dammit[i+1]);
                                yea.setTensao(valor);
                                tensao.setText(dammit[i+1]);
                                porn++;
                            }break;}
                            case "corrente": {
                                if(porn<2 && i+1 < dammit.length){
                                Double valor = Double.parseDouble(dammit[i+1]);
                                yea.setCorrente(valor);
                                corrente.setText(dammit[i+1]);
                                porn++;
                            }break;}
                            case "resistência": {
                                if(porn<2 && i+1 < dammit.length){
                                Double valor = Double.parseDouble(dammit[i+1]);
                                yea.setResistencia(valor);
                                resistencia.setText(dammit[i+1]);
                                porn++;
                            }break;}
                            case "calcular": {
                                Double pot = yea.getPotencia();
                                Double res = yea.getResistencia();
                                Double cor = yea.getCorrente();
                                Double ten = yea.getTensao();
                                potencia.setText(pot.toString());
                                resistencia.setText(res.toString());
                                corrente.setText(cor.toString());
                                tensao.setText(ten.toString());
                            if(porn!=2){
                                yea.reset();
                                potencia.setText("ERRO DE ELEMENTOS, ESPERADO 2");
                                resistencia.setText("ERRO DE ELEMENTOS, ESPERADO 2");
                                corrente.setText("ERRO DE ELEMENTOS, ESPERADO 2");
                                tensao.setText("ERRO DE ELEMENTOS, ESPERADO 2");
                                }
                            }}
                        }
                    }
                }

            }
       // }

    public void Falar(View view) {


        promptSpeechInput();
    }
}
