package com.example.mysplashhjp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {



    private static final String TAG = "Register";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public EditText nombreusuario;
    public EditText edadUsuario;
    public Switch SexoFem;
    public Boolean Sexo = false;
    public EditText username;
    public EditText password;
    private Button Registrar;
    private Button Cancelar;
    ArrayList<File> Usuarios = new ArrayList<>();
    public ArrayList Contrasenias;
    public String NomUsuario = null;

    byte [] res = null;

    MyInfo myInfo = null;
    String myInfo2 = null;
    Gson gson = null;
    String json = null;
    String json2 = null;


    public static final String archivo = "user.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button Registrar = (Button) findViewById(R.id.buttonRegistrarse);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarseUsuarios();
            }
        });

        Button Cancelar = (Button) findViewById(R.id.buttonCancelar);
        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CancelarRegistros();
            }
        });
    }

    public void RegistrarseUsuarios(){

        nombreusuario = findViewById(R.id.nombre);
        edadUsuario = findViewById(R.id.edad);
        SexoFem = findViewById(R.id.sexo1);
        username = findViewById(R.id.user);
        password = findViewById(R.id.contrasenia);

        if(nombreusuario.getText().length() == 0 && edadUsuario.getText().length() == 0 && username.getText().length() == 0 && password.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Por favor llene los campos", Toast.LENGTH_LONG).show();
        }
        else{
            myInfo = new MyInfo();
            myInfo.setNombrepersona(nombreusuario.getText().toString());
            myInfo.setEdad(edadUsuario.getText().toString());
            myInfo.setUserName(username.getText().toString());
            myInfo.setPswd(password.getText().toString());

            if(SexoFem.isChecked()){
                myInfo.setSexoF(true);
            }

            gson = new Gson();
            json = gson.toJson(myInfo);
            if(json != null){
                Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_LONG).show();
                json2 = myInfo.getUserName()+myInfo.getPswd();
            }
            else{
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
            EscribirRegistro();
            }

        }
    public void CancelarRegistros(){
        Intent intent = new Intent (Register.this, Login.class);
        startActivity( intent );
    }

    public void EscribirRegistro(){
        try {
            String tst = new String(createSha1(json2), StandardCharsets.UTF_8); //createSha1(json2).toString();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("MySplashhJp\\app\\src\\main\\java\\com\\example\\mysplashhjp\\" + myInfo.getUserName() + ".txt", Context.MODE_PRIVATE));
            OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(getApplicationContext().openFileOutput("MySplashhJp\\app\\src\\main\\java\\com\\example\\mysplashhjp\\"+myInfo.getUserName()+"1.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(tst);
            outputStreamWriter.close();
            outputStreamWriter1.write(json);
            outputStreamWriter1.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error al Registrar", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        catch (Exception t){
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
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

}
