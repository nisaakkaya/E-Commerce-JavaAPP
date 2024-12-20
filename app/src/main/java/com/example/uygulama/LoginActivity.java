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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.textfield.TextInputLayout;
public class LoginActivity extends AppCompatActivity {
    TextInputLayout userEmailWrapper, userPasswordWrapper;
    EditText userEmail, userPassword;
    Button btnLogin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        userEmailWrapper=findViewById(R.id.userEmailWrapper);
        userPasswordWrapper=findViewById(R.id.userPasswordWrapper);
        userEmail=findViewById(R.id.userEmail);
        userPassword=findViewById(R.id.userPassword);
        btnLogin=findViewById(R.id.btnUserLogin);
        mAuth=FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=userEmail.getText().toString().trim();
                String password=userPassword.getText().toString().trim();
                if(email.isEmpty()){
                    userEmailWrapper.setError("Enter email");
                    userEmailWrapper.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    userPasswordWrapper.setError("Enter password");
                    userPasswordWrapper.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(LoginActivity.this,ShopActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), (CharSequence) task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}