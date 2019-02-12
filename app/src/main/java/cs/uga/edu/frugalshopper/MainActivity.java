/**
 * FRUGAL BUY -
 *          Android App which compares three products and computes the unit price to determine
 *          the furgal buy.
 * @author Shivani Arbat
 * @version 1.0
 * @since 02/11/2019
 */

package cs.uga.edu.frugalshopper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOError;
import java.text.DecimalFormat;

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
    private DecimalFormat formatNumber = new DecimalFormat("$#0.00");

    private TextView textViewPriceA;
    private TextView textViewPriceB;
    private TextView textViewPriceC;

    private TextView textViewWtPoundA;
    private TextView textViewWtPoundB;
    private TextView textViewWtPoundC;

    private TextView textViewWtOzA;
    private TextView textViewWtOzB;
    private TextView textViewWtOzC;

    private TextView textViewUnitPriceLabelA;
    private TextView textViewUnitPriceLabelB;
    private TextView textViewUnitPriceLabelC;

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

        textViewUnitPriceLabelA = (TextView) findViewById(R.id.unitPriceLabelA);
        textViewUnitPriceLabelB = (TextView) findViewById(R.id.unitPriceLabelB);
        textViewUnitPriceLabelC = (TextView) findViewById(R.id.unitPriceLabelC);

        // set views for output
        frugalBuyOutput = (TextView) findViewById(R.id.frugalBuy);
        calculateFrugalBuy = (Button) findViewById(R.id.calculateFrugalBuyButton);
        calculateFrugalBuy.setOnClickListener(new ButtonClickListener());

    }

    /**
     * Returns Double value from string input. Converts input string number to Double.
     * @param strNumber     input String which will be converted
     * @return  Double      output message to be displayed to user
     * @exception NumberFormatException
     * @exception Exception
     */

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            }catch (NumberFormatException ne){
                return -1;
            }
            catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function valid-
                // ates field at intital point
            }
        }
        else return 0;
    }

    /**
     * Returns Integer value from the string input. Converts input string number to Integer.
     * @param strNumber     input String which will be converted
     * @return  Integer     output message to be displayed to user
     * @exception NumberFormatException
     * @exception Exception
     */

    double ParseInt(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Integer.parseInt(strNumber);
            } catch (NumberFormatException ne){
                return -1;
            }
            catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function valid-
                // ates field at initial point
            }
        }
        else return 0;
    }

    /**
    * Returns a String object that can be displayed on screen as which price is minimum and which
    * item is best buy for the computer unit prices.
    * priceA, priceB and priceC arguments are which are compared to find the frugal buy.
     *
     * This method immediately returns the string determined as which product has the minumum unit
     * as per the input values from the user. Also if no minimum is found, displays a message to user
     * to check input and try again.
     *
     * @param priceA    double value to compare
     * @param priceB    double value to compare
     * @param priceC    double value to compare
     * @return String   returns output string to display to user
     */

    String findFrugalBuy(double priceA, double priceB, double priceC) {
        String outputMessage = " ";
        Double[] unitPrices = {priceA,priceB,priceC};
        double min = Double.MAX_VALUE;
        int flag = Integer.MAX_VALUE;

        for(int i=0;i<unitPrices.length;i++){
            if(min > unitPrices[i] && unitPrices[i] != 0.0){
                min = unitPrices[i];
                flag = i;
            }
        }

        switch (flag){
            case 0:
                outputMessage = "Buy product A";
                break;
            case 1:
                outputMessage = "Buy product B";
                break;
            case 2:
                outputMessage = "Buy product C";
                break;
            default:
                outputMessage = " ";
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Enter values and try again ! !",
                        Toast.LENGTH_SHORT);

                toast.show();
        }

        return outputMessage;
    }

    private class ButtonClickListener implements View.OnClickListener {
        /**
         *  Action to perform when user clicks the button.
         *  Read input values entered by the user. User can enter in any sequence. Compute the unit
         *  prices to find out the minimum and display on top in the text view.
         * @param   v   View
         * @see     #findFrugalBuy(double, double, double)
         * @exception ArithmeticException
         * @exception NumberFormatException
         * @exception IOError
         * @exception Exception
         */
        @Override
        public void onClick(View v) {
            /*  */

            frugalBuyOutput.setText(" ");

            try {

                if((!textViewPriceA.getText().equals(null)) && (!textViewWtPoundA.getText().equals(null))
                        && (!textViewWtOzA.getText().equals(null)) ){
                    /* read Item A */
                    getPriceA = ParseDouble(textViewPriceA.getText().toString());
                    getWtPoundA = (int) ParseInt(textViewWtPoundA.getText().toString());
                    getWtOzA = (int) ParseInt(textViewWtOzA.getText().toString());

                    /* calculate unit prices for each item respectively */
                    try {
                        unitPriceA = (getPriceA / ((getWtPoundA * 16) + getWtOzA));
                        if (Double.isNaN(unitPriceA) || Double.isInfinite(unitPriceA)) {
                            unitPriceA = 0.0;
                        }
                    } catch(ArithmeticException ae){
                        unitPriceA = 0.0;
                    }
                    /* set unit price to display to User */
                    textViewUnitPriceLabelA.setText(formatNumber.format(unitPriceA));
                }

                if((!textViewPriceB.getText().equals(null)) && (!textViewWtPoundB.getText().equals(null))
                        && (!textViewWtOzB.getText().equals(null)) ) {
                    /* read Item B */
                    getPriceB = ParseDouble(textViewPriceB.getText().toString());
                    getWtPoundB = (int) ParseInt(textViewWtPoundB.getText().toString());
                    getWtOzB = (int) ParseInt(textViewWtOzB.getText().toString());

                    /* calculate unit prices for each item respectively */
                    try {
                        unitPriceB = (getPriceB / ((getWtPoundB * 16) + getWtOzB));
                        if (Double.isNaN(unitPriceB) || Double.isInfinite(unitPriceB)) {
                            unitPriceB = 0.0;
                        }
                    } catch (ArithmeticException ae){
                        unitPriceB = 0.0;
                    }
                    /* set unit price to display to User */
                    textViewUnitPriceLabelB.setText(formatNumber.format(unitPriceB));

                }

                if((!textViewPriceC.getText().equals(null)) && (!textViewWtPoundC.getText().equals(null))
                        && (!textViewWtOzC.getText().equals(null)) ) {
                    /* read Item C */
                    getPriceC = ParseDouble(textViewPriceC.getText().toString());
                    getWtPoundC = (int) ParseInt(textViewWtPoundC.getText().toString());
                    getWtOzC = (int) ParseInt(textViewWtOzC.getText().toString());

                    /* calculate unit prices for each item respectively */
                    try {
                        unitPriceC = (getPriceC / ((getWtPoundC * 16) + getWtOzC));
                        if (Double.isNaN(unitPriceC) || Double.isInfinite(unitPriceC)) {
                            unitPriceC = 0.0;
                        }
                    } catch (ArithmeticException ae){
                        unitPriceC = 0.0;
                    }
                    /* set unit price to display to User */
                    textViewUnitPriceLabelC.setText(formatNumber.format(unitPriceC));
                }

                /* display the desired result
                *  call method to determine frugal buy */
                frugalBuyOutput.setText(findFrugalBuy(unitPriceA,unitPriceB,unitPriceC));


            } catch (NumberFormatException nfe) {
                Log.i(DEBUG_TAG,"Incorrect Number Format for inputs provided by the user");
                /* set everything to zeros and display Incorrect format in frugalBuyOutput textView */
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

                /* set everything to zeros and display Incorrect format in frugalBuyOutput textView */
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

                /* set everything to zeros and display Incorrect format in frugalBuyOutput textView */
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
