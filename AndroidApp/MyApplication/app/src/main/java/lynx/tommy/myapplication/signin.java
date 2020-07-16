package lynx.tommy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signin extends AppCompatActivity implements View.OnClickListener {
    Button signup,buttonLogin;
    EditText usename,password;
    private FirebaseAuth mAuth;
    private String TAG ="help";
    String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinpage);
        //Sign Up and Forgot Password Button
        signup = (Button) findViewById(R.id.signup);


        //username and password
        init();

        //login button
        buttonLogin = (Button)findViewById(R.id.login);
        buttonLogin.setOnClickListener(this);

        //initialize firebase
        mAuth = FirebaseAuth.getInstance();


        //onclicklisteners for signup and forgot password
        signup.setOnClickListener(this);
        //Firebase auth

    }
    @Override
    public void onClick(View v) {
        if (signup == v) {
            Toast.makeText(signin.this, "Getting you to the Sign Up page",
                    Toast.LENGTH_LONG).show();
            Intent ex = new Intent(signin.this, signupage.class);
            startActivity(ex);

        }
        if(buttonLogin == v)
        {
            //Sign In Existing Users
            email=usename.getText().toString();
            pass=password.getText().toString();
            if(email.isEmpty()||pass.isEmpty())
                Toast.makeText(getApplicationContext(),"One of the fields are empty",
                        Toast.LENGTH_LONG);
            else {
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful",
                                            Toast.LENGTH_LONG).show();
                                    Intent m = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(m);
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Please Check your Email ID and password",
                                            Toast.LENGTH_LONG).show();
                                    Log.d(TAG, email);
                                    Log.d(TAG, pass);
                                    Log.d(TAG, "Firebase error: ", task.getException());
                                }
                            }
                        });
                Toast.makeText(getApplicationContext(), "Wait for a moment", Toast.LENGTH_LONG).show();
            }

        }

    }

    private void init()
    {
        password = (EditText)findViewById(R.id.pwdsign);
        usename = (EditText)findViewById(R.id.idemail);
    }
}


