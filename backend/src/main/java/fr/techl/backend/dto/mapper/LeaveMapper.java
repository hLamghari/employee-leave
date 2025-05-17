package fr.techl.backend.dto.mapper;

import fr.techl.backend.dto.response.LeaveResponse;
import fr.techl.backend.entities.Leave;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "leaveType", target = "leaveType")
//    @Mapping(source = "", target = "")
//    @Mapping(source = "", target = "")
    LeaveResponse toLeaveDto(Leave leave);
}
