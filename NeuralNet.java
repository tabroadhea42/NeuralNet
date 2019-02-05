package NeuralNet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.lang.String;

//neural net structure, use array list to track nodes in same layer for summations
//used to "connect" layers w/ out actually connecting layers
public class NeuralNet
{
	//private variables represent nueral net structure file example given and Array of layer lists in program
	private ArrayList<ArrayList<Node>> net; //array of pointers to hidden layer lists
	private double maxVar;
	private double lRate;
	private double runTime;

	private int increment;
	private int numInputs;
	private int numOutputs;
	private int numHiddenLayers;
	
	// private function used to create hidden layers
	private void createHiddenLayer(int num, int pos)
	{
		ArrayList<Node> hl = new ArrayList<Node>();
		for (int j = 1; j <= num; j++)
		{
			Node n = new Node();
			hl.add(n);
		}
		net.add(pos, hl);
		
		return;
	}
	
	//creates neural net structure
	//every layer is treated as a seperate array list, allows nodes to be simpiler
	public NeuralNet(String properties)
	{
		util.ReadTextFile rf = new util.ReadTextFile(properties);
		String data = rf.readLine();
		numHiddenLayers = Integer.parseInt(rf.readLine());
		
		util.ReadTextFile rif = new util.ReadTextFile(data);
		double maxVar = Double.parseDouble(rif.readLine());
		
		char[] in_v_out;
		in_v_out = rif.readLine().toCharArray();
		numInputs = in_v_out[0] - '0';
		numOutputs = in_v_out[2] - '0';
		
		net = new ArrayList<ArrayList<Node>>(numHiddenLayers + 2);
		ArrayList<Node> in = new ArrayList<Node>();
		ArrayList<Node> out = new ArrayList<Node>();
		
		int counter = 0;
		int incr = 0;
		int outcr = 0;
		String str = rif.readLine();
		while(!rif.EOF())
		{
			String[] line = str.split(",",-1);
			System.out.println(str);
			for(int i = 0; i < numInputs; i++)
			{
				double val = Double.parseDouble(line[i]);
				Node n = new Node();
				n.setValue(val);
				in.add(incr, n);
				incr++;
			}
			for(int j = numInputs; j < numInputs + numOutputs; j++)
			{
				double val = Double.parseDouble(line[j]);
				Node n = new Node();
				n.setValue(val);
				out.add(outcr, n);
				outcr++;
			}
			str = rif.readLine();
		}
		net.add(0, in);
		for(int i = 1; i < numHiddenLayers + 1; i++)
		{
			net.add(i, null);
			
		}
		net.add((numHiddenLayers + 1), out);
		
		// create hidden layers
		for (int i = 1; i <= numHiddenLayers; i++)
		{
			int numNodes = Integer.parseInt(rf.readLine());
			this.createHiddenLayer(numNodes, i);
		}
		
		lRate = Double.parseDouble(rf.readLine());
		runTime = Double.parseDouble(rf.readLine());
		increment = 0;
		
		rf.close();
		rif.close();
		
		return;
	}
	
	public double getRunTime()
	{
		return runTime;
	}
	
	public void increment()
	{
		int check = net.get(1).size();
		increment++;
		if(increment > check)
		{
			increment = 0;
		}
		return;
	}
	
