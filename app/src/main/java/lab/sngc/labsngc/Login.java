package lab.sngc.labsngc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText usr,psw;
    Button sbmt,snup;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        usr = findViewById(R.id.usrnm);
        psw = findViewById(R.id.psswd);
        sbmt = findViewById(R.id.btsbmt);
        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceed();
            }
        });
        snup = findViewById(R.id.btsnup);
        snup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect();
            }
        });

    }

    private void redirect() {

        Intent i = new Intent(this,SignUp.class);
        startActivity(i);
        loginDataBaseAdapter.close();
        finish();
    }

    private void proceed() {

        String usrmn = "";
        String psswd = "";

        int ercode = 0; // no error
        try {
             usrmn = usr.getText().toString();
             psswd = psw.getText().toString();

            if (usrmn == null || usrmn.equals("")) {
                ercode = -1;  // no username
                throw new Exception();
            }
            if (psswd == null || psswd.equals("")) {
                ercode = -2; // no password
                throw new Exception();
            }

            // check database condition here
            String sPass = loginDataBaseAdapter.getSinlgeEntry(usrmn);
            if(sPass.equals("NOT EXIST")){
                ercode = -4; // no username found
                throw new Exception();
            }

            if (psswd.equals(sPass)) {
                ercode = 1;  // proceed to next activity
            } else {
                ercode = -3; // invalid combination of username or password
                throw new Exception();

            }
        } catch (Exception ex) {

            String msg = "alert";

            switch (ercode){
                case -1: msg = "No Username entered";break;
                case -2: msg = "No Password entered";break;
                case -3: msg = "Invalid combination of username or password";break;
                case -4: msg = "Username not found";break;
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
        if (ercode == 1) {
            // successful login
            getSharedPreferences("login",MODE_PRIVATE).edit().putString("usr",usrmn).putString("loggedIn","true").commit();
            Intent i = new Intent(this, Home.class).putExtra("usr",usrmn);
            startActivity(i);
            loginDataBaseAdapter.close();
            finish();
        }

    }
    public void onBackPressed(){
        Intent i = new Intent(this,Launch.class);
        startActivity(i);
        finish();
    }
}
