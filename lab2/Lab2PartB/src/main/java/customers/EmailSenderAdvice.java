package customers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

@Aspect
@Component
public class EmailSenderAdvice {

    @After("execution(* customers.EmailSender.sendEmail(..))")
    public void logArgsFromJoinPoint(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String email = (String) args[0];
        String message = (String) args[1];
        EmailSender sender = (EmailSender) joinPoint.getTarget();
        System.out.println(LocalDateTime.now() + " method=" + joinPoint.getSignature().getName()
        + " address=" + email + " message=" + message + " outgoing mail server = " + sender.getOutgoingMailServer());
    }

    @Around("execution(* customers.EmailSender.sendEmail(..))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        // print the time to the console
        System.out.println("Execution time for method " + call.getSignature().getName() + ": " +
                sw.getLastTaskTimeMillis() + " ms");
        return retVal;
    }
}
