package com.junior.university.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.junior.university.service.ConsoleService;

@Component
public class ConsoleController {

    private final ConsoleService consoleService;

    private static final int EXIT_CODE = 0;

    private static final int MAX_MENU_VALUE = 5;

    private static final int START_DELAY = 3000;

    private static final Scanner scanner = new Scanner(System.in);

    @Autowired
    public ConsoleController(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }


    @Scheduled(fixedRate = START_DELAY)
    public void start() {

        boolean running = true;

        while (running) {
            printMenu();
            running = isContinue();
        }
        System.exit(0);
    }

    private boolean isContinue() {

        int input = getMenuInput();

        if (input == EXIT_CODE) {
            return false;
        }

        System.out.println(getResult(input));
        return true;
    }

    private int getMenuInput() {
        try {
            int input = Integer.parseInt(scanner.nextLine().trim());
            return input <= MAX_MENU_VALUE ? input : getMenuInput();
        } catch (Exception e) {
            return getMenuInput();
        }
    }

    private String getValueInput() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            return getValueInput();
        }
    }

    private String getResult(final int input) {

        switch (input) {
            case 1: {
                printDepartmentRequest();
                return consoleService.whoIsHeadOfDepartment(getValueInput());
            }
            case 2: {
                printDepartmentRequest();
                return consoleService.showDepartmentStatistic(getValueInput());
            }
            case 3: {
                printDepartmentRequest();
                return consoleService.showAverageSalary(getValueInput());
            }
            case 4: {
                printDepartmentRequest();
                return consoleService.showCountOfEmployee(getValueInput());
            }
            case 5: {
                System.out.println();
                System.out.print("Search: ");
                return consoleService.globalSearch(getValueInput());
            }
        }

        return null;
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Who is head of department ?");
        System.out.println("2. Show department statistic.");
        System.out.println("3. Show the average salary of department.");
        System.out.println("4. Show count of employee for department.");
        System.out.println("5. Global employee search.");
        System.out.println("0. Exit.");
        System.out.print("Type here, please: ");
    }

    private void printDepartmentRequest() {
        System.out.println();
        System.out.print("Enter department name, please: ");
    }
}
