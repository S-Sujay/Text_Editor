import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//***************************************************  Main Class ********************************************************  */
class Text
{
	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // The application uses the Look & Feel that is native to 
		}																		 // the operating system. 
		catch(Exception e){}
		frame obj = new frame("Untitled");
		obj.frmsize(500,500);
	}
}
//********************************************************************************************************************************** */
class frame 
{
	private String font = "ARIAL";		// Default font. 
 	private	int size = 15;				// Default font size.
 	private	int style = Font.PLAIN;		// Default font style.
	private JFrame frm;
	private ImageIcon im = new ImageIcon("./logo.png");  // Window icon
	private static JTextArea t1 = new JTextArea(10,50); 
	private JPanel jp = new JPanel(new BorderLayout());

	private JMenuBar mb = new JMenuBar();		// Menu Bar
	private JMenu file = new JMenu("File"); 	
	private JMenu format = new JMenu("Format");
	private JMenu edit = new JMenu("Edit");
//********************  File Menu Options ********************************** */
	private JMenuItem neww = new JMenuItem("New");
	private JMenuItem open = new JMenuItem("Open");				 
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem saveas = new JMenuItem("Save as");				 
	private JMenuItem exit = new JMenuItem("Exit");
//******************* Format Menu Options *********************************** */
	private JMenu fontm = new JMenu("Font");
	private JMenuItem fontsize = new JMenuItem("Font size");
	private JMenu stylem = new JMenu("Style");
	private JButton ok = new JButton("OK");
	private JTextField tf = new JTextField();
							 
//********************* Font Menu Options ************************************* */
	private JMenuItem dialog = new JMenuItem("Dialog");
	private JMenuItem mono = new JMenuItem("Monospaced");
	private JMenuItem sserif = new JMenuItem("Sans Serif");
	private JMenuItem serif = new JMenuItem("Serif");
//********************* Style Menu Options *********************************** */
	private JMenuItem bold = new JMenuItem("BOLD");
	private JMenuItem italic = new JMenuItem("Italic");
	private JMenuItem plain = new JMenuItem("Plain");
//********************* Edit Menu Option ************************************ */
	private JMenuItem find = new JMenuItem("Find");
//********************** Scroll Panel [Text Area is also being added] *************************************** */
	private JScrollPane sp = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//************* Constructor ************************* */
	frame(String str)    
	{
		frm = new JFrame(str);
		frm.setIconImage(im.getImage());
		frm.setVisible(true);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		t1.setVisible(true);
		t1.setFont(new Font(font,style,size));
		mb.setVisible(true);
		jp.setBounds(20,20,400,400);
		jp.setVisible(true);
		addcomps();
		addlisteners();
	}
//******************* Method to add components to the window ****************************** */
	private void addcomps()		
	{
//******************* File Menu Components **************************************** */		
		file.add(neww);
		file.add(open);	
		file.add(save);	
		file.add(saveas); 
		file.add(exit);
//******************* Font Menu Components **************************************** */		
		fontm.add(dialog);
		fontm.add(mono);
		fontm.add(sserif);
		fontm.add(serif);
//******************* Style Menu Components **************************************** */		
		stylem.add(bold);
		stylem.add(italic);
		stylem.add(plain);
//******************* Format Menu Components **************************************** */		
		format.add(fontm);
		format.add(fontsize);
		format.add(stylem);
//******************* Edit Menu Components **************************************** */		
		edit.add(find);
//******************* Menu Bar Components **************************************** */		
		mb.add(file); 
		mb.add(format);
		mb.add(edit);
		
		jp.add(sp);		// Adding scroll panel to Jpanel. Scroll Panel has Text Area.
		jp.add(mb, BorderLayout.NORTH);
		frm.add(jp);	// Adding Jpanel to frame(Window).
	}

//********************** Method to add Action Listeners ************************** */
	private void addlisteners()  	
	{
//************** File Menu listeners *********************** */		
		neww.addActionListener(new filelistener());
		open.addActionListener(new filelistener());
		save.addActionListener(new filelistener());
		saveas.addActionListener(new filelistener());
		exit.addActionListener(new filelistener());
//************** Format Menu listeners *********************** */		
		dialog.addActionListener(new formatlistener());
		mono.addActionListener(new formatlistener());
		sserif.addActionListener(new formatlistener());
		serif.addActionListener(new formatlistener());
		bold.addActionListener(new formatlistener());
		italic.addActionListener(new formatlistener());
		plain.addActionListener(new formatlistener());
		fontsize.addActionListener(new formatlistener());
//************** Edit Menu listeners *********************** */		
		find.addActionListener(new editlistener());
	}
	public void frmsize(int width, int height)
	{
		frm.setBounds(500,150,width,height);
	}
//*************** Class for File Menu listeners ******************************************* */
	class filelistener implements ActionListener 								
	{
		public void actionPerformed(ActionEvent e)
		{
			//**  When New button is clicked, this block gets executed.  ***/
			if(e.getSource()==neww)
			{
				frm.setTitle("Untitled");
				t1.setText("");
			}
			//**  When open button is clicked, this block gets executed.  ***/
			if(e.getSource()==open)
			{
				FileDialog d1 = new FileDialog(new Frame(),"Open",FileDialog.LOAD);
				d1.setVisible(true);
				File f = new File(d1.getDirectory()+d1.getFile()); 
				try
				{
					BufferedReader br = new BufferedReader(new FileReader(f.getPath()));
					String s1="",s2="";
					while((s1=br.readLine())!=null)
						s2+=s1+"\n";
					t1.setText(s2);
					br.close();
				}catch(Exception e2){}	
				
				if(d1.getDirectory()!=null && d1.getFile()!=null )
					frm.setTitle(d1.getDirectory() + d1.getFile());
				
				t1.setFont(new Font("ARIAL",Font.PLAIN,15));
			}
			
			//**  When Save button is clicked, this block gets executed.  ***/
			if(e.getSource()==save)
			{
				if(frm.getTitle()=="Untitled")
				{
					FileDialog d1 = new FileDialog(new Frame(),"Save your File",FileDialog.SAVE);
					d1.setVisible(true);
					if(d1.getDirectory()!=null && d1.getFile()!=null)	
					 	frm.setTitle(d1.getDirectory() + d1.getFile());
				}
				if(frm.getTitle()!="Untitled")
				{
					File f = new File(frm.getTitle());
					try
						{
							BufferedWriter bw = new BufferedWriter(new FileWriter(f.getPath()));
							bw.write(t1.getText());
							bw.close();
						}catch(Exception e3){}
				}
			}
			
			//**  When Saveas button is clicked, this block gets executed.  ***/
			if(e.getSource()==saveas)
			{
				FileDialog d = new FileDialog(new Frame(),"Save As",FileDialog.SAVE);
				d.setVisible(true);
				if(d.getDirectory()!=null && d.getFile()!=null)
				{
					File f = new File(d.getDirectory()+d.getFile());
					try
					{
						BufferedWriter bw = new BufferedWriter(new FileWriter(f.getPath()));
						bw.write(t1.getText());
						bw.close();	
					}
					catch(Exception e4){}	
				}
			}

			//**  When Exit button is clicked, this block gets executed.  ***/
			if(e.getSource()==exit)
			{
				frm.dispose();
			}
		}	
 	}
//*************** Class for Format Menu listeners ******************************************* */
 	class formatlistener implements ActionListener 					
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			//**  When Dialog button is clicked, this block gets executed.  ***/
			if(e.getSource()==dialog)
 			{
 				font = "DIALOG";
 				t1.setFont(new Font(font,style,size));
			}
			
