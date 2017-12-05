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
public class Square extends Shape
{
    //Declared Variables
    float side;
    Color color;
    String Name;
    
    //Constructor??
    public Square(Color BLUE, String mySquare, float f) 
    {
        super(BLUE,mySquare);
        this.color = BLUE;
        this.Name = mySquare;
        this.side = f;
    }

    @Override
    public float computeArea() 
    {
    return (float) Math.pow(side,2);    
    }

    @Override
    public float computePerimeter() 
    {
    return  4 * side;    
    }

    @Override
    public float computeVolume() {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }
    
}
