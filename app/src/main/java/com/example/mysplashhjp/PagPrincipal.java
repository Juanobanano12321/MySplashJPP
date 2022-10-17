package com.example.mysplashhjp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PagPrincipal extends AppCompatActivity{

    public TextView textoJson = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagprincipal);
        textoJson = this.findViewById(R.id.texto12);
        textoJson.setText(LeerArchivo());

    }


    public String LeerArchivo(){
        String ret = "";
        try {
            InputStream inputStream = getApplicationContext().openFileInput("MySplashhJp\\app\\src\\main\\java\\com\\example\\mysplashhjp\\"+"Jorges"+"1.txt");
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