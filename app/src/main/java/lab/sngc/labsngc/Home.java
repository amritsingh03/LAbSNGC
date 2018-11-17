package lab.sngc.labsngc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    Button btback,btlout;
    TextView welcum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btback = findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        btlout = findViewById(R.id.btlogout);
        btlout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        welcum = findViewById(R.id.welcum);
        String usr = getIntent().getExtras().getString("usr");
        welcum.setText("Welcome " + usr + " !");
    }

    private void logout() {
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.remove("usr");
        et.remove("loggedIn");
        et.commit();

        Intent i = new Intent(this,Login.class);
        startActivity(i);
        finish();
    }

    private void back() {
      Intent i = new Intent(this,Launch.class);
      startActivity(i);
      finish();
    }

    public void onBackPressed(){
        back();
    }
}
