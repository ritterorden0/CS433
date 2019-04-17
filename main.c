import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) {
      // initializes quantities of available resources
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      for (int i = 0; i < NUMBER_OF_RESOURCES; i++) {
        available[i] = Integer.parseInt(br.readLine());
      }

      int req[] = new array[Banker.NUMBER_OF_RESOURCES];
      System.out.println("Enter resource request: \n");
      Scanner s = new Scanner(System.in);

      Banker.request_resourceS(custNum, req[]);
  }
}
