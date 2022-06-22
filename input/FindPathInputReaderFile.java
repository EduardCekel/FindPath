package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.script.ScriptContext;

public class FindPathInputReaderFile extends AbstractFindPathInputReader
{
    public FindPathInputReaderFile()
    {}

    public void readInput()
    {
        try 
        {
            Scanner input = new Scanner(System.in);
            int num_ = -1;
            File file_ = new File(input.nextLine());
            Scanner fileRreader_ = new Scanner(file_);
            StringBuilder input_ = new StringBuilder();
            while (fileRreader_.hasNextLine())
            {
            
                String mazeInput_ = fileRreader_.nextLine();
                input_.append(mazeInput_ + "\n");
            }
            super.setMaze(input_.toString());
            fileRreader_.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.print("ERROR: Súbor neexistuje!");
        }
       
    }
}