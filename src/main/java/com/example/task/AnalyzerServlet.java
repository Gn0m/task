package com.example.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Character.toLowerCase;

@WebServlet(name = "analyzer", value = "/analyzer")
public class AnalyzerServlet extends HttpServlet {

    private String string;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        string = req.getParameter("string");


        int countVowels = checkCountVowels(string);
        int countConsonants = checkCountConsonants(string);
        int countMarks = checkCountMarks(string);
        try {
            showVowels(writer, countVowels);
            showConsonants(writer, countConsonants);
            showPunctuationMarks(writer, countMarks);
        } finally {
            writer.close();
        }

    }

    private void showPunctuationMarks(PrintWriter writer, int countMarks) {
        StringBuilder stringBuilder = new StringBuilder("");
        writer.println("<p>CountMarks: " + countMarks + "</p>");
        writer.println("<p>List chars: " + "</p>");
        for (char c : checkMarkList(string)) {
            stringBuilder.append(c).append(" ");
        }

        writer.println("<p> " + stringBuilder + "</p>");
    }

    private int checkCountMarks(String string) {
        int count = 0;
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (checkMarks(c)) {
                count++;
            }
        }
        return count;
    }

    private boolean checkMarks(char c) {
        return "!,:;?.-".indexOf(toLowerCase(c)) > -1;
    }

    private boolean checkENGVowels(char c) {
        return "aeiouy".indexOf(toLowerCase(c)) > -1;
    }

    private boolean checkRUSVowels(char c) {
        return "ауоиэы".indexOf(toLowerCase(c)) > -1;
    }

    private boolean checkENGConsonants(char c) {
        return "pbkfvmzhtdln".indexOf(toLowerCase(c)) > -1;
    }

    private boolean checkRUSConsonants(char c) {
        return "БВГДЖЗЙКЛМНПРСТФХЦЧШЩ".indexOf(toLowerCase(c)) > -1;
    }

    private int checkCountVowels(String string) {
        int count = 0;
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (checkENGVowels(c)) {
                count++;
            }
            if (checkRUSVowels(c)) {
                count++;
            }
        }
        return count;
    }

    private char[] checkVowelsENGList(String string) {
        char[] chars = new char[string.length()];
        int inc = 0;
        for (char c : string.toCharArray()) {
            if (checkENGVowels(c)) {
                chars[inc] = c;
                inc++;
            }
            if (checkRUSVowels(c)) {
                chars[inc] = c;
                inc++;
            }
        }
        return chars;
    }

    private char[] checkMarkList(String string) {
        char[] chars = new char[string.length()];
        int inc = 0;
        for (char c : string.toCharArray()) {
            if (checkMarks(c)) {
                chars[inc] = c;
                inc++;
            }
        }
        return chars;
    }

    private void showVowels(PrintWriter writer, int countVowels) {

        StringBuilder stringBuilder = new StringBuilder("");
        writer.println("<p>CountVowels: " + countVowels + "</p>");
        writer.println("<p>List chars: " + "</p>");
        for (char c : checkVowelsENGList(string)) {
            stringBuilder.append(c).append(" ");
        }

        writer.println("<p> " + stringBuilder + "</p>");

    }

    private void showConsonants(PrintWriter writer, int countConsonants) {

        StringBuilder stringBuilder = new StringBuilder("");
        writer.println("<p>CountConsonants: " + countConsonants + "</p>");
        writer.println("<p>List chars: " + "</p>");
        for (char c : checkConsonantsENGList(string)) {
            stringBuilder.append(c).append(" ");
        }

        writer.println("<p> " + stringBuilder + "</p>");

    }

    private int checkCountConsonants(String string) {
        int count = 0;
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (checkRUSConsonants(c)) {
                count++;
            }
            if (checkENGConsonants(c)) {
                count++;
            }
        }
        return count;
    }

    private char[] checkConsonantsENGList(String string) {
        char[] chars = new char[string.length()];
        int inc = 0;
        for (char c : string.toCharArray()) {
            if (checkENGConsonants(c)) {
                chars[inc] = c;
                inc++;
            }
            if (checkRUSConsonants(c)) {
                chars[inc] = c;
                inc++;
            }
        }
        return chars;
    }
}
