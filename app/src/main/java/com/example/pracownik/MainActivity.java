package com.example.pracownik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pracownik.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String firstName, email, passwd, userName;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        System.out.println("Witaj w apce");
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = ((EditText)findViewById(R.id.firstName)).getText().toString();
                email = binding.email.getText().toString();
                passwd = binding.passwd.getText().toString();
                userName = binding.userName.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                Toast.makeText(MainActivity.this,"Kliknięto\n"+firstName+email+passwd+userName,Toast.LENGTH_LONG).show();


                if (!firstName.isEmpty() && !email.isEmpty() && !passwd.isEmpty() && !userName.isEmpty() && passwd.length()>6){
                    Users users = new Users(firstName,email,passwd,userName,true);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    System.out.println("Dodawanie ");
                    reference.child(userName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            binding.firstName.setText("");
                            binding.email.setText("");
                            binding.passwd.setText("");
                            binding.userName.setText("");
                            Toast.makeText(MainActivity.this,"Successfuly Updated",Toast.LENGTH_SHORT).show();




                        }
                    });


                    mAuth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();

                                FirebaseUser user = mAuth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userName).build();
                                user.updateProfile(profileUpdates);
                                finish();


                            }else{
                                Toast.makeText(MainActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });




                }else {Toast.makeText(MainActivity.this, "Błąd - hasło musi mieć min 7 znaków", Toast.LENGTH_LONG).show();}

            }
        });


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });


    }
}