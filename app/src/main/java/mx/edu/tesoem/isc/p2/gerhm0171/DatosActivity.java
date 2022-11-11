package mx.edu.tesoem.isc.p2.gerhm0171;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Almacen.Archivos;
import Informacion.Datos;

public class DatosActivity extends AppCompatActivity {

    EditText txtmatricula, txtnombre, txtedad, txtsemestre, txtpromedio, txtestado;
    List<String> informacion = new ArrayList<String>();
    ArrayList<String> contenidoGridView = new ArrayList<String>();
    GridView gvDatos;

    String emergente = "Presiona el numero de Id";
    String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        txtmatricula = findViewById(R.id.txtmatricula);
        txtnombre = findViewById(R.id.txtnombre);
        txtedad = findViewById(R.id.txtedad);
        txtsemestre = findViewById(R.id.txtsemestre);
        txtpromedio = findViewById(R.id.txtpromedio);
        txtestado = findViewById(R.id.txtestado);
        gvDatos = findViewById(R.id.gridDatos);

        Archivos archivos = new Archivos();

        if (archivos.VerificaArchivo(this)){
            if (archivos.Leer_info(this)){
                informacion = archivos.Reg_informacion();

                convierteArreglo(informacion);
                //Grid view necesita un ListAdapter, asi que convertimos el contenido a un ArrayAdapter del tipo String
                ArrayAdapter<String> arreglo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contenidoGridView);
                gvDatos.setAdapter(arreglo);

            }else{
                Toast.makeText(this,"No existe informacion a cargar", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"No existe informacion a cargar", Toast.LENGTH_LONG).show();
        }
        //Clickear la id para obtener los datos de esa fila
        gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                if ( (posicion>0) && ((posicion%4)== 0) ){
                    int linea = posicion/4;
                    //Colocar en el log el valor para verificar que es correcto
                    Log.i("Fila", "Valor lista: " + informacion.get(linea-1));
                    Toast.makeText(DatosActivity.this,"linea "+linea, Toast.LENGTH_LONG).show();

                    Gson gson = new Gson();
                    Datos datos = gson.fromJson(informacion.get(linea-1), Datos.class);

                    emergente = informacion.get(linea-1);
                    index = String.valueOf(linea-1);

                    txtmatricula.setText(datos.getMatricula().toString());
                    txtnombre.setText(datos.getNombre().toString());
                    txtedad.setText(String.valueOf(datos.getEdad()));
                    txtsemestre.setText(datos.getSemestre().toString());
                    txtpromedio.setText(String.valueOf(datos.getPromedio()));
                    txtestado.setText(datos.getEstado().toString());
                }
            }
        });

    }

    public void btnagregar(View view){
        Intent agregar = new Intent(this, mx.edu.tesoem.isc.p2.gerhm0171.AgregarActivity.class);
        startActivity(agregar);
    }
    public void btnmodificar(View view){
        if (emergente != "Presiona el numero de Id"){
            Intent actualizar = new Intent(this, mx.edu.tesoem.isc.p2.gerhm0171.ModificarActivity.class);
            actualizar.putExtra("seleccion", emergente);
            actualizar.putExtra("index", index);
            startActivity(actualizar);
        } else {
            Toast.makeText(this,emergente,Toast.LENGTH_LONG).show();
        }
    }
    public void btnelimina(View view){
        if (emergente != "Selecciona una persona"){
            Intent eliminar = new Intent(this, mx.edu.tesoem.isc.p2.gerhm0171.EliminarActivity.class);
            eliminar.putExtra("seleccion", emergente);
            eliminar.putExtra("index", index);
            startActivity(eliminar);
        } else {
            Toast.makeText(this,emergente,Toast.LENGTH_LONG).show();
        }
    }

    private void convierteArreglo(List<String> contenido){
        int id = 1;
        String txtestado;

        contenidoGridView.add("Id");
        contenidoGridView.add("Matricula");
        contenidoGridView.add("Nombre");
        /* contenidoGridView.add("Edad");
        contenidoGridView.add("Semestre");
        contenidoGridView.add("Promedio");*/
        contenidoGridView.add("Estado");

        for (String elemento : contenido){
            Gson gson = new Gson();
            Datos dato = gson.fromJson(elemento, Datos.class);

            contenidoGridView.add(String.valueOf(id));
            contenidoGridView.add(dato.getMatricula());
            contenidoGridView.add(dato.getNombre());
            /* contenidoGridView.add(String.valueOf(dato.getEdad()));
            contenidoGridView.add(dato.getSemestre());
            contenidoGridView.add(String.valueOf(dato.getPromedio())); */

            if (dato.getPromedio() >= 70){
                txtestado = "Acreditado";
            }else{
                txtestado = "No acreditado";
            }
            contenidoGridView.add(txtestado);

            id = id+1;
        }

    }

}