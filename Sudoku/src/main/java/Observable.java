public abstract class Observable {

    private Observer observer;

    Observable(Observer observer) {
        this.observer = observer;
    }

    private void notifyObserver() {
        observer.update();
    }
}
