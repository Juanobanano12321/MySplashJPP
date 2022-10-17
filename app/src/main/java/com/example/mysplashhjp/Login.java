package com.example.mysplashhjp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login extends AppCompatActivity {

    public EditText CampoUsuario;
    public EditText CampoCont;
    public byte[] campos;
    public String res;
    public TextView text1 = null;
    public String user=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Registrarse (View v){
        Intent intent = new Intent (Login.this, Register.class);
        startActivity( intent );
    }
    public void OlvideContr (View v){
        Intent intent = new Intent (Login.this, OlvideCont.class);
        startActivity( intent );
    }
    public void iniciarSesion (View v){
        CampoUsuario = findViewById(R.id.user);
        CampoCont = findViewById(R.id.contrasenia);
        if (CampoCont.getText().length() == 0 || CampoUsuario.getText().length() == 0 ){
            Toast.makeText(getApplicationContext(), "Llene los campos", Toast.LENGTH_LONG).show();
        }
        else{
            campos = createSha1(CampoUsuario.getText().toString()+CampoCont.getText().toString());
            res = new String(campos, StandardCharsets.UTF_8);
            String json = LeerArchivo();
            String son = LeerArchivoDos();
            if(json.equals(res)){
                Intent intent = new Intent (Login.this, PagPrincipal.class);
                startActivity( intent );
            }
            else{
                Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
            }
        }
    }

    public byte[] createSha1( String text )
    {
        MessageDigest messageDigest = null;
        byte[] bytes = null;
        byte[] bytesResult = null;
        try
        {
            messageDigest = MessageDigest.getInstance("SHA-1");
            bytes = text.getBytes("iso-8859-1");
            messageDigest.update(bytes, 0, bytes.length);
            bytesResult = messageDigest.digest();
            return bytesResult;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String LeerArchivo(){
        String ret = "";

        try {
            InputStream inputStream = getApplicationContext().openFileInput("MySplashhJp\\app\\src\\main\\java\\com\\example\\mysplashhjp\\"+CampoUsuario.getText().toString()+".txt");
            user = CampoUsuario.getText().toString();
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            //text1.setText("No se encontró el archivo");
            Toast.makeText(getApplicationContext(), "Error al Ingresar", Toast.LENGTH_LONG).show();
            return "";
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }

    public String LeerArchivoDos(){
        String ret = "";

        try {
            InputStream inputStream = getApplicationContext().openFileInput("MySplashhJp\\app\\src\\main\\java\\com\\example\\mysplashhjp\\"+CampoUsuario.getText().toString()+"1.txt");
            user = CampoUsuario.getText().toString();
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            //text1.setText("No se encontró el archivo");
            Toast.makeText(getApplicationContext(), "Error al Ingresar", Toast.LENGTH_LONG).show();
            return "";
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }

}
