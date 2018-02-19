package com.example.lenovo.ioapoddemo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText emailID;
    private EditText _password;

    private TextView _login;
    private TextView _createnew;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseauth=FirebaseAuth.getInstance();
        progressdialog=new ProgressDialog(this);
        emailID= (EditText) findViewById(R.id.Login_email);
        _password= (EditText) findViewById(R.id.Login_password);
        _login=(TextView) findViewById(R.id.login);
        _createnew=(TextView)findViewById(R.id.Back_to_register);
        _createnew.setOnClickListener(this);
        _login.setOnClickListener(this);

    }  public void LoginUser(){
        String email=emailID.getText().toString().trim();
        String password=_password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressdialog.setMessage("Login..!");
        progressdialog.show();

        //user will register here
      firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                  progressdialog.dismiss();

                  Intent i=new Intent(Login.this,MainProfile.class);
                  startActivity(i);

              }else{
                  progressdialog.dismiss();
                  Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();
              }
          }
      });
    }

    @Override
    public void onClick(View view) {
        if(view==_login){
            LoginUser();
        }else if(view == _createnew){
            Intent i=new Intent(Login.this,MainActivity.class);
            startActivity(i);

        }
    }
}
