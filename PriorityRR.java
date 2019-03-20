import java.util.*;
import java.io.*;

public class PriorityRR implements Algorithm {
    private List<Task> priQueue;
    private final int QUANT = 10;

    public PriorityRR(List<Task> pQueue) {
	this.priQueue = pQueue;
	
    }

    public void schedule() {
	List<Task> tasks = new ArrayList<Task>();

	    while(this.priQueue.size() > 0) {
		Task nextTask = pickNextTask();
		tasks.add(nextTask);
	    }


	for(Task task : tasks) {

	    CPU.run(task,1);
	    
	}
    }


    public Task pickNextTask() {
	int highest =0;
	int highestIndex = -1;

	for(int i=0; i<this.priQueue.size(); i++) {

	    if(highestIndex == -1) {
		highest = this.priQueue.get(i).getPriority();
		highestIndex =0;
	    }

	    else {
		int prio = this.priQueue.get(i).getPriority();
		if(prio>= highest) {
		    highest = prio;
		    highestIndex = i;
		}
		
	    }
	}


	Task tempTask = this.priQueue.get(highestIndex);
	
	if(tempTask.getBurst() > QUANT) {
	    tempTask.setBurst(tempTask.getBurst()-QUANT);
	    this.priQueue.set(highestIndex,tempTask);

	    Task returnTask = new Task(tempTask.getName(),tempTask.getPriority(),tempTask.getBurst());
	    returnTask.setBurst(QUANT);
	    return (returnTask);
	}

	else {

	    this.priQueue.remove(highestIndex);
	    return tempTask;
	}
    }



    public void printTasks() {
	for(int i=0; i<this.priQueue.size();i++) {
	    System.out.println("Priority: "+ this.priQueue.get(i).getPriority());
	}
	
    }

}