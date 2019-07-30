package com.yogo.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorPool {
    private static final ExecutorService executor= Executors.newWorkStealingPool(2);
    private static final ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(4);

    public static ExecutorService getExecutor() {
        return executor;
    }

    public static ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }
}
