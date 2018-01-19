//ComputerCar



public class ComputerCar
{
    int speed;
    int xValue;
    int yValue;

    
    /**
     * Constructor for objects of class 
     */
    public ComputerCar()
    {
        speed=0;
        
        setXValue();
        yValue=0;
       
       

    }
    
    public void setXValue(){
        int random = (int)(Math.random( ) *(650)) + 10;

        xValue = random;
    }
    
    
    
    
}
