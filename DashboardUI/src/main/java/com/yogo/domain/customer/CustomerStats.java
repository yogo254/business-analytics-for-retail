package com.yogo.domain.customer;

import com.yogo.util.DoubleStats;

import java.time.LocalTime;

public class CustomerStats {
    private DoubleStats stats;
    private LocalTime timestamp;

    public CustomerStats() {
    }

    public CustomerStats(DoubleStats stats, LocalTime timestamp) {
        this.stats = stats;
        this.timestamp = timestamp;
    }

    public DoubleStats getStats() {
        return stats;
    }

    public void setStats(DoubleStats stats) {
        this.stats = stats;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CustomerStats{" +
                "stats=" + stats +
                ", timestamp=" + timestamp +
                '}';
    }
}
