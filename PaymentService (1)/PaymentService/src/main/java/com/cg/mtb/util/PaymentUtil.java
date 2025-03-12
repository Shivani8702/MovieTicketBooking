package com.cg.mtb.util;

import com.cg.mtb.dto.PaymentDto;
import com.cg.mtb.entity.PaymentEntity;
import com.cg.mtb.exception.InvalidPaymentStatusException;

import java.util.Set;
import java.util.stream.Collectors;

public class PaymentUtil {

    // ✅ Convert PaymentEntity to PaymentDto
    public static PaymentDto mapToDto(PaymentEntity payment) {
        return new PaymentDto(
                payment.getPaymentId(),
                payment.getUserId(),
                payment.getTicketId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentStatus()
        );
    }

    // ✅ Convert Set of PaymentEntity to Set of PaymentDto
    public static Set<PaymentDto> mapToDtoSet(Set<PaymentEntity> payments) {
        return payments.stream().map(PaymentUtil::mapToDto).collect(Collectors.toSet());
    }

    // ✅ Convert PaymentDto to PaymentEntity
    public static PaymentEntity mapToEntity(PaymentDto dto) {
        PaymentEntity payment = new PaymentEntity();
        payment.setPaymentId(dto.getPaymentId());
        payment.setUserId(dto.getUserId());
        payment.setTicketId(dto.getTicketId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentStatus(dto.getPaymentStatus());
        return payment;
    }

    // ✅ Convert Set of PaymentDto to Set of PaymentEntity
    public static Set<PaymentEntity> mapToEntitySet(Set<PaymentDto> dtos) {
        return dtos.stream().map(PaymentUtil::mapToEntity).collect(Collectors.toSet());
    }

    // ✅ Validate Payment Status
    public static void validatePaymentStatus(String status) throws InvalidPaymentStatusException {
        Set<String> validStatuses = Set.of("PENDING", "COMPLETED", "FAILED", "REFUNDED");
        if (!validStatuses.contains(status.toUpperCase())) {
            throw new InvalidPaymentStatusException("Invalid payment status: " + status);
        }
    }
}
