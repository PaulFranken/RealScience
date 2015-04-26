package com.example.paulfranken.realscience;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by PaulFranken on 23/04/15.
 */
public class Star {



    public Star(double mass, int sCircumference, String name){
        this.sMass = mass;
        this.circumference = new BigDecimal(String.valueOf(sCircumference));
        this.name = name;
    }
    String name;
    double sMass;
    BigDecimal circumference;

    BigDecimal solarMass;
    BigDecimal fourThirds;
    BigDecimal actualMass;
    BigDecimal diameter;
    BigDecimal constant;
    double radius;
    BigDecimal massConstant;
    BigDecimal radiusSquared;
    double density;
    double roundedGravity;
    BigDecimal volume;
    BigDecimal densityDecimal;
    DecimalFormat df = new DecimalFormat("###.##");



    public double getSurfaceGravity(){
        fourThirds = new BigDecimal(4.0/3.0);
        solarMass = new BigDecimal("1989000000000000000000000000000");

        this.actualMass = (solarMass.multiply(new BigDecimal(String.valueOf(sMass))));

        this.diameter = circumference.divide(new BigDecimal(Math.PI), 5, RoundingMode.HALF_UP);

        constant = new BigDecimal(String.valueOf(6.67384 * Math.pow(10, -11))) ;
        radius = (diameter.divide(new BigDecimal(2))).doubleValue();
        massConstant = (constant.multiply(actualMass));
        radiusSquared = new BigDecimal(String.valueOf(Math.pow(radius, 2)));
        volume = fourThirds.multiply(new BigDecimal(Math.PI)).multiply(new BigDecimal(String.valueOf(Math.pow(radius, 3))));
        BigDecimal gravity = massConstant.divide(radiusSquared, 2, RoundingMode.HALF_UP).divide(new BigDecimal(10000000));
        this.density = (actualMass.divide(volume, 2, RoundingMode.HALF_UP).doubleValue());
        this.roundedGravity = (double)Math.round(gravity.doubleValue() * 100.0)/100.0;
        this.actualMass = actualMass.round(new MathContext(2, RoundingMode.HALF_EVEN));
        this.volume = volume.round(new MathContext(2, RoundingMode.HALF_EVEN));
        this.densityDecimal = new BigDecimal(density);
        this.densityDecimal = densityDecimal.divide(new BigDecimal("1000000000000"));
        this.densityDecimal = densityDecimal.round(new MathContext(2, RoundingMode.HALF_EVEN));
        this.diameter = diameter.round(new MathContext(2, RoundingMode.HALF_EVEN));
        return roundedGravity;
    }

}
