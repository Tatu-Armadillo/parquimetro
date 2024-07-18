package br.com.fiap.parquimetro.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "establishment")
public class Establishment {
    
    @Id
    @Column(name = "id_establishment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstablishment;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "price_hour")
    private BigDecimal priceHour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address", foreignKey = @ForeignKey(name = "fk_establishment_address"))
    private Address address;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "users", foreignKey = @ForeignKey(name = "fk_establishment_users"))
    private User user;

    public Long getIdEstablishment() {
        return idEstablishment;
    }

    public void setIdEstablishment(Long idEstablishment) {
        this.idEstablishment = idEstablishment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BigDecimal getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(BigDecimal priceHour) {
        this.priceHour = priceHour;
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

    


}
