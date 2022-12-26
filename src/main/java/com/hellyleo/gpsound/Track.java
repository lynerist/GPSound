/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellyleo.gpsound;

import static java.lang.Math.toRadians;
import java.util.Iterator;
import java.util.function.Consumer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class TrackPoint{

    public TrackPoint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private final int x;
    private final int y;
    private final int z;

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
    private final String alt0;

    public Track(Document doc) {
        track = doc.getElementsByTagName("trkpt");

        Node node0 = track.item(0);

        lat0    = ((Element)node0).getAttribute("lat");
        long0   = ((Element)node0).getAttribute("lon");

        //Get alt0 if "ele" node is there
        Node alt0Node = node0.getFirstChild();
        while (alt0Node != null && !alt0Node.getNodeName().equals("ele")){
            alt0Node = alt0Node.getNextSibling();
        }

        if (alt0Node != null){
            alt0 = alt0Node.getTextContent();
        }else{
            alt0 = "";
        }

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
        int z = 0;

        //To extract the elevation if it exists
        if (! alt0.equals("")){
            Node eleNode = currentNode.getFirstChild();
            while (! eleNode.getNodeName().equals("ele")){
                eleNode = eleNode.getNextSibling();
            }
            z = deltaAlt(alt0, eleNode.getTextContent());
        }
        return new TrackPoint(x,y,z);
    }

    private int haversine(String la0, String lo0, String la1, String lo1){
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
        return haversine(lat0, "0", lat1, "0");
    }
    private int deltaLong(String long0, String long1, String lat0){
        return haversine(lat0, long0, lat0, long1);
    }
    private int deltaAlt(String alt0, String alt1){
        return (int) (Float.valueOf(alt1)-Float.valueOf(alt0)) ;
    }

    @Override
    public Iterator<TrackPoint> iterator() {
        return this;
    }

    public int getNumPoints(){
        return track.getLength();
    }
}
   