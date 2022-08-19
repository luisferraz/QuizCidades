package br.ufpr.tads.luis.quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {
    HashMap<String, String> cidades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textViewPontuacao = findViewById(R.id.textViewPontuacao);

        Bundle bundle = getIntent().getExtras();

        int pontuacao = bundle.getInt("pontuacao", 0);

        cidades = (HashMap<String, String>) bundle.getSerializable("cidades");

        textViewPontuacao.setText(String.valueOf(pontuacao));
        if (pontuacao < 100) {
            textViewPontuacao.setTextColor(Color.RED);
        } else {
            textViewPontuacao.setTextColor(Color.GREEN);
        }

    }

    public void reiniciarJogo(View view) {
        Intent it = new Intent(getApplicationContext(), NewGameActivity.class);
        it.putExtra("cidades", cidades);
        startActivity(it);
        finish();
    }
}