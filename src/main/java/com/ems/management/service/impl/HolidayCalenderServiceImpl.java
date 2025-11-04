package com.ems.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.management.models.HolidayCalender;
import com.ems.management.repository.HolidayCalenderRepository;
import com.ems.management.service.HolidayCalenderService;

import jakarta.transaction.Transactional;


@Service
public class HolidayCalenderServiceImpl implements HolidayCalenderService{
	@Autowired
    private HolidayCalenderRepository holidayRepository;

	@Transactional
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

    @Transactional
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

    @Transactional
    @Override
    public void deleteHoliday(Long id) {
        holidayRepository.deleteById(id);
    }
}