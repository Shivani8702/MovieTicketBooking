package com.cg.mtb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mtb.dto.PaymentDto;
import com.cg.mtb.entity.PaymentEntity;
import com.cg.mtb.exception.PaymentNotFoundException;
import com.cg.mtb.repo.PaymentRepository;
import com.cg.mtb.util.PaymentUtil;

import java.time.LocalDate;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Make a payment for a booked ticket (User)
    @Override
    public PaymentDto makePayment(PaymentDto paymentDto) {
        PaymentEntity payment = new PaymentEntity();
        payment.setUserId(paymentDto.getUserId());
        payment.setTicketId(paymentDto.getTicketId());
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentStatus("PENDING"); // Default status

        PaymentEntity savedPayment = paymentRepository.save(payment);
        return PaymentUtil.mapToDto(savedPayment);
    }

    // Get payment details by payment ID (User & Admin)
    @Override
    public Optional<PaymentDto> getPaymentById(int paymentId) {
        return paymentRepository.findById(paymentId).map(PaymentUtil::mapToDto);
    }

    // Get all payments made by a specific user (User)
    @Override
    public Set<PaymentDto> getUserPayments(int userId) {
        return paymentRepository.findByUserId(userId)
                .stream()
                .map(PaymentUtil::mapToDto)
                .collect(Collectors.toSet());
    }

    // Get all payments made by all users (Admin)
    @Override
    public Set<PaymentDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(PaymentUtil::mapToDto)
                .collect(Collectors.toSet());
    }

    // Admin views a specific payment by ID
    @Override
    public Optional<PaymentDto> getAdminPaymentById(int paymentId) {
        return getPaymentById(paymentId);
    }

    // Admin can update payment status
    @Override
    public PaymentDto updatePaymentStatus(int paymentId, String newStatus) throws PaymentNotFoundException {
        PaymentEntity payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + paymentId));

        payment.setPaymentStatus(newStatus);
        PaymentEntity updatedPayment = paymentRepository.save(payment);
        return PaymentUtil.mapToDto(updatedPayment);
    }
    @Override
    public void deletePayment(int paymentId) throws PaymentNotFoundException {
        PaymentEntity payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + paymentId));
        
        paymentRepository.delete(payment);
    }

    @Override
    public PaymentDto cancelPayment(int paymentId) throws PaymentNotFoundException {
        PaymentEntity payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + paymentId));
        
        payment.setPaymentStatus("CANCELLED");
        PaymentEntity cancelledPayment = paymentRepository.save(payment);
        return PaymentUtil.mapToDto(cancelledPayment);
    }

}
