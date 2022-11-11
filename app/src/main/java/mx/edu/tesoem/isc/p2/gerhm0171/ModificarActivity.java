package mx.edu.tesoem.isc.p2.gerhm0171;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import Almacen.Archivos;
import Informacion.Datos;

public class ModificarActivity extends AppCompatActivity {

    String upd;
    int index;
    EditText txtmatricula3, txtnombre3, txtedad3, txtsemestre3, txtpromedio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtmatricula3 = findViewById(R.id.txtmatricula3);
        txtnombre3 = findViewById(R.id.txtnombre3);
        txtedad3 = findViewById(R.id.txtedad3);
        txtsemestre3 = findViewById(R.id.txtsemestre3);
        txtpromedio3 = findViewById(R.id.txtpromedio3);

        upd = getIntent().getStringExtra("seleccion");
        index = Integer.parseInt(getIntent().getStringExtra("index"));

        Gson gson = new Gson();
        Datos datos = gson.fromJson(upd, Datos.class);

        txtmatricula3.setText(datos.getMatricula());
        txtnombre3.setText(datos.getNombre());
        txtedad3.setText(String.valueOf(datos.getEdad()));
        txtsemestre3.setText(datos.getSemestre());
        txtpromedio3.setText(String.valueOf(datos.getPromedio()));

    }
    public void btnaceptar3(View view){
        String estado;
        if(Float.parseFloat(txtpromedio3.getText().toString()) >= 70){
            estado = "Acreditado";
        } else {
            estado = "No acreditado";
        }

        Datos datos = new Datos(txtmatricula3.getText().toString(),txtnombre3.getText().toString(),Integer.parseInt(txtedad3.getText().toString()),txtsemestre3.getText().toString(),Float.parseFloat(txtpromedio3.getText().toString()),estado);
        Gson gson = new Gson();
        String cadena = gson.toJson(datos);

        Archivos archivos = new Archivos();

        if (archivos.Actualizar(this,index,cadena)){
            Toast.makeText(this,"Se grabo con exito", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Error al grabar", Toast.LENGTH_LONG).show();
        }
    }
    public void btnregresar3(View view){
        Intent regreso = new Intent(this,DatosActivity.class);
        startActivity(regreso);
        finish();
    }
}