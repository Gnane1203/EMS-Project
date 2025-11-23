package com.ems.management.mappers;



import com.ems.management.dto.response.TimesheetResDto;
import com.ems.management.models.TimeSheetRequest;

public class TimesheetMapper {

    public static TimesheetResDto toDto(TimeSheetRequest entity) {
        if (entity == null) {
            return null;
        }

        return new TimesheetResDto(
            entity.getTimesheetId(),
            entity.getEmployee() != null ? entity.getEmployee().getEmpId() : null,
            entity.getProject() != null ? entity.getProject().getProjectId() : null,
            entity.getDate(),
            entity.getHoursWorked(),
            entity.getStatus(),
            entity.getApprover() != null ? entity.getApprover().getEmpId() : null,
            entity.getApproverDate(),
            entity.getMgrComments(),
            entity.getWorkingStatus(),
            entity.getEmpComments(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
