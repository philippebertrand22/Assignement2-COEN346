package Lab2;
public class WaterTank {
    public int Capacity;
    public int CurrentLevel;
    public double ratio;
    public static final String RED = "\033[0;31m"; //RED
    public static final String RESET = "\033[0m";
    Object lock = new Object();

    public WaterTank(int capacity){
        Capacity = capacity;
        CurrentLevel = 0;
        this.ratio = 0.0;
    }
    

public double get_Ratio(){ return ratio; }

void print(String processName){
    System.out.println("(" + processName + "): Tank capacity = " + get_Ratio()*100 + "%\n");
}

public void update() { ratio = ((double) CurrentLevel / (double) Capacity); }

public void put() throws InterruptedException
{
        while(true){
            synchronized(lock){

                while(IsFull())
                {
                    System.out.println("Tank is full");
                    lock.wait();
                }
                CurrentLevel++;
                update();
                print("Producing water");
                lock.notify();
                Thread.sleep(200);
            }
        }
}

public void consume() throws InterruptedException{
    while(true){
        synchronized(lock){

            while (IsEmpty() || Is_critical(0.30)){
                
                if(IsEmpty())
                    System.out.println("Tank is empty");
                if(Is_critical(0.30)){

                    System.out.println(RED + "Tank is equal or low than critical" + RESET);
                    Thread.sleep(1000);
                }    
                    lock.wait();
            }

            CurrentLevel--;
            update();
            print("Consuming Water");
            lock.notify();
            Thread.sleep(500);
        }
    }
}
public boolean IsFull() { return (CurrentLevel == Capacity); }

public boolean IsEmpty() { return (CurrentLevel <= 0); }

public boolean Is_critical(double critical){ return (ratio <= critical); }
}
