import java.util.ArrayList;
public class Home {
    int floortcount=10;
    double hsqr=0;
    int hpeople=0;
    int hrooms=0;
    Floor[] floors=new Floor[floortcount];
    public Home(int fnum, int anum){
        this.floortcount=fnum;
        for (int i=0; i<=fnum; i++){
            Floor floor=new Floor(anum);
            this.floors[i]=floor;
        }
    }
    void createFloor(int fcount){
        floortcount=fcount;
    }
    double getHsqr(){
        double S=0;
        for (int i=0; i<this.floortcount; i++){
            S+=floors[i].getFsqr();
        }
        return S;
    }
    double getHpeople(){
        double S=0;
        for (int i=0; i<this.floortcount; i++){
            S+=floors[i].getFpeople();
        }
        return S;
    }
    double getHrooms(){
        double S=0;
        for (int i=0; i<this.floortcount; i++){
            S+=floors[i].getFrooms();
        }
        return S;
    }
    void Info(){
        System.out.println("Площадь: "+getHsqr()+" Кол-во жильцов: "+getHpeople()+" Кол-во комнат: "+getHrooms());
    }
}
