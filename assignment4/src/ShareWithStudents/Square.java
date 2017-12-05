/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShareWithStudents;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Travis Raghubans
 */
public class Square extends Shape implements I_twoD{
    
    protected int mSide;

    Square(Color color, String name, int side){
        
        super (color, name);
        this.mSide = side;
    }
    
    @Override
    public float computeArea() {
        
        return (this.mSide * this.mSide);
    }

    @Override
    public float computePerimeter() {
        
        return (4.f * this.mSide);
    }
    
    public void print(){
        System.out.println("Square ("+this.mName+") side : "+this.mSide+" and color : "+this.mColor.toString());
    }

    @Override
    public void paint(Graphics g) 
    {
        g.setColor(mColor);
        g.drawRect(mX-105, mY-105, mSide, mSide);
        g.fillRect(mX-105, mY-105, mSide, mSide);
    }
}
