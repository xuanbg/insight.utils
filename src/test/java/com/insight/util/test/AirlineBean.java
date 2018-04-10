package com.insight.util.test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * 航线信息封装
 *
 * @author wiley
 * @time 2017年9月2日 下午3:38:14
 */
public class AirlineBean {
    private String id;
    private String accountId;
    private String airWayId;
    private String airlineId;
    private Date departureStart;
    private Date departureEnd;
    private Integer seatCount;
    private BigDecimal depositAmount;
    private BigDecimal adultPrice;
    private BigDecimal childPrice;
    private Integer ticketAdvance;
    private Integer recoveryAdvance;
    private Integer freeBag;
    private Integer weightLimit;
    private Integer alertAdvance;
    private Integer alertRate;
    private Integer airlineStatus;
    private String manager;
    private String managerId;
    private String creatorUser;
    private String creatorUserId;
    private Integer flightType;
    private Integer resType;
    private Integer seatType;
    private Integer payAdvance;
    private String supplireName;
    private List<FlightDetail> msdAirlineInfoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirWayId() {
        return airWayId;
    }

    public void setAirWayId(String airWayId) {
        this.airWayId = airWayId;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public Date getDepartureStart() {
        return departureStart;
    }

    public void setDepartureStart(Date departureStart) {
        this.departureStart = departureStart;
    }

    public Date getDepartureEnd() {
        return departureEnd;
    }

    public void setDepartureEnd(Date departureEnd) {
        this.departureEnd = departureEnd;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public Integer getTicketAdvance() {
        return ticketAdvance;
    }

    public void setTicketAdvance(Integer ticketAdvance) {
        this.ticketAdvance = ticketAdvance;
    }

    public Integer getRecoveryAdvance() {
        return recoveryAdvance;
    }

    public void setRecoveryAdvance(Integer recoveryAdvance) {
        this.recoveryAdvance = recoveryAdvance;
    }

    public Integer getFreeBag() {
        return freeBag;
    }

    public void setFreeBag(Integer freeBag) {
        this.freeBag = freeBag;
    }

    public Integer getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Integer getAlertAdvance() {
        return alertAdvance;
    }

    public void setAlertAdvance(Integer alertAdvance) {
        this.alertAdvance = alertAdvance;
    }

    public Integer getAlertRate() {
        return alertRate;
    }

    public void setAlertRate(Integer alertRate) {
        this.alertRate = alertRate;
    }

    public Integer getAirlineStatus() {
        return airlineStatus;
    }

    public void setAirlineStatus(Integer airlineStatus) {
        this.airlineStatus = airlineStatus;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public List<FlightDetail> getMsdAirlineInfoList() {
        return msdAirlineInfoList;
    }

    public void setMsdAirlineInfoList(List<FlightDetail> msdAirlineInfoList) {
        this.msdAirlineInfoList = msdAirlineInfoList;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getFlightType() {
        return flightType;
    }

    public void setFlightType(Integer flightType) {
        this.flightType = flightType;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public Integer getPayAdvance() {
        return payAdvance;
    }

    public void setPayAdvance(Integer payAdvance) {
        this.payAdvance = payAdvance;
    }

    public String getSupplireName() {
        return supplireName;
    }

    public void setSupplireName(String supplireName) {
        this.supplireName = supplireName;
    }

}