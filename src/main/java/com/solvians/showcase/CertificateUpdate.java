package com.solvians.showcase;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class CertificateUpdate {
    private long timestamp;
    private String isin;
    private double bidPrice;
    private int bidSize;
    private double askPrice;
    private int askSize;
    private LocalDate maturityDate;

    public CertificateUpdate(long timestamp, String isin, double bidPrice, int bidSize, double askPrice, int askSize, LocalDate maturityDate) {
        this.timestamp = timestamp;
        this.isin = isin;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrice = askPrice;
        this.askSize = askSize;
        this.maturityDate = maturityDate;
    }

    @Override
    public String toString() {
        return timestamp + "," + isin + "," + bidPrice + "," + bidSize + "," + askPrice + "," + askSize + "," + maturityDate;
    }
}
