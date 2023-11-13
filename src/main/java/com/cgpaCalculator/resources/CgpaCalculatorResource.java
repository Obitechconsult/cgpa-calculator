package com.cgpaCalculator.resources;

import com.codahale.metrics.annotation.Timed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.concurrent.atomic.AtomicLong;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class CgpaCalculatorResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public CgpaCalculatorResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public String getCGPA(
            @QueryParam("score1") @DefaultValue("-1") int score1,
            @QueryParam("unit1") @DefaultValue("0") int unit1,
            @QueryParam("score2") @DefaultValue("-1") int score2,
            @QueryParam("unit2") @DefaultValue("0") int unit2,
            @QueryParam("score3") @DefaultValue("-1") int score3,
            @QueryParam("unit3") @DefaultValue("0") int unit3,
            @QueryParam("score4") @DefaultValue("-1") int score4,
            @QueryParam("unit4") @DefaultValue("0") int unit4,
            @QueryParam("score5") @DefaultValue("-1") int score5,
            @QueryParam("unit5") @DefaultValue("0") int unit5,
            @QueryParam("score6") @DefaultValue("-1") int score6,
            @QueryParam("unit6") @DefaultValue("0") int unit6,
            @QueryParam("score7") @DefaultValue("-1") int score7,
            @QueryParam("unit7") @DefaultValue("0") int unit7
    ) {
        // If scores are not provided, render the form
        if (score1 == -1) {
            return renderForm();
        }

        // Calculate CGPA logic goes here
        double totalScore = (convertToPoints(score1) * unit1 + convertToPoints(score2) * unit2
                + convertToPoints(score3) * unit3 + convertToPoints(score4) * unit4
                + convertToPoints(score5) * unit5 + convertToPoints(score6) * unit6
                + convertToPoints(score7) * unit7);
        double totalUnits = unit1 + unit2 + unit3 + unit4 + unit5 + unit6 + unit7;
        double cgpa = totalScore / totalUnits;

        // Format the result as HTML
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "            background-color: #f5f5f5;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            height: 100vh;\n" +
                "        }\n" +
                "\n" +
                "        .result-container {\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "            padding: 20px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            color: #333;\n" +
                "        }\n" +
                "\n" +
                "        .result-box {\n" +
                "            background-color: #dff0d8;\n" +
                "            border: 1px solid #d6e9c6;\n" +
                "            color: #3c763d;\n" +
                "            padding: 15px;\n" +
                "            border-radius: 4px;\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "\n" +
                "        #cgpa-value {\n" +
                "            font-size: 24px;\n" +
                "            font-weight: bold;\n" +
                "            color: #4caf50;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <title>CGPA Result</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"result-container\">\n" +
                "        <h1>Your CGPA Result</h1>\n" +
                "        <div class=\"result-box\">\n" +
                "            <p>Your CGPA is: <span id=\"cgpa-value\">" + String.format("%.2f", cgpa) + "</span></p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";    }
