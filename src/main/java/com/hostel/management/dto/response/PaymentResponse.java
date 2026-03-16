package com.hostel.management.dto.response;
import com.hostel.management.model.Payment;
import java.math.BigDecimal;
public class PaymentResponse {
    private Long paymentId; private Long studentId; private String studentName;
    private BigDecimal amount; private String month; private Payment.Status status;
    public PaymentResponse() {}
    public Long getPaymentId()          { return paymentId; }   public void setPaymentId(Long v)          { this.paymentId = v; }
    public Long getStudentId()          { return studentId; }   public void setStudentId(Long v)          { this.studentId = v; }
    public String getStudentName()      { return studentName; } public void setStudentName(String v)      { this.studentName = v; }
    public BigDecimal getAmount()       { return amount; }      public void setAmount(BigDecimal v)       { this.amount = v; }
    public String getMonth()            { return month; }       public void setMonth(String v)            { this.month = v; }
    public Payment.Status getStatus()   { return status; }      public void setStatus(Payment.Status v)   { this.status = v; }
}
