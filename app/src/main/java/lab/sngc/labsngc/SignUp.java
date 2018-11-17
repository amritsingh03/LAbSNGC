package lab.sngc.labsngc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText usr,psw;
    Button snup;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        usr = findViewById(R.id.usrnm);
        psw = findViewById(R.id.psswd);
        snup = findViewById(R.id.btsnup);
        snup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceed();
            }
        });


    }

    private void proceed() {

        String usrmn = "admin";
        String psswd = "admin";

        int ercode = 0; // no error
        try {
             usrmn = usr.getText().toString();
             psswd = psw.getText().toString();

            if (usrmn == null || usrmn.equals("")) {
                ercode = -1;  // no username
                throw new Exception();
            }
            if (psswd == null || psswd.equals("") || psswd.length() < 8) {
                ercode = -2; // no password
                throw new Exception();
            }
        } catch (Exception ex) {

            String msg = "alert";

            switch (ercode){
                case -1: msg = "No username entered";break;
                case -2: msg = "Minimum password length is 8 characters";break;
                case -3: msg = "Invalid combination of username or password";break;
            }

            // Create alert dialog

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage(msg);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
        if (ercode == 0) {

            // write to database
            loginDataBaseAdapter.insertEntry(usrmn, psswd);
            // goto login page
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            loginDataBaseAdapter.close();
            finish();
        }

    }

    public void onBackPressed(){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
        finish();
    }
}
