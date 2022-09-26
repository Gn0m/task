package com.example.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "maxNumber", value = "/max-number")
public class CalcServlet extends HttpServlet {

    private Integer[] integers = new Integer[3];
    private StringBuilder stringBuilder;
    private String operation;
    private int max,min;
    private double avg;


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        parse(request);

        switch (operation){
            case "max": max = maxNumber(integers);
            break;
            case "min": min = minNumber(integers);
            break;
            case "avg": avg = avg(integers);
        }

        showResult(operation,writer);

    }

    public void destroy() {
    }

    private int maxNumber(Integer[] integers) {
        int max = 0;
        for (int i = 0; i < integers.length; i++) {
            max = Math.max(max, integers[i]);
        }
        return max;
    }

    private int minNumber(Integer[] integers){
        int min = integers[0];
        for (int i = 1; i < integers.length; i++) {
            min = Math.min(min, integers[i]);
        }
        return min;
    }

    private double avg(Integer[] integers){
        int avg = 0;
        for (Integer value: integers) {
            avg += value;
        }
        return avg/integers.length;
    }

    private void parse(HttpServletRequest request) {
        for (int i = 0; i < 3; i++) {
            stringBuilder = new StringBuilder("number_");
            stringBuilder.append(i + 1);
            integers[i] = Integer.valueOf(request.getParameter(stringBuilder.toString()));
        }

        operation = request.getParameter("operation");
    }

    private void showResult(String operation, PrintWriter writer){

        try {
            for (int i = 0; i < integers.length; i++) {
                stringBuilder = new StringBuilder("number_");
                writer.println("<p>" + stringBuilder.append(i + 1) + ": " + integers[i] + "</p>");
            }

            switch (operation){
                case "max": writer.println("<p><b>Max Number:<b> " + max + "</p>");
                break;
                case "min": writer.println("<p><b>Min Number:<b> " + min + "</p>");
                break;
                case "avg": writer.println("<p><b>AVG Number:<b> " + avg + "</p>");
                break;
            }
        } finally {
            writer.close();
        }
    }
}
