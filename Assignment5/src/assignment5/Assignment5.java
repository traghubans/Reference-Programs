/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.border.EtchedBorder;

class GlobalVariables {
    public ArrayList<Fish> mFish;
    public Assignment5 mFishTank;

    private GlobalVariables() {
        mFish = new ArrayList<Fish>();
        mFishTank = new Assignment5();
    }

    private static GlobalVariables instance;

    public static GlobalVariables getInstance() {
        if (instance == null){
            instance = new GlobalVariables();
        }
        return instance;
    }
}

class Fish implements Comparable<Fish>{
    
    int mX;
    int mY;
    int mId;
    Color mColor;
    
    public Fish(int id, int x, int y, Color color){
        
        mId = id;
        mX = x;
        mY = y;
        mColor = color;
    }
    
    public void paint(Graphics g){
        
        int x = this.mX;
        int y = this.mY;
        
        g.setColor(mColor);
        g.fillOval(x+1, y+1, 23, 23);

    }
    
    public void move()
    {
        int x = this.mX + (int)(Math.floor(Math.random() * 3) - 1) * 30;
        int y = this.mY + (int)(Math.floor(Math.random() * 3) - 1) * 30;
        for (Fish o : GlobalVariables.getInstance().mFish) {
            if (o.mX == x && o.mY == y) {
                return;
            }
        }
        this.mX = x;
        this.mY = y;     
       
        
    }

    @Override
    public int compareTo(Fish o) 
    {
       if((this.mId != o.mId) && (this.mX == o.mX) && (o.mY == this.mY)) 
          {
             return 1;
          }
      return 0;
    }
       
   
    }

class FishTick extends TimerTask{

    @Override
    public void run() {
     
        if (Assignment5.mSimulateStatus){
            
            for (int x=0;x<GlobalVariables.getInstance().mFish.size();x++){
                
                Fish fish = GlobalVariables.getInstance().mFish.get(x);
                fish.move();
                GlobalVariables.getInstance().mFish.set(x, fish);
            }
              
            GlobalVariables.getInstance().mFishTank.mDrawPanel.paint();
        }
    }
}

public class Assignment5 extends javax.swing.JFrame implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener{
    
    private final int mNumRows = 20;
    private final int mNumCols = 20;
    private final int mGridSz = 30;
    
    private int mSelectedFishIndex = -1;
    private boolean mDragged = false;
    
    private int mTopHeight;
           
    JToolBar mToolbar;
    JToggleButton mSimulationButton;
    DrawPanel mDrawPanel;
    
    private int mFishIndex = 0;
    
    static public boolean mSimulateStatus = false;
    
    public static void main(String[] args) {
 
        GlobalVariables global = GlobalVariables.getInstance();
        
        if (global == null){
            System.out.println("Cannot initialize, exiting ....");
            return;
        }
        
    }

    private JToggleButton addButton(String title){
        
        JToggleButton button = new JToggleButton(title);
        button.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent ev) {
               mSimulateStatus = !mSimulateStatus;
           }
        }); 
        
        this.mToolbar.add(button);
        
        return (button);
    }
    
    public Assignment5()
    {  
        JFrame guiFrame = new JFrame();
 
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("MY FISH TANK");
        
        // Create a toolbar and give it an etched border.
        this.mToolbar = new JToolBar();
        this.mToolbar.setBorder(new EtchedBorder());
        
        mSimulationButton = addButton("Simulate");
        this.mToolbar.add(mSimulationButton);
 
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
    
        this.mDrawPanel = new DrawPanel(mNumRows, mNumCols, mGridSz);
        
        this.mDrawPanel.setBackground(Color.cyan); 
        this.mDrawPanel.paint();
        
        guiFrame.add(mDrawPanel);
        guiFrame.add(this.mToolbar, BorderLayout.NORTH);
        
        // Add the Exit Action
        JButton button = new JButton("Quit");
        button.setToolTipText("Quit the program");
        button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
        });
        mToolbar.add(button);
      
        guiFrame.addMouseListener(this);
        guiFrame.addMouseMotionListener(this);
        
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
        
        mTopHeight = guiFrame.getInsets().top + mToolbar.getHeight();
        guiFrame.setSize(mNumRows * mGridSz, mNumCols * mGridSz + mTopHeight);
        
        Timer timer = new Timer("tick", true);
        timer.scheduleAtFixedRate(new FishTick(), Calendar.getInstance().get(Calendar.MILLISECOND), 500);
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        
        int pixel_x = e.getX();
        int pixel_y = e.getY();
        
        int grid_x = pixel_x / this.mGridSz;
        int grid_y = pixel_y / this.mGridSz;
        
        
        if (!this.mSimulateStatus) 
        {
            //Random Colors
            int red = (int) (0xff * Math.random());
            int green = (int) (0xff * Math.random());
            int blue = (int) (0xff * Math.random());
          
            //Position
            int mx = e.getX() - 8;
            int my = e.getY() - 5;
          
            int x = (mx - (mx % this.mGridSz));
            int y = (my - (my % this.mGridSz)) - 60;
          
            int test = 0;
            Fish fish = new Fish(this.mFishIndex++, x, y, new Color(red,green,blue));
            GlobalVariables.getInstance().mFishTank.mDrawPanel.paint();
            for (Fish o : GlobalVariables.getInstance().mFish) 
            {

                if (fish.compareTo(o) == 1) 
                {
                    test = 1;
                    break;
                }
            }
            if (test == 0)
            {
                if (x <= 570)
                {
                    if (y <= 570)
                    {
                        GlobalVariables.getInstance().mFish.add(fish);
                    }
                }
            }
          
        }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
          if (this.mDragged && this.mSelectedFishIndex != -1) {
            Fish f = GlobalVariables.getInstance().mFish.get(this.mSelectedFishIndex);
            f.mX = e.getX() - (e.getX() % 30);
            f.mY = e.getY() - (e.getY() % 30) - 60;
            GlobalVariables.getInstance().mFishTank.mDrawPanel.paint();
        }
        this.mSelectedFishIndex = -1;
        this.mDragged = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
         if (!this.mSimulateStatus) {
            this.mDragged = true;
          
            int x = (int)Math.floor((e.getX() - 8) / 30) * 30;
            int y = (int)Math.floor((e.getY() - 8) / 30) * 30 - 60;
          
            for (Fish o : GlobalVariables.getInstance().mFish) {
                if (o.mX == x && o.mY == y) {
                    this.mSelectedFishIndex = GlobalVariables.getInstance().mFish.indexOf(o);
                    break;
                }
            }
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }


class DrawPanel extends JPanel{

    int mRows;
    int mCols;
    int mGridSz;
    int maxGridSz;
    
    ArrayList<Fish> mFish;
    
    public DrawPanel(int numberOfRows, int numberOfCols, int gridSz){
        
        mGridSz = gridSz;
        mRows = numberOfRows;
        mCols = numberOfCols;
        maxGridSz = mGridSz * mRows;
    }
    
    private void paintBackground(Graphics g){
        
        for (int i = 1; i < mRows; i++) { 
            g.drawLine(i * mGridSz, 0, i * mGridSz, maxGridSz); 
        }
        
        for (int mAnimateStatus = 1; mAnimateStatus < mCols; mAnimateStatus++) { 
            g.drawLine(0, mAnimateStatus * mGridSz, maxGridSz, mAnimateStatus * mGridSz); 
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        paintBackground(g);
        
        for (Fish f:GlobalVariables.getInstance().mFish){  
            f.paint(g);
        }
        
    }

    public void paint(){ 
        repaint();
    }
}
}