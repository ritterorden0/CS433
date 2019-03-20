//SJF: Shortest-job-first, which schedules tasks in order of the length of the tasks'
//next CPU burst.

import java.util.*;
import java.io.*;

public class SJF implements Algorithm 
{
    private List<Task> Queue;
    private static int count = -1;


    
    public SJF(List<Task> queue) 
    {
	this.Queue = queue;
    }

//---------SCHEDULE-----------
    public void schedule() 
    {
	System.out.println("sch..");
	
	Sort_asc();
	
	for(int i=0; i<this.Queue.size(); i++) 
	{
	    Task tempTask = pickNextTask();
	    CPU.run(tempTask,tempTask.getBurst());
	}
	
    }//end of schedule 

//--------PICK NEXT TASK------------
    public Task pickNextTask() 
    {
	count++;
	return (this.Queue.get(count));
	
    }

//-----------SORT---------------
    public void Sort_asc() 
    {
	for(int i=1; i< this.Queue.size(); i++) 
	{
	    for(int j=i; j>0 &&this.Queue.get(j-1).getBurst() > this.Queue.get(j).getBurst(); j--)
		{
		    Task tempTask = this.Queue.get(j);
		    this.Queue.set(j,this.Queue.get(j-1));
		    this.Queue.set(j-1,tempTask);
		    
		}
	}
    }//end of Sort_asc

//----------PRINT TASKS-------------
    public void printTasks() 
    {

	for(int i=0; i<this.Queue.size(); i++) 
	{
	    Task tempTask = this.Queue.get(i);
	    System.out.println(tempTask.getName());
	}
    }//end of printTasks
      
}//end