package dk.via.calculator.model;

import dk.via.calculator.client.MathClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManager implements Model, PropertyChangeListener {
    private final MathClient client;
    private final PropertyChangeSupport support;

    public ModelManager(MathClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
        client.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }

    @Override
    public double add(double a, double b)  {
        try {
            return client.plus(a, b);
        } catch (IOException e) {
            throw new RuntimeException("Server communication error", e);
        }
    }

    @Override
    public double subtract(double a, double b) {
        try {
            return client.minus(a, b);
        } catch (IOException e) {
            throw new RuntimeException("Server communication error", e);
        }
    }

    @Override
    public double multiply(double a, double b) {
        try {
            return client.times(a, b);
        } catch (IOException e) {
            throw new RuntimeException("Server communication error", e);
        }
    }

    @Override
    public double divide(double a, double b) {
        try {
            return client.divide(a, b);
        } catch (IOException e) {
            throw new RuntimeException("Server communication error", e);
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
