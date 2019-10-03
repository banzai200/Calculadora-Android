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
    private void letsRocknRoll(String fuck)
    {
        if(fuck.equals("calcular")){
            yea.reset();
            return;
        }
        yea.reset();

    }
    //Mostra entrada de dialogo do google speake
    private void promptSpeechInput() {


        //Passar o parametro para onde será chamado
        Intent it = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        it.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
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
        TextView potencia = findViewById(R.id.p);
        TextView resistencia = findViewById(R.id.r);
        TextView corrente = findViewById(R.id.c);
        TextView tensao = findViewById(R.id.t);

        switch (requestCode) {

            case REQ_CODE_SPEAKER_INPUT: {

                if (requestCode == RESULT_OK && data != null) {

                    //recebe a mensagem do intent e adiciona no array
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    //passa a mensagem capitada no array e passa para a Satring
                    //mostrar a mensagem

                    while (true) {
                        String mensagem = "";
                        Log.i("Falar o calculo", mensagem);
                    int porn = 0;
                    String[] dammit = result.get(0).split(" ");
                        for (int i = 0; porn < 2 && i < dammit.length; i++) {
                            switch (dammit[i].toLowerCase()){
                                case "potência": {
                                if(porn<2){
                                Double valor = Double.parseDouble(dammit[i++]);
                                yea.setPotencia(valor);
                                potencia.setText(valor.toString());
                                porn++;
                            }break;}
                            case "tensão": {
                                if(porn<2){
                                Double valor = Double.parseDouble(dammit[i++]);
                                yea.setTensao(valor);
                                tensao.setText(valor.toString());
                                porn++;
                            }break;}
                            case "corrente": {
                                if(porn<2){
                                Double valor = Double.parseDouble(dammit[i++]);
                                yea.setCorrente(valor);
                                corrente.setText(valor.toString());
                                porn++;
                            }break;}
                            case "resistência": {
                                if(porn<2){
                                Double valor = Double.parseDouble(dammit[i++]);
                                yea.setResistencia(valor);
                                resistencia.setText(valor.toString());
                                porn++;
                            }break;}
                            case "calcullar": {
                            if(porn==2){
                                Double pot = yea.getPotencia();
                                Double res = yea.getResistencia();
                                Double cor = yea.getCorrente();
                                Double ten = yea.getTensao();

                                potencia.setText(String.format(pot.toString()));
                                resistencia.setText(String.format(res.toString()));
                                corrente.setText(String.format(pot.toString()));
                                tensao.setText(String.format(ten.toString()));
                            }
                            else{yea.reset();}
                            }}
                        }
                    }
                }

            }
        }
    }

    public void Falar(View view) {
        promptSpeechInput();
    }
}
