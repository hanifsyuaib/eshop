package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;

        String[] methodList = {"VOUCHER_CODE", "CASH_ON_DELIVERY"};
        if (Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))) {
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }

        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }

        String[] paymentDataList = {"voucherCode", "address", "deliveryFee"};
        if (Arrays.stream(paymentDataList).noneMatch(item -> (paymentData.containsKey(item)))) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }
    }
}
