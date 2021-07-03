package Projeto;

public class Peer extends Thread
{
    private int id;
    private String name;

    public Peer()
    {

    }
    public Peer(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public void run()
    {
        System.out.println("Entrei na thread");
        int i =0;
        while(i<4)
        {
            System.out.println("Thread:" + this.id + "momento:" + i);
            i++;
        }
        try {
            Thread.sleep(50);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }


    }

}
