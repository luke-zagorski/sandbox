package com.performgroup.interview.cmd;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main entry point for the video ingest task
 */
public class VideoTaskRunner {

  private static transient final Logger LOGGER = Logger.getLogger(VideoTaskRunner.class);

  private static transient final String USAGE =
          "\n====== Usage ====== \n" +
                  "list = list videos in the system \n" +
                  "add = add a video after prompting for file path \n" +
                  "report summary  = list summary video report \n" +
                  "report type  = list summary for video type \n" +
                  "exit = exits the program \n" +
                  "usage = prints this help";

  public static void usage() {
    LOGGER.info(USAGE);
  }

  public static void main(final String[] args) throws IOException {

    final GenericApplicationContext ctx = new GenericApplicationContext();
    final XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);

    xmlReader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
    ctx.refresh();
    VideoProcessor videoProcessor = (VideoProcessor) ctx.getBean("videoProcessor");

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    while (true) {

      System.out.print(">");

      String input = in.readLine();
      if (input.equalsIgnoreCase("usage")) {
        usage();
      } else if (input.equalsIgnoreCase("list")) {
        videoProcessor.listVideos(LOGGER);
      } else if (input.equalsIgnoreCase("add")) {
        System.out.println("VideoDto file?");
        String videoDataFile = in.readLine();
        videoProcessor.ingestVideo(LOGGER, videoDataFile);
      } else if (input.equalsIgnoreCase("report type")) {
        System.out.println("VideoType(FRUIT_MATCH, VEGETABLE_MATCH, DRINK_MATCH) name?");
        String videoType = in.readLine();
        videoProcessor.reportVideoType(LOGGER, videoType);
      } else if (input.equalsIgnoreCase("report summary")) {
        videoProcessor.reportSummaryVideo(LOGGER);
      } else if (input.equalsIgnoreCase("exit")) {
        System.exit(0);
      } else {
        System.out.println("Unrecognised command");
      }

    }
  }
}
