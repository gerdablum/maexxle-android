package de.alina.maexxle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.os.Vibrator;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



/**
 * Diese Klasse repräsentiert die Ansicht mit 2 Würfeln und 2 Buttons
 */
public class MainDices extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Shaker mShakeDetector;

    private Button showHide;
    private Button throwing;
    private ImageView diceImage1;
    private ImageView diceImage2;
    private CheckBox checkVibration;
    private boolean isVibratorOn = true;
    private Dices dices;
    private Vibrator v;
    private AdView mAdView;

    /**
     * Würfel anzeigen/verbergen
     */
    public void showHide(){
        if(!dices.isVisible()){
            dices.setVisible(true);
            setImage(diceImage1, dices.getValues()[0]);
            setImage(diceImage2, dices.getValues()[1]);
            showHide.setText("verstecken");
        } else{
            dices.setVisible(false);
            diceImage1.setImageResource(R.drawable.hidden);
            diceImage2.setImageResource(R.drawable.hidden);
            showHide.setText("anzeigen");
        }
    }

    /**
     * würfeln und Zahlen zur Kontrolle auf der Konsole ausgeben
     */
    public void throwDices(){
        int[] values = dices.throwDice();
        if(dices.isVisible()){
            setImage(diceImage1, values[0]);
            setImage(diceImage2, values[1]);
        }

        if(isVibratorOn){
            v.vibrate(500);
        }

        System.out.println(dices.getValues()[0]);
        System.out.println(dices.getValues()[1]);
    }

    /**
     * Ändert die Quelle des Bildes
     * @param dice Würfel, dessen Augenzahl geändert wird
     * @param value Augenzahl
     */
    public void setImage(ImageView dice, int value){
        switch (value){
            case 1: dice.setImageResource(R.drawable.dice_1);
                break;
            case 2: dice.setImageResource(R.drawable.dice_2);
                break;
            case 3: dice.setImageResource(R.drawable.dice_3);
                break;
            case 4: dice.setImageResource(R.drawable.dice_4);
                break;
            case 5: dice.setImageResource(R.drawable.dice_5);
                break;
            case 6: dice.setImageResource(R.drawable.dice_6);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dices);

        //neues Würfelobjekt anlegen
        dices = new Dices();

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        //Buttons und Images erzeugen
        showHide = (Button) findViewById(R.id.showHide);
        throwing = (Button) findViewById(R.id.throwing);
        checkVibration = (CheckBox) findViewById(R.id.isVibratorOn);
        diceImage1 = (ImageView) findViewById(R.id.imageDice1);
        diceImage2 = (ImageView) findViewById(R.id.imageDice2);

        //Checkbox default true setzen
        checkVibration.setChecked(true);

        //Werbeobjekte setzen
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //ActionListener setzen
        showHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHide();
            }
        });

        throwing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throwDices();
            }
        });

        checkVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isVibratorOn = true;
                } else {
                    isVibratorOn = false;
                }
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new Shaker();
        mShakeDetector.setOnShakeListener(new Shaker.OnShakeListener() {

            @Override
            public void onShake(int count) {
				throwDices();
            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
