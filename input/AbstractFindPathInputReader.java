package input;

abstract class AbstractFindPathInputReader 
{
    private StringBuffer mazeInput_ = new StringBuffer();
    public abstract void readInput();

    public void setMaze(String maze)
    {
        mazeInput_.append(maze);
    }

    public Character getCell()
    {
        int startIndex = mazeInput_.length()-1, endIndex = mazeInput_.length();
        Character value_ = null;
        try
        {
            value_ = mazeInput_.charAt(0);
            mazeInput_.delete(0, 1);
        }
        catch (IndexOutOfBoundsException e)
        {}
       
        return value_;
    }
}