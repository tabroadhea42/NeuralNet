package NeuralNet;
import java.util.Random;

public class Node
{
   private double wght; //weight to next step
   private double value; //value to be multiplied by bias weight to get next (a value)
   private double bias; //bias wght, not used in input layer
   private double change; //delta value used for previous step
   private double y; //used only in output nodes for masured output
   private double sum;

   public Node() //constructor
   {
	  wght = util.Random.getRandomNumberGenerator().randomFloat(-1, 1);
	  value = 0;
	  bias = 1;
	  change = 1;
	  y = 0;
   }

   public void setWght(double w)
   {
      wght = w;
   } 

   public double getWght() 
   {
      return wght;
   } 

   public void setValue(double input) 
   {
      value = input;
   } 

   public double getValue() 
   {
      return value;
   }

   public void setBias(double input)
   {
	   bias = input;
   }
   
   public double getBias()
   {
	   return bias;
   }
   
    public void setChange(double input)
   {
	   change = input;
   }
   
   public double getChange()
   {
	   return change;
   }
   
    public void setY(double input)
   {
	   y = input;
   }
   
   public double getY()
   {
	   return y;
   }
   
   public void setSum(double s)
   {
	   sum = s;
   }
   
   public double getSum()
   {
	   return sum;
   }
} 