	public void forward()
	{
		int check = 0;
		while(check !> net.get(0).size())
		{
			ArrayList<ArrayList<Node>> run = net;
			
			run.set(0, inputs);
			run.set(run.size(), outputs);
		}
		
		/*
		for(int z = 0; z < net.get(0).size(); z++)
		{
			ArrayList<Node> inputs = new ArrayList<Node>();
			for(int y = 0; y < numInputs; y++)
			{
				inputs.add(y, net.get(z));
				z++;
			}
			
			for(int x = 1; x <= net.size(); x++)
			{
				ArrayList<Node> output = new ArrayList<Node>();
				if(x == net.size())
				{
					for(int d = 0; d < numOutputs; d++)
					{
						
					}
				}
				else
				{
					ArrayList<Node> curr = net.get(x);
				}
				
				net.get(net.size()-1);
				ArrayList<Node> curr = net.get(x);
				if(x = 1)
				{
					ArrayList<Node> prev = inputs;
				}
				else
				{
					ArrayList<Node> prev = net.get(x - 1);
				}
				
				for(int i = 0; i < curr.size(); i++)
				{
					for(int j = 0; j < prev.size(); j++)
					{
						
					}
				}
			}
		}
*/		
		
		/*
		int inputStart = numInputs * increment;
		int OutputStart = numOutputs * increment;
		
		int runs = 0;
		for(int x = 1; x <= (numHiddenLayers + 1); x++)
		{
			ArrayList<Node> currLayer = net.get(x);
			ArrayList<Node> prevLayer = net.get(x - 1);
			
			if(x==2)
			{
				runs = numOutputs;
			}
			else
			{
				runs = currLayer.size();
			}
			
			for(int q = 0; q <= runs; q++);
			{
				System.out.println("kill me");
				System.out.println(x);
				
				if(numHiddenLayers == 0)
				{
					for(int j = OutputStart; j < OutputStart + numOutputs; j++)
					{
						double sum = 0;
						for(int i = inputStart; i < inputStart + numInputs; i++)
						{
							double wght = prevLayer.get(i).getWght();
							double val = prevLayer.get(i).getValue();
							sum = sum + (wght * val);
						}
						currLayer.get(j).setSum(sum);
						sum = sum + currLayer.get(j).getBias();
						double a = 1/(1 - (Math.pow(2.7, (sum * -1))));
						currLayer.get(j).setValue(a); //g(in) = a
					}
				}
				else
				{
						if(x == 2)
						{
							for(int i = 0; i < net.get(x).size(); i++)
							{
								double sum = 0;
								for(int j = inputStart; j < inputStart + numInputs; j++)
								{
									double wght = prevLayer.get(j).getWght();
									double val = prevLayer.get(j).getValue();
									sum = sum + (wght * val);
								}
								sum = sum + currLayer.get(i).getBias();
								double a = 1/(1 - (Math.pow(2.7, (sum * -1))));
								currLayer.get(i).setValue(a); //g(in) = a
							}
						}
						else if(x == numHiddenLayers + 1)
						{
							for(int j = OutputStart; j < OutputStart + numOutputs; j++)
							{
								double sum = 0;
								for(int i = 0; i < net.get(x - 1).size(); i++)
								{
									double wght = prevLayer.get(i).getWght();
									double val = prevLayer.get(i).getValue();
									sum = sum + (wght * val);
								}
								sum = sum + currLayer.get(j).getBias();
								double a = 1/(1 - (Math.pow(2.7, (sum * -1))));
								currLayer.get(j).setValue(a); //g(in) = a
							}
						}
						else
						{
							for(int i = 0; i < net.get(x).size(); i++)
							{
								double sum = 0;
								for(int j = 0; j < net.get(x - 1).size(); j++)
								{
									double wght = prevLayer.get(j).getWght();
									double val = prevLayer.get(j).getValue();
									sum = sum + (wght * val);
								}
								sum = sum + currLayer.get(i).getBias();
								double a = 1/(1 - (Math.pow(2.7, (sum * -1))));
								currLayer.get(i).setValue(a); //g(in) = a
							}
							System.out.println("kill me");
						}
					
				}
			}
		}
		
		return;
		*/
	}
	
	public void error()
	{
		ArrayList<Node> outputs = net.get(numHiddenLayers + 2);
		int OutputStart = 1 + (numOutputs * increment);
		for(int j = OutputStart; j < OutputStart + numOutputs; j++)
		{
			double g = 1/(1 - (Math.pow(2.7, outputs.get(j).getSum() * -1)));
			outputs.get(j).setChange((g * (1 - g)) * ((outputs.get(j).getY()/maxVar) - outputs.get(j).getValue()));
		}
		return;
	}
	
	public void adjust()
	{
		int inputStart = 1 + (numInputs * increment);
		int OutputStart = 1 + (numOutputs * increment);
		
		for(int x = numHiddenLayers + 2; x >= 2; x--)
		{	
			ArrayList<Node> curr = net.get(x);
			ArrayList<Node> prev = net.get(x - 1);
			
			double sum = 0;
			
			if(numHiddenLayers == 0)
			{
				for(int i = inputStart; i < inputStart + numInputs; i++)
				{
					for(int j = OutputStart; j < OutputStart + numOutputs; j++)
					{
						sum = curr.get(j).getChange() * prev.get(i).getWght();
					}
					double g = 1/1 - prev.get(i).getSum();
					prev.get(i).setChange((g-(1 - g)) * sum);
				}
			}
			else
			{
				if(x == 2)
				{
					for(int i = inputStart; i < inputStart + numInputs; i++)
					{
						for(int j = 1; j < curr.size(); j++)
						{
							sum = curr.get(j).getChange() * prev.get(i).getWght();
						}
					double g = 1/1 - prev.get(i).getSum();
					prev.get(i).setChange((g-(1 - g)) * sum);						
					}
				}
				else if (x == numHiddenLayers + 2)
				{
					for(int i = 1; i < prev.size(); i++)
					{
						for(int j = OutputStart; j < OutputStart + numOutputs; j++)
						{
							sum = curr.get(j).getChange() * prev.get(i).getWght();
						}
					double g = 1/1 - prev.get(i).getSum();
					prev.get(i).setChange((g-(1 - g)) * sum);
					}
				}
				else
				{
					for(int i = 1; i < prev.size(); i++)
					{
						for(int j = 1; j < curr.size(); j++)
						{
							sum = curr.get(j).getChange() * prev.get(i).getWght();
						}
					double g = 1/1 - prev.get(i).getSum();
					prev.get(i).setChange((g-(1 - g)) * sum);						
					}
				}
			}
		}
		return;
	}
	
