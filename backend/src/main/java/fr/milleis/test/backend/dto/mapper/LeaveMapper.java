package fr.milleis.test.backend.dto.mapper;

import fr.milleis.test.backend.dto.response.LeaveResponse;
import fr.milleis.test.backend.entities.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "leaveType", target = "leaveType")
//    @Mapping(source = "", target = "")
//    @Mapping(source = "", target = "")
    LeaveResponse toLeaveDto(Leave leave);
}
