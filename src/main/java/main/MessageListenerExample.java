
package main;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;

import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;
import javax.security.auth.login.LoginException;


public class MessageListenerExample extends ListenerAdapter
{


    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        JDA jda = event.getJDA();                   //JDA, the core of the api.
        long responseNumber = event.getResponseNumber();//The amount of discord events that JDA has received since the last reconnect.

            //Event specific information
        User author = event.getAuthor();                  //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        MessageChannel channel = event.getChannel();
        Guild guild = event.getGuild();
        String msg = message.getContent();              //the message recieved
        boolean bot = author.isBot();                     //Bot or no

            //Setting up music bot in here
        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers manager = new AudioSourceManagers();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioPlayer player = playerManager.createPlayer();

        FileViewer util = new FileViewer(); //This has random classes I use.

            // Here are the directories
        String mcAlbums = "C:\\Botmusic\\MCalbums";
        String songFolder = "C:\\Botmusic\\songs";


            // Random strings I found myself needing
        String dir; //just to add random directories
        String store = "";


        if (msg.equals(",ping"))
        {
            channel.sendMessage("pong!").queue();
        }
        else if (msg.equalsIgnoreCase(",help"))
        {
            channel.sendMessage("Command | Action" ).queue();
        }
        else if (msg.equalsIgnoreCase("ayy") && !msg.equals("AYY"))
        {
            channel.sendMessage("lmao").queue();
        }
        else if (msg.equals("AYY"))
        {
            channel.sendMessage("LMAO").queue();
        }
        else if (msg.equals(",3141592653589793238462643383"))
        {
            System.exit(0);  //shuts down the bot
        }
        else if (msg.equals(",die"))
        {
            util.sendDM("Kill \n yourself.", author);
        }

            // List commands here
        // List albums in the MC folder
        else if (msg.equals(",mc albums"))
        {
            channel.sendMessage("Monstercat albums in dirtectory:").queue();
            channel.sendMessage(util.listFolders(mcAlbums)).queue();
        }
        // List songs within a specific MC album
        else if (msg.startsWith(",list mc "))
        {
            store = util.getName(msg, 9);
            util.listSongs(mcAlbums + "\\" + store, author);
        }
        // List all songs in the songs folder
        else if (msg.equalsIgnoreCase(",song list"))
        {
            util.listSongs(songFolder, author);
        }


            // Connection Commands
        // Join a Voice Channel
        else if (msg.startsWith(",join "))
        {
            store = util.getName(msg, 6);
            VoiceChannel myChannel = null;
            myChannel = event.getGuild().getVoiceChannelsByName(store, true).get(0);
            if (myChannel == null)
            {
                channel.sendMessage("No channel found.").queue();
            }
            else
            {
                guild.getAudioManager().openAudioConnection(myChannel);
            }
        }
        else if (msg.equalsIgnoreCase(",leave"))
        {
            event.getGuild().getAudioManager().closeAudioConnection();
        }

            //Queuing songs
        
    }
}