public abstract class Observable {

    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    private void notifyObserver() {
        observer.update();
    }
}
