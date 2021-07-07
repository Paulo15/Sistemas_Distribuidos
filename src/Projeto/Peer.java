package Projeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Peer
{
    public static class ClientPeer extends Thread
    {
        private int id;
        private String name;

        public ClientPeer(int id, String name)
        {
            this.id = id;
            this.name = name;
            this.start();
        }


        public void run()
        {
            while (true)
            {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                        System.in));

                DatagramSocket clientSocket = null;
                try {
                    clientSocket = new DatagramSocket();
                } catch (SocketException e) {
                    e.printStackTrace();
                }

                String servidor = "localhost";
                int porta = 10098;

                InetAddress IPAddress = null;
                try {
                    IPAddress = InetAddress.getByName(servidor);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];

                System.out.println("Digite o texto a ser enviado ao servidor: ");
                String sentence = null;
                try {
                    sentence = inFromUser.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IPAddress, porta);

                System.out
                        .println("Enviando pacote UDP para " + servidor + ":" + porta);
                try {
                    clientSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);

                try {
                    clientSocket.receive(receivePacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Pacote UDP recebido...");

                String modifiedSentence = new String(receivePacket.getData());

                System.out.println("Texto recebido do servidor:" + modifiedSentence);
                clientSocket.close();
                System.out.println("Socket cliente fechado!");
            }

        }
    }

    
    public static void main(String args[])
    {
        ClientPeer udpClient = new ClientPeer(1,"first");

    }
}
