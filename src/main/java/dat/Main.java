package dat;

import dao.DriverDAOImpl;
import model.Driver;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        DriverDAOImpl driverDAO = new DriverDAOImpl();
        Date oldDate = new Date(2021, 8, 25);


        BigDecimal salary = new BigDecimal("20000.00");
        BigDecimal newSalary = new BigDecimal("29000.00");
        Driver d1 = new Driver("Fido", "Ulriksen", salary);
        Driver d2 = new Driver("Bastian", "Holm", salary);
        Driver d3 = new Driver("Liss", "Udyr", newSalary);

        /*
        driverDAO.saveDriver(d1.getName(), d1.getSurname(), d1.getSalary());
        driverDAO.saveDriver(d2.getName(), d2.getSurname(), d2.getSalary());
        driverDAO.saveDriver(d3.getName(), d3.getSurname(), d3.getSalary());
         */
        List<Driver> drivers = driverDAO.findAllDriversEmployedAtTheSameYear("2021");
        List<Driver> salaryList = driverDAO.fetchAllDriversWithSalaryGreaterThan10000();
        List<String> namesOfDrivers = driverDAO.fetchFirstNameOfAllDrivers();
        Driver driverWithHighestSalary = driverDAO.fetchDriverWithHighestSalary();
        long amount = driverDAO.calculateNumberOfDrivers();
        double highestSalary = driverDAO.fetchHighestSalary();
        for (Driver driver : drivers) {
            System.out.println(driver.getId());
        }
        for (Driver driver : salaryList) {
            System.out.println();
            System.out.println("ID: " + driver.getId() + "\n" +
                                "Name: " + driver.getName() + " " + driver.getSurname() + "\n" +
                                    "Salary: " + driver.getSalary());
        }
        for (String s : namesOfDrivers) {
            System.out.println(s);
        }
        System.out.println("Amount of Drivers: " + amount);

        System.out.println("Highest Salary: " + highestSalary);

        System.out.println("This driver has the highest salary: [" + driverWithHighestSalary.getName() + " " + driverWithHighestSalary.getSurname() + "] earning $" + highestSalary);


    }

}