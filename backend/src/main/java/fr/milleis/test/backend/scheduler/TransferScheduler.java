package fr.milleis.test.backend.scheduler;

import fr.milleis.test.backend.model.entity.Transfer;
import fr.milleis.test.backend.model.enums.TransfertStatus;
import fr.milleis.test.backend.repository.TransferRepository;
import fr.milleis.test.backend.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransferScheduler {

    private final TransferRepository transferRepository;
    private final TransferService transferService;

    @Scheduled(cron = "0 0 0 * * ?") // Every day at midnight to execute pending transfer
    public void processPendingTransfers() {
        LocalDate today = LocalDate.now();
        List<Transfer> pendingTransfers = transferRepository.findByStatusAndExecutionDateLessThanEqual(TransfertStatus.PENDING, today);

        for (Transfer transfer : pendingTransfers) {
            try {
                transferService.executeScheduledTransfer(transfer);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        }
    }
}