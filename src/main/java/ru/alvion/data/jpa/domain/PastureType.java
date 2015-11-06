package ru.alvion.data.jpa.domain;

/**
 *
 */
public enum PastureType {
    GOOD(0.5), BAD(1.5);

    private double coef;

    PastureType(double coef) {
        this.coef = coef;
    }

    public double getCoef() {
        return coef;
    }
}
