import java.awt.*;
import java.applet.*;
import java.net.*;
import java.awt.event.*;


public class CarGame extends Applet implements Runnable 
{
        Thread runner;
        Image Buffer;
        Graphics gBuffer;
    
      boolean startGame;
      boolean moveRight;
      boolean moveLeft;
      boolean endGame;
      
      int carW;
      int carH;
      int score=0;
      int yValue;
      int speed;
      
      long time;
      long startTime;
      
      Image myCar;
      Image enemyCar;
      Image explosion;
      int counter;
      Car c;
      ComputerCar cc;
       

        public void init()
        {
                Buffer=createImage(size().width,size().height);
                gBuffer=Buffer.getGraphics();
                URL codeBase = getCodeBase();
                  carW=100;
                  carH=202;
                    counter=0;
                        startGame=false;
                        moveRight=false;
                        moveLeft = false;
                        
              
                        myCar = getImage(codeBase, "carover2.png");
                        enemyCar = getImage(codeBase, "carover1.png");
                        explosion = getImage(codeBase, "explosion.png");
                        
                        c= new Car();
                        cc = new ComputerCar();
                       
                        c.speed=6;
                        
                        resize(700, 700);
                        
                        startTime = System.currentTimeMillis();
                        

        }
        
 public boolean keyDown(Event e, int key)//added
{
if(key == Event.RIGHT)
{
    moveRight=true;
   }

if(key == Event.LEFT)
{
    moveLeft=true;
   
}

if(key == Event.UP)
{
    startGame=true;
  
}    

return true;
}


 public boolean keyUp(Event e, int key)//added
{
if(key == Event.RIGHT)
{
 
    moveRight=false;   
   }

if(key == Event.LEFT)
{

    moveLeft = false;
   
}


            
return true;
}



        public void start()
        {
                if (runner == null)
                {
                        runner = new Thread (this);
                        runner.start();
                }
        }

        public void stop()
        {
                if (runner != null)
                {
                        runner.stop();
                        runner = null;
                }
        }

        public void run()
        {
                while(true)
                {
                        //Thread sleeps for 15 milliseconds here
                        try {runner.sleep(15);}
                        catch (Exception e) { }
                //move balls around
             

                        repaint();
                }//end of while loop
        }//end of run method

        public void update(Graphics g)
        {
                paint(g);
        }

        
        public void paint(Graphics g)
        {
                g.setColor(Color.black);
                g.fillRect(0,0,1500,1500);
              
                time=System.currentTimeMillis();
               
              
              if(startGame){
           
                 
                if(moveRight)      //if(moveRight==true)
                c.xValue+=c.speed;
                
                if(moveLeft)
                c.xValue-=c.speed;
                
                Border();
                g.setColor(Color.white);
                //g.drawLine(25,25,250,250);
         
                //draw a gray background and a grid of lines
               //size().width,size().height);
                //  
                
                
                cc.yValue+=c.speed;
                yValue+=c.speed;
                if(cc.yValue>700){
                 cc.setXValue();
                 cc.yValue= -100;
                 score+=5;
                 c.speed+=1;
                    if(c.speed>14){
                        
                    c.speed=14;
                 }
                }
                
                if(yValue>175){
                    yValue=0;
                }
                
                
               g.setColor(Color.yellow); 
               g.fillRect(233, yValue, 10, 40);//x,y,width, height
               g.fillRect(233, yValue+175, 10, 40);
               g.fillRect(233, yValue+350, 10, 40);
               g.fillRect(233, yValue+525, 10, 40);
               
               g.fillRect(466, yValue, 10, 40);//x,y,width, height
               g.fillRect(466, yValue+175, 10, 40);
               g.fillRect(466, yValue+350, 10, 40);
               g.fillRect(466, yValue+525, 10, 40);
               
               
                 int width = myCar.getWidth(this);
                 int height = myCar.getHeight(this);
                  g.setColor(Color.white);
                 // g.fillRect(240, counter, 10, 40);
                 
                    
                g.drawImage(myCar, c.xValue, 500, width, height, this);
                
                g.drawImage(enemyCar, cc.xValue, cc.yValue , width, height, this);
                
              
                
                if(didCarsCollide()==true){
                    g.drawImage(explosion, c.xValue+20, 550 , 90, 90, this);
                    cc.setXValue();
                    c.speed=0;
                    
                   cc.yValue=100;
                   
                   
                    startGame=false;
                     g.drawString("Your score is: " + score , 540, 60);
                     g.drawString("Your speed is: " + c.speed +"0 mph" , 540, 90);
                     g.drawString("Time: " +(time-startTime)/1000 + " seconds", 540, 120);
                   try {runner.sleep(2000);}
                        catch (Exception e) { } 
                   
                    score=0;
                    
                   
                    
                }
                
                 g.drawString("Your score is: " + score , 540, 60);
                 g.drawString("Your speed is: " + c.speed + "0 mph" , 540, 90);
                 g.drawString("Time: " +(time-startTime)/1000 +" seconds", 540, 120);
               
                }else{
                      Font font = new Font("Arial",Font.BOLD,20);
                      g.setFont(font);
                      g.setColor(Color.red);
                     g.drawString("Welcome to Casual Driver! Press the up key to start driving! " , 50, 60);
                    init();
                   
                }
                
               
          }
        
          public void Border(){
              if(c.xValue>699){
                  c.xValue=700;
                }
              if(c.xValue<0){
                  c.xValue=0;
                }
              
            }
            
           
               
            
            
          public boolean didCarsCollide(){
              int carY=500;
              if( ((c.xValue>cc.xValue && c.xValue<cc.xValue+carW) || (cc.xValue>c.xValue && cc.xValue<c.xValue+carW)) && carY<cc.yValue+carH){
              return true;
            }else{
              return false;
            }
            
        }
}








   

   
   