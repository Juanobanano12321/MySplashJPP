package com.example.mysplashhjp;

import java.io.Serializable;

public class MyInfo implements Serializable {
    private String nombrepersona;
    private String edad;
    private String userName;
    private String pswd;
    private Boolean sexoF;

    public MyInfo(){

    }

    public String getNombrepersona() {
        return nombrepersona;
    }

    public void setNombrepersona(String nombrepersona) {
        this.nombrepersona = nombrepersona;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Boolean getSexoF() {
        return sexoF;
    }

    public void setSexoF(Boolean sexoF) {
        this.sexoF = sexoF;
    }


}

