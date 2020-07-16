package lynx.tommy.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class signupage extends AppCompatActivity {

    private EditText fullname, email, password;
    private Button b1;
    private FirebaseAuth mAuth;
    private String TAG= "help";
    final String regexPassword = ".{8,}";
    AwesomeValidation mw = new AwesomeValidation(ValidationStyle.COLORATION);
    String emailx,passwordx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupage);
        b1 =(Button)findViewById(R.id.button);
        init();

        mAuth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener()
                              {
                                  public void onClick(View v)
                                  {
                                      validate_users();
                                  }
                              }
        );
    }

    private void validate_users()
    {
        //validate email
        mw.setColor(Color.RED);
        mw.addValidation(this, R.id.fullname, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        mw.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mw.addValidation(this, R.id.password, regexPassword, R.string.invalid_password);
        emailx=email.getText().toString();
        passwordx=password.getText().toString();
        if(mw.validate()) {
            mAuth.createUserWithEmailAndPassword(emailx, passwordx)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent tx = new Intent(getApplicationContext(), signin.class);
                                startActivity(tx);
                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.d(TAG, "firebase success");
                                Log.d(TAG,emailx);
                                Log.d(TAG,passwordx);
                            } else {
                                Toast.makeText(getApplicationContext(), "User Exists",
                                        Toast.LENGTH_LONG).show();
                                Log.d(TAG, "firebase failure",task.getException());
                                Log.d(TAG, emailx);
                                Log.d(TAG, passwordx);
                            }
                        }
                    });
        }
    }

    private void init()
    {
        fullname = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }


}








