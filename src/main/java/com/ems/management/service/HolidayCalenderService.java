package com.ems.management.service;

import java.util.List;

import com.ems.management.models.HolidayCalender;

public interface HolidayCalenderService {
	
	HolidayCalender createHoliday(HolidayCalender holiday);
    
    List<HolidayCalender> getAllHolidays();
    
    HolidayCalender getHolidayById(Long id);
    
    HolidayCalender updateHoliday(Long id, HolidayCalender holiday);
    
    void deleteHoliday(Long id);
}
