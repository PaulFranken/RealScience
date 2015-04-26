package com.example.paulfranken.realscience;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class PlanetShowcaseActivity extends Activity {

    Planet planet;
    Planet earth;
    private Canvas canvas;
    ArrayList<String> list;
    Paint line = new Paint();
    Paint stroke = new Paint();
    Paint paint = new Paint();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_showcase);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C6D729")));
        bar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("PlanetOrStar");
        //planet = new Planet(Integer.parseInt(diameterText.getText().toString()), Double.parseDouble(mdText), name);
        double rDensity = (double)Math.round(Double.parseDouble(list.get(1).toString())*100)/100;


        TextView nameView = (TextView)findViewById(R.id.planetName);
        TextView circView = (TextView)findViewById(R.id.circVal);
        TextView diamView = (TextView)findViewById(R.id.diamVal);
        TextView massView = (TextView)findViewById(R.id.massVal);
        TextView volView = (TextView)findViewById(R.id.volVal);
        TextView densView = (TextView)findViewById(R.id.densVal);
        TextView gravView = (TextView)findViewById(R.id.gravVal);

        TextView comparedTo = (TextView)findViewById(R.id.textView39);
        TextView circComp = (TextView)findViewById(R.id.circComp);
        TextView diamComp = (TextView)findViewById(R.id.diamComp);
        TextView massComp = (TextView)findViewById(R.id.massComp);
        TextView volComp = (TextView)findViewById(R.id.volComp);
        TextView densComp = (TextView)findViewById(R.id.densComp);
        TextView gravComp = (TextView)findViewById(R.id.gravComp);



        if(Integer.parseInt(list.get(3)) == 1){
            planet = new Planet((Integer.parseInt(list.get(0).toString())), rDensity, list.get(2).toString());
            planet.getSurfaceGravity();



            nameView.setText(planet.name);
            circView.setText(String.valueOf(planet.circumference));
            diamView.setText(String.valueOf(planet.diameter));
            massView.setText(String.valueOf(planet.getMass()));
            volView.setText(String.valueOf(planet.volume));
            densView.setText(String.valueOf(planet.density));
            gravView.setText(String.valueOf(planet.gravity));

            earth = new Planet(40000, 5.51, "Earth");
            earth.getSurfaceGravity();
            double compCirc = (double)planet.circumference/(double)earth.circumference;
            double compDiam = (double)planet.diameter/(double)earth.diameter;
            double earthMass = earth.mass.divide(new BigDecimal("1000000000000000000000")).doubleValue();
            double planetMass = planet.mass.divide(new BigDecimal("1000000000000000000000")).doubleValue();
            double earthVolume = earth.volume.divide(new BigDecimal("1000000000")).doubleValue();
            double planetVolume = planet.volume.divide(new BigDecimal("1000000000")).doubleValue();

            double compMass = planetMass/earthMass;
            double compVol = planetVolume/earthVolume;
            double compDens = (planet.density.divide(earth.density, 2, RoundingMode.HALF_EVEN)).doubleValue();
            double compGrav = (planet.gravity.divide(earth.gravity, 2, RoundingMode.HALF_EVEN)).doubleValue();

            comparedTo.setText("Compared to Earth:");
            circComp.setText(String.valueOf(Math.round(compCirc * 100.0)/100.0));
            diamComp.setText(String.valueOf(Math.round(compDiam * 100.0)/100.0));
            massComp.setText(String.valueOf(Math.round(compMass * 100.0)/100.0));
            volComp.setText(String.valueOf(Math.round(compVol * 100.0)/100.0));
            densComp.setText(String.valueOf(Math.round(compDens * 100.0)/100.0));
            gravComp.setText(String.valueOf(Math.round(compGrav * 100.0)/100.0));



            drawPlanet();

        }
        else{
            Star star = new Star(Integer.parseInt(list.get(1)), (Integer.parseInt(list.get(0).toString())), list.get(2).toString());
            star.getSurfaceGravity();

            Star sun = new Star(1, 4400000, "Sun");
            sun.getSurfaceGravity();

            nameView.setText(star.name);
            circView.setText(String.valueOf(star.circumference));
            diamView.setText(String.valueOf(star.diameter));
            massView.setText(String.valueOf(star.actualMass));
            volView.setText(String.valueOf(star.volume));
            densView.setText(String.valueOf(star.densityDecimal));
            gravView.setText(String.valueOf(star.roundedGravity));

            comparedTo.setText("Compared to the Sun:");

            double compCirc = (star.circumference.divide(sun.circumference, 2, RoundingMode.HALF_EVEN)).doubleValue();
            double compDiam = (star.diameter.divide(sun.diameter, 2, RoundingMode.HALF_EVEN)).doubleValue();
            double sunMass = sun.actualMass.divide(new BigDecimal("1000000000000000000000")).doubleValue();
            double starMass = star.actualMass.divide(new BigDecimal("1000000000000000000000")).doubleValue();
            double sunVolume = sun.volume.divide(new BigDecimal("1000000000")).doubleValue();
            double starVolume = star.volume.divide(new BigDecimal("1000000000")).doubleValue();

            double compMass = starMass/sunMass;
            double compVol = starVolume/sunVolume;
            double compDens = (double)star.density/(double)sun.density;
            double compGrav = (double)star.roundedGravity/(double)sun.roundedGravity;

            circComp.setText(String.valueOf(Math.round(compCirc * 100.0)/100.0));
            diamComp.setText(String.valueOf(Math.round(compDiam * 100.0)/100.0));
            massComp.setText(String.valueOf(Math.round(compMass * 100.0)/100.0));
            volComp.setText(String.valueOf(Math.round(compVol * 100.0)/100.0));
            densComp.setText(String.valueOf(Math.round(compDens * 100.0)/100.0));
            gravComp.setText(String.valueOf(Math.round(compGrav * 100.0)/100.0));

            drawPlanet();
        }





        Toast t = Toast.makeText(getApplicationContext(), String.valueOf(list.get(0).toString()), Toast.LENGTH_LONG);
        t.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planet_showcase, menu);
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

    private void drawPlanet(){
        SurfaceView sf = (SurfaceView)findViewById(R.id.surfaceView2);
        sf.setZOrderOnTop(true);
        SurfaceHolder sfTrack = sf.getHolder();

        sfTrack.setFormat(PixelFormat.TRANSLUCENT);

        if(Integer.parseInt(list.get(3)) == 1){
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.terrestrial_texture_mini);
            BitmapShader shader = new BitmapShader(bm, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
            paint.setShader(shader);
            Bitmap bg = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bg);
            canvas.drawCircle(200, 140, 100, paint);


            sf.setBackgroundDrawable(new BitmapDrawable(bg));
        }
        else{
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.texture_sun);
            BitmapShader shader = new BitmapShader(bm, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
            paint.setShader(shader);
            Bitmap bg = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bg);
            canvas.drawCircle(200, 140, 100, paint);


            sf.setBackgroundDrawable(new BitmapDrawable(bg));
        }





    }
}
