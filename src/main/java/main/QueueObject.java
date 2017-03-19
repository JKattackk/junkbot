package main;

public class QueueObject
{
    private int position;
    private String name;
    private String filePath;
    public static int numberInQueue = 1;

    public QueueObject(int position, String name, String filePath)
    {
        this.position = position;
        this.name = name;
        this.filePath = filePath;
        if (name != "queue")
        {
            numberInQueue++;
        }
    }
    public int getPosition()
    {
        return position;
    }
    public String getName()
    {
        return name;
    }
    public String getPath()
    {
        return filePath;
    }
    public int NumberInQueue()
    {
        return numberInQueue;
    }
}
