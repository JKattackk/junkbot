package main;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.File;
import java.sql.Time;
import java.util.Random;

public class FileViewer extends ListenerAdapter
{
    Random rand = new Random();
    AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    AudioPlayer player = playerManager.createPlayer();


    // seperates a string from a command starting at the offset number.
    public String getName(String name, int offset)
    {
        int length = name.length();
        System.out.println(length);
        String newName = "";
        char character;
        while (offset < length)
        {
            character = name.charAt(offset);
            newName += character;
            offset++;
        }
        return newName;
    }


    public void addFolder(String dir)
    {

        File directory = new File(dir);
        File[] fList = directory.listFiles();
        for (File file : fList)
        {
            if (file.isFile())
            {
            }
        }
    }

    //Send someone a DM
    public void sendDM(String msg, User author)
    {
        while (!author.hasPrivateChannel())
        {
            author.openPrivateChannel();
        }
        PrivateChannel chan = author.getPrivateChannel();
        chan.sendMessage(msg).queue();
    }


    //List folders in a directory
    public String listFolders(String directoryName)
    {
        String folders = "";
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList)
        {
            if (file.isDirectory())
            {
                folders = folders + file.getName() + " | ";
            }
        }
        return folders;
    }

    //List files in any specific folder.
    public void listSongs(String dir, User author)
    {
        while (!author.hasPrivateChannel())
        {
            author.openPrivateChannel();
        }
        PrivateChannel channelPriv = author.getPrivateChannel();
        dir = dir + "\\";
        File directory = new File(dir);
        File[] fList = directory.listFiles();
        int retCount = 0;
        String ret = "Files currently in directory: \n";
        String ret1 = "";
        String ret2 = "";
        String ret3 = "";
        String ret4 = "";
        String ret5 = "";
        String ret6 = "";
        String ret7 = "";
        String ret8 = "";
        for (File file : fList)
        {
            if (file.isFile())
            {
                if (ret.length() < 1900)
                {
                    ret = ret + file.getName() + "\n";
                } else
                {
                    if (ret1.length() < 1900)
                    {
                        retCount ++;
                        ret1 = ret1 + file.getName() + "\n";
                    } else
                    {
                        if (ret2.length() < 1900)
                        {
                            retCount ++;
                            ret2 = ret2 + file.getName() + " \n";
                        } else
                        {
                            if (ret3.length() < 1900)
                            {
                                retCount ++;
                                ret3 = ret3 + file.getName() + "\n";
                            } else
                            {
                                if (ret4.length() < 1900)
                                {
                                    retCount ++;
                                    ret4 = ret4 + file.getName() + "\n";
                                } else
                                {
                                    if (ret5.length() < 1900)
                                    {
                                        retCount ++;
                                        ret5 = ret5 + file.getName() + "\n";
                                    } else
                                    {
                                        if (ret6.length() < 1900)
                                        {
                                            retCount ++;
                                            ret6 = ret6 + file.getName() + "\n";
                                        } else
                                        {
                                            if (ret7.length() < 1900)
                                            {
                                                retCount ++;
                                                ret7 = ret7 + file.getName() + "\n";
                                            }
                                            else
                                            {
                                                if (ret8.length() < 1900)
                                                {
                                                    retCount ++;
                                                    ret8 = ret8 + file.getName() + "\n";
                                                }
                                                else
                                                {
                                                    if (ret8.length() < 1900)
                                                    {
                                                        retCount ++;
                                                        ret8 = ret8 + file.getName() + "\n";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (ret8.length() > 1899)
                {
                    channelPriv.sendMessage("Error, character max.  Inform developer.").queue();
                }
            }
        }
        channelPriv.sendMessage(ret).queue();
        if (retCount > 0)
        {
            channelPriv.sendMessage(ret1).queue();
            if (retCount > 1)
            {
                channelPriv.sendMessage(ret2).queue();
                if (retCount > 2)
                {
                    channelPriv.sendMessage(ret3).queue();
                    if (retCount > 3)
                    {
                        channelPriv.sendMessage(ret4).queue();
                        if (retCount > 4)
                        {
                            channelPriv.sendMessage(ret5).queue();
                            if (retCount > 5)
                            {
                                channelPriv.sendMessage(ret6).queue();
                                if (retCount > 6)
                                {
                                    channelPriv.sendMessage(ret7).queue();
                                    if (retCount > 7)
                                    {
                                        channelPriv.sendMessage(ret8).queue();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


