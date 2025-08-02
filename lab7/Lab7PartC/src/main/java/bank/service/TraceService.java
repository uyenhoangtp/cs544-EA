package bank.service;

import bank.domain.TraceRecord;
import bank.repositories.TraceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraceService {
    @Autowired
    TraceRecordRepository traceRecordRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void traceSuccess(String customerName, String accountNumber) {
        String message = "Customer " + customerName + " created with account " + accountNumber;
        traceRecordRepository.save(new TraceRecord(message));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void traceFailure(String customerName, String accountNumber) {
        String message = "Could not create customer " + customerName + " with account " + accountNumber;
        traceRecordRepository.save(new TraceRecord(message));
    }
}
