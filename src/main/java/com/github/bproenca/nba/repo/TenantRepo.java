package com.github.bproenca.nba.repo;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.bproenca.nba.api.HostInfoController;
import com.github.bproenca.nba.db.MultitenantDataSource;

@Repository
public class TenantRepo {

    private static Logger log = LoggerFactory.getLogger(TenantRepo.class);

    @Autowired
    @Qualifier("multitenantDataSource")
    private MultitenantDataSource multitenantDataSource;

    public List<Map<String, Object>> getData(String tenant) {
        log.info(">> Repo JdcbTemplate using DS tenant: {}", tenant);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(multitenantDataSource.getDataSource(tenant));
        String query = "select id, info_ci, TO_CHAR(ins_date, 'DD/MM/YYYY HH24:MI:SS') ins_date from ATABLE order by id desc fetch first 5 rows only";
        return jdbcTemplate.queryForList(query);
	}

    public void insertData(String tenant) {
        log.info("# Insert data {} in tenant {}", LocalTime.now(), tenant);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(multitenantDataSource.getDataSource(tenant));
        jdbcTemplate.update("insert into ATABLE (INS_DATE, INFO_CI) values (sysdate, ?)", HostInfoController.getHostInfo());
    }

    public List<Map<String, Object>> getNLS(String tenant) {
        log.info(">> Repo JdcbTemplate using DS tenant: {}", tenant);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(multitenantDataSource.getDataSource(tenant));
        return jdbcTemplate.queryForList("SELECT * from NLS_SESSION_PARAMETERS order by 1");
    }
    
}
