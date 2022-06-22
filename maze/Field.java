package maze;

import java.util.ArrayList;

import input.FindPathInputReaderFile;
import input.FindPathInputReaderStdIn;

public class Field {
    private int xZ = -1, yZ= -1, xK= -1, yK= -1, xZbefore = -1, yZbefore = -1;
    private String path = "";
    boolean upBorder = false, downBorder = false;
    private Direction direc = Direction.DOWN;
    private ArrayList<ArrayList<Character>> maze_;

    public Field(int option)
    {
        this.maze_ = new ArrayList<ArrayList<Character>>();
        this.maze_.add(new ArrayList<Character>());

        boolean continue_ = true;
        int i = 0;
        // zvolenie odkial sa bude nacitavat
        switch (option)
        {
            case 1:
            {
                FindPathInputReaderStdIn stdIn = new FindPathInputReaderStdIn();
                stdIn.readInput();
                while (continue_)
                {
                    Character char_ = stdIn.getCell();
                    if (char_ != null)
                    {
                        if (char_ != '\n')
                            maze_.get(i).add(char_);
                        else { maze_.add(new ArrayList<Character>()); i++; } 
                    }
                    else continue_ = false;
                }
                break;
            }
            case 2:
            {
                FindPathInputReaderFile stdInF = new FindPathInputReaderFile();
                stdInF.readInput();
                System.out.println();
                while (continue_)
                {
                    Character char_ = stdInF.getCell();
                    if (char_ != null)
                    {
                        if (char_ != '\n')
                            maze_.get(i).add(char_);
                        else { maze_.add(new ArrayList<Character>()); i++; } 
                        System.out.print(char_);
                    }
                    else continue_ = false;
                }
                System.out.println();
                break;
            }
            default:
                System.out.println("ERROR: Zle zvolená možnosť!");
                break;
        }
        
    }

    public String findPath()
    {
        for (int i = 0; i < maze_.size(); i++)
        {
            for (int j = 0; j < this.maze_.get(i).size(); j++) {
                if (this.maze_.get(i).get(j) == 'S')
                    { this.yZ = i; this.xZ = j; }
                if (this.maze_.get(i).get(j) == 'X')
                    { this.yK = i; this.xK = j; }
            }
        }

        if ((this.xZ <= this.xK || this.xZ >= this.xK) && this.yZ < this.yK) this.direc = Direction.DOWN;
        else if (this.xZ < this.xK && this.yZ == this.yK) this.direc = Direction.RIGHT;
        else if ((this.xZ <= this.xK || this.xZ >= this.xK) && this.yZ > this.yK) this.direc = Direction.UP;
        else if (this.xZ > this.xK && this.yZ == this.yK) this.direc = Direction.LEFT;
        this.tryFind();
        return path;
    }

    private void tryFind()
    {
        boolean found = false;
        while (!found)
        {
            switch (direc)
            {
                case DOWN:
                {
                    try 
                    {
                        if (this.maze_.get(this.yZ+1).get(this.xZ) != '#')
                        { 
                            this.yZbefore = yZ;
                            this.xZbefore = xZ;
                            if (this.maze_.get(this.yZ+1).get(this.xZ) == 'X') { path += "d"; found = true;}
                            else 
                            { 
                                if (this.yZ >= this.yK && this.xZ < xK) this.direc = Direction.RIGHT;
                                else if (this.yZ == this.yK && this.xZ > xK) this.direc = Direction.LEFT;
                                else { this.yZ++; path += "d,"; }
                            }
                        }
                        else this.choose(Direction.DOWN);
                    }
                    catch (IndexOutOfBoundsException e)
                    { this.choose(Direction.DOWN); }
                    break;
                }  
                case RIGHT:
                {
                    try 
                    {
                        if (this.maze_.get(this.yZ).get(this.xZ+1) != '#')
                        { 
                            this.yZbefore = yZ;
                            this.xZbefore = xZ;
                            if (this.maze_.get(this.yZ).get(this.xZ+1) == 'X') { path += "r"; found = true;}
                            else 
                            { 
                                if (this.xZ >= this.xK && this.yZ > this.yK) this.direc = Direction.UP;
                                else if (this.xZ >= this.xK && this.yZ < this.yK) this.direc = Direction.DOWN;
                                else {this.xZ++; path += "r,"; }
                            }
                        }
                        else this.choose(Direction.RIGHT);
                    }
                    catch (IndexOutOfBoundsException e)
                    { this.choose(Direction.RIGHT); }
                    break;
                }
                case UP:
                {
                    try 
                    {
                        if (this.upBorder && this.downBorder) { found = true; path = "ERROR! Path doesn't exist!"; break;}
                        if (this.maze_.get(this.yZ-1).get(this.xZ) != '#')
                        { 
                            this.yZbefore = yZ;
                            this.xZbefore = xZ;
                            if (this.maze_.get(this.yZ-1).get(this.xZ) == 'X') { path += "u"; found = true;}
                            else 
                            { 
                                if (this.yZ <= this.yK  && this.xZ < this.xK) this.direc = Direction.RIGHT;
                                else if (this.yZ <= this.yK && this.xZ > this.xK) this.direc = Direction.LEFT;
                                else { this.yZ--; path += "u,"; }
                            }
                        }
                        else this.choose(Direction.UP);
                    }
                    catch (IndexOutOfBoundsException e)
                    { this.choose(Direction.UP); }
                    break;
                } 
                case LEFT:
                {
                    try 
                    {
                        if (this.maze_.get(this.yZ).get(this.xZ-1) != '#')
                        { 
                            this.yZbefore = yZ;
                            this.xZbefore = xZ;
                            if (this.maze_.get(this.yZ).get(this.xZ-1) == 'X') { path += "l"; found = true;}
                            else 
                            { 
                                if (this.xZ == this.xK && this.yZ > this.yK) this.direc = Direction.UP;
                                else if (this.xZ == this.xK && this.yZ < this.yK) this.direc = Direction.DOWN;
                                else {this.xZ--; path += "l,"; }
                            }
                        }
                        else this.choose(Direction.LEFT);
                    }
                    catch (IndexOutOfBoundsException e)
                    { this.choose(Direction.LEFT); }
                    break;
                }                                     
            }
            
            
        }
    }