	public void adjWght()
	{
		int inputStart = 1 + (numInputs * increment);
		int OutputStart = 1 + (numOutputs * increment);
		
		double delta = 0;
		ArrayList<Node> curr = net.get(numHiddenLayers + 2);
		
		for(int j = OutputStart; j < OutputStart + numOutputs; j++)
		{
			delta = delta + curr.get(j).getChange();
			curr.get(j).setBias(curr.get(j).getBias() + (lRate * delta));
		}
		
		for(int x = net.size(); x >= 2; x--)
		{
			curr = net.get(x);
			ArrayList<Node> prev = net.get(x - 1);
			
			if(x == 2)
			{
				for(int i = inputStart; i < inputStart + numInputs; i++)
				{
					prev.get(i).setWght(prev.get(i).getWght() + (lRate * prev.get(i).getValue() * delta));
					prev.get(i).setBias(prev.get(i).getBias() + (lRate * delta));
				}
			}
			else
			{
				for(int i = 1; i < prev.size(); i++)
				{
					prev.get(i).setWght(prev.get(i).getWght() + (lRate * prev.get(i).getValue() * delta));
					prev.get(i).setBias(prev.get(i).getBias() + (lRate * delta));
				}
				
				delta = 0;
				for(int i = 1; i < prev.size(); i++)
				{
					delta = delta + prev.get(i).getChange();
				}
			}
		}
	}
	
	public void print()
	{
		util.WriteTextFile wf = new util.WriteTextFile("NeuralNetOutput.txt");
		boolean done = false;
		int counter = net.get(0).size();
		int inputStart = 0;
		int OutputStart = 0;
		while(done == false)
		{
			ArrayList<Node> curr = net.get(0);
			for(int i = inputStart; i < inputStart + numInputs; i++)
			{
				wf.writeLine("Input: " + Double.toString(curr.get(i).getValue()));
			}
			inputStart = inputStart + numInputs;
			
			curr = net.get(net.size() - 1);
			for(int j = OutputStart; j < OutputStart + numOutputs; j++)
			{
				wf.writeLine("Measured Output: " + Double.toString(curr.get(j).getY()));
				wf.writeLine("Neural Net Output: " + Double.toString(curr.get(j).getValue()));
			}
			OutputStart = OutputStart + numOutputs;
			
			if(inputStart == counter)
			{
				done = true;
			}
		}
		
		wf.close();
		
		return;
	}
	
}

