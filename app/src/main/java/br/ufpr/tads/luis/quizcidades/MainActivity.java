package br.ufpr.tads.luis.quizcidades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciar(View view) {
        HashMap<String, String> cidades = preencheCidades();
        Intent it = new Intent(getApplicationContext(), NewGameActivity.class);
        it.putExtra("cidades", cidades);
        startActivity(it);
    }

    public HashMap<String, String> preencheCidades() {
        HashMap<String, String> cidades = new HashMap<>();
        cidades.put("Barcelona", "01_barcelona");
        cidades.put("Brasília", "02_brasilia");
        cidades.put("Curitiba", "03_curitiba");
        cidades.put("Las Vegas", "04_lasvegas");
        cidades.put("Montreal", "05_montreal");
        cidades.put("Paris", "06_paris");
        cidades.put("Rio de Janeiro", "07_riodejaneiro");
        cidades.put("Salvador", "08_salvador");
        cidades.put("São Paulo", "09_saopaulo");
        cidades.put("Tóquio", "10_toquio");
        return cidades;
    }
}