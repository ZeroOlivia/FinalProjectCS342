import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {



    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 5555;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(in.readLine()); // Welcome message from server

            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println(serverResponse); // Print server messages

                if (serverResponse.equals("Game Over!")) {
                    break;
                }

                if (serverResponse.startsWith("Place your ship")) {
                    // Player's turn, place ships
                    String input = userInput.readLine();
                    out.println(input);
                }
                else if (serverResponse.startsWith("Your turn") || serverResponse.startsWith("AI chose")) {
                    // Player's turn or AI's turn, print board and prompt for input
                    //System.out.println(serverResponse);

                    // If it's the player's turn, prompt for input
                    if (serverResponse.startsWith("Your turn")) {
                        //System.out.println("Enter target coordinates (e.g., 'A1'):");
                        String input = userInput.readLine();
                        out.println(input);
                    }
                } else if (serverResponse.startsWith("Do you want to bomb") || serverResponse.startsWith("Enter the")) {
                    String input = userInput.readLine();
                    out.println(input);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


