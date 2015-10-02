package com.marine;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import net.sf.marineapi.nmea.parser.*;
import net.sf.marineapi.nmea.parser.PositionEventToSentence;
import net.sf.marineapi.nmea.sentence.*;
import net.sf.marineapi.nmea.util.Time;
import net.sf.marineapi.nmea.util.*;
import net.sf.marineapi.nmea.util.Date;
import net.sf.marineapi.nmea.util.FaaMode;
import net.sf.marineapi.provider.event.PositionEvent;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.geotools.referencing.GeodeticCalculator;
import java.awt.geom.Point2D;

public class Boat {

     private static Position origin;
     private static Position destination;
     private static double speed;

     public static void setOrigin (Position p) {
        origin = p; 
     }

     public static double getMetersPerMinute() {
        return 52.138574514489 * speed;
     }

     public static void setDestination (Position p) {
        destination = p;
     }

     public static Double getNextCoordinate () {
          GeodeticCalculator gc = new GeodeticCalculator(crs);
          gc.setStartingPosition();
          gc.setDirection();
          Point2D dest = gc.getDestinationGeographicPoint();
          return Point2D;
     }

     public  static void setSpeed () {

     }

     public  static void setSpeed (double s) {
         speed = s;
     }
}

