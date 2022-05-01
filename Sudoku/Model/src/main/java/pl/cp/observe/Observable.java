package pl.cp.observe;

public abstract class Observable {

    protected Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    protected void notifyObserver() {
        observer.update();
    }
}
