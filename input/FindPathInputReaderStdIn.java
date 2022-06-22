package input;

import java.util.Scanner;

public class FindPathInputReaderStdIn extends AbstractFindPathInputReader
{
    public FindPathInputReaderStdIn()
    {}

    public void readInput()
    {
        Scanner input = new Scanner(System.in);
        int num_ = -1;
        StringBuilder input_ = new StringBuilder();
        while (num_ != 0)
        {
        
            String mazeInput_ = input.nextLine();
            try 
            {
                num_ = Integer.parseInt(mazeInput_);
                if (num_ != 0)
                {
                System.out.print(String.format("\033[%dA",1)); // Move up
                System.out.print("\033[2K");
                }
            }
            catch (Exception e)
            {
                input_.append(mazeInput_ + "\n");
            }
        }
        super.setMaze(input_.toString());
    }

    public void readIForTest(String param_)
    {
        super.setMaze(param_);
    }
}
