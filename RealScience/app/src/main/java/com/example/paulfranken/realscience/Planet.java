package com.example.paulfranken.realscience;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by PaulFranken on 22/04/15.
 */
public class Planet {



    public Planet(int circumference, double density, String name){
        this.diameter = circumference / Math.PI;
        this.circumference = circumference;
        this.density = new BigDecimal(density);
        this.name = name;

    }

    String name;
    double diameter;
    int circumference;
    BigDecimal density;
    BigDecimal fourThree;
    BigDecimal bgConstant;
    double radius;
    BigDecimal radiusCubed;
    BigDecimal pi;
    BigDecimal radiusCubedPi;
    BigDecimal volume;
    BigDecimal volumeMeters;
    BigDecimal densityMeters;
    BigDecimal mass;
    BigDecimal gravity;

    public BigDecimal getMass(){

        return this.mass;
    }

    public double getSurfaceGravity(){

        density = density.round(new MathContext(2, RoundingMode.HALF_EVEN));
        BigDecimal fourThree = new BigDecimal(4.0/3.0);
        BigDecimal bgConstant = new BigDecimal(String.valueOf(6.67384 * Math.pow(10, -11))) ;
        double radius = diameter/2;
        BigDecimal radiusCubed = new BigDecimal(String.valueOf(Math.pow(radius, 3)));
        BigDecimal pi = new BigDecimal(Math.PI);
        BigDecimal radiusCubedPi = radiusCubed.multiply(pi);
        BigDecimal volume = fourThree.multiply(radiusCubedPi);
        BigDecimal million = new BigDecimal(1000000);
        BigDecimal billion = new BigDecimal(1000000000);



        BigDecimal volumeMeters = volume.multiply(billion);
        BigDecimal densityMeters = density.multiply(million);
        BigDecimal mass = densityMeters.multiply(volumeMeters).divide(new BigDecimal(1000));
        this.mass = mass.round(new MathContext(2, RoundingMode.HALF_EVEN));

        BigDecimal part1 = bgConstant.multiply(mass);
        part1 = part1.round(new MathContext(2, RoundingMode.HALF_EVEN));

        BigDecimal part2 = new BigDecimal(Math.pow(radius, 2));
        part2 = part2.round(new MathContext(2, RoundingMode.HALF_EVEN));

        BigDecimal part12 = part1.divide(part2, 2, RoundingMode.HALF_EVEN);

        BigDecimal gravity = part12.divide(million, 2, RoundingMode.HALF_UP);
        this.gravity = gravity.round(new MathContext(2, RoundingMode.HALF_EVEN));
        this.volume = volume.round(new MathContext(2, RoundingMode.HALF_EVEN));
        this.diameter = Math.round(diameter);

        return gravity.doubleValue();
    }
}
