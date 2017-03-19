package com.sedmelluq.discord.lavaplayer.container.adts;

import com.sedmelluq.discord.lavaplayer.container.MediaContainerDetection;
import com.sedmelluq.discord.lavaplayer.container.MediaContainerDetectionResult;
import com.sedmelluq.discord.lavaplayer.container.MediaContainerProbe;
import com.sedmelluq.discord.lavaplayer.tools.io.SeekableInputStream;
import com.sedmelluq.discord.lavaplayer.track.AudioReference;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Container detection probe for ADTS stream format.
 */
public class AdtsContainerProbe implements MediaContainerProbe {
  private static final Logger log = LoggerFactory.getLogger(AdtsContainerProbe.class);

  @Override
  public String getName() {
    return "adts";
  }

  @Override
  public MediaContainerDetectionResult probe(AudioReference reference, SeekableInputStream inputStream) throws IOException {
    AdtsStreamReader reader = new AdtsStreamReader(inputStream);

    if (reader.findPacketHeader(MediaContainerDetection.STREAM_SCAN_DISTANCE) == null) {
      return null;
    }

    log.debug("Track {} is an ADTS stream.", reference.identifier);

    return new MediaContainerDetectionResult(this, new AudioTrackInfo(
        reference.title != null ? reference.title : MediaContainerDetection.UNKNOWN_TITLE,
        MediaContainerDetection.UNKNOWN_ARTIST,
        Long.MAX_VALUE,
        reference.identifier,
        true
    ));
  }

  @Override
  public AudioTrack createTrack(AudioTrackInfo trackInfo, SeekableInputStream inputStream) {
    return new AdtsAudioTrack(trackInfo, inputStream);
  }
}
