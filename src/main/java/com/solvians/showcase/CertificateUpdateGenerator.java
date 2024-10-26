package com.solvians.showcase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    public Stream<CertificateUpdate> generateQuotes() {
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<Callable<List<CertificateUpdate>>> taskList = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            taskList.add(() -> {
                List<CertificateUpdate> updates = new ArrayList<>();
                for (int j = 0; j < quotes; j++) {
                    IsinGenerator isinGenerator = new IsinGenerator();
                    updates.add(new CertificateUpdate(
                            generateTimestamp(),
                            isinGenerator.generateIsin(isinGenerator.get2randomLetters() + isinGenerator.get9randomAlphaNumerics()),
                            generateBidPrice(),
                            generateBidSize(),
                            generateAskPrice(),
                            generateAskSize(),
                            generateMaturityDate()));
                }
                return updates;
            });
        }

        List<CertificateUpdate> combinedResults = new ArrayList<>();

        try {
            List<Future<List<CertificateUpdate>>> futures = executorService.invokeAll(taskList);
            for (Future<List<CertificateUpdate>> future : futures) {
                combinedResults.addAll(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        return combinedResults.stream();
    }
    private long generateTimestamp() {
        return System.currentTimeMillis();
    }

    private double generateBidPrice() {
        return roundToTwoDecimalPlaces(100.0 + ThreadLocalRandom.current().nextDouble(100.01));
    }

    private int generateBidSize() {
        return ThreadLocalRandom.current().nextInt(1000, 5001);
    }

    private double generateAskPrice() {
        return roundToTwoDecimalPlaces(100.0 + ThreadLocalRandom.current().nextDouble(100.01));
    }

    private int generateAskSize() {
        return ThreadLocalRandom.current().nextInt(1000, 10001);
    }

    private LocalDate generateMaturityDate() {
        long now = LocalDate.now().toEpochDay();
        long end = LocalDate.now().plusYears(2).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(now, end);
        return LocalDate.ofEpochDay(randomDay);
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
