package Projeto;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;



public class Servidor {

   private static class ServerPeer
   {
      Server central;
      UDPServer UDPCon;

      public ServerPeer(Server one) throws IOException
      {
         this.central = one;
         this.UDPCon = new UDPServer(this);

      }
   }
   private static class Server
   {
      public boolean on;
      public int porta;
      public ArrayList<ServerPeer> lstServerPeer;


      public Server(int porta)
      {
         this.on = true;
         this.porta = porta;
         this.lstServerPeer = new ArrayList<ServerPeer>();
      }
      public int setNumConexion()
      {
         if (this.lstServerPeer.size() == 0)
            return 1;
         else
            return this.lstServerPeer.size() + 1;
      }

   }

   private static class UDPServer extends Thread
   {
      ServerPeer myPeer;
      int numConn;

      public UDPServer(ServerPeer peer)
      {
         this.myPeer = peer;
         this.start();
      }

      public void run()
      {
         try
         {
            boolean conexion = this.UDPServerConexion();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      public boolean UDPServerConexion() throws IOException
      {
         int porta = this.myPeer.central.porta;
         DatagramSocket serverSocket = new DatagramSocket(porta);

         byte[] receiveData = new byte[1024];
         byte[] sendData = new byte[1024];

         while (true)
         {

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            System.out.println("Esperando por datagrama UDP na porta " + porta);
            try
            {
               serverSocket.receive(receivePacket);
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
            System.out.print("Datagrama UDP [" + numConn + "] recebido...");

            String sentence = new String(receivePacket.getData());
            System.out.println(sentence);

            InetAddress IPAddress = receivePacket.getAddress();

            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();

            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            System.out.print("Enviando " + capitalizedSentence + "...");

            serverSocket.send(sendPacket);
            System.out.println("OK\n");
         }
      }
   }


   public static void main(String[] args) throws Exception {
      int porta = 10098;
      Server server = new Server(porta);

      System.out.println("Estou aqui");

      ServerPeer current = null;

      while (server.on) {
         if (current == null) {
            current = new ServerPeer(server);
         }


      }
   }
}











