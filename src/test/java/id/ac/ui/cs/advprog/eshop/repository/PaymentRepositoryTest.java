package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;
    Map<String, String> paymentData;



    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
                products, 1708570000L, "Bambang Sudrajat");
        orders.add(order3);

        payments = new ArrayList<>();
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.VOUCHER_CODE.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);
        payments.add(payment1);
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                PaymentMethod.VOUCHER_CODE.getValue(), PaymentStatus.REJECTED.getValue(), paymentData);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.get(0);
        Payment savedPayment = paymentRepository.addPayment(orders.get(0),
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getMethod(), savedPayment.getMethod());
        assertEquals(payment.getStatus(), savedPayment.getStatus());
        assertEquals(payment.getPaymentData(), savedPayment.getPaymentData());
;    }

    @Test
    void testSetStatusPayment() {
        Payment payment = payments.get(0);
        Payment savedPayment = paymentRepository.addPayment(orders.get(0),
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        payment.setStatus(PaymentStatus.REJECTED.getValue());
        savedPayment = paymentRepository.setStatus(savedPayment, PaymentStatus.REJECTED.getValue());

        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getMethod(), savedPayment.getMethod());
        assertEquals(payment.getStatus(), savedPayment.getStatus());
        assertEquals(payment.getPaymentData(), savedPayment.getPaymentData());

    }

    @Test
    void testGetPaymentByIdIfIdFound() {
        Payment payment = payments.get(0);
        paymentRepository.addPayment(orders.get(0),
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        Payment savedPayment = paymentRepository.getPayment(payment.getId());

        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getMethod(), savedPayment.getMethod());
        assertEquals(payment.getStatus(), savedPayment.getStatus());
        assertEquals(payment.getPaymentData(), savedPayment.getPaymentData());
    }

    @Test
    void testGetPaymentByIdIfIdNotFound() {
        Payment savedPayment = paymentRepository.getPayment("zczc");
        assertNull(savedPayment);
    }

    @Test
    void testGetAllPayment() {
        paymentRepository.addPayment(orders.get(0),
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        paymentRepository.addPayment(orders.get(1),
                PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        List<Payment> paymentList = paymentRepository.getAllPayments();

        assertEquals(payments.size(), paymentList.size());
    }

}
