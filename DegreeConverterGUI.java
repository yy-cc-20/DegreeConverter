import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;

// Get input and display output
public class DegreeConverterGUI extends JPanel {
	private NumberFormat numberFieldFormatter;
	
	private JFormattedTextField decimalDegreeField;
	private JFormattedTextField degreesField;
	private JFormattedTextField minutesField;
	private JFormattedTextField secondsField;
	
	// JButton if any
	// controller if any
	
	public DegreeConverterGUI() {
		numberFieldFormatter = NumberFormat.getInstance( // only accept number
				Locale.getDefault()); // platform independent
		numberFieldFormatter.setMaximumFractionDigits(4);
	
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetFieldListener());
		
		setLayout(new BorderLayout());
		add(createDecimalPanel(), BorderLayout.WEST);
		add(createDMSPanel(), BorderLayout.CENTER);
		add(resetButton, BorderLayout.SOUTH);
	}
	
	public JPanel getDegreeConverterPanel() {
		return this;
	}
	
	private class ResetFieldListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			decimalDegreeField.setText("");
			degreesField.setText("");
			minutesField.setText("");
			secondsField.setText("");
		}
	}
	
	// create different panel for decimal degree and dms
	private JPanel createDecimalPanel() {
		JPanel decimalPanel = new JPanel();
		JLabel decimalDegreesLabel = new JLabel("Decimal Degree");
		
		decimalDegreeField = new JFormattedTextField(numberFieldFormatter);
		decimalDegreeField.setColumns(10);
		decimalDegreeField.addActionListener(new DecimalToDMSListener());
		
		decimalPanel.add(decimalDegreesLabel);
		decimalPanel.add(decimalDegreeField);
		
		decimalPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Decimal"));
		
		return decimalPanel;
	}

	// create inner class to implement event listener
	private class DecimalToDMSListener implements ActionListener {
		public void actionPerformed(ActionEvent e) { // press enter
			// get input
			Degrees degrees = new Degrees(
					Double.parseDouble(
							decimalDegreeField.getText()
							.replaceAll(",", "."))); 
			// NumberFormatter will add "," and "." automatically, you will have to remove it when convert to number
			
			// output
			degreesField.setText(degrees.getDMS().degrees + ""); // + "" to parse to String
			minutesField.setText(degrees.getDMS().minutes + "");
			secondsField.setText(degrees.getDMS().seconds + "");
		}
	}
	
	private JPanel createDMSPanel() {
		JPanel dmsPanel = new JPanel(new GridLayout(3, 2, -40, 25));
		JLabel degreesLabel = new JLabel("Degrees");
		JLabel minutesLabel = new JLabel("Minutes");
		JLabel secondsLabel = new JLabel("Seconds");
		
		DMSToDecimalListener dmsToDecimalListener = new DMSToDecimalListener();
		
		degreesField = new JFormattedTextField(numberFieldFormatter);
		degreesField.setColumns(10);
		degreesField.addActionListener(dmsToDecimalListener);
		
		minutesField = new JFormattedTextField(numberFieldFormatter);
		minutesField.setColumns(10);
		minutesField.addActionListener(dmsToDecimalListener);
		
		secondsField = new JFormattedTextField(numberFieldFormatter);
		secondsField.setColumns(10);
		secondsField.addActionListener(dmsToDecimalListener);
		
		dmsPanel.add(degreesLabel);
		dmsPanel.add(degreesField);
		dmsPanel.add(minutesLabel);
		dmsPanel.add(minutesField);
		dmsPanel.add(secondsLabel);
		dmsPanel.add(secondsField);
		
		dmsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DMS"));
		
		return dmsPanel;
	}
	
	private class DMSToDecimalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double d = 0, m = 0, s = 0;
			// get input
			if (!degreesField.getText().isBlank())
				d = Double.parseDouble(
						degreesField.getText()
						.replaceAll(",", "."));
			if (!minutesField.getText().isBlank())
				m = Double.parseDouble(
						minutesField.getText()
						.replaceAll(",", "."));
			if (!secondsField.getText().isBlank())
				s = Double.parseDouble(
						secondsField.getText()
						.replaceAll(",", "."));
			
			Degrees degrees = new Degrees(new DMS(d, m, s));
			
			// output
			decimalDegreeField.setText(degrees.getDecimalDegrees() + "");
		}
	}
}