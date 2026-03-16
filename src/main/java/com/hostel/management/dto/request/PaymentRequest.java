package com.hostel.management.dto.request;
import com.hostel.management.model.Payment;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public class PaymentRequest {
    @NotNull private Long studentId;
    @NotNull @DecimalMin("0.01") private BigDecimal amount;
    @NotBlank private String month;
    @NotNull private Payment.Status status;
    public PaymentRequest() {}
    public Long getStudentId()       { return studentId; }   public void setStudentId(Long v)       { this.studentId = v; }
    public BigDecimal getAmount()    { return amount; }      public void setAmount(BigDecimal v)    { this.amount = v; }
    public String getMonth()         { return month; }       public void setMonth(String v)         { this.month = v; }
    public Payment.Status getStatus(){ return status; }      public void setStatus(Payment.Status v){ this.status = v; }
}
