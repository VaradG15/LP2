package job_scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Job{
	int id;
	int profit;
	int deadline;
	public Job(int id, int profit, int deadline) {
		super();
		this.id = id;
		this.profit = profit;
		this.deadline = deadline;
	}
	public int getProfit() {
		return profit;
	}
	
}

public class JobScheduling {

	public static List<Job> jobScheduling(List<Job> jobs) {
		
		jobs.sort(Comparator.comparingInt(Job :: getProfit).reversed());
		
		int maxDeadline = 0;
		for(Job job : jobs) {
			maxDeadline = Math.max(maxDeadline, job.deadline);
		}
		
		boolean[] slots = new boolean[maxDeadline + 1];
		List<Job> scheduledJobs = new ArrayList<>();
		
		for(Job job : jobs) {
			for(int i = job.deadline; i>0; i--) {
				if(!slots[i]) {
					slots[i] = true;
					scheduledJobs.add(job);
					break;
				}
			}
		}
		return scheduledJobs;
	}
	
	public static void main(String [] args) {
		List<Job> jobs = new ArrayList<>();
		jobs.add(new Job(1, 100, 3));
		jobs.add(new Job(2, 160, 4));
		jobs.add(new Job(3, 150, 1));
		jobs.add(new Job(4, 50, 2));
		jobs.add(new Job(5, 200, 3));
		
		List<Job> scheduledJobs = jobScheduling(jobs);
		int totalProfit = 0;
        System.out.println("Scheduled Jobs:");
        for (Job job : scheduledJobs) {
            System.out.println("Job ID: " + job.id + ", Deadline: " + job.deadline + ", Profit: " + job.profit);
            totalProfit += job.profit;
        }
        System.out.println("Total Profit: " + totalProfit);	
	}
}
