package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String,String> paymentData) {
        if (paymentRepository.getPayment(order.getId()) == null) {
            if (paymentData.containsKey("address")
                    && paymentData.containsKey("deliveryFee")) {

                return addCashOnDeliveryPayment(order, method, paymentData);
            }

            return addVoucherCodePayment(order, method, paymentData);
        }
        return null;
    }

    private Payment addCashOnDeliveryPayment(Order order, String method, Map<String,String> paymentData) {
        if (paymentData.containsValue("")
                || paymentData.containsValue(null)) {
            paymentRepository.addPayment(order, method, paymentData);
            Payment payment = paymentRepository.getPayment(order.getId());
            payment = paymentRepository.setStatus(payment, "REJECTED");
            return new Payment(order.getId(), method, "REJECTED", paymentData);
        }
        paymentRepository.addPayment(order, method, paymentData);
        return new Payment(order.getId(), method, "SUCCESS", paymentData);
    }

    private  Payment addVoucherCodePayment(Order order, String method, Map<String,String> paymentData) {
        if (isNotValidVoucherCode(paymentData.get("voucherCode"))) {
            paymentRepository.addPayment(order, method, paymentData);
            Payment payment = paymentRepository.getPayment(order.getId());
            payment = paymentRepository.setStatus(payment, "REJECTED");
            return new Payment(order.getId(), method, "REJECTED", paymentData);
        }

        paymentRepository.addPayment(order, method, paymentData);
        return new Payment(order.getId(), method, "SUCCESS", paymentData);
    }

    private boolean isNotValidVoucherCode(String voucherCode) {
        return voucherCode == null ||
                voucherCode.length() != 16 ||
                !voucherCode.startsWith("ESHOP") ||
                !voucherCode.substring(5).matches("\\d{8}");
    }

    @Override
    public Payment setStatus(Payment payment, String status){
        if (payment != null) {
            paymentRepository.setStatus(payment, status);
            return payment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}
