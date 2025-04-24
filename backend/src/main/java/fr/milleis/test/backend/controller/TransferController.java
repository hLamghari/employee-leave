package fr.milleis.test.backend.controller;


import fr.milleis.test.backend.model.dto.TransferRequestDTO;
import fr.milleis.test.backend.model.dto.TransferResponseDTO;
import fr.milleis.test.backend.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Transfer", description = "Transfer management APIs")
public class TransferController {

    private final TransferService transferService;

    @Operation(
            summary = "Make a transfer",
            description = "Performs a one-time or recurring transfer between two accounts"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer completed successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<TransferResponseDTO> makeTransfer(@RequestBody TransferRequestDTO request) {
        log.info("Transfer request received: {}", request);
        TransferResponseDTO response = transferService.makeTransfer(request);
        return ResponseEntity.ok(response);
    }
}