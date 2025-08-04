package bank.integration;

import bank.dao.TraceRecordRepository;
import bank.domain.TraceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableAsync
public class AccountChangeListener {
    @Autowired
    private TraceRecordRepository traceRecordRepository;

    @Async
    @EventListener
    public void handleEvent (AccountChangeEvent event) {
        System.out.println("[EMAIL] Account: " + event.getAccountNumber() +
                ", Operation: " + event.getOperation() +
                ", Amount: " + event.getAmount());


        TraceRecord record = new TraceRecord();
        record.setAccountNumber(event.getAccountNumber());
        record.setOperation(event.getOperation());
        record.setAmount(event.getAmount());
        record.setDateTime(LocalDateTime.now());

        traceRecordRepository.save(record);

    }
}