/*
	private ArrayList<Node> in;
	private ArrayList<Node> out;
	private int numRuns;

	public void setInOut()
	{
		ArrayList<Node> currIn = new ArrayList<Node>;
		ArrayList<Node> currOut = new ArrayList<Node>;
		
		int start = (numRuns * numInputs) + 1
		if(start >= in.size())
		{
			numRuns = 0;
			start = (numRuns * numInputs) + 1;
		}
		int j = 1;
		
		for(int i = (start); i < (start + numIn); i++)
		{
			currIn[j].add(in.[i]);
			j++;
		}
		
		net.set(1, currIn);
		j = 1;
		start = (numRuns * numOutputs) + 1
		for(int = (start); i < (start + numOut); i++)
		{
			
		}
		
		
		start = 
		
		numRuns++;
	}
	
	public double getMax()
	{
		return maxVar;
	}
	
	public double getRunTime()
	{
		return runTime;
	}
	
	public double getlRate()
	{
		return lRate;
	}
	
	public int getNumInputs()
	{
		return numInputs;
	}
	
	public int getNumOutputs() 
	{
		return numOutputs;
	}
	
	public int getNumHL() //number of hidden layers
	{
		return numHiddenLayers;
	}
	
	public ArrayList<Node> getLayer(int num)
	{
		return net.get(num);
	}
	
			/*
		while(!rif.EOF())
		{	
			double val = 0;
			String line = rif.readLine();
			for(int i = 0; i < numInputs; i++)
			{
				String str = line.substring(0, line.indexOf("'") + 1);
				System.out.println(str);
				String remainder = line.substring(line.indexOf("'") + 1, line.length());
				System.out.println("HELLO");
				val = Double.parseDouble(str);
				System.out.println("HELLO");
		Node n = new Node();
				n.setValue(val);
				in.add(i, n);		
				line = remainder;
				System.out.println("HELLO");
			}

			System.out.println("HELLO");
			for(int j = numOutputs; j > 0; j--)
			{
				if(line.indexOf("'") >= 0)
				{
					String str = line.substring(0, line.indexOf("'") + 1);
					String remainder = line.substring(line.indexOf("'") + 1, line.length());
					val = Double.parseDouble(str);
				}
				else
				{
					val = Double.parseDouble(line);
				}
				Node n = new Node();
				n.setY(val);
				int i = 0;
				out.add(i, n);
				i++;
			}
		}
		
		for(int i = 0; i <= line.length; i++)
			{
				String n = new String();
				if(line[i] == )
				{
				n = n + line[i];
				incr++;
				}
				else
				{
					String w = new String(n);
					double val = Double.parseDouble(w);
					System.out.println("kill me");
					if(counter < numInputs)
					{
						Node x = new Node();
						x.setValue(val);
						in.add(x);
					}
					else
					{
						Node x = new Node();
						x.setValue(val);
						in.add(x);
					}
					incr = 0;
				}
			}
			
			/*
		int inputStart = numInputs * increment;
		int OutputStart = numOutputs * increment;
		
		int runs = 0;
		for(int x = 1; x <= (numHiddenLayers + 1); x++)
		{
			ArrayList<Node> currLayer = net.get(x);
			ArrayList<Node> prevLayer = net.get(x - 1);
			
			if(x==2)
			{
				runs = numOutputs;
			}
			else
			{
				runs = currLayer.size();
			}
			
			for(int q = 0; q <= runs; q++);
			{
				System.out.println("kill me");
				System.out.println(x);
				
				if(numHiddenLayers == 0)
				{
					for(int j = OutputStart; j < OutputStart + numOutputs; j++)
					{
						double sum = 0;
						for(int i = inputStart; i < inputStart + numInputs; i++)
						{
							double wght = prevLayer.get(i).getWght();
							double val = prevLayer.get(i).getValue();
							sum = sum + (wght * val);
						}
						currLayer.get(j).setSum(sum);
						sum = sum + currLayer.get(j).getBias();
						double a = 1/(1 - (Math.pow(2.7, (sum * -1))));
						currLayer.get(j).setValue(a); //g(in) = a
					}
				}
				else
				{
						if(x == 2)
						{
							for(int i = 0; i < net.get(x).size(); i++)
							{
								double sum = 0;
								for(int j = inputStart; j < inputStart + numInputs; j++)
								{
									double wght = prevLayer.get(j).getWght();
									double val = prevLayer.get(j).getValue();
									sum = sum + (wght * val);
								}
								sum = sum + currLayer.get(i).getBias();
								double a = 1/(1 - (Math.pow(2.7, (sum * -1))));
								currLayer.get(i).setValue(a); //g(in) = a
							}
						}
						else if(x == numHiddenLayers + 1)
						{
							for(int j = OutputStart; j < OutputStart + numOutputs; j++)
							{
								double sum = 0;
								for(int i = 0; i < net.get(x - 1).size(); i++)
								{
									double wght = prevLayer.get(i).getWght();
									double val = prevLayer.get(i).getValue();
									sum = sum + (wght * val);
								}
								sum = sum + currLayer.get(j).getBias();
								double a = 1/(1 - (Math.pow(2.7, (sum * -1))));
								currLayer.get(j).setValue(a); //g(in) = a
							}
						}
						else
						{
							for(int i = 0; i < net.get(x).size(); i++)
							{
								double sum = 0;
								for(int j = 0; j < net.get(x - 1).size(); j++)
								{
									double wght = prevLayer.get(j).getWght();
									double val = prevLayer.get(j).getValue();
									sum = sum + (wght * val);
								}
								sum = sum + currLayer.get(i).getBias();
								double a = 1/(1 - (Math.pow(2.7, (sum * -1))));
								currLayer.get(i).setValue(a); //g(in) = a
							}
							System.out.println("kill me");
						}
					
				}
			}
		}
		/*
		for(int z = 0; z < net.get(0).size(); z++)
		{
			ArrayList<Node> inputs = new ArrayList<Node>();
			for(int y = 0; y < numInputs; y++)
			{
				inputs.add(y, net.get(z));
				z++;
			}
			
			for(int x = 1; x <= net.size(); x++)
			{
				ArrayList<Node> output = new ArrayList<Node>();
				if(x == net.size())
				{
					for(int d = 0; d < numOutputs; d++)
					{
						
					}
				}
				else
				{
					ArrayList<Node> curr = net.get(x);
				}
				
				net.get(net.size()-1);
				ArrayList<Node> curr = net.get(x);
				if(x = 1)
				{
					ArrayList<Node> prev = inputs;
				}
				else
				{
					ArrayList<Node> prev = net.get(x - 1);
				}
				
				for(int i = 0; i < curr.size(); i++)
				{
					for(int j = 0; j < prev.size(); j++)
					{
						
					}
				}
			}
		}
		
		return;
		*/