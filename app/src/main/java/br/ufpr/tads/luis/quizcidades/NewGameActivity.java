package br.ufpr.tads.luis.quizcidades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Random;

public class NewGameActivity extends AppCompatActivity {
    ImageView imageViewCidade;
    TextView textViewResposta;
    EditText editTextCidade;
    Button buttonEnviar, buttonProxima;
    HashMap<String, String> cidades;
    String cidade;
    int cont, pontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        imageViewCidade = findViewById(R.id.imageViewCidade);
        textViewResposta = findViewById(R.id.textViewResposta);
        buttonEnviar = findViewById(R.id.buttonEnviar);
        buttonProxima = findViewById(R.id.buttonProxima);
        editTextCidade = findViewById(R.id.editTextCidade);

        cont = 0;
        pontuacao = 0;
        Intent it = getIntent();
        //Recebe as cidades preenchidas pela tela anterior
        cidades = (HashMap<String, String>) it.getSerializableExtra("cidades");
        if (cidades == null) {
            Toast.makeText(this, "Erro ao encontrar imagens", Toast.LENGTH_SHORT).show();
            finish();
        }
        cidade = sorteiaCidade(cidades);
    }
    //Busca no hashmap de forma aleatória uma cidade, baixa sua respectiva imagem e retorna o nome para posterior conferencia
    public String sorteiaCidade(@NonNull HashMap cidades) {
        Object[] arrayKeys = cidades.keySet().toArray();
        Object key = arrayKeys[new Random().nextInt(arrayKeys.length)];
        String cidade = key.toString();
        String imagem = (String) cidades.get(key.toString());
        ProgressDialog progressDialog = new ProgressDialog(this);
        //Ja que o servidor com a imagem das cidades está fora, vamos utilizar uma API dummy de imagens
//        String url = "http://200.236.3.202/Cidades/" + imagem;
//        String url = "https://picsum.photos/200";
        String url = "https://www.dropbox.com/s/" + imagem + "?raw=1";
        MyTask task = new MyTask(imageViewCidade, progressDialog);
        task.execute(url);
        cont++;
        Log.i("CIDADE", "sorteiaCidade: " + cidade);
        return cidade;
    }

    //Efetua a verificação da resposta informada, mostrando a respectiva imagem de erro ou sucesso
    public void verificaResposta(View view) {
        String mensagem = "";
        int msgColor;

        if (editTextCidade.length() == 0) {
            Toast.makeText(this, "Digite sua resposta!", Toast.LENGTH_SHORT).show();
            return;
        }

        String resposta = editTextCidade.getText().toString();
        //confere a resposta do usuario
        if (resposta.equalsIgnoreCase(cidade)) {
            //Soma 25 na pontução e exibe a mensagem de sucesso
            pontuacao += 25;
            mensagem = "Resposta Correta!";
            msgColor = R.color.right_answer;
        } else {
            //Exibe a mensagem em vermelho com a resposta correta
            mensagem = "Resposta Errada!\nA resposta correta é: " + cidade;
            msgColor =  R.color.wrong_answer;
        }
        textViewResposta.setText(mensagem);
        textViewResposta.setTextColor(ContextCompat.getColor(getApplicationContext(), msgColor));

        //Desabilita e esconde campos de preenchimento
        editTextCidade.setEnabled(false);
        buttonEnviar.setEnabled(false);
        //Habilita o botão de proxima pergunta
        buttonProxima.setEnabled(true);
        buttonProxima.setVisibility(View.VISIBLE);
    }


    public void proximaPergunta(View view) {
        if (cont >=4) {
            //Chama a intent de fim de jogo para exibir a pontuação
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("pontuacao", pontuacao);
            bundle.putSerializable("cidades", cidades);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else {
            //Desabilita o botão de proxima pergunta
            buttonProxima.setEnabled(false);
            buttonProxima.setVisibility(View.INVISIBLE);
            //Limpa os campos de preenchimento e resposta
            editTextCidade.setEnabled(true);
            editTextCidade.setText("");
            textViewResposta.setText("");
            //Habilita o botão de enviar a resposta
            buttonEnviar.setEnabled(true);
            //Sorteia uma nova cidade para o usuário
            cidade = sorteiaCidade(cidades);
        }

    }

}
