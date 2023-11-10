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
        CgpaCalculatorResource resource = new CgpaCalculatorResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);
        // getting-started: CgpaCalculatorApplication#run->CgpaCalculatorResource

        // getting-started: HelloWorldApplication#run->TemplateHealthCheck
        TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        // getting-started: HelloWorldApplication#run->TemplateHealthCheck

    }

}
