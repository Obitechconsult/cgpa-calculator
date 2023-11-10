package com.cgpaCalculator;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class CgpaCalculatorApplicationStart extends Application<CgpaCalculatorConfiguration> {
    public static void main(String[] args) throws Exception {
        new CgpaCalculatorApplicationStart().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<CgpaCalculatorConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CgpaCalculatorConfiguration configuration, Environment environment) {
        // nothing to do yet
    }
}