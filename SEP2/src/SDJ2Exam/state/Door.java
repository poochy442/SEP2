package SDJ2Exam.state;

public class Door {

    private DoorState state;

    public Door() {
        state = new DoorClosed();
    }

    public void click() {
        state.click(this);
    }

    public void setState(DoorState state) {
        this.state = state;

    }

    public void complete() {
        state.complete(this);
    }

    public String status() {
        return state.status();
    }

    public void timeout()
    {
        state.timeout(this);
    }
}

