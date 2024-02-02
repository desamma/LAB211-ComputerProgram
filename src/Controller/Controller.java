package Controller;

import Common.Library;
import Model.Calculator.BMI;
import Model.Calculator.Operator;
import View.Menu;

public class Controller extends Menu<String> {

    private static String[] mc = {"Normal Calculator", "BMI Calculator", "Exit"};
    private Library lib;
    
    public Controller() {
        super("Calculator Program", mc);
        lib = new Library();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                normal();
                break;
            case 2:
                bmi();
                break;
            case 3:
                System.exit(0);

        }
    }

    public void normal(){
        double mem = 0;
        double a = lib.getDouble("Enter number: ");
        String op = lib.getString("Enter Operator: ");
        double b = lib.getDouble("Enter number: ");
        Operator operator = checkOperator(op);
        while (!op.equals("=")) {
            try {
                if (operator == null) throw new NullPointerException("Please input (+, -, *, /, ^)");

                switch (operator){
                    case ADD:
//                        System.out.println(mem);
                        mem = a + b;
                        System.out.println("Memory: " + mem);
                        break;
                    case SUBTRACT:
                        mem = a - b;
                        System.out.println("Memory: " + mem);
                        break;
                    case MULTIPLY:
                        mem = a * b;
                        System.out.println("Memory: " + mem);
                        break;
                    case DIVIDE:
                        if (b == 0) throw new ArithmeticException("Cannot divide to 0");
                        mem = a / b;
                        System.out.println("Memory: " + mem);
                        break;
                    case POWER:
                        mem = Math.pow(a, b);
                        System.out.println("Memory: " + mem);
                        break;
                }                 
            } catch (ArithmeticException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
            a = mem;
            op = lib.getString("Enter Operator: ");
            operator = checkOperator(op);
            if (!op.equals("=")) b = lib.getDouble("Enter number: ");
        } 
        System.out.println("Result: " + mem);
    }
    
    public Operator checkOperator(String op){
        switch(op){
            case "+":
                return Operator.ADD;
            case "-":
                return Operator.SUBTRACT;
            case "*":
                return Operator.MULTIPLY;
            case "/":
                return Operator.DIVIDE;
            case "^":
                return Operator.POWER;
            default:
                return null;
        }
    }
    
    public void bmi(){
        double w = lib.getDouble("Enter Weight(kg): ");
        double h = lib.getDouble("Enter Height(cm): ");
        try {
            if (w <= 0 || h <= 0) throw new IllegalArgumentException("Weight and height must be positive");
            double value = w / Math.pow(h / 100, 2);
            if (value < 18.5) {
                System.out.println(value);
                System.out.println("BMI status: " + BMI.UNDERWEIGHT);
            } else if (value < 24.9) {
                System.out.println(value);
                System.out.println("BMI status: " + BMI.NORMAL);
            } else if (value < 29.9) {
                System.out.println(value);
                System.out.println("BMI status: " + BMI.OVERWEIGHT);
            } else {
                System.out.println(value);
                System.out.println("BMI status: " + BMI.OBESE);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
}