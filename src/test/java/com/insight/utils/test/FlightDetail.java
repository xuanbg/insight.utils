package com.insight.utils.test;

/**
 * @author duxl
 * @date 2017/9/22
 * @remark
 */
public class FlightDetail {
    private String arrAirportCode;
    private String datePeriod;
    private String datesByWeek;
    private String days;
    private String depAirportCode;
    private String flightNo;
    private String weekFlights;

    public String getArrAirportCode() {
        return arrAirportCode;
    }

    public void setArrAirportCode(String arrAirportCode) {
        this.arrAirportCode = arrAirportCode;
    }

    public String getDatePeriod() {
        return datePeriod;
    }

    public void setDatePeriod(String datePeriod) {
        this.datePeriod = datePeriod;
    }

    public String getDatesByWeek() {
        return datesByWeek;
    }

    public void setDatesByWeek(String datesByWeek) {
        this.datesByWeek = datesByWeek;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDepAirportCode() {
        return depAirportCode;
    }

    public void setDepAirportCode(String depAirportCode) {
        this.depAirportCode = depAirportCode;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getWeekFlights() {
        return weekFlights;
    }

    public void setWeekFlights(String weekFlights) {
        this.weekFlights = weekFlights;
    }

    @Override
    public String toString() {
        return "FlightDetail [arrAirportCode=" + arrAirportCode + ", datePeriod=" + datePeriod + ", datesByWeek="
                + datesByWeek + ", days=" + days + ", depAirportCode=" + depAirportCode + ", flightNo=" + flightNo
                + ", weekFlights=" + weekFlights + "]";
    }
}
