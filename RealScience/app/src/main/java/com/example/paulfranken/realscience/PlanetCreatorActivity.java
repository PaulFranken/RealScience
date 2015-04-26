package com.example.paulfranken.realscience;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NavUtils;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.paulfranken.realscience.Planet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class PlanetCreatorActivity extends Activity implements View.OnClickListener {


    Spinner typeSpinner;
    EditText diameterText;
    EditText nameText;
    EditText massDensityEditText;
    ArrayAdapter<String> typeArray;
    private static TextView massDensityText;
    public static Planet planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_creator);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C6D729")));
        bar.setDisplayHomeAsUpEnabled(true);

        String mArray[] = getResources().getStringArray(R.array.planetTypesArray);
        typeSpinner = (Spinner) findViewById(R.id.spinner);
        typeSpinner.setAdapter(typeArray);

        typeArray = new ArrayAdapter<String>(this, R.layout.spinner, mArray);
        typeSpinner.setAdapter(typeArray);
        massDensityText = (TextView)findViewById(R.id.textView29);
        massDensityEditText = (EditText)findViewById(R.id.editText3);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getSelectedItemPosition() == 2){
                    massDensityText.setText("Solar Masses: ");
                    massDensityEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                }
                else{
                    massDensityText.setText("Density: ");
                    massDensityEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button createButton = (Button)findViewById(R.id.button2);
        createButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planet_creator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makePlanet(View view){
        diameterText = (EditText)findViewById(R.id.editText);
        nameText = (EditText)findViewById(R.id.editText2);
        massDensityEditText = (EditText)findViewById(R.id.editText3);
        int diameter = Integer.valueOf(diameterText.getText().toString());
        String name = nameText.getText().toString();
        double massDensity;
        String mdText = massDensityEditText.getText().toString();


        if(typeSpinner.getSelectedItemPosition() == 2){
            List<String> list = new ArrayList<>();
            list.add(diameterText.getText().toString());
            list.add(mdText);
            list.add(name);
            list.add("2");
            Intent intent = new Intent(getBaseContext(), PlanetShowcaseActivity.class);
            intent.putStringArrayListExtra("PlanetOrStar", (ArrayList<String>)list);
            startActivity(intent);
        }
        else{
            List<String> list = new ArrayList<String>();
            list.add(diameterText.getText().toString());
            list.add(mdText);
            list.add(name);
            list.add("1");
            Intent intent = new Intent(getBaseContext(), PlanetShowcaseActivity.class);
            intent.putStringArrayListExtra("PlanetOrStar", (ArrayList<String>)list);
            startActivity(intent);
        }

        //Planet planet = new Planet(diameter, );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                makePlanet(v);
        }
    }
}
