package bank.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class BankAdvice {

    @Before("execution(* bank.dao.*.*(..))")
    public void logDAOMethods(JoinPoint joinPoint) {
        System.out.println("DAO method called: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* bank.service.*.*(..))")
    public Object measureServiceTime(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object result = call.proceed();
        sw.stop();
        System.out.println("Service method [" + call.getSignature().getName() + "] took "
                + sw.getLastTaskTimeMillis() + "ms");
        return result;
    }

    @After("execution(* bank.jms.JMSSender.sendJMSMessage(..))")
    public void logJMSMessage(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("AOP: JMS message sent => " + args[0]);
    }
}
