package com.cg.mtb.ctrl;

import com.cg.mtb.dto.PaymentDto;
import com.cg.mtb.exception.InvalidPaymentStatusException;
import com.cg.mtb.exception.PaymentNotFoundException;
import com.cg.mtb.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 🎟️ 1️⃣ Make a Payment (User)
    @PostMapping
    public ResponseEntity<PaymentDto> makePayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto processedPayment = paymentService.makePayment(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(processedPayment);
    }

    // 🎟️ 2️⃣ Get Payment Details by Payment ID (User & Admin)
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable int paymentId) throws PaymentNotFoundException {
        Optional<PaymentDto> payment = paymentService.getPaymentById(paymentId);
        return payment.map(ResponseEntity::ok)
                      .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + paymentId));
    }

    // 🎟️ 3️⃣ Get All Payments Made by a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<PaymentDto>> getUserPayments(@PathVariable int userId) {
        Set<PaymentDto> payments = paymentService.getUserPayments(userId);
        if (payments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(payments);
    }

    // 🛠️ Admin Endpoints 🛠️

    // 🎟️ 4️⃣ Get All Payments (Admin)
    @GetMapping("/admin")
    public ResponseEntity<Set<PaymentDto>> getAllPayments() {
        Set<PaymentDto> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // 🎟️ 5️⃣ Get Payment Details by Payment ID (Admin)
    @GetMapping("/admin/{paymentId}")
    public ResponseEntity<PaymentDto> getAdminPaymentById(@PathVariable int paymentId) throws PaymentNotFoundException {
        Optional<PaymentDto> payment = paymentService.getPaymentById(paymentId);
        return payment.map(ResponseEntity::ok)
                      .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + paymentId));
    }

    //  Update Payment Status (Admin)
    @PutMapping("/admin/{paymentId}/status")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable int paymentId, @RequestParam String status) {
        try {
            PaymentDto updatedPayment = paymentService.updatePaymentStatus(paymentId, status);
            return ResponseEntity.ok("Payment status updated successfully to: " + updatedPayment.getPaymentStatus());
        } catch (PaymentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidPaymentStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/admin/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable int paymentId) {
        try {
            paymentService.deletePayment(paymentId);
            return ResponseEntity.ok("Payment deleted successfully.");
        } catch (PaymentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/admin/{paymentId}/cancel")
    public ResponseEntity<PaymentDto> cancelPayment(@PathVariable int paymentId) throws PaymentNotFoundException {
        PaymentDto cancelledPayment = paymentService.cancelPayment(paymentId);
        return ResponseEntity.ok(cancelledPayment);
    }
}
