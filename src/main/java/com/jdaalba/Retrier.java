package com.jdaalba;

public class Retrier {

    private final Runnable execution;

    private int numberOfAttempts = 3;

    public Retrier(Runnable execution) {
        this.execution = execution;
    }

    public void execute() {
        try {
            execution.run();
        } catch (TechnicalException e) {
            numberOfAttempts--;
            if (numberOfAttempts > 0) {
                this.execute();
            } else {
                throw e;
            }
        }
    }
}

class BusinessException extends RuntimeException {

}

class TechnicalException extends RuntimeException {

}
