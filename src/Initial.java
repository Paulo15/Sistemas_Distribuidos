import Projeto.Peer;

public class Initial {

    public static void main(String args[])
    {
        Peer thread = new Peer(1,"First");
        Peer thread2 = new Peer(2,"Second");

        thread.start();
        thread2.start();
    }

}
