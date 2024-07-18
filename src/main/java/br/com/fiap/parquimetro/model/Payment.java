package br.com.fiap.parquimetro.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "id_payment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_format")
    private String paymentFormat;

    @Column(name = "payment_completed")
    private Boolean paymentCompleted;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking", foreignKey = @ForeignKey(name = "fk_payment_parking"))
    private Parking parking;

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentFormat() {
        return paymentFormat;
    }

    public void setPaymentFormat(String paymentFormat) {
        this.paymentFormat = paymentFormat;
    }

    public Boolean getPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(Boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

}
