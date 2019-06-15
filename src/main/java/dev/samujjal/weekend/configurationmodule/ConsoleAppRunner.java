package dev.samujjal.weekend.configurationmodule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsoleAppRunner implements CommandLineRunner {

    @Autowired
    private CountDownLatchDemo countDownLatchDemo;

    @Autowired
    private CyclicBarrierDemo cyclicBarrierDemo;

    @Override
    public void run(String... args) throws Exception {
        log.info("Executing Console runner");
//        countDownLatchDemo.run();
        cyclicBarrierDemo.run();
    }
}
