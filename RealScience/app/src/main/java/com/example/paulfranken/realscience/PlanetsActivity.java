package com.example.paulfranken.realscience;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;


public class PlanetsActivity extends Activity implements View.OnClickListener, ToolTipView.OnToolTipViewClickedListener{


    private ToolTipView diameterToolTip;
    private ToolTipView circumferenceToolTip;
    private ToolTipView massToolTip;
    private ToolTipView densityToolTip;
    Animation in = new AlphaAnimation(0.0f, 1.0f);
    Animation in2 = new AlphaAnimation(0.0f, 1.0f);
    Animation in3 = new AlphaAnimation(0.0f, 1.0f);
    Animation in4 = new AlphaAnimation(0.0f, 1.0f);
    Animation in5 = new AlphaAnimation(0.0f, 1.0f);
    Animation in6 = new AlphaAnimation(0.0f, 1.0f);
    Animation in7 = new AlphaAnimation(0.0f, 1.0f);

    private Canvas canvas;
    Paint line = new Paint();
    Paint stroke = new Paint();

    private ToolTipRelativeLayout mToolTipFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C6D729")));
        bar.setDisplayHomeAsUpEnabled(true);

        mToolTipFrameLayout = (ToolTipRelativeLayout) findViewById(R.id.activity_main_tooltipRelativeLayout);
        findViewById(R.id.diameter).setOnClickListener(this);
        findViewById(R.id.circumference).setOnClickListener(this);
        findViewById(R.id.mass).setOnClickListener(this);
        findViewById(R.id.density).setOnClickListener(this);


        findViewById(R.id.planetsIntro).startAnimation(in);

        in.setDuration(3000);
        in2.setDuration(1500);
        in3.setDuration(2500);
        in4.setDuration(1500);
        in5.setDuration(1500);
        in6.setDuration(1500);
        in7.setDuration(2500);
        //animation
        in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.diameter).setAlpha(1f);
                findViewById(R.id.circumference).setAlpha(1f);
                findViewById(R.id.mass).setAlpha(1f);
                findViewById(R.id.density).setAlpha(1f);
                findViewById(R.id.diameter).startAnimation(in4);
                findViewById(R.id.circumference).startAnimation(in2);
                findViewById(R.id.mass).startAnimation(in2);
                findViewById(R.id.density).startAnimation(in2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        in4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.surfaceView).setAlpha(1f);
                findViewById(R.id.textView2).setAlpha(1f);


                findViewById(R.id.surfaceView).startAnimation(in3);
                findViewById(R.id.textView2).startAnimation(in3);




            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        in3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                canvas.drawLine(24, 100, 186, 100, line);
                findViewById(R.id.textView3).setAlpha(1f);
                findViewById(R.id.textView3).startAnimation(in5);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        in5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.textView4).setAlpha(1f);
                findViewById(R.id.textView4).startAnimation(in6);


                canvas.drawCircle(105, 100, 80, stroke);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        in6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.textView5).setAlpha(1f);
                findViewById(R.id.textView6).setAlpha(1f);
                findViewById(R.id.textView5).startAnimation(in7);
                findViewById(R.id.textView6).startAnimation(in7);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        drawEarth();
    }




    private void addDiameterToolTip(){
        String diameterString = "diameter = C<sup>circumference</sup> / π";

        ToolTip toolTip = new ToolTip()
                .withText(Html.fromHtml(diameterString))
                .withShadow();
        diameterToolTip = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.diameter));
        diameterToolTip.setOnToolTipViewClickedListener(this);

    }

    private void addCircumferenceToolTip(){
        String circumferenceString = "circumference = π * D<sup>diameter</sup>";

        ToolTip toolTip = new ToolTip()
                .withText(Html.fromHtml(circumferenceString))
                .withShadow();
        circumferenceToolTip = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.circumference));
        circumferenceToolTip.setOnToolTipViewClickedListener(this);

    }

    private void addMassToolTip(){
        String massString = "mass = ρ<sup>density</sup> * V<sup>volume</sup>";

        ToolTip toolTip = new ToolTip()
                .withText(Html.fromHtml(massString))
                .withShadow();
        massToolTip = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.mass));
        massToolTip.setOnToolTipViewClickedListener(this);

    }

    private void addDensityToolTip(){
        String densityString = "ρ<sup>density</sup> = m<sup>mass</sup> / V<sup>volume</sup>";

        ToolTip toolTip = new ToolTip()
                .withText(Html.fromHtml(densityString))
                .withShadow();
        densityToolTip = mToolTipFrameLayout.showToolTipForView(toolTip, findViewById(R.id.density));
        densityToolTip.setOnToolTipViewClickedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planets, menu);
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



    public void removeTooltips(){
        try{
            diameterToolTip.remove();

        }catch(Exception e){

        }
        try{
            circumferenceToolTip.remove();

        }catch(Exception e){

        }
        try{
            massToolTip.remove();

        }catch(Exception e){

        }
        try{
            densityToolTip.remove();

        }catch(Exception e){

        }


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.diameter){
            if(diameterToolTip == null){
                removeTooltips();
                addDiameterToolTip();
            }
            else{
                diameterToolTip.remove();
                diameterToolTip = null;
            }
        }
        if(id == R.id.circumference){
            if(circumferenceToolTip == null){
                removeTooltips();
                addCircumferenceToolTip();
            }
            else{
                circumferenceToolTip.remove();
                circumferenceToolTip = null;
            }
        }
        if(id == R.id.mass){
            if(massToolTip == null){
                removeTooltips();
                addMassToolTip();
            }
            else{
                massToolTip.remove();
                massToolTip = null;
            }
        }
        if(id == R.id.density){
            if(densityToolTip == null){
                removeTooltips();
                addDensityToolTip();
            }
            else{
                densityToolTip.remove();
                densityToolTip = null;
            }
        }
    }

    @Override
    public void onToolTipViewClicked(ToolTipView toolTipView) {
        if(diameterToolTip == toolTipView){
            diameterToolTip = null;
        }
    }

    private void drawEarth(){
        SurfaceView sf = (SurfaceView) findViewById(R.id.surfaceView);
        sf.setZOrderOnTop(true);
        SurfaceHolder sfTrack = sf.getHolder();

        sfTrack.setFormat(PixelFormat.TRANSLUCENT);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.earth_mini);
        BitmapShader shader = new BitmapShader(bm, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);

        Paint paint = new Paint();
        Paint text = new Paint();


        text.setColor(Color.parseColor("#f6ff00"));
        line.setColor(Color.parseColor("#f6ff00"));
        stroke.setStyle(Paint.Style.STROKE);
        stroke.setColor(Color.parseColor("#ffa200"));
        stroke.setStrokeWidth(3);
        paint.setShader(shader);
        line.setStrokeWidth(3);
        Bitmap bg = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bg);

        canvas.drawCircle(105, 100, 80, paint);






        sf.setBackgroundDrawable(new BitmapDrawable(bg));

    }
}
