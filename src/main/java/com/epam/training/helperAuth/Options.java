package com.epam.training.helperAuth;

public class Options {
    private int idFlight;
    private boolean luggage;
    private boolean vip;

    public Options() {
    }

    public Options(int idFlight, boolean luggage, boolean vip) {
        this.idFlight = idFlight;
        this.luggage = luggage;
        this.vip = vip;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public boolean isLuggage() {
        return luggage;
    }

    public void setLuggage(boolean luggage) {
        this.luggage = luggage;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Options{" +
                "idFlight=" + idFlight +
                ", luggage=" + luggage +
                ", vip=" + vip +
                '}';
    }
}
