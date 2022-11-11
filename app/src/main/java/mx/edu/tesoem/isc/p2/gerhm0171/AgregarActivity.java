package mx.edu.tesoem.isc.p2.gerhm0171;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Almacen.Archivos;
import Informacion.Datos;

public class AgregarActivity extends AppCompatActivity {

    EditText txtmatricula2, txtnombre2, txtedad2, txtsemestre2, txtpromedio2;
    List<String> informacion = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        txtmatricula2 = findViewById(R.id.txtmatricula2);
        txtnombre2 = findViewById(R.id.txtnombre2);
        txtedad2 = findViewById(R.id.txtedad2);
        txtsemestre2 = findViewById(R.id.txtsemestre2);
        txtpromedio2 = findViewById(R.id.txtpromedio2);
    }

    public void btnagregar2(View view){
        String txtestado;
        if(Float.parseFloat(txtpromedio2.getText().toString()) >= 70){
            txtestado = "Acreditado";
        } else {
            txtestado = "No acreditado";
        }

        Datos datos = new Datos(txtmatricula2.getText().toString(),txtnombre2.getText().toString(),Integer.parseInt(txtedad2.getText().toString()),txtsemestre2.getText().toString(),Float.parseFloat(txtpromedio2.getText().toString()),txtestado);
        Gson gson = new Gson();
        String cadena = gson.toJson(datos);

        informacion.add(cadena);
        Archivos archivos = new Archivos();

        if (archivos.Grabar(this,informacion)){
            Toast.makeText(this,"Se grabo con exito", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Error al grabar", Toast.LENGTH_LONG).show();
        }

    }

    public void btnregresar2(View view){
        Intent regreso2 = new Intent(this,DatosActivity.class);
        startActivity(regreso2);
        finish();
    }

}