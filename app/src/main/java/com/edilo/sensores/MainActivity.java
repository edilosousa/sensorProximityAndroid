package com.edilo.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView proximitySensor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proximitySensor=findViewById(R.id.proximitySensorTv);
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null){
            Sensor proximitySensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if(proximitySensor!=null){
                sensorManager.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }else{
            Toast.makeText(this,"Sensor nao encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String sensorValue="Proximity Value: "+sensorEvent.values[0];
        proximitySensor.setText(sensorValue);
        if(sensorEvent.values[0]>0){
            Toast.makeText(this,"O Objeto esta longe", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"O Objeto esta proximo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}