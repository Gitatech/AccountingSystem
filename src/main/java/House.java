
import java.util.Scanner;

public class House {
    //private int N_ground;
    private Ground[]grounds;

    public House() {
        System.out.println("Enter number of grounds:");
        Scanner in = new Scanner(System.in);
        this.grounds = new Ground[in.nextInt()];
        System.out.println("Enter number of flats in ground:");
        int k = in.nextInt();
        System.out.println("How do you want to populate:\n1.Random\n2.Input");
        int How = in.nextInt();
        switch (How)
        {
            case (1):
                for (int i = 0; i < grounds.length; i++) {
                    grounds[i] = new Ground(k,false);
                }
                    break;
            case (2):
                for (int i = 0; i < grounds.length; i++) {
                    grounds[i] = new Ground(k, true);
                }
                    break;
            default:
                System.out.println("Incorrect value.Random filing");
                for (int i = 0; i < grounds.length; i++) {
                    grounds[i] = new Ground(k, false);
                }
                    break;
        }
        this.grounds[1].NUM(0);
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
    public double Flat_area(int i) {
        int gr = i / (grounds[0].flat_on_ground());
        return grounds[gr].flat_area(i);
    }
    public int get_Man_falt(int i){
        int gr = i / (grounds[0].flat_on_ground());
        return grounds[gr].get_Man_Flat(i);
    }

    public void Compare_flats(int Number1,int Number2) {
        if(this.Flat_area(Number1) > this.Flat_area(Number2)){
            System.out.println("The area on the first flat is larger: "+ this.Flat_area(Number1));
        }
        else if(this.Flat_area(Number1) < this.Flat_area(Number2)){
            System.out.println("The area on the second flat is larger: "+ this.Flat_area(Number2));
        }
        else{
            System.out.println("Areas are equal: " + this.Flat_area(Number1));
        }
        if(this.get_Man_falt(Number1) > this.get_Man_falt(Number2)){
            System.out.println("There are more people on the first flat: "+ this.get_Man_falt(Number1));
        }
        else if(this.get_Man_falt(Number1) < this.get_Man_falt(Number2)){
            System.out.println("There are more people on the second flat: "+ this.get_Man_falt(Number2));
        }
        else{
            System.out.println("Residents equally: " + this.get_Man_falt(Number1));
        }
    }

    public  void compare_houses(House house2)
    {
        if(this.House_area() > house2.House_area()){
            System.out.println("House 1 bigger with the area "+ this.House_area());
        }
        else  if(this.House_area() < house2.House_area()){
            System.out.println("House 2 bigger with the area "+ house2.House_area());
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