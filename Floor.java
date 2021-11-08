import java.util.ArrayList;

public class Floor {
    int apartcount;
    double fsqr=0;
    int fpeople=0;
    int frooms=0;
    Apart[] aparts=new Apart[10];
    public Floor (int anum){
        this.apartcount=anum;
        for (int i=0; i<=anum; i++){
            Apart apart=new Apart();
            aparts[i]=apart;
        }
    }
    void createFloor(int acount){
        apartcount=acount;
    }

    double getFsqr(){
        double S=0;
        for (int i=0; i<this.apartcount; i++){
            S+=aparts[i].sqr;
        }
        return S;
    }
    double getFpeople() {
        double S = 0;
        for (int i = 0; i < this.apartcount; i++) {
            S += aparts[i].people;
        }
        return S;
    }
    double getFrooms() {
        double S = 0;
        for (int i = 0; i < this.apartcount; i++) {
            S += aparts[i].rooms;
        }
        return S;
    }
    void Info(){
        System.out.println("Площадь: "+getFsqr()+" Кол-во жильцов: "+getFpeople()+" Кол-во комнат: "+getFrooms());
    }


}
