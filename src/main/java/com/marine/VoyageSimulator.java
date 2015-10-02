
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


/* 
* Cannabalized from here: 
* http://stackoverflow.com/questions/18581020/what-im-doing-wrong-when-i-implement-gpsd-tcp-feed-with-netty
*
*/

public class VoyageSimulator extends ChannelInboundHandlerAdapter { 

   private static double originLatitude = 55.403252;
   private static double originLongitude = 012.312946;
   private static double destLatitude = 55.403252;
   private static double destLongitude = 012.312946;
   private static double sog = 2.0;
   private static double cog = 0.0;
   private static Date date = new Date();
   private static Time time = new Time();

   @Override
   public void channelActive(final ChannelHandlerContext ctx) {
       while (true) {

           Position pos = new Position(originLatitude, originLongitude);
           Position dest = new Position(destLatitude, destLongitude);

           Boat sailboat = new Boat();
           sailboat.setSpeed(2);
           sailboat.setOrigin(pos);
           sailboat.setDestination(dest);
           //sailboat.getRouteSentences();
           
           //for (String routeSentence: sailboat.getRouteSentences() ) {
           //    PositionEvent event = new PositionEvent(VoyageSimulator.class, pos, boat.getSpeed(), boat.getBearing(), date, time, FaaMode.AUTOMATIC, GpsFixQuality.NORMAL);
           //    PositionEventToSentence sent = new PositionEventToSentence(event);
           //    String GGAsentenceString = sent.getParsedGGAData();
   
           //    final ByteBuf outtext = ctx.alloc().buffer(GGAsentenceString.getBytes().length); // (2)
           //    outtext.writeBytes(GGAsentenceString.getBytes());
           //    ctx.writeAndFlush(outtext); 
   
           //    GeodeticCalculator calc = new GeodeticCalculator();
           //    calc.setStartingGeographicPoint(longitude, latitude);
           //    calc.setDirection(90, 200);
           //    Point2D dest = calc.getDestinationGeographicPoint();
   
           //    System.out.println("Longitude: " + dest.getX() + " Latitude: " + dest.getY());
           //    longitude = dest.getX();
           //    latitude  = dest.getY();
           //}
       }
}

@Override
public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
    cause.printStackTrace();
    ctx.close();
}
}

