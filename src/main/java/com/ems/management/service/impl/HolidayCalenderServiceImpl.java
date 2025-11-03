package com.ems.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ems.management.models.HolidayCalender;
import com.ems.management.repository.HolidayCalenderRepository;
import com.ems.management.service.HolidayCalenderService;

public class HolidayCalenderServiceImpl implements HolidayCalenderService{
	@Autowired
    private HolidayCalenderRepository holidayRepository;

    @Override
    public HolidayCalender createHoliday(HolidayCalender holiday) {
        return holidayRepository.save(holiday);
    }

    @Override
    public List<HolidayCalender> getAllHolidays() {
        return holidayRepository.findAll();
    }

    @Override
    public HolidayCalender getHolidayById(Long id) {
        Optional<HolidayCalender> optionalHoliday = holidayRepository.findById(id);
        return optionalHoliday.orElse(null);
    }

    @Override
    public HolidayCalender updateHoliday(Long id, HolidayCalender holiday) {
        HolidayCalender existingHoliday = holidayRepository.findById(id).orElse(null);
        if (existingHoliday != null) {
            existingHoliday.setHolidayName(holiday.getHolidayName());
            existingHoliday.setHolidayDate(holiday.getHolidayDate());
            existingHoliday.setOptional(holiday.isOptional());
            existingHoliday.setUpdatedAt(holiday.getUpdatedAt());
            return holidayRepository.save(existingHoliday);
        }
        return null;
    }

    @Override
    public void deleteHoliday(Long id) {
        holidayRepository.deleteById(id);
    }
}