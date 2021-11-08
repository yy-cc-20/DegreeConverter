package DegreeConverter;

// Represent degrees in either decimal format or DMS format
public class Degrees {
	private double decimalDegrees; // degrees in decimal
		
	public Degrees(double d) {
		decimalDegrees = d;
	}
	
	public Degrees(DMS d) {
		decimalDegrees = computeDecimalDegrees(d);
	}
	
	public double getDecimalDegrees() {
		return decimalDegrees;
	}
	
	public DMS getDMS() {
		return computeDMS(decimalDegrees);
	}
	
	// convert the degrees in decimal to dms format
	// @param 	degrees in decimal
	// @return 	degrees in degrees, minutes, seconds
	public static DMS computeDMS(double decimalDegrees) {
		DMS dms = new DMS(); 
		
		dms.degrees = (int) decimalDegrees;
		dms.minutes = (int) ((decimalDegrees - dms.degrees) * 60);
		dms.seconds = (((decimalDegrees - dms.degrees) * 60) - dms.minutes) * 60;
		
		return dms;
	}
	
	// convert the degrees in dms format to decimal degrees
	// @param 	degrees in degrees, minutes, seconds
	// @return	degrees in decimal
	public static double computeDecimalDegrees(DMS dms) {
		if (dms == null)
			return 0;
		
		return dms.degrees + dms.minutes / 60 + dms.seconds / 60 / 60;
	}
}