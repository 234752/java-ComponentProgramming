public abstract class Observer {

    protected boolean observerIsTurnedOn = false;

    abstract void update();

    public void switchObserver(boolean observerIsTurnedOn) {
        this.observerIsTurnedOn = observerIsTurnedOn;
    }
}
