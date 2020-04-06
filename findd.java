//*********************** Listener class for find ******************************** */
class findd implements ActionListener   
{
	private JFrame frm = new JFrame("Find");
	private JTextField t1 = new JTextField(); // Text field for taking the string for searching.
	private JTextField t2 = new JTextField(); // Text field for taking the string for replacing.
	private JButton b1 = new JButton("Find");
	private JButton b2 = new JButton("Find Next");
	private JButton b3 = new JButton("Replace");
	private JButton b4 = new JButton("Replace All");
	private JTextArea txt;
	private String pattern;
	private int start=0,end=0,pos=-1;

//********************* Constructor ****************************** */
	public findd(JTextArea txta)
	{
		txt = txta;
		frm.setLayout(null);
		frm.setVisible(true);
		frm.setBounds(500,400,500,300);
		t1.setVisible(true);
		t1.setBounds(130,10,100,30);
		frm.add(t1);
		t2.setVisible(true);
		t2.setBounds(260,10,100,30);
		frm.add(t2);
		b1.setVisible(true);
		b1.setBounds(130,50,100,30);
		b2.setVisible(true);
		b2.setBounds(130,90,100,30);
		b3.setVisible(true);
		b3.setBounds(260,50,100,30);
		b4.setVisible(true);
		b4.setBounds(260,90,100,30);
		frm.add(b1);
		frm.add(b2);
		frm.add(b3);
		frm.add(b4);
		b1.addActionListener(this);	
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}
	//*** Method for action listeners */
	public void actionPerformed(ActionEvent e)
	{
		// When Find button is clicked, this block gets executed.
		if(e.getSource() == b1)
		{
			try
			{
				pattern = t1.getText();  // Reading the string and storing in pattern.
				if(txt.getText().indexOf(pattern,0)!= -1) 
				{	
					start = txt.getText().indexOf(pattern); // Storing the starting index of first occurrence of pattern in start.
					end = start+ pattern.length();			// By adding length to the start, we get last index of first occurrence of pattern.
					txt.select(start,end);		// Selecting the substring by passing indices as arguments.
					pos=end;				
				}
				else  // If the word is not found, print error message.
				{
					JOptionPane.showMessageDialog(frm, "Word Couldn't be found!", "Error", JOptionPane.ERROR_MESSAGE);
					start=0;
					end=0;
					pos=-1;
				}
			}
			catch(Exception ex){}
		}

		// When Find next button is clicked, this block gets executed.
		if(e.getSource() == b2)
		{
			try
			{
				pattern = t1.getText();
				int li = txt.getText().lastIndexOf(pattern);//Starting index of last occurrence of pattern is stored in li(last index).
				if(start!=li) 
				{	
					pattern = t1.getText();  // Reading the string from the text field.
					if(txt.getText().indexOf(pattern,pos)!= -1)
					{
						start = txt.getText().indexOf(pattern,pos);	// This block works same as the above, but pattern is searched after the
						end = start+ pattern.length();	//  "pos" value. pos is an index which gets upadated every time the block is executed.
						txt.select(start,end);			
						pos=end;
					}
					else // If the pattern is not found, print error message and reset the indices.
					{
						JOptionPane.showMessageDialog(frm, "Word Couldn't be found!", "Error", JOptionPane.ERROR_MESSAGE);
						start=0;
						end=0;
						pos=-1;
					}
				}
				else  // After finding last occurrence of the pattern, reset the indices.
				{
					start=0;
					end=0;
					pos=-1;
		//********************************************************************************
					pattern = t1.getText();  // Reading the string from the text field. Search for the first occurrence again.
					if(txt.getText().indexOf(pattern,pos)!= -1)
					{
						start = txt.getText().indexOf(pattern,pos);	
						end = start+ pattern.length();	
						txt.select(start,end);			
						pos=end;
					}
					else // If the pattern is not found, print error message and reset the indices.
					{
						JOptionPane.showMessageDialog(frm, "Word Couldn't be found!", "Error", JOptionPane.ERROR_MESSAGE);
						start=0;
						end=0;
						pos=-1;
					}
				}
			}
			catch(Exception ex){}
		}

		// When Replace button is clicked, this block gets executed.
		if(e.getSource() == b3)
		{
			StringBuilder str = new StringBuilder(txt.getText());
			str.replace(start,end,t2.getText());				
			txt.setText(str.toString());
		}

		// When Replace All button is clicked, this block gets executed.
		if(e.getSource() == b4)
		{
			txt.setText(txt.getText().replaceAll(t1.getText(),t2.getText()));
		}	
	}
}