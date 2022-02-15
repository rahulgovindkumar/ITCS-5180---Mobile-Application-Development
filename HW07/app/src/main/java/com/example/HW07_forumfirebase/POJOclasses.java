/*HW07
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */



package com.example.HW07_forumfirebase;

import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class POJOclasses {

    public static class Route {
        ArrayList<GeoPoint> points;
        String dateTime;

        public Route(ArrayList<GeoPoint> points, String dateTime) {
            this.points = points;
            this.dateTime = dateTime;
        }

        public ArrayList<GeoPoint> getPoints() {
            return points;
        }

        public void setPoints(ArrayList<GeoPoint> points) {
            this.points = points;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        @Override
        public String toString() {
            return dateTime;
        }
    }
}
