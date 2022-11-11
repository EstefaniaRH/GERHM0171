package Almacen;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Archivos {
    private final String NomArch="GERH0171.txt";
    private List<String> datos = new ArrayList<String>();

    public boolean Grabar(Context context, List<String> Dato){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput(NomArch, Activity.MODE_PRIVATE));


            for (String linea:Dato){
                archivo.write(linea+"\n");
            }

            archivo.flush();
            archivo.close();
        } catch (Exception ex){
            return false;
        }
        return true;
    }

    public boolean Leer_info(Context context){
        try{
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(NomArch));
            BufferedReader bufferedReader = new BufferedReader(archivo);
            String linea;

            while((linea = bufferedReader.readLine()) != null){
                datos.add(linea);
            }

        }catch(Exception ex){
            return false;
        }
        return true;
    }

    public List<String> Reg_informacion(){
        return datos;
    }
    //Verificar que el archivo existe
    public boolean VerificaArchivo(Context context){
        String[] archivos = context.fileList();

        for(String archivo : archivos){
            if (archivo.equals(NomArch)){
                return true;
            }
        }

        return false;
    }
    public boolean EliminarRegistro(Context context,int index){
        try{
            Leer_info(context);
            datos.remove(index);
            Grabar(context,datos);
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    public boolean Actualizar(Context context,int index, String nuevoDato){
        try{

            Leer_info(context);
            datos.set(index, nuevoDato);
            Grabar(context,datos);

        }catch(Exception ex){
            return false;
        }
        return true;
    }
}
