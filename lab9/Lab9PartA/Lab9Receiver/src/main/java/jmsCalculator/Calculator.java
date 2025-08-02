package jmsCalculator;

public class Calculator {
    private String operator;
    private int value;

    public Calculator() {
    }

    public Calculator(String operator, int value) {
        this.operator = operator;
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "operator='" + operator + '\'' +
                ", value=" + value +
                '}';
    }
}
