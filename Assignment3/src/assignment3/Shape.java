/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

//Imports
import java.awt.Color;
import java.lang.Class;
/**
 *
 * @author Travis Raghubans
 */
public abstract class Shape implements I_twoD, I_threeD 
{
    //Declared Variables 
    String Sname;
    Color color;
    float typename;
   
    public Shape(Color RED,String Name)
    {
       this.Sname = Name;
       this.color = RED;
    }
    
     public String getName()
    {
       if(Sname.equals("myCircle"))
       {
           return "Circle";
       }
       else if (Sname.equals("mySquare"))
       {
           return "Square";
       }
       else 
       {
           return "Cube";
       }
    } 
    
    public void print(float typename)
    {
        System.out.println("This is a "+getName());
        System.out.println("The name of this " +getName()+" is "+Sname);
        System.out.println("The color of this " +getName()+" is "+color);
        if(getName().equals("Circle"))
        {
            System.out.println("The radius measurements are " +typename);
        }
        else
        {
            System.out.println("The side measurements are " +typename);
        }
    }
}
