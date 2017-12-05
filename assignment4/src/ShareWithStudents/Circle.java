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
public class Circle extends Shape implements I_twoD{
    
    private int mRadius;

    public Circle(Color color, String name, int radius) {
        
        super(color, name);
        this.mRadius = radius;
        mX = -1;
        mY = -1;
    }

    @Override
    public float computeArea() {
        return (float) (Math.PI * this.mRadius * this.mRadius);
    }

    @Override
    public float computePerimeter() {
        return (float)(2.f * Math.PI * this.mRadius);
    }

    @Override
    public void print() {
        System.out.println("Circle ("+this.mName+") radius : "+this.mRadius+" and color : "+this.mColor.toString());
    }

    @Override
    public void paint(Graphics g) 
    {
        g.setColor(mColor);
        g.drawOval(mX-35, mY-40, mRadius, mRadius);
        g.fillOval(mX-35, mY-40, mRadius, mRadius);
           
    }
    
}
