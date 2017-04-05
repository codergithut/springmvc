package concurrency.resource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianjian on 2017/4/5.
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed() {
        boolean captured = lock.tryLock();
        try{
            System.out.println("tryLock(): " + captured);
        }finally {
            if(captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;
        try{
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, TimeUtil.SECONDS): " + captured);
        } finally {
            if(captured) {
                lock.unlock();
            }
        }
    }
}
