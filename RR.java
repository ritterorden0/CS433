//RR: Round-robin scheduling, where each task is run for a time quantum (or for
//the remainder of its CPU burst).

import java.util.*;
import java.io.*;

public class RR implements Algorithm 
{
    private List<Task> Queue;
    private final int QUANT_TIME = 10;
    
    public RR(List<Task> queue) 
    {
	this.Queue = queue;
    }

//-----------SCHEDULE---------
    public void schedule() 
    {
	
	while(!(this.Queue.isEmpty())) 
	{
	    
	    if(this.Queue.get(0).getBurst() > QUANT_TIME) 
	    {
		
		this.Queue.get(0).setBurst(this.Queue.get(0).getBurst()-QUANT_TIME);
		CPU.run(this.Queue.get(0), QUANT_TIME);
		
		//add to back of list
		Task tempTask = this.Queue.get(0);
		this.Queue.remove(0);
		this.Queue.add(this.Queue.size(), tempTask);
		
	    }
	    
	    else 
	    {
		CPU.run(this.Queue.get(0),this.Queue.get(0).getBurst());
		this.Queue.remove(0);
	    }
	    	    
	}//end of while
	
    }//end of schedule 
    
}//end