			//**  When Mono button is clicked, this block gets executed.  ***/
			if(e.getSource()==mono)
 			{
 				font = "MONOSPACED";
 				t1.setFont(new Font(font,style,size));
			}
			
			//**  When San Serif button is clicked, this block gets executed.  ***/
			if(e.getSource()==sserif)
 			{
 				font = "SANS_SERIF";
 				t1.setFont(new Font(font,style,size));
			}
			
			//**  When Serif button is clicked, this block gets executed.  ***/
			if(e.getSource()==serif)
 			{
 				font = "SERIF";
 				t1.setFont(new Font(font,style,size));
			}

			//**  When Bold button is clicked, this block gets executed.  ***/
			if(e.getSource()==bold)
 			{
 				style = Font.BOLD;
 				t1.setFont(new Font(font,style,size));
			}
			
			//**  When Italic button is clicked, this block gets executed.  ***/
			if(e.getSource()==italic)
 			{
 				style = Font.ITALIC;
 				t1.setFont(new Font(font,style,size));
			}

			//**  When Plain button is clicked, this block gets executed.  ***/
			if(e.getSource()==plain)
 			{
 				style = Font.PLAIN;
 				t1.setFont(new Font(font,style,size));
			}

			//**  When Font Size button is clicked, this block gets executed.  ***/
			if(e.getSource()==fontsize)
 			{
				JLabel l1 = new JLabel("Font Size: "); 
 				JDialog d = new JDialog(frm,"Font size");
				 d.setVisible(true);
				 d.setLayout(null);
				 d.setBounds(600,300,250,150);
				 l1.setBounds(10,10,100,30);
				 l1.setVisible(true);
				 tf.setBounds(90,10,100,30);
				 ok.setBounds(110,50,60,30);
				 d.add(l1);
				 d.add(tf);
				 d.add(ok);
				ok.addActionListener(this);	
			}
			if(e.getSource()==ok)
			{
					if(tf.getText()!=null)
					{	
						size = Integer.parseInt(tf.getText());
						t1.setFont(new Font(font,style,size));
					}
			}

 		}	
 	}

//*************** Class for Edit Menu listeners ******************************************* */	 
 	class editlistener implements ActionListener 				
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			//**  When Find button is clicked, this block gets executed.  ***/
			if(e.getSource()==find)
 			{
 				new findd(frame.t1);
 			}
 		}
 	}
}

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