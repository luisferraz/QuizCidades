package br.ufpr.tads.luis.quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Random;

public class NewGameActivity extends AppCompatActivity {
    private String nomeCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        //Carrega uma imagem aleatoria de uma das possiveis cidades


    }

    //Busca no hashmap de forma aleatória uma cidade, retornando o nome da imagem que devera ser baixada, e salvando o nome da cidade para validação
    public String sortState(HashMap cidades) {
        Object[] arrayKeys = cidades.keySet().toArray();
        Object key = arrayKeys[new Random().nextInt(arrayKeys.length)];
        nomeCidade = (String) cidades.get(key.toString());
        return key.toString();
    }
}