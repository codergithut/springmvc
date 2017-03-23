package concurrency.daemon;

import concurrency.daemon.DaemonThreadFactory;

import java.util.concurrent.ThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * Created by tianjian on 2017/3/22.
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
    public DaemonThreadPoolExecutor(){
        super(0, Integer.MAX_VALUE, 60l,
                null, new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
    }
}
