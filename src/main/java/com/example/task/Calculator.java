package com.example.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calculator", value = "/calculator")
public class Calculator extends HttpServlet {

    private StringBuilder stringBuilder;
    private Double[] arr = new Double[2];
    private String operation;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        parse(req, writer);

        switchOperation(operation, writer);
    }

    private void parse(HttpServletRequest request, PrintWriter writer) {
        for (int i = 0; i < 2; i++) {
            stringBuilder = new StringBuilder("number_");
            stringBuilder.append(i + 1);
            System.out.println(Double.valueOf(request.getParameter(stringBuilder.toString())));
            arr[i] = Double.valueOf(request.getParameter(stringBuilder.toString()));
        }

        operation = request.getParameter("operation");

    }

    private void showResult(PrintWriter writer, double result, String str) {
        String string = str;
        switch (str) {
            case "+":
                string = "Сложение: ";
                break;
            case "-":
                string = "Вычитание: ";
                break;
            case "*":
                string = "Умножение: ";
                break;
            case "/":
                string = "Деление: ";
                break;
            case "^":
                string = "Степень: ";
                break;
            case "%":
                string = "Проценты: ";
                break;
        }
        try {
            writer.println("<p><b>" + string + "<b>" + result + "</p>");
        } finally {
            writer.close();
        }
    }

    private void switchOperation(String operation, PrintWriter writer) {
        switch (operation) {
            case "+":
                sum(writer, operation);
                break;
            case "-":
                subtraction(writer, operation);
                break;
            case "*":
                multiplication(writer, operation);
                break;
            case "/":
                division(writer, operation);
                break;
            case "^":
                degree(writer, operation);
                break;
            case "%":
                percent(writer, operation);
                break;
        }
    }

    private void percent(PrintWriter writer, String operation) {
        double percent = (arr[0] * arr[1])/100;

        showResult(writer, percent, operation);
    }

    private void degree(PrintWriter writer, String operation) {
        double degree = Math.pow(arr[0], arr[1]);
        showResult(writer, degree, operation);
    }

    private void division(PrintWriter writer, String operation) {
        double division = arr[0];
        division /= arr[1];

        showResult(writer, division, operation);
    }

    private void multiplication(PrintWriter writer, String operation) {
        double multiplication = arr[0];
        multiplication *= arr[1];

        showResult(writer, multiplication, operation);
    }

    private void subtraction(PrintWriter writer, String operation) {
        double subtraction = arr[0];
        subtraction -= arr[1];

        showResult(writer, subtraction, operation);
    }

    private void sum(PrintWriter writer, String operation) {
        int sum = 0;
        for (Double d : arr) {
            sum += d;
        }
        showResult(writer, sum, operation);
    }
}
