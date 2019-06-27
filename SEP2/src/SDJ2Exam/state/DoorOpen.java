package SDJ2Exam.state;

public class DoorOpen extends DoorState {
    @Override
    public void click(Door door) {
        door.setState(new DoorStayOpen());
    }
    public void timeout(Door door)
    {
        door.setState(new DoorClosing());
    }
}
