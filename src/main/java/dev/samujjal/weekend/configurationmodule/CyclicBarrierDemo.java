package dev.samujjal.weekend.configurationmodule;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import sun.awt.windows.ThemeReader;

import java.time.Instant;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Service
@Log4j2
public class CyclicBarrierDemo {

    public void run(){
        class CallBack implements Runnable{

            @Override
            public void run() {
                log.info("Call back executed for cyclic barrier once the last thread has arrived");
            }
        }
        long startTime = Instant.now().toEpochMilli();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CallBack());
        log.info("Started run method for {}", this.getClass().getCanonicalName());
        class Runner implements Runnable{
            private CyclicBarrier cyclicBarrier;

            public Runner(CyclicBarrier cyclicBarrier) {
                this.cyclicBarrier = cyclicBarrier;
            }

            @Override
            public void run() {
                try {
                    log.info("About to await on cyclic barrier, Thread ID: {}, Thread Name: {}", Thread.currentThread().getId(), Thread.currentThread().getName());
                    Thread.sleep(1500);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        new Thread(new Runner(cyclicBarrier)).start();
        new Thread(new Runner(cyclicBarrier)).start();
        new Thread(new Runner(cyclicBarrier)).start();
        log.info("Total Time taken {}", Instant.now().toEpochMilli() - startTime);
        log.info("Everyone is done awaiting");
    }
}
