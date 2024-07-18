package br.com.fiap.parquimetro.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @Column(name = "id_parking")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParking;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "time_fixed")
    private boolean timeFixed;

    @Column(name = "time_start")
    private LocalDateTime timeStart;

    @Column(name = "time_end")
    private LocalDateTime timeEnd;

    @Column(name = "total_time")
    private Long totalTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle", foreignKey = @ForeignKey(name = "fk_parking_vehicle"))
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "establishment", foreignKey = @ForeignKey(name = "fk_parking_establishment"))
    private Establishment establishment;

    public Long getIdParking() {
        return idParking;
    }

    public void setIdParking(Long idParking) {
        this.idParking = idParking;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public boolean isTimeFixed() {
        return timeFixed;
    }

    public void setTimeFixed(boolean timeFixed) {
        this.timeFixed = timeFixed;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

}
