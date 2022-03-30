public abstract class Observer {
    protected boolean isTurnedOn = false;
    abstract void update();
    public void switchObserver(boolean isTurnedOn) {
        this.isTurnedOn = isTurnedOn;
    }
}
