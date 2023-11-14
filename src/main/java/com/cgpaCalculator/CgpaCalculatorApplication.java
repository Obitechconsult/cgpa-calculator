package com.cgpaCalculator;

import com.cgpaCalculator.health.TemplateHealthCheck;
import com.cgpaCalculator.resources.CgpaCalculatorResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class CgpaCalculatorApplication extends Application<CgpaCalculatorConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CgpaCalculatorApplication().run(args);
    }

    @Override
    public String getName() {
        return "cgpa calculator";
    }

    @Override
    public void initialize(final Bootstrap<CgpaCalculatorConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CgpaCalculatorConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

        // getting-started: CgpaCalculatorApplication#run->CgpaCalculatorResource
        CgpaCalculatorResource resource = new CgpaCalculatorResource("Your CGPA is: {}", "defaultName");

        // Instead of calling getCGPA with a single score, provide individual scores and units
        String cgpaHtml = resource.getCGPA(75, 2, 80, 3, 90, 1, 85, 2, 95, 3, 60, 1, 70, 2);

        // Print the HTML response
        System.out.println(cgpaHtml);

        // Extract the CGPA value (you may need to use a proper HTML parser for robustness)
        String cgpaValue = cgpaHtml.split("<span id=\"cgpa-value\">")[1].split("</span>")[0];

        // Parse the CGPA value
        double cgpa = Double.parseDouble(cgpaValue);

        // Print the parsed CGPA
        System.out.println("Your CGPA is: " + String.valueOf(cgpa));

        environment.jersey().register(resource);
        // getting-started: CgpaCalculatorApplication#run->CgpaCalculatorResource

        // getting-started: HelloWorldApplication#run->TemplateHealthCheck
        TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        // getting-started: HelloWorldApplication#run->TemplateHealthCheck
    }
}
