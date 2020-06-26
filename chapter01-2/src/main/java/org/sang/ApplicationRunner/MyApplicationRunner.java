package org.sang.ApplicationRunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 系统任务
 */
@Component
@Order(1)
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("nonOptionsArgs" + nonOptionArgs);

        Set<String> optionNames = args.getOptionNames();
        for(String optionName : optionNames){
            System.out.println("key: " + optionName + "; value: " + args.getOptionValues(optionName));
        }
    }
}
