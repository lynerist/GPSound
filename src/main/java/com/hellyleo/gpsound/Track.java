package com.hellyleo.gpsound;

import static java.lang.Math.toRadians;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class TrackPoint{
    private final int x;
    private final int y;
    private final int z;
    
    public TrackPoint(int x, int y, int z) {
        this.x = x; //delta long
        this.y = y; //delta lat
        this.z = z; //alt
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "TrackPoint{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}

public class Track implements Iterable<TrackPoint>, Iterator<TrackPoint>{
    private final NodeList track;
    private int currentIndex;
    private final String lat0;
    private final String long0;
    
    private int getAltitude(Node node){
        while (node != null && !node.getNodeName().equals("ele")){
            node = node.getNextSibling();
        }
        return node==null?-1:Float.valueOf(node.getTextContent()).intValue();
    }

    public Track(Document doc) {
        track = doc.getElementsByTagName("trkpt");
        lat0    = ((Element)track.item(0)).getAttribute("lat");
        long0   = ((Element)track.item(0)).getAttribute("lon");
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < track.getLength();
    }

    @Override
    public TrackPoint next() {
        if (! hasNext()){
            throw new ArrayIndexOutOfBoundsException("No More Points");
        }
        Node currentNode = track.item(currentIndex);
        currentIndex++;

        int x = deltaLong(long0, ((Element)currentNode).getAttribute("lon"), lat0);
        int y = deltaLat(lat0, ((Element)currentNode).getAttribute("lat"));
        int z = getAltitude(currentNode.getFirstChild());
        z = z<0?440:(z>8000?8000:(z<20?20:z)); //default is 440 if ele doesn't exist.
        
        return new TrackPoint(x,y,z);
    }

    private int haversineDistanceBetweenPoints(String la0, String lo0, String la1, String lo1){
        Double lat0 = Double.valueOf(la0);
        Double lon0 = Double.valueOf(lo0);
        Double lat1 = Double.valueOf(la1);
        Double lon1 = Double.valueOf(lo1);
        final int EarthRadius = 6371000;
        Double latDistance = toRadians(lat1-lat0);
        Double lonDistance = toRadians(lon1-lon0);
               
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
        Math.cos(toRadians(lat0)) * Math.cos(toRadians(lat1)) * 
        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (int) (EarthRadius * c) * (latDistance >= 0 && lonDistance >=0?1:-1);
    }

    private int deltaLat(String lat0, String lat1){
        return haversineDistanceBetweenPoints(lat0, "0", lat1, "0");
    }
    private int deltaLong(String long0, String long1, String lat0){
        return haversineDistanceBetweenPoints(lat0, long0, lat0, long1);
    }

    @Override
    public Iterator<TrackPoint> iterator() {
        return this;
    }

    public int getNumPoints(){
        return track.getLength();
    }
}
   