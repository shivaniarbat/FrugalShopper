package cs.uga.edu.frugalshopper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOError;
import java.io.IOException;

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
            /* Read input values entered by the user. User can enter in any sequence. Compute the unit
            * prices to find out the minimum and display on top in the text view */

            try {

                if((!textViewPriceA.getText().equals(null)) && (!textViewWtPoundA.getText().equals(null))
                        && (!textViewWtOzA.getText().equals(null)) ){
                    // read Item A
                    getPriceA = ParseDouble(textViewPriceA.getText().toString());
                    getWtPoundA = (int) ParseInt(textViewWtPoundA.getText().toString());
                    getWtOzA = (int) ParseInt(textViewWtOzA.getText().toString());

                    // calculate unit prices for each item respectively
                    try {
                        unitPriceA = (getPriceA / ((getWtPoundA * 16) + getWtOzA));
                    } catch(ArithmeticException ae){
                        unitPriceA = 0.0;
                    }
                }

                if((!textViewPriceB.getText().equals(null)) && (!textViewWtPoundB.getText().equals(null))
                        && (!textViewWtOzB.getText().equals(null)) ) {
                    // read Item B
                    getPriceB = ParseDouble(textViewPriceB.getText().toString());
                    getWtPoundB = (int) ParseInt(textViewWtPoundB.getText().toString());
                    getWtOzB = (int) ParseInt(textViewWtOzB.getText().toString());

                    // calculate unit prices for each item respectively
                    try {
                        unitPriceB = (getPriceB / ((getWtPoundB * 16) + getWtOzB));
                    } catch (ArithmeticException ae){
                        unitPriceB = 0.0;
                    }
                }

                if((!textViewPriceC.getText().equals(null)) && (!textViewWtPoundC.getText().equals(null))
                        && (!textViewWtOzC.getText().equals(null)) ) {
                    // read Item C
                    getPriceC = ParseDouble(textViewPriceC.getText().toString());
                    getWtPoundC = (int) ParseInt(textViewWtPoundC.getText().toString());
                    getWtOzC = (int) ParseInt(textViewWtOzC.getText().toString());

                    // calculate unit prices for each item respectively
                    try {
                        unitPriceC = (getPriceC / ((getWtPoundC * 16) + getWtOzC));
                    } catch (ArithmeticException ae){
                        unitPriceC = 0.0;
                    }
                }

                // display the desired result
                if(unitPriceA < unitPriceB) {
                    if(unitPriceA < unitPriceC){
                        if(unitPriceA != 0.0 && unitPriceA != Double.POSITIVE_INFINITY) {
                            frugalBuyOutput.setText("A");
                        }
                    } else {
                        if(unitPriceC != 0.0 && unitPriceA != Double.POSITIVE_INFINITY) {
                            frugalBuyOutput.setText("C");
                        }
                    }
                } else {
                    if(unitPriceB < unitPriceC){
                        if(unitPriceB != 0.0 && unitPriceA != Double.POSITIVE_INFINITY) {
                            frugalBuyOutput.setText("B");
                        }
                    } else {
                        if(unitPriceC != 0.0 && unitPriceA != Double.POSITIVE_INFINITY) {
                            frugalBuyOutput.setText("C");
                        }
                    }
                }
            } catch (NumberFormatException nfe) {
                Log.i(DEBUG_TAG,"Incorrect Number Format for inputs provided by the user");
                // set everything to zeros and display Incorrect format in frugalBuyOutput textView
                textViewPriceA.setText(null);
                textViewPriceB.setText(null);
                textViewPriceC.setText(null);

                textViewWtOzA.setText(null);
                textViewWtOzB.setText(null);
                textViewWtOzC.setText(null);

                textViewWtPoundA.setText(null);
                textViewWtPoundB.setText(null);
                textViewWtPoundC.setText(null);

                frugalBuyOutput.setText("Incorrect Data Format");
            } catch (IOError ioe){
                Log.i(DEBUG_TAG,"Incorrect Number Format for inputs provided by the user");

                // set everything to zeros and display Incorrect format in frugalBuyOutput textView
                textViewPriceA.setText(null);
                textViewPriceB.setText(null);
                textViewPriceC.setText(null);

                textViewWtOzA.setText(null);
                textViewWtOzB.setText(null);
                textViewWtOzC.setText(null);

                textViewWtPoundA.setText(null);
                textViewWtPoundB.setText(null);
                textViewWtPoundC.setText(null);

                frugalBuyOutput.setText("Incorrect Data Format");
            } catch (Exception e) {
                Log.i(DEBUG_TAG,"Incorrect Number Format for inputs provided by the user");

                // set everything to zeros and display Incorrect format in frugalBuyOutput textView
                textViewPriceA.setText(null);
                textViewPriceB.setText(null);
                textViewPriceC.setText(null);

                textViewWtOzA.setText(null);
                textViewWtOzB.setText(null);
                textViewWtOzC.setText(null);

                textViewWtPoundA.setText(null);
                textViewWtPoundB.setText(null);
                textViewWtPoundC.setText(null);

                frugalBuyOutput.setText("Something went Wrong!");
            }
        }
    }
}
