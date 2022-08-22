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

    //Chama a intent de inicio de jogo
    public void iniciar(View view) {
        HashMap<String, String> cidades = preencheCidades();
        Intent it = new Intent(getApplicationContext(), NewGameActivity.class);
        it.putExtra("cidades", cidades);
        startActivity(it);
    }

    //Preenche o Hashmap com as cidades possiveis e suas respectivas imagens
    public HashMap<String, String> preencheCidades() {
        HashMap<String, String> cidades = new HashMap<>();
//        cidades.put("Barcelona", "01_barcelona");
//        cidades.put("Brasília", "02_brasilia");
//        cidades.put("Curitiba", "03_curitiba");
//        cidades.put("Las Vegas", "04_lasvegas");
//        cidades.put("Montreal", "05_montreal");
//        cidades.put("Paris", "06_paris");
//        cidades.put("Rio de Janeiro", "07_riodejaneiro");
//        cidades.put("Salvador", "08_salvador");
//        cidades.put("São Paulo", "09_saopaulo");
//        cidades.put("Tóquio", "10_toquio");
        cidades.put("Barcelona", "5539hvtjwum4bw9/01_barcelona.jpg");
        cidades.put("Brasília", "oj81zcnv182pq9b/02_brasilia.jpg");
        cidades.put("Curitiba", "0xurnj5sxlq2sfa/03_curitiba.jpg");
        cidades.put("Las Vegas", "qqpysrmrthju24z/04_lasvegas.jpg");
        cidades.put("Montreal", "extohoa6ffolcr8/05_montreal.jpg");
        cidades.put("Paris", "ovjp2p6g64thnfm/06_paris.jpg");
        cidades.put("Rio de Janeiro", "h9yp8pak9m1lgko/07_riodejaneiro.jpg");
        cidades.put("Salvador", "4o1c4w7px3xjog0/08_salvador.jpg");
        cidades.put("São Paulo", "gg70vuvygj1fh07/09_saopaulo.jpg");
        cidades.put("Tóquio", "zovk35h5jb913ax/10_toquio.jpg");
        return cidades;
    }
}