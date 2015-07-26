package wuliuxitong;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public abstract class TextFieldWithComboBox extends JTextField {
	
	JComboBox comboBox;
	JTextField relatedTF;
	
	public JComboBox getComboBox() {
		return comboBox;
	}
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	
	TextFieldWithComboBox(){
		init();
	}
	
	public void init(){
//		TextFieldWithComboBox(DefaultComboBoxModel<String> model){
			this.relatedTF = null;
			this.setDocument(new ExceptSpaceDocument());
			this.setColumns(10);
			this.setLayout(new BorderLayout());
			this.comboBox = new JComboBox(){
	            public Dimension getPreferredSize() {
	                return new Dimension(super.getPreferredSize().width, 0);
	            }
	        };
	        setAdjusting(comboBox, false);
//			this.tixingCB = new JComboBox(model){
//	            public Dimension getPreferredSize() {
//	                return new Dimension(super.getPreferredSize().width, 0);
//	            }
//	        };
//			setAdjusting(cbInput, false);
			this.add(comboBox, BorderLayout.SOUTH);
			
			this.addKeyListener(new TextFieldKeyListener(this));
			this.getDocument().addDocumentListener(new TextFieldDocumentListener(this));
			this.comboBox.addActionListener(new ComboBoxActionListener(this));
	}
	
	TextFieldWithComboBox(JTextField tf){
		init();
		this.relatedTF = tf;
	}
	/*
	 * 保证JComboBox原子性的同步锁
	 * isAdjusting 判断JComboBox是否正在使用，
	 * 当JComboBox正在被使用时使用setAdjusting(comboBox, true)上锁
	 * 使用完后用setAdjusting(comboBox, false)解锁
	 */
	private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }
	
	private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }
	
	protected abstract void updateComboBoxModel() throws Exception;
	
	protected abstract void updaterelatedTextField() throws Exception;
	
	public class TextFieldDocumentListener implements DocumentListener {

		TextFieldWithComboBox textField;
		JComboBox comboBox;
		
		TextFieldDocumentListener(TextFieldWithComboBox tfwcb){
			this.textField = tfwcb;
			this.comboBox = tfwcb.getComboBox();
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			updateList();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			updateList();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			updateList();
		}
		
		private void updateList(){
			if(!TextFieldWithComboBox.isAdjusting(this.comboBox)){
//			while(!TextFieldWithComboBox.isAdjusting(this.comboBox)){}
				TextFieldWithComboBox.setAdjusting(this.comboBox, true);
				this.comboBox.removeAllItems();
				if(this.textField.getText() != null && !this.textField.getText().equals("")){
					try {
						updateComboBoxModel();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.comboBox.setVisible(true);
					this.comboBox.setPopupVisible(true);
				} else {
					this.comboBox.removeAllItems();
					this.comboBox.setPopupVisible(false);
				}
				TextFieldWithComboBox.setAdjusting(this.comboBox, false);
			}
		}
	}
	
	public class ComboBoxActionListener implements ActionListener{

		TextFieldWithComboBox textField;
		JComboBox comboBox;
		
		ComboBoxActionListener(TextFieldWithComboBox tfwcb){
			this.textField = tfwcb;
			this.comboBox = tfwcb.getComboBox();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			System.out.println("testaction");
			if(!TextFieldWithComboBox.isAdjusting(this.comboBox)){
//			while(TextFieldWithComboBox.isAdjusting(this.comboBox)){}
				TextFieldWithComboBox.setAdjusting(this.comboBox, true);
				if (this.comboBox.getSelectedItem() != null) {
					this.textField.setText(this.comboBox.getSelectedItem().toString());
                }
				TextFieldWithComboBox.setAdjusting(this.comboBox, false);
			}
		}
	}
	
	public class TextFieldKeyListener implements KeyListener{

		TextFieldWithComboBox textField;
		JComboBox comboBox;
		
		TextFieldKeyListener(TextFieldWithComboBox tfwcb){
			this.textField = tfwcb;
			this.comboBox = tfwcb.getComboBox();
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
//			keyEventDispatch(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			keyEventDispatch(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
//			keyEventDispatch(e);
		}
		
		private void keyEventDispatch(KeyEvent e){
			setAdjusting(this.comboBox, true);
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (this.comboBox.isPopupVisible()) {
                    e.setKeyCode(KeyEvent.VK_ENTER);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                e.setSource(this.comboBox);
                this.comboBox.dispatchEvent(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    this.textField.setText(this.comboBox.getSelectedItem().toString());
                    if(this.textField.relatedTF != null){
						try {
							updaterelatedTextField();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
                    this.comboBox.setPopupVisible(false);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            	this.comboBox.setPopupVisible(false);
            }
            setAdjusting(this.comboBox, false);
		}
	}
	
	public class ExceptSpaceDocument extends PlainDocument{

		@Override
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			// TODO Auto-generated method stub
			for(int i=0;i<str.length();i++){  
	            if(str.charAt(i) == ' '){   
	                return;   
	            }
	        }
			super.insertString(offs, str, a);
		}
		
	}
}
