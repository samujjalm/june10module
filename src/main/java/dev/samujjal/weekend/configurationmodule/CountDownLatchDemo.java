package dev.samujjal.weekend.configurationmodule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class CountDownLatchDemo {

    void run() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        class Runner implements Runnable{

            private CountDownLatch countDownLatch;

            private Runner(CountDownLatch countDownLatch) {
                this.countDownLatch = countDownLatch;
            }

            @Override
            public void run() {
                log.info("Started Thread ID: {}, {}", Thread.currentThread().getId(), Thread.currentThread().getName());
                try {
                    Thread.sleep(1500);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        Runner runner1 = new Runner(countDownLatch);
        Runner runner2 = new Runner(countDownLatch);
        (new Thread(runner1)).start();
        (new Thread(runner2)).start();
        log.info("Awaiting on countdown latch");
        countDownLatch.await();
        log.info("Await completed for count down latch");
    }
}
