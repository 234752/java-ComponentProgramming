package observe;

public abstract class Observable {

    private Observer observer;

    protected void setObserver(Observer observer) {
        this.observer = observer;
    }

    protected void notifyObserver() {
        observer.update();
    }
}
