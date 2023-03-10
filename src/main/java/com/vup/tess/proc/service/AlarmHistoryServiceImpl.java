package com.vup.tess.proc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vup.tess.repository.AlarmHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmHistoryServiceImpl implements AlarmHistoryService{
	@Autowired
	private AlarmHistoryRepository alarmHistoryRepository;
	
	@Transactional(readOnly = true)
	public int countAlarm(String factoryId, String pointId, String statDateM) {
		// TODO Auto-generated method stub
		return (int) alarmHistoryRepository.countAlarm(factoryId, pointId, statDateM);
	}

}
