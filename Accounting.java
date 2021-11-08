import java.lang.Double;

public class Accounting {
    int homecount;
    Home[] homes=new Home[100];
    public void createHome(int hnum, int fnum, int anum){
        Home home=new Home(fnum, anum);
        homes[hnum]=home;
        System.out.println("Дом добавлен");
    }
    public void createApart(int nh, int nf, int na, int s, int p, int r){
        this.homes[nh].floors[nf].aparts[na].createApart(s,p,r);
    }
    public String getSign(double a, double b) {
        if (Double.compare(a, b) > 0) {
            return ">";
        }
        else if (Double.compare(a, b) < 0) {
            return"<";
        }
        else {

           return "=";
        }
    }

    public void compareApart(int homen1, int floorn1, int apartn1,int homen2, int floorn2, int apartn2){
    Apart apartment1=homes[homen1].floors[floorn1].aparts[apartn1];
    Apart apartment2=homes[homen2].floors[floorn2].aparts[apartn2];
    System.out.println("Площадь1"+getSign(apartment1.sqr, apartment2.sqr)+"Площадь2");
    System.out.println("Жильцов1"+getSign(apartment1.people, apartment2.people)+"Жильцов2");
    System.out.println("Комнат1"+getSign(apartment1.people, apartment2.people)+"Комнат2");
    }
    public void compareHome(int n1, int n2){
    Home home1=homes[n1];
    Home home2=homes[n2];
    System.out.println("Площадь1"+getSign(home1.getHsqr(), home2.getHsqr())+"Площадь2");
    System.out.println("Жильцов1"+getSign(home1.getHpeople(), home2.getHpeople())+"Жильцов2");
    System.out.println("Комнат1"+getSign(home1.getHrooms(), home2.getHrooms())+"Комнат2");
    }
}
