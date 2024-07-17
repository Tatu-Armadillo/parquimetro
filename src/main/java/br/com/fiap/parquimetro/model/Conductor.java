package br.com.fiap.parquimetro.model;

import java.util.Set;

import br.com.fiap.parquimetro.model.enuns.PaymentFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "conductor")
public class Conductor {

    @Id
    @Column(name = "id_conductor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConductor;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "payment_format")
    @Enumerated(EnumType.STRING)
    private PaymentFormat paymentFormat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address", foreignKey = @ForeignKey(name = "fk_conductor_address"))
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "users", foreignKey = @ForeignKey(name = "fk_conductor_users"))
    private User user;

    @OneToMany(mappedBy = "conductor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehicle> vehicles;

    public Long getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Long idConductor) {
        this.idConductor = idConductor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PaymentFormat getPaymentFormat() {
        return paymentFormat;
    }

    public void setPaymentFormat(PaymentFormat paymentFormat) {
        this.paymentFormat = paymentFormat;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

}
