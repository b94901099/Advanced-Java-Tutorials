package Lyft;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;

public class LyftDetour {

	public void calculateDetour(Point[] points) {
		
		if (points.length != 4) {
			throw new RuntimeException("There should be 4 points");
		}
		
		Point pointA = points[0];
		Point pointB = points[1];
		Point pointC = points[2];
		Point pointD = points[3];

		// A -> C -> D -> B
		double detourDistance1 = calculateDistance(pointA, pointC)
				+ calculateDistance(pointC, pointD)
				+ calculateDistance(pointD, pointB);
		double AtoBdistance = calculateDistance(pointA, pointB);
		System.out.println("The original distance from A to B is "
				+ AtoBdistance + " m");
		System.out.println("If driver one go from A, through C, D to B,"
				+ " the total distance would be " + detourDistance1 + " m");

		// C -> A -> B -> D
		double detourDistance2 = calculateDistance(pointC, pointA)
				+ calculateDistance(pointA, pointB)
				+ calculateDistance(pointB, pointD);
		double CtoDdistance = calculateDistance(pointC, pointD);
		System.out.println("The original distance from C to D is "
				+ CtoDdistance + " m");
		System.out.println("If driver two go from C, through A, B to D,"
				+ " the total distance would be " + detourDistance2 + " m");

		if (detourDistance1 == detourDistance2) {
			System.out.println("Both routes are the same");
		} else if (detourDistance1 < detourDistance2) {
			System.out.println("The shorter route would be A -> C -> D -> B");
		} else {
			System.out.println("The shorter route would be C -> A -> B -> D");
		}
	}

	public double calculateDistance(Point startPoint, Point endPoint) {
		double distance = 0;
		String startpoint = startPoint.latitude + "," + startPoint.longitude;
		String endpoint = endPoint.latitude + "," + endPoint.longitude;

		Document doc = getRemoteXML("http://maps.googleapis.com/maps/api/"
				+ "distancematrix/xml?origins=" + startpoint + "&destinations="
				+ endpoint + "&mode=driving&sensor=false");

		NodeList nl = doc.getElementsByTagName("value");
		if (nl.getLength() == 0) {
			System.out.println("no definitions found"); // no definitions found
		} else {
			NodeList n3 = doc.getElementsByTagName("value");
			String reply = n3.item(0).getTextContent();
			distance = Double.parseDouble(reply);
		}

		return distance;
	}

	private Document getRemoteXML(String url) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(url);
			return db.parse(is);
		} catch (Exception e) {
			System.out.print("Yikes, hit the error: " + e);
			return null;
		}
	}

	public static void main(String[] args) {
		LyftDetour ld = new LyftDetour();
		Point[] points = new Point[4];
		points[0] = new Point(40.44186, -79.89438);
		points[1] = new Point(40.43500, -79.96154);
		points[2] = new Point(40.46996, -79.93351);
		points[3] = new Point(40.45135, -79.86962);
		ld.calculateDetour(points);
	}

}

class Point {
	double latitude;
	double longitude;

	Point(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
