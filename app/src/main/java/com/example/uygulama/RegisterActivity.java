package com.example.uygulama;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
public class RegisterActivity extends AppCompatActivity {
EditText ufirstname,ulastname,uemail,upassword,uconfpassword,ucontactno;
Button btnRegister;
TextInputLayout userFirstNameWrapper, userLastNameWrapper, userEmailWrapper,userPasswordWrapper, userConfPasswordWrapper, userContactNoWrapper;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        ufirstname=findViewById(R.id.userFirstName);
        ulastname=findViewById(R.id.userLastName);
        uemail=findViewById(R.id.userEmailAddress);
        upassword=findViewById(R.id.userPassword);
        uconfpassword=findViewById(R.id.userConfPassword);
        ucontactno=findViewById(R.id.userContactNumber);
        userFirstNameWrapper=findViewById(R.id.firstNameWrapper);
        userLastNameWrapper=findViewById(R.id.lastNameWrapper);
        userEmailWrapper=findViewById(R.id.userEmailAddressWrapper);
        userPasswordWrapper=findViewById(R.id.passwordWrapper);
        userConfPasswordWrapper=findViewById(R.id.confPasswordWrapper);
        userContactNoWrapper=findViewById(R.id.contactNoWrapper);
        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()==null){
                    Toast.makeText(RegisterActivity.this,"Account not create, reason for system", Toast.LENGTH_LONG).show();
                  //user is logged in and can redirect in another activity
                }else {
                    String firstname = ufirstname.getText().toString().trim();
                    String lastname = ulastname.getText().toString().trim();
                    String email = uemail.getText().toString().trim();
                    String password = upassword.getText().toString().trim();
                    String confpassword = uconfpassword.getText().toString().trim();
                    String contactno = ucontactno.getText().toString().trim();
                    if (firstname.isEmpty()) {
                        userFirstNameWrapper.setError("Enter Firstname");
                        userFirstNameWrapper.requestFocus();
                        return;
                    }
                    if (lastname.isEmpty()) {
                        userLastNameWrapper.setError("Enter Lastname");
                        userLastNameWrapper.requestFocus();
                        return;
                    }
                    if (email.isEmpty()) {
                        userEmailWrapper.setError("Enter Email");
                        userEmailWrapper.requestFocus();
                        return;
                    }
                    if (password.isEmpty()) {
                        userPasswordWrapper.setError("Enter Password");
                        userPasswordWrapper.requestFocus();
                        return;
                    }
                    if (confpassword.isEmpty()) {
                        userConfPasswordWrapper.setError("Enter Confirm Password");
                        userConfPasswordWrapper.requestFocus();
                        return;
                    }
                    if (!password.equals(confpassword)) {
                        userConfPasswordWrapper.setError("Password didn't match");
                        userConfPasswordWrapper.requestFocus();
                        return;
                    }
                    if (contactno.isEmpty()) {
                        userContactNoWrapper.setError("Enter Firstname");
                        userContactNoWrapper.requestFocus();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Users user=new Users(firstname,lastname,email,contactno);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(RegisterActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                                    startActivity(intent);

                                                }else{
                                                    Toast.makeText(RegisterActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                }

                                            }
                                        });
                            }else{
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}