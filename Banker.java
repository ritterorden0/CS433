import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Banker 
{

    public static final int NUMBER_OF_CUSTOMERS = 5; //n customers
    public static final int NUMBER_OF_RESOURCES = 4; //m resources
    
    //the available amount of each resource
    public int[] available = new int[NUMBER_OF_RESOURCES]; //int available[NUMBER_OF_RESOURCES];

	///the maximum demand of each customer 
    public int[][] maximum = new int[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES]; //int maximum[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
   
	//the amount currently allocated to each customer
    public int[][] allocation = new int[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES]; //int allocation[NUMBER OF CUSTOMERS][NUMBER OF RESOURCES];
   
	//the remaining need of each customer
    public int[][] need = new int[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES]; //int need[NUMBER OF CUSTOMERS][NUMBER OF RESOURCES];
    
    public (int a[][], int b[][])  
    {
		this.maximum = a;
		this.allocation = b;
    }

    public Banker() 
    {
		;
    }
   
   //request_resources() function should return 0 if successful and −1 if unsuccessful
    public int request_resources(int customer_num, int[] request) //int request_resources(int customer_num, int request[]);

    {
	
		return 0;
    }
    
    public void release_resources(int customer_num, int[] release) //void release_resources(int customer_num, int release[]);

    {
		;

    }


    //------------FIND NEED----------
    public void find_need(int max[][], int allocation[][])
    {
	int[][] temp = new int[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
	
	for(int i=0; i < NUMBER_OF_CUSTOMERS; i++)
	{

	    for(int j=0; j < NUMBER_OF_RESOURCES; j++)
	    { 
			
		temp[i][j] = max[i][j] - allocation[i][j]; 
		   
	    }	
	}
	need = temp; 
    }

    //--------FIND AVAIlABLE------------
    public void find_Available()
    {
	for(int i=0; i<NUMBER_OF_RESOURCES; i++)
	{
	    for(int j=0; j<NUMBER_OF_CUSTOMERS; j++)
	    {
		available[j] -= allocation[j][i]; 	
	    }	
	}	
    }
    
    //---------SAFETY ALGORITHM----------------
    // banker will grant a request if it satisfies the safety algorithm 
    public boolean safety_Algorithm()
    {
	  int[] Work = new int[NUMBER_OF_RESOURCES];
	  int[] Finish = new int[NUMBER_OF_CUSTOMERS];
	  
	  int safetyCount=0;
	  int smallerCount=0;
	  
	  boolean finalSafetyCheck=true;
	  while(safetyCount != NUMBER_OF_CUSTOMERS)
	  {
	    for(int i = 0; i<NUMBER_OF_CUSTOMERS; i++)
	    {
		if(Work[0] <= need[i][0] && Finish[i] == 0)
		{		
			for(int j = 0; j < NUMBER_OF_RESOURCES; j++)
			{
				if(Work[j] <= need[i][j])
				{
					smallerCount++;
				}
				else if(smallerCount == NUMBER_OF_RESOURCES)
				{
					for(int k=0; k < NUMBER_OF_RESOURCES; k++)
					{
						Work[k] += allocation[i][k];
					}
					smallerCount=0;
				}
				else
				smallerCount=0;
			}
			Finish[i] = 1;
			safetyCount = 0;		
		}
		else		
		{
			safetyCount++;
		}	
	}
    }
	  
	  for(int i = 0; i < NUMBER_OF_RESOURCES; i++)
	      {
		  if(Finish[i] == 0)
		  {
			finalSafetyCheck = false;
		  }
		  else
		  {	
			;
		  }
	      }
	  
	  return finalSafetyCheck; 
    }

    
    //---------FILL AVAILABLE--------
    public void fill_available() 
    {
	Scanner read = new Scanner(System.in);
	
	for(int i = 0; i < NUMBER_OF_RESOURCES; i++) 
	{
	    System.out.println("Enter data for available: ");
	    available[i] = read.nextInt();
	}

    }

    //----------ACCEPTED-----------------
    public boolean resource_Request_Accepted(int index, int[] request)
    {
	boolean needSuccess=false;
	boolean availSuccess=false;
	boolean finalSuccess;
	
	int count = 0;
	int custNumber = 0;

	for(int j = 0; j < NUMBER_OF_RESOURCES; j++)
	    {
		if(request[j] <= need[index][j])
		    {
			count++;
		    }
		else if(count == NUMBER_OF_RESOURCES)
		    {
			needSuccess = true;
			custNumber = index;
		    }
		else
		    ;
	    }
	
	
	for(int i = 0; i < NUMBER_OF_RESOURCES; i++)
	    {
		if(request[i] <= available[i])
		    {
			count++;  
		    } 
		else if(count == NUMBER_OF_RESOURCES)
		    {
			availSuccess = true; 
		    }
		else
		    ;
	    }
	
	if(needSuccess == true && availSuccess == true)
	    {
		for(int i = 0; i < NUMBER_OF_RESOURCES; i++)
		    {
			available[i] = available[i] - request[i];
			allocation[custNumber][i] = allocation[custNumber][i] + request[i];
			need[custNumber][i] = need[custNumber][i] - request[i];
		    }
		return true;
	    }
	else
	    ;
	
	return false;
    }


    //----------RELEASE RESOURCES -------------
    public void resource_release(int index, int[] request) 
    {
	
	for(int i = 0; i < NUMBER_OF_RESOURCES; i++) 
	{
	    available[i] = available[i] + request[i];
	    allocation[index][i] = allocation[index][i] - request[i];
	    need[index][i] = need[index][i] + request[i];
	}
	
    }
    
 
    //--------PRINT AVAILABLE-----------
    public void print_avail() 
    {
	for(int i = 0; i < NUMBER_OF_RESOURCES; i++) 
	{
	    System.out.println(available[i]);
	}

    }


    //--------------MAXIMUM--------------
    public void fill_max() 
    {
   //program will initially read in a file containing the maximum number of requests for each customer
	File file = new File("input.txt");
	int i = 0;
	int j = 0;
	
	try 
	{
	    Scanner scanner = new Scanner(file);
	    
	    while(scanner.hasNext()) 
	    {
		int num = scanner.nextInt();
		
		
		if(j == 3 ) 
		{
		    maximum[i][j] = num;
		    i++;
		    j = 0;
		   
		    System.out.println(maximum[i][j]);
		}
		else if(i == 4) 
		{
		    
		    break;
		}
		else 
		{
		    maximum[i][j] = num;
		    System.out.println(maximum[i][j]);
		    
		}
		j++;
		
	    } //end of while
	} //end of try
	catch(FileNotFoundException e) 
	{
	    ;
	}
    
    } //end of fill_max

    public void print_max() 
    {
	for(int i = 0; i < NUMBER_OF_CUSTOMERS; i++) 
	{
	    for(int j = 0; j < NUMBER_OF_RESOURCES; j++)
	    {
		System.out.println(maximum[i][j]+ " ");
	    }
	    
	}
    }
    
    //----------PRINT ARRAY------------
    public void print_array(int[][] a) 
    {
	for(int i=0; i<NUMBER_OF_CUSTOMERS;i++) 
	{
	    for(int j=0; j<NUMBER_OF_RESOURCES; j++) 
	    {
		System.out.print(a[i][j]+",");
	    }
	    System.out.println(" ");
	}

    }
    
    //-------------MAIN----------------
    public static void main(String[] args) 
    {
    Scanner inpt = new Scanner(System.in);

	int array[][] = new int[][]
	{
	    {6,4,7,3},
	    {4,2,3,2},
	    {2,5,3,3},
	    {6,3,3,2},
	    {5,6,7,5}
	};

	int br[][] = new int[][]
	{
	    {1,1,1,1},
	    {1,1,1,1},
	    {1,1,1,1},
	    {1,1,1,1},
	    {1,1,1,1}
	};
	

	Banker test = new Banker(array,br);
	test.find_need(array,br);
	test.fill_available();


	boolean isGoing = true;
	String command;
	
	while(isGoing) 
	{
	    System.out.println("Enter a command: "); //have user enter a command
	    command = inpt.nextLine();

	    switch(command.split(" ")[0]) 
	    {
	   
	    case "exit":
		isGoing = false;
		break;

	    //command ‘RQ’ for requesting resources
	    case "RQ":
		String[] req = command.split(" ");
		if(req.length !=6) 
		{
		    System.out.println("Error: invalid Argument size");
		    
		}
		else 
		{
		    int cID = Integer.parseInt(req[1]);
		    int reqArray[] = 
		    {
			Integer.parseInt(req[2]),Integer.parseInt(req[3]), Integer.parseInt(req[4]),

			
			Integer.parseInt(req[5])
		    };
		     
		    boolean safety = test.safety_Algorithm();
		    if(safety) 
		    {
			boolean accepted = test.resource_Request_Accepted(cID,reqArray);
			if(accepted)
			    System.out.println("Resource allocated.");

			else
			    System.out.println("Resource not allocated. ");
		    }
		    else
			{

			    System.out.println("Safety Algorithm failed");
			
			}
		    break;
		    
		}

		
	    //command 'RL’ for releasing resources
	    case "RL":

		String[] req2 = command.split(" ");
		if(req2.length !=6) 
		{
		    System.out.println("Error: invalid Argument size");
		}
		else 
		{
		    int cID2 = Integer.parseInt(req2[1]);
		    int reqArray2[] = 
		    {
			Integer.parseInt(req2[2]),Integer.parseInt(req2[3]), Integer.parseInt(req2[4]),
			Integer.parseInt(req2[5])
		    };

		    test.resource_Release(cID2,reqArray2);
		    System.out.println("Resource released.");
		}
		
		break;
		
	    //command ‘*’ to output the values of the different data structures
	    case "*":
		System.out.println("Maximum:");
		test.print_array(test.maximum);
		System.out.println("Allocation:");
		test.print_array(test.allocation);
		System.out.println("Need:");
		test.print_array(test.need);
		
		break;
		
	    default:
		System.out.println("Invalid command... try again");
		break;
		
		
		
	    }//end of switch
	    
	    
	}//end of while
	
	
    
    }//end of main
}//end
