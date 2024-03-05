package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreatePaymentValidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", "SUCCESS", paymentData);

        assertEquals("VOUCHER_CODE", payment.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("voucherCode", "ESHOP1234ABC5678");

            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "MEOW", "SUCCESS", paymentData);
        });
    }

    @Test
    void testCreatePaymentValidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", "SUCCESS", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("voucherCode", "ESHOP1234ABC5678");

            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "VOUCHER_CODE", "MEOW", paymentData);
        });
    }

    @Test
    void testCreatePaymentValidPaymentData() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", "SUCCESS", paymentData);
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentInvalidPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("code", "ESHOP1234ABC5678");

            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "VOUCHER_CODE", "SUCCESS", paymentData);
        });
    }

}
