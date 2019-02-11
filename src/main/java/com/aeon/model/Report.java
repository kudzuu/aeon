package com.aeon.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "aenaflight_source")
@XmlRootElement(name = "record")
public class Report {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "adep")
    private String adep;

    @Column(name = "ades")
    private String ades;

    @Column(name = "flight_code")
    private String flightCode;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "carrier_code")
    private String carrierCode;

    @Column(name = "carrier_number")
    private String carrierNumber;

    @Column(name = "status_info")
    private String statusInfo;

    @Column(name = "schd_dep_lt")
    private Timestamp schdDepLt;

    @Column(name = "schd_arr_lt")
    private Timestamp schdArrLt;

    @Column(name = "est_dep_lt")
    private Timestamp estDepLt;

    @Column(name = "est_arr_lt")
    private Timestamp estArrLt;

    @Column(name = "act_dep_lt")
    private Timestamp actDepLt;

    @Column(name = "act_arr_lt")
    private Timestamp actArrLt;

    @Column(name = "flt_leg_seq_no")
    private int fltLegSeqNo;

    @Column(name = "aircraft_name_scheduled")
    private String aircraftNameScheduled;

    @Column(name = "baggage_info")
    private String baggageInfo;

    @Column(name = "counter")
    private String counter;

    @Column(name = "gate_info")
    private String gateInfo;

    @Column(name = "lounge_info")
    private String loungeInfo;

    @Column(name = "terminal_info")
    private String terminalInfo;

    @Column(name = "arr_terminal_info")
    private String arrTerminalInfo;

    @Column(name = "source_data")
    private String sourceData;

    @Column(name = "created_at")
    private Timestamp createdAt;

    private String actArrDateTimeLt;
    private String estArrDateTimeLt;
    private String estDepDateTimeLt;
    private String schdArrOnlyDateLt;
    private String schdArrOnlyTimeLt;
    private String actDepDateTimeLt;
    private String schdDepOnlyDateLt;
    private String schdDepOnlyTimeLt;

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "adep")
    public String getAdep() {
        return adep;
    }

    public void setAdep(String adep) {
        this.adep = adep;
    }

    @XmlElement(name = "ades")
    public String getAdes() {
        return ades;
    }

    public void setAdes(String ades) {
        this.ades = ades;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrierNumber() {
        return carrierNumber;
    }

    public void setCarrierNumber(String carrierNumber) {
        this.carrierNumber = carrierNumber;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Timestamp getSchdDepLt() {
        return schdDepLt;
    }

    public void setSchdDepLt(Timestamp schdDepLt) {
        this.schdDepLt = schdDepLt;
    }

    public Timestamp getSchdArrLt() {
        return schdArrLt;
    }

    public void setSchdArrLt(Timestamp schdArrLt) {
        this.schdArrLt = schdArrLt;
    }

    public Timestamp getEstDepLt() {
        return estDepLt;
    }

    public void setEstDepLt(Timestamp estDepLt) {
        this.estDepLt = estDepLt;
    }

    public Timestamp getEstArrLt() {
        return estArrLt;
    }

    public void setEstArrLt(Timestamp estArrLt) {
        this.estArrLt = estArrLt;
    }

    public Timestamp getActDepLt() {
        return actDepLt;
    }

    public void setActDepLt(Timestamp actDepLt) {
        this.actDepLt = actDepLt;
    }

    public Timestamp getActArrLt() {
        return actArrLt;
    }

    public void setActArrLt(Timestamp actArrLt) {
        this.actArrLt = actArrLt;
    }

    public String getActArrDateTimeLt() {
        return actArrDateTimeLt;
    }

    public void setActArrDateTimeLt(String actArrDateTimeLt) {
        this.actArrDateTimeLt = actArrDateTimeLt;
    }

    public String getEstArrDateTimeLt() {
        return estArrDateTimeLt;
    }

    public void setEstArrDateTimeLt(String estArrDateTimeLt) {
        this.estArrDateTimeLt = estArrDateTimeLt;
    }

    public String getEstDepDateTimeLt() {
        return estDepDateTimeLt;
    }

    public void setEstDepDateTimeLt(String estDepDateTimeLt) {
        this.estDepDateTimeLt = estDepDateTimeLt;
    }

    public String getSchdArrOnlyDateLt() {
        return schdArrOnlyDateLt;
    }

    public void setSchdArrOnlyDateLt(String schdArrOnlyDateLt) {
        this.schdArrOnlyDateLt = schdArrOnlyDateLt;
    }

    public String getSchdArrOnlyTimeLt() {
        return schdArrOnlyTimeLt;
    }

    public void setSchdArrOnlyTimeLt(String schdArrOnlyTimeLt) {
        this.schdArrOnlyTimeLt = schdArrOnlyTimeLt;
    }

    public String getActDepDateTimeLt() {
        return actDepDateTimeLt;
    }

    public void setActDepDateTimeLt(String actDepDateTimeLt) {
        this.actDepDateTimeLt = actDepDateTimeLt;
    }

    public String getSchdDepOnlyDateLt() {
        return schdDepOnlyDateLt;
    }

    public void setSchdDepOnlyDateLt(String schdDepOnlyDateLt) {
        this.schdDepOnlyDateLt = schdDepOnlyDateLt;
    }

    public String getSchdDepOnlyTimeLt() {
        return schdDepOnlyTimeLt;
    }

    public void setSchdDepOnlyTimeLt(String schdDepOnlyTimeLt) {
        this.schdDepOnlyTimeLt = schdDepOnlyTimeLt;
    }

    public int getFltLegSeqNo() {
        return fltLegSeqNo;
    }

    public void setFltLegSeqNo(int fltLegSeqNo) {
        this.fltLegSeqNo = fltLegSeqNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", id, adep, flightCode);
    }
}
