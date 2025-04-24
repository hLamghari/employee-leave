package fr.milleis.test.backend.mapper;

import fr.milleis.test.backend.model.dto.AccountDTO;
import fr.milleis.test.backend.model.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    public AccountDTO toDto(Account account) {
        if (account == null) return null;
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setNom(account.getNom());
        dto.setIban(account.getIban());
        dto.setType(account.getType());
        dto.setMontant(account.getMontant());
        dto.setTauxInteret(account.getTauxInteret());
        return dto;
    }

    public List<AccountDTO> toDtoList(List<Account> accounts) {
        return accounts.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Account toEntity(AccountDTO dto) {
        if (dto == null) return null;
        Account account = new Account();
        account.setNom(dto.getNom());
        account.setIban(dto.getIban());
        account.setType(dto.getType());
        account.setMontant(dto.getMontant());
        account.setTauxInteret(dto.getTauxInteret());
        return account;
    }
}