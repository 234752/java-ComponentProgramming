package observe;

public abstract class Observable {

    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    protected void notifyObserver() {
        observer.update();
    }
}