// Render the HTML form
private String renderForm() {
    return "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <style>\n" +
            "        /* Overall form styling */\n" +
            "        .input-container {\n" +
            "            background-color: #ffffff;\n" +
            "            border-radius: 8px;\n" +
            "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
            "            padding: 20px;\n" +
            "            width: 80%;\n" +
            "            max-width: 400px;\n" +
            "            text-align: center;\n" +
            "            margin: 20px;\n" +
            "        }\n" +
            "\n" +
            "        /* Label styling */\n" +
            "        label {\n" +
            "            display: block;\n" +
            "            margin: 10px 0 5px;\n" +
            "            font-weight: bold;\n" +
            "            font-size: 16px;\n" +
            "        }\n" +
            "\n" +
            "        /* Input styling */\n" +
            "        input {\n" +
            "            width: 100%;\n" +
            "            padding: 8px;\n" +
            "            margin-bottom: 15px;\n" +
            "            box-sizing: border-box;\n" +
            "            border: 1px solid #ccc;\n" +
            "            border-radius: 4px;\n" +
            "        }\n" +
            "\n" +
            "        /* Button styling */\n" +
            "        button {\n" +
            "            background-color: #4caf50;\n" +
            "            color: white;\n" +
            "            padding: 10px 20px;\n" +
            "            border: none;\n" +
            "            border-radius: 4px;\n" +
            "            cursor: pointer;\n" +
            "            font-size: 16px;\n" +
            "        }\n" +
            "\n" +
            "        /* Media query for responsiveness */\n" +
            "        @media (max-width: 768px) {\n" +
            "            .input-container {\n" +
            "                width: 90%;\n" +
            "                max-width: none;\n" +
            "            }\n" +
            "        }\n" +
            "    </style>\n" +
            "    <title>CGPA Calculator</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"input-container\">\n" +
            "        <h1>Enter Your Scores and Units</h1>\n" +
            "        <form action=\"/\" method=\"get\">\n" +
            "            <label for=\"score1\">FSC 701:</label>\n" +
            "            <input type=\"number\" id=\"score1\" name=\"score1\" required>\n" +
            "            <label for=\"unit1\">Unit:</label>\n" +
            "            <input type=\"number\" id=\"unit1\" name=\"unit1\" value=\"2\" required>\n" +
            "            <label for=\"score2\">FSC 715:</label>\n" +
            "            <input type=\"number\" id=\"score2\" name=\"score2\" required>\n" +
            "            <label for=\"unit2\">Unit:</label>\n" +
            "            <input type=\"number\" id=\"unit2\" name=\"unit2\" value=\"2\" required>\n" +
            "            <label for=\"score3\">FSC 721:</label>\n" +
            "            <input type=\"number\" id=\"score3\" name=\"score3\" required>\n" +
            "            <label for=\"unit3\">Unit:</label>\n" +
            "            <input type=\"number\" id=\"unit3\" name=\"unit3\" value=\"2\" required>\n" +
            "            <label for=\"score4\">FSC 723:</label>\n" +
            "            <input type=\"number\" id=\"score4\" name=\"score4\" required>\n" +
            "            <label for=\"unit4\">Unit:</label>\n" +
            "            <input type=\"number\" id=\"unit4\" name=\"unit4\" value=\"2\" required>\n" +
            "            <label for=\"score5\">FSC 731:</label>\n" +
            "            <input type=\"number\" id=\"score5\" name=\"score5\" required>\n" +
            "            <label for=\"unit5\">Unit:</label>\n" +
            "            <input type=\"number\" id=\"unit5\" name=\"unit5\" value=\"2\" required>\n" +
            "            <label for=\"score6\">FSC 741:</label>\n" +
            "            <input type=\"number\" id=\"score6\" name=\"score6\" required>\n" +
            "            <label for=\"unit6\">Unit:</label>\n" +
            "            <input type=\"number\" id=\"unit6\" name=\"unit6\" value=\"2\" required>\n" +
            "            <label for=\"score7\">FSC 755:</label>\n" +
            "            <input type=\"number\" id=\"score7\" name=\"score7\" required>\n" +
            "            <label for=\"unit7\">Unit:</label>\n" +
            "            <input type=\"number\" id=\"unit7\" name=\"unit7\" value=\"2\" required>\n" +
            "            <button type=\"submit\">Calculate CGPA</button>\n" +
            "        </form>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";
}


    // Convert score to points
    private double convertToPoints(int score) {
        if (score >= 0 && score <= 39) {
            return 0.0;
        } else if (score >= 40 && score <= 44) {
            return 1.0;
        } else if (score >= 45 && score <= 49) {
            return 2.0;
        } else if (score >= 50 && score <= 54) {
            return 3.0;
        } else if (score >= 55 && score <= 59) {
            return 4.0;
        } else if (score >= 60 && score <= 64) {
            return 5.0;
        } else if (score >= 65 && score <= 69) {
            return 6.0;
        } else if (score >= 70 && score <= 100) {
            return 7.0;
        } else {
            // Handle invalid score
            throw new IllegalArgumentException("Invalid score");
        }
    }
}    