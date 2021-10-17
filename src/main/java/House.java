
import java.util.Scanner;

public class House {
    //private int N_ground;
    Ground[]grounds;

    public House() {
        System.out.println("Enter number of grounds:");
        Scanner in = new Scanner(System.in);
        this.grounds = new Ground[in.nextInt()];
        System.out.println("Enter number of flats in ground:");
        int k = in.nextInt();
        //Ground[] grounds = new Ground[N_ground];
        for (int i = 0; i < grounds.length; i++) {
            grounds[i] = new Ground(k);

        }
    }

    public int get_N_man(){
        int KOL = 0;
        for(int i = 0;i<grounds.length;i++)
        {
            KOL += grounds[i].get_Man_Ground();
        }
        return KOL;
    }

    public double House_area(){
        int SQ = 0;
        for(int i = 0;i<grounds.length;i++)
        {
            SQ += grounds[i].Ground_area();
        }
        return SQ;
    }
    public double Flat_area(int Ground,int flat) {
        return grounds[--Ground].flat_area(flat);
    }
    public int get_Man_falt(int Ground,int flat){
        return grounds[--Ground].get_Man_Flat(flat);
    }

    public void Compare_flats(int Ground1,int flat1,int Ground2,int flat2) {
        if(this.Flat_area(Ground1,flat1) > this.Flat_area(Ground2,flat2)){
            System.out.println("The area on the first flat is larger: "+ this.Flat_area(++Ground1,++flat1));
        }
        else if(this.Flat_area(Ground1,flat1) < this.Flat_area(Ground2,flat2)){
            System.out.println("The area on the second flat is larger: "+ this.Flat_area(++Ground2,++flat2));
        }
        else{
            System.out.println("Areas are equal: " + this.Flat_area(++Ground1,++flat1));
        }
        if(this.get_Man_falt(Ground1,flat1) > this.get_Man_falt(Ground2,flat2)){
            System.out.println("There are more people on the first flat: "+ this.get_Man_falt(++Ground1,++flat1));
        }
        else if(this.get_Man_falt(Ground1,flat1) < this.get_Man_falt(Ground2,flat2)){
            System.out.println("There are more people on the second flat: "+ this.get_Man_falt(++Ground2,++flat2));
        }
        else{
            System.out.println("Residents equally: " + this.get_Man_falt(++Ground1,++flat1));
        }
    }

    public  void compare_houses(House house2)
    {
        if(this.House_area() > house2.House_area()){
            System.out.println("House 1 bigger with area "+ this.House_area());
        }
        else  if(this.House_area() < house2.House_area()){
            System.out.println("House 2 bigger with area "+ house2.House_area());
        }
        else{
            System.out.println("Areas are the same: " +house2.House_area());
        }


        if(this.get_N_man() > house2.get_N_man()){
            System.out.println("There are more people in the first house: "+ this.get_N_man() +" vs "+house2.get_N_man());
        }
        else  if(this.get_N_man() < house2.get_N_man()){
            System.out.println("There are more people in the first house: "+ house2.get_N_man() +" vs "+this.get_N_man());
        }
        else{
            System.out.println("Equal number of residents: " +house2.get_N_man());
        }
    }
}