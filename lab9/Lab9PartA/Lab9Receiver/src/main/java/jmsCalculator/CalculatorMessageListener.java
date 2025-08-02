package jmsCalculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CalculatorMessageListener {
    private int result = 0;

    @JmsListener(destination = "testTopic")
    public void calculateReceivedMessage(final String calculatorAsString){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Calculator calculatorReceiver = objectMapper.readValue(calculatorAsString, Calculator.class);
            switch (calculatorReceiver.getOperator()){
                case "+" -> result += calculatorReceiver.getValue();
                case "-" -> result -= calculatorReceiver.getValue();
                case "*" -> result *= calculatorReceiver.getValue();
                default -> System.out.println("Unknown operator: " + calculatorReceiver.getOperator());
            }
            System.out.println("JMS Calculator receiver: calculate received message:" + result);

        } catch (IOException e) {
            System.out.println("JMS Calculator receiver: Cannot convert : " + calculatorAsString+" to a Calculator object");
        }
    }
}
