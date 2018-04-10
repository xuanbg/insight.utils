package com.insight.util.test;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author wiley
 * @date 2017/10/14
 * @remark 封装飞常准数据
 */
public class VariFlight implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     *错误码
     */
    @JsonProperty("error_code")
    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     *错误信息
     */
    @JsonProperty("error")

    private String errorMessage;

    /**
     * 航班属性（0:国内-国内;1国内-国际;2国内-地区;3:地区-国际;4:国际-国际;5:未知）
     */
    @JsonProperty("fcategory")
    private String fcategory;

    /**
     * 出发地时区
     */
    @JsonProperty("org_timezone")
    private String orgTimezone;

    /**
     * 目的地时区
     */
    @JsonProperty("dst_timezone")
    private String dstTimezone;

    /**
     * 航班号
     */
    @JsonProperty("FlightNo")
    private String flightNo;

    /**
     * 航空公司
     */
    @JsonProperty("FlightCompany")
    private String flightCompany;

    /**
     * 出发机场三字码
     */
    @JsonProperty("FlightDepcode")
    private String flightDepcode;

    /**
     * 到达机场三字码
     */
    @JsonProperty("FlightArrcode")
    private String flightArrcode;

    /**
     * 计划起飞时间
     */
    @JsonProperty("FlightDeptimePlanDate")
    private String flightDeptimePlanDate;

    /**
     * 计划到达时间
     */
    @JsonProperty("FlightArrtimePlanDate")
    private String flightArrtimePlanDate;

    /**
     *
     */
    @JsonProperty("FlightDeptimeDate")
    private String flightDeptimeDate;

    /**
     *
     */
    @JsonProperty("FlightArrtimeDate")
    private String flightArrtimeDate;

    /**
     * 航班状态（计划、延误、取消、备降、返航、起飞、到达、备降起飞、备降取消、备降到达、返航起飞、返航取消、返航到达、提前取消）
     */
    @JsonProperty("FlightState")
    private String flightState;

    /**
     * 共享航班号（实际承运航班的航班号）
     */
    @JsonProperty("ShareFlightNo")
    private String shareFlightNo;

    /**
     * 是否经停（0:不经停;1:经停）
     */
    @JsonProperty("StopFlag")
    private String stopFlag;

    /**
     * 是否共享（0:不共享;1:共享）
     */
    @JsonProperty("ShareFlag")
    private String shareFlag;

    /**
     * 是否虚拟（0：非虚拟航班；1：虚拟航班；未来航班返回为空）
     */
    @JsonProperty("VirtualFlag")
    private String virtualFlag;

    /**
     * 航段标识（0计划航段；1因备降而产生的航段；2因返航而产生的航段）
     */
    @JsonProperty("LegFlag")
    private String legFlag;

    /**
     * 出发城市
     */
    @JsonProperty("FlightDep")
    private String flightDep;

    /**
     * 到达城市
     */
    @JsonProperty("FlightArr")
    private String flightArr;

    /**
     * 出发机场
     */
    @JsonProperty("FlightDepAirport")
    private String flightDepAirport;

    /**
     * 到达机场
     */
    @JsonProperty("FlightArrAirport")
    private String flightArrAirport;

    public String getFcategory() {
        return fcategory;
    }

    public void setFcategory(String fcategory) {
        this.fcategory = fcategory;
    }

    public String getOrgTimezone() {
        return orgTimezone;
    }

    public void setOrgTimezone(String orgTimezone) {
        this.orgTimezone = orgTimezone;
    }

    public String getDstTimezone() {
        return dstTimezone;
    }

    public void setDstTimezone(String dstTimezone) {
        this.dstTimezone = dstTimezone;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightCompany() {
        return flightCompany;
    }

    public void setFlightCompany(String flightCompany) {
        this.flightCompany = flightCompany;
    }

    public String getFlightDepcode() {
        return flightDepcode;
    }

    public void setFlightDepcode(String flightDepcode) {
        this.flightDepcode = flightDepcode;
    }

    public String getFlightArrcode() {
        return flightArrcode;
    }

    public void setFlightArrcode(String flightArrcode) {
        this.flightArrcode = flightArrcode;
    }

    public String getFlightDeptimePlanDate() {
        return flightDeptimePlanDate;
    }

    public void setFlightDeptimePlanDate(String flightDeptimePlanDate) {
        this.flightDeptimePlanDate = flightDeptimePlanDate;
    }

    public String getFlightArrtimePlanDate() {
        return flightArrtimePlanDate;
    }

    public void setFlightArrtimePlanDate(String flightArrtimePlanDate) {
        this.flightArrtimePlanDate = flightArrtimePlanDate;
    }

    public String getFlightDeptimeDate() {
        return flightDeptimeDate;
    }

    public void setFlightDeptimeDate(String flightDeptimeDate) {
        this.flightDeptimeDate = flightDeptimeDate;
    }

    public String getFlightArrtimeDate() {
        return flightArrtimeDate;
    }

    public void setFlightArrtimeDate(String flightArrtimeDate) {
        this.flightArrtimeDate = flightArrtimeDate;
    }

    public String getFlightState() {
        return flightState;
    }

    public void setFlightState(String flightState) {
        this.flightState = flightState;
    }

    public String getShareFlightNo() {
        return shareFlightNo;
    }

    public void setShareFlightNo(String shareFlightNo) {
        this.shareFlightNo = shareFlightNo;
    }

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(String shareFlag) {
        this.shareFlag = shareFlag;
    }

    public String getVirtualFlag() {
        return virtualFlag;
    }

    public void setVirtualFlag(String virtualFlag) {
        this.virtualFlag = virtualFlag;
    }

    public String getLegFlag() {
        return legFlag;
    }

    public void setLegFlag(String legFlag) {
        this.legFlag = legFlag;
    }

    public String getFlightDep() {
        return flightDep;
    }

    public void setFlightDep(String flightDep) {
        this.flightDep = flightDep;
    }

    public String getFlightArr() {
        return flightArr;
    }

    public void setFlightArr(String flightArr) {
        this.flightArr = flightArr;
    }

    public String getFlightDepAirport() {
        return flightDepAirport;
    }

    public void setFlightDepAirport(String flightDepAirport) {
        this.flightDepAirport = flightDepAirport;
    }

    public String getFlightArrAirport() {
        return flightArrAirport;
    }

    public void setFlightArrAirport(String flightArrAirport) {
        this.flightArrAirport = flightArrAirport;
    }
}
