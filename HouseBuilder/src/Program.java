import java.util.ArrayList;

public class Program {
    Command command;
    protected boolean status;
    protected ArrayList<House> city=new ArrayList<>();

    protected Program(){
        this.status=true;
    }

    protected boolean isRunning() {
        return status;
    }
}