    private void choose(Direction pDir)
    {
        switch (pDir)
        {
            case DOWN:
            {
                if (this.maze_.get(this.yZ).get(this.xZ + 1) == '#') { downBorder = true; this.direc = Direction.UP; }
            else if (this.xZ <= this.xK && this.xZbefore <= this.xZ && this.xZ < this.maze_.get(yZ).get(xZ).SIZE-1) { this.direc = Direction.RIGHT;/*this.xZ++; path += "r,";*/ }
                else { this.direc = Direction.LEFT; this.xZ--; path += "l,"; }
                break;
            }
            case UP:
            {
                if (this.maze_.get(this.yZ).get(this.xZ + 1) == '#') { upBorder = true; this.direc = Direction.DOWN; }
                else if (this.xZ <= this.xK && this.xZbefore <= this.xZ && this.xZ < this.maze_.get(yZ).get(xZ).SIZE-1) { this.direc = Direction.RIGHT;this.xZ++; path += "r,"; }
                else { this.direc = Direction.LEFT; this.xZ--; path += "l,"; }
                break;
            }
            case RIGHT:
            {
                if (this.yZ <= yK && this.yZbefore >= this.yZ && !upBorder && this.yZ >= 1) {this.direc = Direction.UP; this.yZ--; path += "u,";}
                else { this.direc = Direction.DOWN; this.yZ++; path += "d,"; }
                break;
            }
            case LEFT:
            {
                if (this.yZ >= yK && this.yZbefore >= this.yZ && !upBorder && this.yZ > 0) {this.direc = Direction.UP; this.yZ--; path += "u,";}
                else { this.direc = Direction.DOWN; this.yZ++; path += "d,"; }
                break;
            }
        }
        
    }

    // overenie platneho vstupu
    public boolean verifyMaze()
    {
        int countZ_ = 0, countK_ = 0;
        for (ArrayList<Character> arrayList : maze_) {
            for (Character character : arrayList) {
                if (character == 'S') countZ_++;
                if (character == 'X') countK_++;
                if (countZ_ > 1 || countK_ > 1 || !(character == '.' || character == '#' || character == 'S' || character == 'X')) return false;
            }
        }
        return true;
    }

    public void setDefaultMaze(String param_)
    {
        FindPathInputReaderStdIn stdIn = new FindPathInputReaderStdIn();
        stdIn.readIForTest(param_);
        int i = 0;
        boolean continue_ = true;
        while (continue_)
        {
            Character char_ = stdIn.getCell();
            if (char_ != null)
            {
                if (char_ != '\n')
                    maze_.get(i).add(char_);
                else { maze_.add(new ArrayList<Character>()); i++; } 
                System.out.print(char_);
            }
            else continue_ = false;
        }
    }

    enum Direction
    {
        DOWN,
        UP,
        RIGHT,
        LEFT
    }

}
