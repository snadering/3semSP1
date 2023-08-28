package dat;

import model.Driver;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        BigDecimal salary = new BigDecimal("45000.00");
        Driver d1 = new Driver("Sander", "Roust", salary);
        System.out.println(d1.generateId());
    }
}