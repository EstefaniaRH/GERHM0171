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

public class EliminarActivity extends AppCompatActivity {

    EditText matricula, nombre, edad, semestre, promedio, estado;
    String seleccion;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        matricula = findViewById(R.id.txtmatricula4);
        nombre = findViewById(R.id.txtnombre4);
        edad = findViewById(R.id.txtedad4);
        semestre = findViewById(R.id.txtsemestre4);
        promedio = findViewById(R.id.txtpromedio4);
        estado = findViewById(R.id.txtestado4);

        seleccion = getIntent().getStringExtra("seleccion");
        index = Integer.parseInt(getIntent().getStringExtra("index"));

        Gson gson = new Gson();
        Datos datos = gson.fromJson(seleccion, Datos.class);

        matricula.setText(datos.getMatricula());
        nombre.setText(datos.getNombre());
        edad.setText(String.valueOf(datos.getEdad()));
        semestre.setText(datos.getSemestre());
        promedio.setText(String.valueOf(datos.getPromedio()));
        estado.setText(datos.getEstado());
    }
    public void btnregresar4(View view){
        Intent regreso = new Intent(this,DatosActivity.class);
        startActivity(regreso);
        finish();
    }
    public void btnaceptar4(View view){
        //Toast.makeText(this, seleccion, Toast.LENGTH_LONG).show();
        Archivos archivos = new Archivos();
        if (archivos.EliminarRegistro(this,index)){
            Toast.makeText(this,"Eliminado con exito",Toast.LENGTH_LONG).show();
        }
        Intent regreso = new Intent(this,DatosActivity.class);
        startActivity(regreso);
        finish();
    }

}