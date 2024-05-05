import java.util.*;

class Job {
    int id;
    int deadline;
    int profit;

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobScheduling {
    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(1, 2, 100));
        jobs.add(new Job(2, 1, 50));
        jobs.add(new Job(3, 2, 200));
        jobs.add(new Job(4, 4, 60));
        jobs.add(new Job(5, 3, 150));

        List<Job> scheduledJobs = scheduleJobs(jobs);
        int totalProfit = 0;
        System.out.println("Scheduled Jobs:");
        for (Job job : scheduledJobs) {
            System.out.println("Job ID: " + job.id + ", Deadline: " + job.deadline + ", Profit: " + job.profit);
            totalProfit += job.profit;
        }
        System.out.println("Total Profit: " + totalProfit);
    }

    public static List<Job> scheduleJobs(List<Job> jobs) {
        // Sort the jobs by profit in descending order
        Collections.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job j1, Job j2) {
                return j2.profit - j1.profit;
            }
        });

        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        boolean[] slots = new boolean[maxDeadline + 1];
        List<Job> scheduledJobs = new ArrayList<>();

        // Iterate through each job
        for (Job job : jobs) {
            // Find a free slot before the deadline
            for (int i = job.deadline; i > 0; i--) {
                if (!slots[i]) {
                    slots[i] = true;
                    scheduledJobs.add(job);
                    break;
                }
            }
        }
        return scheduledJobs;
    }
}
