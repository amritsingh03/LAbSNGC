package lab.sngc.labsngc;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Accelerometer extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    Sensor accelerometer;
    private TextView textView4,textView6,textView7;
    private TextView xmin,xmax,ymin,ymax,zmin,zmax;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        textView4=(TextView) findViewById(R.id.textView4);
        textView6=(TextView) findViewById(R.id.textView6);
        textView7=(TextView) findViewById(R.id.textView7);

        xmin=(TextView) findViewById(R.id.textView8);
        ymin=(TextView) findViewById(R.id.textView9);
        zmin=(TextView) findViewById(R.id.textView10);
        xmax=(TextView) findViewById(R.id.textView11);
        ymax=(TextView) findViewById(R.id.textView12);
        zmax=(TextView) findViewById(R.id.textView13);
        xmin.setText(""+Float.MAX_VALUE);
        ymin.setText(""+Float.MAX_VALUE);
        zmin.setText(""+Float.MAX_VALUE);
        xmax.setText(""+Float.MIN_VALUE);
        ymax.setText(""+Float.MIN_VALUE);
        zmax.setText(""+Float.MIN_VALUE);

        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Accelerometer.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onBackPressed(){
        Intent i = new Intent(this,Launch.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        textView4.setText(" "+event.values[0]);
        float newvalue1=event.values[0];
        float prevmin1=Float.parseFloat(xmin.getText().toString());
        if(newvalue1<prevmin1){
            xmin.setText(""+newvalue1);
        }
        float prevmax1=Float.parseFloat(xmax.getText().toString());
        if(newvalue1>prevmax1){
            xmax.setText(""+newvalue1);
        }


        textView6.setText(" "+event.values[1]);
        float newvalue2=event.values[1];
        float prevmin2=Float.parseFloat(ymin.getText().toString());
        if(newvalue2<prevmin2){
            ymin.setText(""+newvalue2);
        }
        float prevmax2=Float.parseFloat(ymax.getText().toString());
        if(newvalue2>prevmax2){
            ymax.setText(""+newvalue2);
        }

        textView7.setText(" "+event.values[2]);
        float newvalue3=event.values[2];
        float prevmin3=Float.parseFloat(zmin.getText().toString());
        if(newvalue3<prevmin3){
            zmin.setText(""+newvalue3);
        }
        float prevmax3=Float.parseFloat(zmax.getText().toString());
        if(newvalue3>prevmax3){
            zmax.setText(""+newvalue3);
        }

    }
}