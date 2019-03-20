//Priority with round-robin, which schedules tasks in order of priority and uses
//round-robin scheduling for tasks with equal priority.

import java.util.*;
import java.io.*;

public class Priority implements Algorithm 
{
    private List<Task> Queue;
    private static int currentCount = 8;
    
    public Priority(List<Task> queue) 
    {
	this.Queue = queue;
    }
    
//---------SCHEDULE----------
    public void schedule() 
    {
	System.out.println("Hello sche");
	sort_asc();

	for(int i=this.Queue.size()-1; i >=0; i--) 
	{
	    System.out.println(i);

	    Task tempTask = pickNextTask();
	    CPU.run(tempTask,tempTask.getBurst());
	        
	}//end of for loop
	
    }//end of schedule

//-----PICK NEXT TASK-------------
    public Task pickNextTask() 
    {
	currentCount--;
	return(this.Queue.get(currentCount));
	
    }

//-------SORT------------
    public void sort_asc() 
    {
	for(int i=1; i<this.Queue.size(); i++) 
	{
	    for(int j=i; j>0 && this.Queue.get(j-1).getPriority() > this.Queue.get(j).getPriority();
		j--)
		{
		    Task tempTask = this.Queue.get(j);
		    this.Queue.set(j,this.Queue.get(j-1));
		    this.Queue.set(j-1,tempTask);
		    
		}
	}
    }//end of sort_asc
  
//-------PRINT TASKS-----------
    public void printTasks() 
    {
	for(int i=0; i<this.Queue.size(); i++) 
	{
	    System.out.println(this.Queue.get(i).getPriority());
	}
    }//end of printTasks
    
}//end