package com.solvians.showcase;

import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public App(String threads, String quotes) {

    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            int threads = Integer.parseInt(args[0]);
            int quotes = Integer.parseInt(args[1]);

            CertificateUpdateGenerator certificateUpdateGenerator = new CertificateUpdateGenerator(threads, quotes);
            Stream<CertificateUpdate> updates = certificateUpdateGenerator.generateQuotes();
            updates.forEach(update -> System.out.println(update.toString()));
            return;
        }
        throw new RuntimeException("Expect at least number of threads and number of quotes. But got: " + args);
    }
}
