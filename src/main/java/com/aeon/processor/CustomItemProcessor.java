package com.aeon.processor;

import org.springframework.batch.item.ItemProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.aeon.model.Report;
import com.aeon.model.Source;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomItemProcessor implements ItemProcessor<Report, Report> {
    @Override
    public Report process(Report item) throws Exception {
        Log log = LogFactory.getLog(CustomItemProcessor.class);

        log.info("***** Processing: " + item);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY hh:mm");

        try {
            Date schdDepTS = dateFormat.parse(item.getSchdDepOnlyDateLt() + " " + item.getSchdDepOnlyTimeLt());
            Date schdArrTS = dateFormat.parse(item.getSchdArrOnlyDateLt() + " " + item.getSchdArrOnlyTimeLt());
            Date estDepTS = dateFormat.parse(item.getEstDepDateTimeLt());
            Date estArrTS = dateFormat.parse(item.getEstArrDateTimeLt());
            Date actDepTS = dateFormat.parse(item.getActDepDateTimeLt());
            Date actArrTS = dateFormat.parse(item.getActArrDateTimeLt());

            item.setSchdDepLt(new Timestamp(schdDepTS.getTime()));
            item.setSchdArrLt(new Timestamp(schdArrTS.getTime()));
            item.setEstDepLt(new Timestamp(estDepTS.getTime()));
            item.setEstArrLt(new Timestamp(estArrTS.getTime()));
            item.setActDepLt(new Timestamp(actDepTS.getTime()));
            item.setActArrLt(new Timestamp(actArrTS.getTime()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        /*
        Report report = new Report();
        report.setId(item.getId());
        report.setAdep(item.getAdep());

        report.setFlightCode(rs.getString("flight_icao_code"));
        report.setFlightNumber(rs.getString("flight_number"));
        report.setCarrierCode(rs.getString("carrier_icao_code"));
        report.setCarrierNumber(rs.getString("carrier_number"));
        report.setStatusInfo(rs.getString("status_info"));
        report.setSchdDepOnlyDateLt(rs.getString("schd_dep_only_date_lt"));
        report.setSchdDepOnlyTimeLt(rs.getString("schd_dep_only_time_lt"));

        */



        return item;
    }
}
