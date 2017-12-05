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
public class Cube extends Square implements I_threeD{

    public Cube(Color color, String name, int side) {
        super(color, name, side);
    }

    public float computeVolume() {
        
        return (this.mSide * this.mSide * this.mSide);
    }
    
    public void print(){
        System.out.println("Cube ("+this.mName+") side : "+this.mSide+" and color : "+this.mColor.toString());
    }
    
    @Override
    public void paint(Graphics g) 
    {
        g.setColor(mColor);
        g.drawRect(mX-(120-5), mY-(130+20), mSide, mSide); 
        g.drawLine(mX-(120-5),mY-(130+20),mX-65,mY-(100+90)); 
        g.drawLine(mX-15, mY-(130+20),mX+35, mY-190);
        g.drawLine(mX-65, mY-(100+90),mX+35, mY-(100+90));
        g.drawLine(mX-15, mY-50, mX+35, mY-90);
        g.drawLine(mX+35, mY-(100-10), mX+35, mY-(100+90));
        g.fillRect(mX-(120-5), mY-(130+20), mSide, mSide);
        
        
        final int step = 20; 
    }
}
