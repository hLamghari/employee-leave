package fr.milleis.test.backend.service;


import fr.milleis.test.backend.model.dto.TransferRequestDTO;
import fr.milleis.test.backend.model.dto.TransferResponseDTO;
import fr.milleis.test.backend.model.entity.Transfer;

public interface TransferService {
    TransferResponseDTO makeTransfer(TransferRequestDTO request);
    void executeScheduledTransfer(Transfer transfer);
}