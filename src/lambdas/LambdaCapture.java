package lambdas;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.event.*;
import java.awt.*;


public class LambdaCapture {

    private final static int FIELD_WIDTH = 20;
	private static JTextField staticTextField;
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();

	    staticTextField = new JTextField(FIELD_WIDTH);
	    staticTextField.setText("Static field");

	    JTextField localTextField = new JTextField(FIELD_WIDTH);
	    localTextField.setText("Local variable");
	    	    
	    JButton helloButton = new JButton("Say Hello");
	    
	    // For this we use a regular anonymous class
	    helloButton.addActionListener( new ActionListener() {
	    		public void actionPerformed(ActionEvent event) {
               staticTextField.setText("Hello, world!");
               localTextField.setText("Hello, world!");
            }
	    });

	    // For this we use a lambda expression (actually, block)	    
	    JButton goodbyeButton = new JButton("Say Goodbye");
	    goodbyeButton.addActionListener(	event -> {
	    		staticTextField.setText("Goodbye, world!");
	    		localTextField.setText("Goodbye, world!");
	    });

	    Container contentPane = frame.getContentPane();
	    contentPane.setLayout(new FlowLayout());
	    contentPane.add(staticTextField);
	    contentPane.add(localTextField);
	    contentPane.add(helloButton);
	    contentPane.add(goodbyeButton);

	    // The following line does not lead to a compilation error:
	    // lambdas can capture a static or instance variable,
	    // even if it is not effectively final.
	    
	    //    staticTextField = null;
	    
	    
	    // The following line would lead to a compilation error:
	    // the lambda above cannot capture a *local* variable
	    // that is not effectively final.
	    
	    //    localTextField = null;
	    
	    frame.setAlwaysOnTop( true );
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	static {
		FontUIResource largeFont = new javax.swing.plaf.FontUIResource("Serif",Font.PLAIN,26);
		UIManager.put("Button.font", largeFont);
		UIManager.put("TextField.font", largeFont);
		UIManager.put("TitledBorder.font", largeFont);
	}
}

