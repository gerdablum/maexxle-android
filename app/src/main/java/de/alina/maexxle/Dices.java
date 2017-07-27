package de.alina.maexxle;

/**
 * Diese Klasse repräsentiert die zwei Würfel
 * Created by Alina on 12.02.2017.
 */
import java.util.Random;
public class Dices {

    private boolean isVisible;
    private int[] values;

    public Dices(){
        isVisible = true;
        values = new int[]{1,1};
    }

    public boolean isVisible(){
        return isVisible;
    }

    public void setVisible(boolean isVisible){
        this.isVisible = isVisible;
    }

    public int[] getValues(){
        return values;
    }

    /**
     * erzeugt neue Würfelzahlen die sortiert werden
     * @return Werte der Würfel von 0 bis 5
     */
    public int[] throwDice(){
        //2 neue Zufallszahlen erzeugen und in values speichern
        Random r = new Random();
        values[0] =( Math.abs(r.nextInt()) %6)+1;
        values[1] =( Math.abs(r.nextInt()) %6)+1;

        //die größere Zahl ist immer vorne
        if(values[0] < values[1]){
            int a = values[0];
            values[0] = values[1];
            values[1] = a;
        }
        return values;
    }
}
