package webSource.quartz;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/22.
 */
@Component
@Configurable
public class ScheduledTasks{

    public void reportCurrentTime(){
        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date()));
    }


    public void reportCurrentByCron(){
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date ()));
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }

}