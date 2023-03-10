package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.AlarmHistory;

@Repository
public interface AlarmHistoryRepository extends JpaRepository<AlarmHistory, String>{

	@Query(value="select count(*)\r\n"
			+ "  from tbl_alarm_info tai, tbl_alarm_history tah\r\n"
			+ " where tah.ALARM_ID = tai.ALARM_ID\r\n"
			+ "  and tai.MASTER_ID = ?1\r\n"
			+ "  and tai.SLAVE_ID =?2\r\n"
			+ "  and DATE_FORMAT(tah.SAVE_DATE, '%Y%m') like ?3%", nativeQuery=true)
	long countAlarm(String string, String pointId, String statDateM);

}
