package main;

import java.util.Scanner;
import maze.Field;

public class Main {
    public static void main(String[] args) 
    {
      // FindPath
      System.out.print("\033[H\033[2J");   
      System.out.flush();  
      System.out.println("Načítanie vstupu: \n   1. konzola\n   2. txt súbor\n\nZvoľte možnosť:");
      Scanner scan_ = new Scanner(System.in);
      int option;
      try
      {
        option = Integer.parseInt(scan_.nextLine());
        switch (option)
        {
          case 1:
              System.out.println("Nacitajte vstup. Pre ukončenie stlačte '0' :");
              break;
          case 2:
              System.out.println("\nZadajte názov súboru:");
              break;
          default:
              throw new Exception("ERROR: Zle zvolená možnosť!");
        }
        Field field_ = new Field(option);
        if (!field_.verifyMaze()) System.out.println("ERROR: Neplatný vstup!");
        else System.out.println("Path: " + field_.findPath());
      }
      catch (NumberFormatException e)
      {
        System.out.println("ERROR: Zle zvolená možnosť!");
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      
    }
}
