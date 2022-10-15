package br.com.teste.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String originAccount;

    private String destinationAccount;

    private LocalDate transferDate;

    private LocalDate scheduleDate;

    private BigDecimal transferValue;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "tax_id")
    private Tax tax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(String originAccount) {
        this.originAccount = originAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public BigDecimal getTransferValue() {
        return transferValue;
    }

    public void setTransferValue(BigDecimal transferValue) {
        this.transferValue = transferValue;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheduling that = (Scheduling) o;
        return originAccount.equals(that.originAccount) && destinationAccount.equals(that.destinationAccount) && transferDate.equals(that.transferDate) && scheduleDate.equals(that.scheduleDate) && transferValue.equals(that.transferValue) && tax.equals(that.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originAccount, destinationAccount, transferDate, scheduleDate, transferValue, tax);
    }
}
