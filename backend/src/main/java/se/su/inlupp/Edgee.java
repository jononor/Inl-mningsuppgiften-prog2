package se.su.inlupp;

public class Edgee<T> implements Edge<T> {
    private final T destination;
    private final String name;
    private int weight;

    public Edgee(T destination, String name, int weight) {
        this.destination = destination;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("vikten får inte vara negativ eller null");
        }
        this.weight = weight;
    }

    @Override
    public T getDestination() {
        return destination;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("till %s med %s tar %d",destination, name, weight);
    }
}
