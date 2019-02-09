package cs.uga.edu.frugalshopper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "FrugalShopper";
    private double getPriceA = 0.0;
    private double getPriceB = 0.0;
    private double getPriceC = 0.0;
    private int getWtPoundA = 0;
    private int getWtPoundB = 0;
    private int getWtPoundC = 0;
    private int getWtOzA = 0;
    private int getWtOzB = 0;
    private int getWtOzC = 0;
    private double unitPriceA = 0.0;
    private double unitPriceB = 0.0;
    private double unitPriceC = 0.0;

    private TextView textViewPriceA;
    private TextView textViewPriceB;
    private TextView textViewPriceC;

    private TextView textViewWtPoundA;
    private TextView textViewWtPoundB;
    private TextView textViewWtPoundC;

    private TextView textViewWtOzA;
    private TextView textViewWtOzB;
    private TextView textViewWtOzC;


    private TextView frugalBuyOutput;
    private Button calculateFrugalBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(DEBUG_TAG,"onCreate() method of the MainActivity Class");

        setContentView(R.layout.activity_main);


        System.out.println("IN ONCREATE()");
        // set views for input
        textViewPriceA = (TextView) findViewById(R.id.priceA);
        textViewPriceB = (TextView) findViewById(R.id.priceB);
        textViewPriceC = (TextView) findViewById(R.id.priceC);

        textViewWtPoundA = (TextView) findViewById(R.id.wtPoundA);
        textViewWtPoundB = (TextView) findViewById(R.id.wtPoundB);
        textViewWtPoundC = (TextView) findViewById(R.id.wtPoundC);

        textViewWtOzA = (TextView) findViewById(R.id.wtOzA);
        textViewWtOzB = (TextView) findViewById(R.id.wtOzB);
        textViewWtOzC = (TextView) findViewById(R.id.wtOzC);

        // set views for output
        frugalBuyOutput = (TextView) findViewById(R.id.frugalBuy);
        calculateFrugalBuy = (Button) findViewById(R.id.calculateFrugalBuyButton);
        calculateFrugalBuy.setOnClickListener(new ButtonClickListener());

    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return 0;
    }

    double ParseInt(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Integer.parseInt(strNumber);
            } catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return 0;
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // read input values
            // read prices
            getPriceA = ParseDouble(textViewPriceA.getText().toString());
            getPriceB = ParseDouble(textViewPriceB.getText().toString());
            getPriceC = ParseDouble(textViewPriceC.getText().toString());

            // read weights in pounds
            getWtPoundA = (int) ParseInt(textViewWtPoundA.getText().toString());
            getWtPoundB = (int) ParseInt(textViewWtPoundB.getText().toString());
            getWtPoundC = (int) ParseInt(textViewWtPoundC.getText().toString());

            // read weights in Oz
            getWtOzA = (int) ParseInt(textViewWtOzA.getText().toString());
            getWtOzB = (int) ParseInt(textViewWtOzB.getText().toString());
            getWtOzC = (int) ParseInt(textViewWtOzC.getText().toString());

            // calculate unit prices for each item respectively
            unitPriceA = (getPriceA / ((getWtPoundA * 16) + getWtOzA));
            unitPriceB = (getPriceB / ((getWtPoundB * 16) + getWtOzB));
            unitPriceC = (getPriceC / ((getWtPoundC * 16) + getWtOzC));

            System.out.println(unitPriceA);
            System.out.println(unitPriceB);
            System.out.println(unitPriceC);

            if(unitPriceA < unitPriceB) {
                if(unitPriceA < unitPriceC){
                    frugalBuyOutput.setText("A");
                } else {
                    frugalBuyOutput.setText("C");
                }
            } else {
                if(unitPriceB < unitPriceC){
                    frugalBuyOutput.setText("B");
                } else {
                    frugalBuyOutput.setText("C");
                }
            }
        }
    }
}
