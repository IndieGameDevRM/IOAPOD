package com.example.lenovo.ioapoddemo;

/**
 * Created by lenovo on 2/8/2018.
 */

public class database {
    private String Moisture;
    private String ID;
    private String Temp;
    private String name;
    private String mail;
    private String Password;


    public database(String _name,String _mail,String password){
        name=_name;
        mail=_mail;
        Password=password;
    }
    public String getMoisture(){
        return Moisture;
    }
    public String getID(){
        return ID;
    }
    public String getTemp(){
        return Temp;
    }
    public String getname(){
        return  name;
    }
    public String getmail(){
        return mail;
    }
    public String getPassword(){return Password;}
}
