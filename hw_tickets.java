class ticketMachine implements Runnable{
    private static int totalTickets = 10000;
    private int saledTickets;
    Thread t;

    //constructor
    public ticketMachine(String machineName){
        saledTickets = 0;
        t = new Thread(this, machineName);
        t.start();
    }

    //sale method 
    public synchronized void sale(){
        int i = 1;
        while(grabTickets(i = ticketNumber())){
                saledTickets += i;
                System.out.println(Thread.currentThread().getName()+"賣出"+i+"張票");
        }
        System.out.println(Thread.currentThread().getName()+"總共賣出"+saledTickets+"張票");
    }

    //synchronized
    private boolean grabTickets(int temp){
        if(totalTickets>0 && temp <= totalTickets){
            totalTickets -= temp;
            return true;
        }
        else if(temp > totalTickets){
            return false;
        }
        else{
            return false;
        }
    }

    //用亂數選票的數量
    public int ticketNumber(){
        int number = (int)(Math.random()*4)+1;
        return number;
    }

    //run method
    @Override
    public void run(){
        sale();
    }
}
public class hw_tickets {
    public static void main(String[] args) {
        ticketMachine t1 = new ticketMachine("機器1");
        ticketMachine t2 = new ticketMachine("機器2");
        ticketMachine t3 = new ticketMachine("機器3");
        ticketMachine t4 = new ticketMachine("機器4");
    }
}
