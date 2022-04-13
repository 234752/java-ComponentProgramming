package observe;

public abstract class Observer {

    protected boolean observerIsTurnedOn = false;

    public abstract void update();

    public void switchObserver(boolean observerIsTurnedOn) {
        this.observerIsTurnedOn = observerIsTurnedOn;
    }
}
