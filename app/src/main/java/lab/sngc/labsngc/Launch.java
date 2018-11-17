package lab.sngc.labsngc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Launch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    public void next(View v){

        Intent i;
        switch (v.getId()){

            case R.id.login: i = forward();break;

            case R.id.accl: i = new Intent(this,Accelerometer.class);break;

            case R.id.gps:  i = new Intent(this,GPS.class);break;

            case R.id.gpssrv:  i = new Intent(this,GPSWithService.class);break;

            default: i = new Intent(this,Launch.class);
        }

        startActivity(i);
        finish();
    }

    private Intent forward() {
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.contains("loggedIn")){
            // user already logged in
            String usr = sp.getString("usr"," ");
            return  new Intent(this,Home.class).putExtra("usr",usr);
        }
        else{
            // redirect user to login page
            return new Intent(this,Login.class);
        }
    }
}
