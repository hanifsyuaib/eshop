package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
                PaymentMethod.VOUCHER_CODE.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);

        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("voucherCode", "ESHOP1234ABC5678");

            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "MEOW", PaymentStatus.SUCCESS.getValue(), paymentData);
        });
    }

    @Test
    void testCreatePaymentValidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.VOUCHER_CODE.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("voucherCode", "ESHOP1234ABC5678");

            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    PaymentMethod.VOUCHER_CODE.getValue(), "MEOW", paymentData);
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
