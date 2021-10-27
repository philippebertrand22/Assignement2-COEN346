package Lab2;
public class Driver{
    public static void main(String[] args)
    {
        WaterTank waterTank = new WaterTank(100);

        Thread producer = new Thread(new Runnable(){
                @Override 
                public void run() {

                    try{
                        waterTank.put();
                    }   catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
        });


       Thread consumer = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    waterTank.consume();
                }   catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
       }); 

       producer.start();
       consumer.start();

       try{
            producer.join();
            consumer.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
       }
    }
    

