package ds.gui;

import javax.swing.JButton;

public class MyButton extends JButton{
	private ButtonsIdentification identificator;
	
    public MyButton(String text,ButtonsIdentification identificator) {
    	this.identificator = identificator;
        this.setText(text);
        //this.setFont(new Font("Dialog", Font.BOLD, 15));
    }

    @Override
    public String toString()
    {
        return this.identificator.toString();
    }
}
