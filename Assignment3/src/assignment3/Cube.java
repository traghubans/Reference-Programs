/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

//Imports
import java.awt.Color;
/**
 *
 * @author Travis Raghubans
 */
public class Cube extends Square
{
    float side;
    Color color;
    String Name;
 
    public Cube(Color RED, String Name, float f)
    {
        super(RED,Name,f);
        this.color = RED;
        this.Name = Name;
        this.side = f;
    }
    
    @Override
    public float computeVolume()
    {
      return (float) Math.pow(side , 3);
    }
}
