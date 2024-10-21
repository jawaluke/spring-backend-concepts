package SpringBootWorkAroundBranch.entity;

import SpringBootWorkAroundBranch.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "ecom_payment", schema = "system_design")
@Builder
@Data
public class PaymentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private long paymentId;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "product_amount")
    private int payableAmount;

    @Column(name = "product_total_amount")
    private int TotalAmount;

    @Column(name = "payment_status")
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "payment_created_time")
    private Date paymentCreatedTime;

    @Column(name = "payment_completed_time")
    private Date paymentCompletedTime;
}
