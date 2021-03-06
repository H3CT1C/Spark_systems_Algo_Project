import jdk.jfr.Event;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Timer implements Job {

    public void execute(JobExecutionContext arg0) {
        EventManager em = (EventManager) arg0.getJobDetail().getJobDataMap().get("em");
        String tag = (String) arg0.getJobDetail().getJobDataMap().get("tag");

        try{
            em.publish(new ScheduleEvent(tag));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
