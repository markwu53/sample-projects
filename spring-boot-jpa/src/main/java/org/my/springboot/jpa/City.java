package org.my.springboot.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "big_city")
public class City {

    @EmbeddedId
    private CityId id;

    @Column
    private String city;

    @Column(name = "client_state")
    private String clientState;

    @Column(precision = 12, scale = 2)
    private double premium;

    public CityId getId() {
        return id;
    }

    public void setId(CityId id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

}

@Embeddable
class CityId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String renewalDate;
    private String insuredName;

    public String getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(String renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

}