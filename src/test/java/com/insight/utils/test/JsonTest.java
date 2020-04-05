package com.insight.utils.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insight.utils.Json;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class JsonTest {

    @Test
    public void testJsonUtils() {

        List<ExcelClass> lists = new ArrayList<>();
        ExcelClass e1 = new ExcelClass();
        e1.setId("a");
        e1.setName("testa");
        lists.add(e1);

        ExcelClass e2 = new ExcelClass();
        e2.setId("");
        e2.setName("testb");
        lists.add(e2);

        ExcelClass e3 = new ExcelClass();
        e3.setId("c");
        e3.setName("testc");
        lists.add(e3);

        String json = Json.toJson(lists);
        System.out.println(json);

        //
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id", "testa");
        linkedHashMap.put("name", "testaname");
        List<LinkedHashMap<String, String>> ll1 = new ArrayList<>();
        ll1.add(linkedHashMap);

//        List<ExcelClass> listll1 = Json.toBean(ll1, ExcelClass.class);
//        for (ExcelClass ec : listll1) {
//            System.out.println(ec.getName());
//        }

        List<ExcelClass> listll2 = Json.toList(json, ExcelClass.class);
        for (ExcelClass ec : listll2) {
            System.out.println(ec);
        }

//        JavaType jt = Json.getJavaType(List.class, ExcelClass.class);
//        List<ExcelClass> list2 = Json.toBean(json, jt);
//        System.out.println(list2);


        String json2 = "{\"adultPrice\":\"1\",\"airWayId\":\"b45f232c9dbc40e1b7ceaa479d8cfa1c\",\"alertAdvance\":\"1\",\"alertRate\":\"1\",\"childPrice\":\"1\",\"flightType\":\"2\",\"freeBag\":\"1\",\"manager\":\"财务005\",\"managerId\":\"02ee0373076a4a9897ffd1d7b14d9959\",\"msdAirlineInfoList\":\"[{\\\"datePeriod\\\":\\\"2017-09-26/2017-10-17\\\",\\\"arrAirportCode\\\":\\\"SZX\\\",\\\"flightNo\\\":\\\"MF8309\\\",\\\"datesByWeek\\\":\\\"2017-10-01,2017-10-08,2017-10-15,2017-10-02,2017-10-09,2017-10-16,2017-09-26,2017-10-03,2017-10-10,2017-10-17,2017-09-27,2017-10-04,2017-10-11,2017-09-28,2017-10-05,2017-10-12,2017-09-29,2017-10-06,2017-10-13,2017-09-30,2017-10-07,2017-10-14\\\",\\\"weekFlights\\\":\\\"1,1,1,1,1,1,1\\\",\\\"days\\\":\\\"9\\\"},{\\\"datePeriod\\\":\\\"2017-10-04/2017-10-25\\\",\\\"arrAirportCode\\\":\\\"SZX\\\",\\\"flightNo\\\":\\\"MF8309\\\",\\\"datesByWeek\\\":\\\"2017-10-04,2017-10-05,2017-10-06,2017-10-07,2017-10-08,2017-10-09,2017-10-10,2017-10-11,2017-10-12,2017-10-13,2017-10-14,2017-10-15,2017-10-16,2017-10-17,2017-10-18,2017-10-19,2017-10-20,2017-10-21,2017-10-22,2017-10-23,2017-10-24,2017-10-25\\\",\\\"weekFlights\\\":\\\"1,1,1,1,1,1,1\\\",\\\"days\\\":\\\"9\\\"}]\",\"seatCount\":\"1\",\"seatType\":\"2\",\"ticketAdvance\":\"1\",\"weightLimit\":\"1\",\"recoveryAdvance\":\"1\",\"resType\":\"1\",\"d\":\"null\"}";
        ObjectMapper mapper = new ObjectMapper();
//        JavaType jt2 = mapper.getTypeFactory().constructParametricType(AirlineBean.class, List.class, FlightDetail.class);
//        AirlineBean airline = Json.toBean(json2, jt2);
//        AirlineBean airline = Json.toBean(json2, AirlineBean.class);
//        System.out.println(airline);
//

//        String result = "[{\"fcategory\":\"1\",\"org_timezone\":\"28800\",\"dst_timezone\":\"25200\",\"FlightNo\":\"8L9691\",\"FlightCompany\":\"\\u4e91\\u5357\\u7965\\u9e4f\\u822a\\u7a7a\\u6709\\u9650\\u8d23\\u4efb\\u516c\\u53f8\",\"FlightDepcode\":\"CGO\",\"FlightArrcode\":\"HKT\",\"FlightDeptimePlanDate\":\"2017-10-18 11:50:00\",\"FlightArrtimePlanDate\":\"2017-10-18 15:50:00\",\"FlightDeptimeDate\":\"\",\"FlightArrtimeDate\":\"\",\"FlightState\":\"\\u8ba1\\u5212\",\"ShareFlightNo\":\"\",\"StopFlag\":\"0\",\"ShareFlag\":\"0\",\"VirtualFlag\":\"\",\"LegFlag\":\"0\",\"FlightDep\":\"\\u90d1\\u5dde\",\"FlightArr\":\"\\u666e\\u5409\\u5c9b\",\"FlightDepAirport\":\"\\u90d1\\u5dde\\u65b0\\u90d1\",\"FlightArrAirport\":\"\\u666e\\u5409\"}]";
//        JavaType jtx = Json.getJavaType(List.class, VariFlight.class);
//        List<VariFlight> lsv = Json.toBean(result, jtx);
//        System.out.println("VariFlight------------------" + lsv);


        String bodyStr = "";
        System.out.println(bodyStr);
        Json.toMap(bodyStr);
    }
}
