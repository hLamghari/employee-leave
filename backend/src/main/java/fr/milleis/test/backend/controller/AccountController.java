package fr.milleis.test.backend.controller;

import fr.milleis.test.backend.model.dto.AccountDTO;
import fr.milleis.test.backend.model.dto.GroupedAccountsDTO;
import fr.milleis.test.backend.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Account", description = "Account management APIs")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(
            summary = "Get all accounts by user ID",
            description = "Retrieve all accounts for a given user ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User or accounts not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/users/{userId}/grouped")
    public ResponseEntity<GroupedAccountsDTO> getAccountsByUserId(@PathVariable Long userId) {
        log.info("Received request to get accounts for userId: {}", userId);
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }

    @Operation(
            summary = "Get account by ID",
            description = "Retrieve account details for a given account ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        log.info("Received request to get account details for accountId: {}", accountId);
        AccountDTO dto = accountService.getAccountById(accountId);
        return ResponseEntity.ok(dto);
    }
}