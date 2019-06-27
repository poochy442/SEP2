package SDJ2Exam.state;

public class RunState {
    public static void main(String[] args) {
        Door d = new Door();
        System.out.println("Start up, the door is " + d.status());
        d.click();
        System.out.println("Clicked, the door is " + d.status());
        d.click();
        System.out.println("Clicked, the door is " + d.status());
        d.complete();
        System.out.println("Clicked, the door is " + d.status());
        d.click();
        System.out.println("Clicked, the door is " + d.status());
        d.complete();
        System.out.println("Clicked, the door is " + d.status());
        d.click();
        System.out.println("Clicked, the door is " + d.status());
        d.click();
        System.out.println("Clicked, the door is " + d.status());
        d.complete();
        System.out.println("Clicked, the door is " + d.status());
        d.timeout();
        System.out.println("TIMEOUT, the door is still " + d.status());
        d.click();
        System.out.println("Clicked, the door is " + d.status());
        d.complete();
        System.out.println("Clicked, the door is " + d.status());
        d.timeout();
        System.out.println("Clicked, the door is " + d.status());
        d.complete();
        System.out.println("Clicked, the door is " + d.status());



    }
}
