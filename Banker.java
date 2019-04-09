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
    
    public (int a[][])  
    {
		this.maximum = a;
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
	

    }
    
    //----AVAILABLE--------
    public void fill_available() 
    {
	Scanner read = new Scanner(System.in);
	
	for(int i = 0; i < NUMBER_OF_RESOURCES; i++) 
		{
	    System.out.println("Enter data for available: ");
	    available[i] = read.nextInt();
		}

    }

    public void print_avail() 
    {
	for(int i = 0; i < NUMBER_OF_RESOURCES; i++) 
		{
	    System.out.println(available[i]);
		}

    }


//--------------MAXIMUM-----------
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
    
    
    public static void main(String[] args) 
    {
	int array[][] = new int[][]
	{
	    {6,4,7,3},
	    {4,2,3,2},
	    {2,5,3,3},
	    {6,3,3,2},
	    {5,6,7,5}
	};

	Banker test = new Banker(array);
	
	test.print_max();
    }


    
} //end