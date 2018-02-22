package com.example.lenovo.ioapoddemo.register_user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.ioapoddemo.Login;
import com.example.lenovo.ioapoddemo.R;
import com.example.lenovo.ioapoddemo.database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText emailID;
    private EditText _password;
    private EditText _confirmPassword;
    private TextView _Register;
    private TextView _alreadyhaveaccount;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;
    public EditText _UserName;
    public DatabaseReference firebasedatabase;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //Firebase auth

        firebaseauth=FirebaseAuth.getInstance();
        //firebasedatabase

        firebasedatabase= FirebaseDatabase.getInstance().getReference("Registered_Detail");
        progressdialog=new ProgressDialog(this);

        //taking reference from views


        emailID= (EditText) findViewById(R.id.Register_email);
        _password= (EditText) findViewById(R.id.Register_password);
        _confirmPassword=(EditText)findViewById(R.id.Confirm_password);
        _UserName=(EditText)findViewById(R.id.User_Name);
        _Register=(TextView) findViewById(R.id.login);

        //Setlisitener

        _alreadyhaveaccount=(TextView)findViewById(R.id.alreadyhave);
        _alreadyhaveaccount.setOnClickListener(this);
        _Register.setOnClickListener(this);


    }
    public void RegisterUser(){
        final String email=emailID.getText().toString().trim();
        final String _userName=_UserName.getText().toString().trim();
        final String password=_password.getText().toString().trim();
        String confirmPass=_confirmPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(_userName)){
            //email is empty
            Toast.makeText(this,"Please enter Username",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(confirmPass)){
            //password is empty
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressdialog.setMessage("Registering User.......");
        progressdialog.show();
        firebaseauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Register Successful..!",Toast.LENGTH_SHORT).show();
                    progressdialog.dismiss();
                    Intent i=new Intent(MainActivity.this,Login.class);
                    startActivity(i);

                    id=firebasedatabase.push().getKey();
                    database db=new database(id,_userName,email,password);
                    firebasedatabase.child(id).setValue(db);
                }else{
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Toast.makeText(MainActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    // Toast.makeText(loginActivity.this, "Could not login please try again ..!", Toast.LENGTH_SHORT).show();

                    progressdialog.dismiss();

                }
            }
        });

    }
    @Override
    public void onClick(View view) {
        if(view==_Register){
            RegisterUser();

        }if(view==_alreadyhaveaccount){
            Intent i=new Intent(MainActivity.this,Login.class);
            startActivity(i);
        }
    }
    public String GetName(){
        return _UserName.getText().toString().trim();
    }
    public String GetMail(){
        return  emailID.getText().toString().trim();
    }
}
