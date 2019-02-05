package NeuralNet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class NeuralNetDriver
{
	public static void main(String[] args)
	{
		//String file = new String(args[0]);
		//"resources/sample_input.txt"
		//file = "resources/" + file;
		NeuralNet net = new NeuralNet("resources/sample_input.txt");

		double time = net.getRunTime();
		time = time * 60;
		long startTime = System.currentTimeMillis();
		while(false||(System.currentTimeMillis()-startTime < (time * 1000)))
		{
			net.forward();
			net.error();
			net.adjust();
			net.adjWght();
			net.increment();
		}
		
		net.print();
		
		return;
	}
}

/*
public class NeuralNetDriver
{	
	public static void main(String[] args)
	{
		String fileName = args[0];
		NeuralNet net = new NeuralNet(fileName); //creates neural net
		
		/*
		for creating initial weights and setting inputs to expected outputs,
		I have that set up in the NeuralNet constructor. The constructor 
		contains the loops required for set up of structure.
		first 3 for loops are performed during construction of net object
		
		
		int numhl = net.getNumHL();

		float time = net.getRunTime();
		time = time * 60;

		long startTime = System.currentTimeMillis();
		while(false||(System.currentTimeMillis()-startTime < (time * 1000))
		{	
			/*
			for loop that sums over wights of prev layer
			4th for statement in psuedo code from textbook
			
			int numIn = net.getNumInputs();
			int numOut = net.getNumOutputs();
			int inMarker = 1;
			int outMarker = 1;
			
/*4		for(int x = 2; x <= (numhl + 2), x++)
			{	
				Arraylist<Node> currLayer = net.getLayer(x); //current layer or next layer in forward motion
				Arraylist<Node> prevLayer = net.getLayer(x-1); //previous layer

				if(x = 2) //input
				{
					for(int i = 1; i < currlayer; i++)
					{
					int sum = 0;
					for(int j = inMarker; j <= (inMarker + numIn); j++)
					{
						int wght = prevLayer[j].getWght();
						int val = prevLayer[j].getValue();
						sum = sum + (wght * val);
					}
					sum = sum + currLayer[i].getBias();
					currLayer[i].setG(sum);
					int a = 1/(1 - 2.7^(sum * -1));
					currLayer[i].setVal(a);
					}
					inMarker = inMarker + numIn;
				}
				
				else if(x = numhl + 1) //output
				{
					for(int i = 0; i < currlayer)
					{
					int sum = 0;
					for(int j = OutMarker; j <= (OutMarker + numOut); j++)
					{
						int wght = prevLayer[j].getWght();
						int val = prevLayer[j].getValue();
						sum = sum + (wght * val);
					}
					sum = sum + currLayer[i].getBias();
					int a = 1/(1 - 2.7^(sum * -1));
					currLayer[i].setVal(a);
					}
					outMarker = outMarker + numOut;
				}
				else
				{
					for(int i = 1; i <= currLayer; i++) //only hidden layers
					{
					int sum = 0;
					for(int j = 1; j < prevLayer; j++)
					{
						int wght = prevLayer[j].getWght();
						int val = prevLayer[j].getValue();
						sum = sum + (wght * val);
					}
						
					sum = sum + currLayer[i].getBias();  //???
					int a = 1/(1 - 2.718281828^(sum * -1));
					currLayer[i].setValue(a);
					}
				}
			}
			
			ArrayList<Node> outputs = net[numhl + 2];
			for(int i = 1; i <= outputs.size(); i ++)
			{
				outputs[i].setChange(net.getMax()); //function in Node for delta j
			}
			
			
			// create conditions for inputs and outputs
			for(int i = net.size() i >= 1; i--)
			{
				Arraylist<Node> currLayer = net.getLayer(i); //current layer or next layer in forward motion
				Arraylist<Node> prevLayer = net.getLayer(i-1); //previous layer
				
				for(int i = 1; i <= currLayer; i++) //only hidden layers
					{
					int sum = 0;
					for(int j = 1; j < prevLayer; j++)
					{
						int wght = prevLayer[j].getWght();
						int val = prevLayer[j].getValue();
						sum = sum + (wght * val);
					}
						
					sum = sum + currLayer[i].getBias();  //???
					int a = 1/(1 - 2.718281828^(sum * -1));
					currLayer[i].setValue(a);
					}
			}

		}
		/*
			input: loop over inputs
			cout << "inputs: " << input << endl;
			Measured Output: loop over outputs
			cout << "Measured Outputs: " << 
			Neural Net Output: loop over net outputs
			---
			
		*/


//old code, kept for pulling possible functions as program evolved

/*			
			int numIn = net.getNumInputs();
			int numOut = net.getNumOutputs();
			int inMarker = 1;
			int outMarker = 1;
			
			/*
				Because of my design, the following has to account for
				the following conditions:
				if prevLayer is Input
				if currLayer is Output
				the following if and else if statements account for these conditions
				so the network does not compute all test inputs for every output
			*/
/*			if(x = 2) //input
			{
				for(int i = 1; i < currlayer; i++)
				{
					int sum = 0;
					for(int j = inMarker; j <= (inMarker + numIn); j++)
					{
						int wght = prevLayer[j].getWght();
						int val = prevLayer[j].getValue();
						sum = sum + (wght * val);
					}
					sum = sum + currLayer[i].getBias();
					int a = 1/(1 - 2.7^(sum * -1));
					currLayer[i].setVal(a);
				}
				inMarker = inMarker + numIn;
			}
			else if(x = numhl + 1) //output
			{
				for(int i = 0; i < currlayer)
				{
					int sum = 0;
					for(int j = OutMarker; j <= (OutMarker + numOut); j++)
					{
						int wght = prevLayer[j].getWght();
						int val = prevLayer[j].getValue();
						sum = sum + (wght * val);
					}
					sum = sum + currLayer[i].getBias();
					int a = 1/(1 - 2.7^(sum * -1));
					currLayer[i].setVal(a);
				}
				outMarker = outMarker + numOut;
			} */