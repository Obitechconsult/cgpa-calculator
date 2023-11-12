package com.cgpaCalculator.resources;

import com.codahale.metrics.annotation.Timed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.concurrent.atomic.AtomicLong;

@Path("/cgpa")
@Produces(MediaType.TEXT_PLAIN)
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
    public String getCGPA() {
        // Calculate CGPA logic goes here
        double cgpa = calculateCgpa();
        return String.valueOf(cgpa);
    }

    // CGPA calculation logic
    private double calculateCgpa() {
        // Replace this with your actual score
        int score = 75;

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
