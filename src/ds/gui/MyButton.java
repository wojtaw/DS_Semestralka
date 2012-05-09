package ds.gui;

import javax.swing.JButton;

public class MyButton extends JButton{
	private String identificator = "UNKNOWN";
	
    public MyButton(String text,String identificator) {
    	this.identificator = identificator;
        this.setText(text);
        //this.setFont(new Font("Dialog", Font.BOLD, 15));
    }

    @Override
    public String toString()
    {
        return this.identificator;
    }
}
