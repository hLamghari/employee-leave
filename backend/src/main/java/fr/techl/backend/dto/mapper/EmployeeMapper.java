package fr.techl.backend.dto.mapper;

import fr.techl.backend.dto.response.EmployeeResponse;
import fr.techl.backend.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "lastName", target = "nom"),
            @Mapping(source = "firstName", target = "prenom"),
            @Mapping(source = "category", target = "categorie"),
            @Mapping(source = "dateHiring", target = "dateEmbauche", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "saleLeave", target = "soldeConges"),
            @Mapping(source = "saleRTT", target = "soldeRTT")
    })
    EmployeeResponse toExecutiveEmployee(Employee employee);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "lastName", target = "nom"),
            @Mapping(source = "firstName", target = "prenom"),
            @Mapping(source = "category", target = "categorie"),
            @Mapping(source = "dateHiring", target = "dateEmbauche", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "saleLeave", target = "soldeConges")
    })
    EmployeeResponse toNonExecutiveEmployee(Employee employee);
}
