package com.example.examen_cl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examen_cl3.adaptador.RegistroAdapter;
import com.example.examen_cl3.entidad.RegistroPedido;
import com.example.examen_cl3.servicio.ServicioRest;
import com.example.examen_cl3.util.ConnectionRest;


public class MainActivity extends AppCompatActivity {

        Button btnAdd;
        ListView listView;
        RegistroAdapter adaptadorListView;
        ServicioRest servicio;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            setTitle("Retrofit 2 CRUD Demo");

            btnAdd = (Button) findViewById(R.id.btnAdd);

            // Al adaptador se le pasa la data y el diseño
            listView = (ListView) findViewById(R.id.listView);

            // Se crea la conexion al servicio
            servicio = ConnectionRest.getConnection().create(ServicioRest.class);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mensaje("Se pulsó el agregar");
                    Intent intent = new Intent(MainActivity.this, RegistraPedidoActivity.class);
                    intent.putExtra("var_metodo", "REGISTRAR");
                    startActivity(intent);
                }
            });


        void mensaje(String msg){
            Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
            toast1.show();
        }
    }
