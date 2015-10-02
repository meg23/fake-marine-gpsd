
package net.sf.marineapi.nmea.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import net.sf.marineapi.nmea.parser.*;
import net.sf.marineapi.nmea.sentence.*;
import net.sf.marineapi.nmea.util.Time;
import net.sf.marineapi.nmea.util.*;
import net.sf.marineapi.nmea.util.Date;
import net.sf.marineapi.nmea.util.FaaMode;
import net.sf.marineapi.provider.event.PositionEvent;

public class PositionEventToSentence {

    private static String sentenceIdentifier;
    private static Time time;    
    private static double latitude;        
    private static char latitudeHemisphere;        
    private static double longitude;       
    private static char longitudeHemisphere;       
    private static int fixQuality;
    private static int satellites;
    private static double hdop;
    private static double altitude;        
    private static String altitudeSpecifier;        
    private static double geoid;
    private static String geoidSpecifier;
    private static String lastUpdateTime;
    private static String dgps;
    private static String checksum;
    
    private static PositionEvent event;

    public PositionEventToSentence (PositionEvent s) {
        event = s; 
        sentenceIdentifier = "$GPGGA";
        time = event.getTime();
        latitude = event.getPosition().getLatitude();
        latitudeHemisphere =  event.getPosition().getLatitudeHemisphere().toString().charAt(0);
        longitude = event.getPosition().getLongitude();
        longitudeHemisphere =  event.getPosition().getLongitudeHemisphere().toString().charAt(0);
        fixQuality = 1;
        satellites = 5;
        hdop = 1.5;
        altitude = 2.5;
        altitudeSpecifier = "M";
        geoid = -1.5;
        geoidSpecifier = "M";
        lastUpdateTime = "";
        dgps = "0000";
    }

    public static String getParsedGGAData () {
          StringBuilder rawData = new StringBuilder();
          rawData.append(sentenceIdentifier);
          rawData.append(",");
          rawData.append(time);
          rawData.append(",");
          rawData.append(latitude);
          rawData.append(",");
          rawData.append(latitudeHemisphere);
          rawData.append(",");
          rawData.append("0");
          rawData.append(longitude);
          rawData.append(",");
          rawData.append(longitudeHemisphere);
          rawData.append(",");
          rawData.append(fixQuality);
          rawData.append(",");
          rawData.append(satellites);
          rawData.append(",");
          rawData.append(hdop);
          rawData.append(",");
          rawData.append(altitude);
          rawData.append(",");
          rawData.append(altitudeSpecifier);
          rawData.append(",");
          rawData.append(geoid);
          rawData.append(",");
          rawData.append(geoidSpecifier);
          rawData.append(",");
          rawData.append(lastUpdateTime);
          rawData.append(",");
          rawData.append(dgps);
          rawData.append("*");
          String strResult = getSum(rawData.toString());
          rawData.append(strResult);
          GGASentence ggas = new GGAParser(rawData.toString());
          return ggas.toSentence();
    }

    private static String getSum(String in) {
        int checksum = 0;
        if (in.startsWith("$")) {
            in = in.substring(1, in.length());
        }
    
        int end = in.indexOf('*');
        if (end == -1)
            end = in.length();
        for (int i = 0; i < end; i++) {
            checksum = checksum ^ in.charAt(i);
        }
        String hex = Integer.toHexString(checksum);
        if (hex.length() == 1)
            hex = "0" + hex;
        return hex.toUpperCase();
    }
}
