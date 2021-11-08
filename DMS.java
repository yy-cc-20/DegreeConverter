package DegreeConverter;

// Represents degrees in degrees, minutes, seconds
public class DMS {
	public double degrees;
	public double minutes;
	public double seconds;
	
	public DMS() {}
	
	public DMS(double d, double m, double s) {
		degrees = d;
		minutes = m;
		seconds = s;
	}

	public String toString() {
		return degrees + "\u00b0 " + minutes + "' " + seconds + "''"; // \u00b0 == °
	}
}