package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertUpdateGeneratorTest {

    @Test
    public void testGenerateCertUpdate() {
        IsinGenerator isinGenerator = new IsinGenerator();
        CertificateUpdate certificateUpdate = new CertificateUpdate(Long.parseLong("1729965401692"),
                isinGenerator.generateIsin("DE123456789"),
                154.61,
                2552,
                166.12,
                9328,
                LocalDate.of(2024, 12, 19));
        System.out.println(certificateUpdate);
        String sampleOutput = "1729965401692,DE1234567896,154.61,2552,166.12,9328,2024-12-19";
        assertEquals(certificateUpdate.toString(), sampleOutput);
    }
}