package com.aeon.processor;

import com.aeon.model.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapper implements org.springframework.jdbc.core.RowMapper<Report> {
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report report = new Report();

        report.setId(rs.getInt("id"));
        report.setAdep(rs.getString("dep_apt_code_iata"));
        report.setAdes(rs.getString("arr_apt_code_iata"));
        report.setFlightCode(rs.getString("flight_icao_code"));
        report.setFlightNumber(rs.getString("flight_number"));
        report.setCarrierCode(rs.getString("carrier_icao_code"));
        report.setCarrierNumber(rs.getString("carrier_number"));
        report.setStatusInfo(rs.getString("status_info"));
        //report.setSchdDepOnlyDateLt(rs.getString("schd_dep_only_date_lt"));
        //report.setSchdDepOnlyTimeLt(rs.getString("schd_dep_only_time_lt"));

        return report;
    }
}
