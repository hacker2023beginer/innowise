package main.com.innowise.taskarray.observer;

import main.com.innowise.taskarray.observer.impl.ArrayEntityObserver;

public interface ArrayEntityObservable {

        void attach(ArrayEntityObserver observer);

        void detach(ArrayEntityObserver observer);

        void notifyObservers();

}
