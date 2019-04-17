import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) {
      // initializes quantities of available resources
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      for (int i = 0; i < NUMBER_OF_RESOURCES; i++) {
        available[i] = Integer.parseInt(br.readLine());
      }

      String[] resArray = new String[6];
      for (int i = 0; i < 6; i++) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        resArray[i] = s;
      }

      Int[] numArray = new Int[5];
      for (int i = 0; i < 5; i++) {
        resArray[i+1] = numArray[i];
      }
      
      switch(resArray[0]) {
        case "RQ":
          request_resources(resArray[1], numArray);
          break;
        case "RL":
          release_resources(resArray[1], numArray);
          break;
        default:
          System.out.println("Invalid instruction.\n");
      }
      
  }
}
