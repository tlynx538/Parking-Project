package lynx.tommy.myapplication;

import android.widget.EditText;

public class validateclass {
    public boolean validateemail(EditText email)
    {
        String emailtext = email.getText().toString().trim();
        String emailPatttern = "String emailPattern = \"[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+\"";
        if(emailtext.matches(emailPatttern))
            return true;

        else if(emailtext.length()==0)
            return false;
        else
            return false;
    }
}
