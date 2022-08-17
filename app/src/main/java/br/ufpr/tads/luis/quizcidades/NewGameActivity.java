package br.ufpr.tads.luis.quizcidades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Random;

public class NewGameActivity extends AppCompatActivity {
    ImageView imageViewCidade;
    HashMap<String, String> cidades;
    String[] cidade;
    int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        imageViewCidade = findViewById(R.id.imageViewCidade);
        contador = 0;
        Intent it = getIntent();
        cidades = (HashMap<String, String>) it.getSerializableExtra("cidades");
        cidade = sorteiaCidade(cidades);
        Log.i("CIDADE", "onCreate: " + cidade[0]);
        baixaCidade(cidade[1]);
    }

    public void verificaResposta(View view) {
        EditText editTextCidade = findViewById(R.id.editTextCidade);
        if (editTextCidade.length() == 0) {
            Toast.makeText(this, "Digite sua resposta!", Toast.LENGTH_SHORT).show();
            return;
        }

        String resposta = editTextCidade.getText().toString();
        //confere a resposta do usuario
        if (resposta.equalsIgnoreCase(cidade[0])) {
            contador += 25;
        }
        else {

        }

    }

    //Faz o download da imagem da cidade
    public void baixaCidade (String imageName) {
        ProgressDialog progressDialog = new ProgressDialog(this);
//        String url = "http://200.236.3.202/Cidades/" + cidade[1];
        String url = "https://picsum.photos/200";
        MyTask task = new MyTask(imageViewCidade, progressDialog);
        task.execute(url);
    }
    //Busca no hashmap de forma aleatória uma cidade, retornando o nome da cidade e da imagem que devera ser baixada
    public String[] sorteiaCidade(HashMap cidades) {
        String[] cidade = new String[2];
        Object[] arrayKeys = cidades.keySet().toArray();
        Object key = arrayKeys[new Random().nextInt(arrayKeys.length)];
        cidade[0] = (String) cidades.get(key.toString());
        cidade[1] = key.toString();
        return cidade;
    }
}