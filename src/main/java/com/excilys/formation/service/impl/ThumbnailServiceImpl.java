package com.excilys.formation.service.impl;

import com.excilys.formation.service.ThumbnailService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by loic on 04/09/2014.
 */
@Service
public class ThumbnailServiceImpl implements ThumbnailService {

  private static final Logger logger = LoggerFactory.getLogger(ThumbnailService.class);

  @Value("${images.src.dir}")
  String imagesDirValue;

  @Value("${images.target.dir}")
  String thumbnailsDirValue;

  File imagesDir;
  File thumbnailsDir;

  @PostConstruct
  private void init() {
    imagesDir = new File(imagesDirValue);
    thumbnailsDir = new File(thumbnailsDirValue);
  }

  @Override
  public List<String> processImages(int width, int height) {
    clearDirectory(thumbnailsDir);

    StringBuilder sb = new StringBuilder("Image processed: width: ")
        .append(width)
        .append(" height: ").append(height);

    logger.debug(sb.toString());

    return recursiveProcessImages(imagesDir, thumbnailsDir, new Integer(width), new Integer(height));

  }

  @Override
  public List<String> processImages(int width, int height, int limit) {
    clearDirectory(thumbnailsDir);

    StringBuilder sb = new StringBuilder("Image processed: width: ")
        .append(width)
        .append(" height: ").append(height)
        .append(" limit: ").append(limit);

    logger.debug(sb.toString());

    if(limit == 0)
      return recursiveProcessImages(imagesDir,thumbnailsDir,width,height);
    else
      return recursiveProcessImages(imagesDir, thumbnailsDir, width, height,limit);
  }

  @Override
  public byte[] getThumbnail(String fileName) {
    File thumbnail = new File(thumbnailsDir,fileName);
    StringBuilder sb = new StringBuilder("Thumbnail retrieved: filename: ")
        .append(fileName);

    logger.debug(sb.toString());

    if(!thumbnail.exists()) {
      logger.warn("Error in getThumbnail: file {} does not exist.",fileName);
      throw new IllegalStateException("Thumbnail file does not exist");
    }

    try {
      return IOUtils.toByteArray(new FileInputStream(thumbnail));
    } catch (IOException e) {
      logger.warn("Error in getThumbnail while reading file {}",fileName);
      throw new IllegalStateException(e);
    }
  }

  private void clearDirectory(File dir) {
    for(File f : dir.listFiles()) {
      if(!f.isFile())
        clearDirectory(f);
      f.delete();
    }
  }

  private List<String> recursiveProcessImages(File root, File thumbnailsDir, Integer width, Integer height) {
    //TODO
    return null;
  }

  private List<String> recursiveProcessImages(File root, File thumbnailsDir, Integer width, Integer height, Integer limit) {
    //TODO
    return null;
  }

}
