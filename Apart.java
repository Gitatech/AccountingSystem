public class Apart {
    double sqr=0;
    int people=0;
    int rooms=0;
    public void Apart(){
        this.sqr=0;
        this.people=0;
        this.rooms=0;
    }
    void createApart(double s, int p, int r){
        sqr=s;
        people=p;
        rooms=r;
    }
    void Info(){
        System.out.println("Площадь: "+sqr+" Кол-во жильцов: "+people+" Кол-во комнат: "+rooms);
    }
}
