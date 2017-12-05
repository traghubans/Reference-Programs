/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.Color;

/**
 *
 * @author Travis Raghubans
 */
public class Circle extends Shape 
{

//Declared Variables 
    private float pi = (float) Math.PI;
    private float radius; 
    String Color;
    String Name;
    

public Circle(Color RED, String Name, float f)
    {
        super(RED,Name);
        this.Color = Color;
        this.Name = Name;
        this.radius = f;
    }
  

    @Override
    public float computeArea() 
    { 
         return (float) (pi * Math.pow(radius, 2.0) );
    }

    @Override 
    public float computePerimeter()
    {
        return pi * 2 * radius;
    }

    @Override
    public float computeVolume() {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
}
