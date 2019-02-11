package com.aeon.configuration;

import com.aeon.model.Report;
import com.aeon.processor.CustomItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job sourceFlightJob() {
        return jobs.get("sourceFlightJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return steps.get("step1")
                .<Report, Report>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemStreamReader<Report> reader() {
        // HibernateCursorItemReader<Report> reader = new HibernateCursorItemReader<Report>();
        JdbcCursorItemReader<Report> reader = new JdbcCursorItemReader<Report>();
        // JdbcPagingItemReader<Report> reader = new JdbcPagingItemReader<Report>();
        // reader.setQueryString("from Source");
        // reader.setSessionFactory(sessionFactory());
        reader.setDataSource(dataSource);
        reader.setSql("SELECT\n" +
                "       t1.id,\n" +
                "       t1.act_arr_date_time_lt,\n" +
                "       t1.aircraft_name_scheduled,\n" +
                "       t1.arr_apt_name_es,\n" +
                "       t1.arr_apt_code_iata,\n" +
                "       t2.baggage_info,\n" +
                "       t1.carrier_airline_name_en,\n" +
                "       t1.carrier_icao_code,\n" +
                "       t1.carrier_number,\n" +
                "       t2.counter,\n" +
                "       t1.dep_apt_name_es,\n" +
                "       t1.dep_apt_code_iata,\n" +
                "       t1.est_arr_date_time_lt,\n" +
                "       t1.est_dep_date_time_lt,\n" +
                "       t1.flight_airline_name_en,\n" +
                "       t1.flight_airline_name,\n" +
                "       t1.flight_icao_code,\n" +
                "       t1.flight_number,\n" +
                "       t1.flt_leg_seq_no,\n" +
                "       t2.gate_info,\n" +
                "       t2.lounge_info,\n" +
                "       t1.schd_arr_only_date_lt,\n" +
                "       t1.schd_arr_only_time_lt,\n" +
                "       t1.source_data,\n" +
                "       t1.status_info,\n" +
                "       t2.terminal_info,\n" +
                "       t2.arr_terminal_info,\n" +
                "       to_timestamp(t1.created_at) AS created_at,\n" +
                "       t1.act_dep_date_time_lt,\n" +
                "       t1.schd_dep_only_date_lt,\n" +
                "       t1.schd_dep_only_time_lt\n" +
                "\n" +
                "FROM aenaflight_2017_01 AS t1\n" +
                "JOIN (\n" +
                "  SELECT\n" +
                "         flight_number,\n" +
                "         array_to_string(array_agg(DISTINCT baggage_info ORDER BY baggage_info DESC), ',') baggage_info,\n" +
                "         array_to_string(array_agg(DISTINCT counter ORDER BY counter DESC), ',') counter,\n" +
                "         array_to_string(array_agg(DISTINCT gate_info ORDER BY gate_info DESC), ',') gate_info,\n" +
                "         array_to_string(array_agg(DISTINCT lounge_info ORDER BY lounge_info DESC), ',') lounge_info,\n" +
                "         array_to_string(array_agg(DISTINCT terminal_info ORDER BY terminal_info DESC), ',') terminal_info,\n" +
                "         array_to_string(array_agg(DISTINCT arr_terminal_info ORDER BY arr_terminal_info), ',') arr_terminal_info,\n" +
                "         MAX(created_at) created_at\n" +
                "  FROM aenaflight_2017_01\n" +
                "  GROUP BY flight_number\n" +
                "  ) t2\n" +
                "ON\n" +
                "  t1.flight_number = t2.flight_number AND t1.created_at = t2.created_at");
        reader.setRowMapper((rs, rowNum) -> {
            Report report = new Report();

            report.setId(rs.getInt("id"));
            report.setAdep(rs.getString("dep_apt_code_iata"));
            report.setAdes(rs.getString("arr_apt_code_iata"));
            report.setFlightCode(rs.getString("flight_icao_code"));
            report.setFlightNumber(rs.getString("flight_number"));
            report.setCarrierCode(rs.getString("carrier_icao_code"));
            report.setCarrierNumber(rs.getString("carrier_number"));
            report.setStatusInfo(rs.getString("status_info"));
            report.setFltLegSeqNo(rs.getInt("flt_leg_seq_no"));

            report.setActArrDateTimeLt(rs.getString("act_arr_date_time_lt"));
            report.setEstArrDateTimeLt(rs.getString("est_arr_date_time_lt"));
            report.setEstDepDateTimeLt(rs.getString("est_dep_date_time_lt"));
            report.setSchdArrOnlyDateLt(rs.getString("schd_arr_only_date_lt"));
            report.setSchdArrOnlyTimeLt(rs.getString("schd_arr_only_time_lt"));
            report.setActDepDateTimeLt(rs.getString("act_dep_date_time_lt"));
            report.setSchdDepOnlyDateLt(rs.getString("schd_dep_only_date_lt"));
            report.setSchdDepOnlyTimeLt(rs.getString("schd_dep_only_time_lt"));

            report.setCreatedAt(rs.getTimestamp("created_at"));

            return report;
        });

        return reader;
    }

    @Bean
    public ItemProcessor<Report, Report> processor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<Report> writer() {
        JdbcBatchItemWriter<Report> writer = new JdbcBatchItemWriter<Report>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Report>());
        writer.setSql("INSERT INTO aenaflight_source (id, adep, ades, flight_code, flight_number, carrier_code, carrier_number, status_info, schd_dep_lt, schd_arr_lt, est_dep_lt, est_arr_lt, act_dep_lt, act_arr_lt, flt_leg_seq_no, created_at) VALUES (:id, :adep, :ades, :flightCode, :flightNumber, :carrierCode, :carrierNumber, :statusInfo,  :schdDepLt, :schdArrLt, :estDepLt, :estArrLt, :actDepLt, :actArrLt, :fltLegSeqNo, :createdAt)");
        writer.setDataSource(dataSource);
        return writer;
    }
}
