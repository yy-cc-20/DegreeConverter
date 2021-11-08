import javax.swing.JFrame;

// Build user interface and run the program
public class DegreeConverter {
	public static void createFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Degree Converter");
		frame.add(new DegreeConverterGUI().getDegreeConverterPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.setSize(400, 200);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		createFrame();	
	}
};