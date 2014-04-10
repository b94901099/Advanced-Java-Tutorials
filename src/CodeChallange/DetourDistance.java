package CodeChallange;

import java.util.*;

/*
 * Calculate the detour distance between two different rides. Given four latitude
 * longitude pairs, where driver one is traveling from Point A to Point B and driver
 * two is traveling from Point C to Point D, write a function (in your language of choice)
 * to calculate the shorter of the detour distances the drivers would need to take to
 * pick-up and drop-off the other driver.
 */

public class DetourDistance {

}


class Point {
    private double longitude, latitude;

    public Point(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    double getLongitude() {
        return this.longitude;
    }

    double getLatitude() {
        return this.latitude;
    }
}