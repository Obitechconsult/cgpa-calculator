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
    public String getCGPA(@QueryParam("score") @DefaultValue("-1") int score) {
        // If score is not provided in the query, render the form
        if (score == -1) {
            return renderForm();
        }

        // Calculate CGPA logic goes here
        double cgpa = calculateCgpa(score);

        // Format the result as HTML
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"/path/to/style.css\">\n" +
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
                "</html>";
    }

    // Render the HTML form
    private String renderForm() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"/path/to/style.css\">\n" +
                "    <title>CGPA Calculator</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"input-container\">\n" +
                "        <h1>Enter Your Score</h1>\n" +
                "        <form action=\"/\" method=\"get\">\n" +
                "            <label for=\"score\">Score:</label>\n" +
                "            <input type=\"number\" id=\"score\" name=\"score\" required>\n" +
                "            <button type=\"submit\">Calculate CGPA</button>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }

    // CGPA calculation logic
    private double calculateCgpa(int score) {
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