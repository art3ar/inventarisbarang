package pbo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class mainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	int iteration = 0;
	JPanel inputPanel, outputPanel;
	JLabel inputLabel, listLabel;
	JLabel inputNama, inputJenis, inputHarga;
	JTextField inputNamaField, inputJenisField, inputHargaField;
	JMenu systemMenu;
	JButton inputBtn;
	JFrame showInput, showList;
	JTable tabel;
	DefaultTableModel model;
	String kolom[] = {"Kode","Nama Barang","Jenis Barang","Harga"};
	String record[][];
	Color pink = new Color(237,8,123);
	Color std = new Color(238,238,238);
	Color white = new Color(255,255,255);
	
	static JButton dataBtn;
	public TableColumn tc1 = new TableColumn();
	public TableColumn tc2 = new TableColumn();
	public TableColumn tc3 = new TableColumn();
	public TableColumn tc4 = new TableColumn();
	public static boolean smallWindow = true; 

	public static void main(String[] args) {
		mainFrame homeFrame = new mainFrame();
		homeFrame.setTitle("Data Barang");
		homeFrame.setSize(344, 350);
		homeFrame.setLocation(500, 200);
		homeFrame.setResizable(false);
		homeFrame.setVisible(true);
		dataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (smallWindow == true) {
					homeFrame.setSize(900, 350);
					homeFrame.setLocation(300,180);
					dataBtn.setText("<< Data");
					smallWindow = false;
				} else if (smallWindow == false) {
					homeFrame.setSize(344, 350);
					homeFrame.setLocation(500, 200);
					dataBtn.setText("Data >>");
					smallWindow = true;
				}
				
			}
		});
	}

	public mainFrame() {

		Container mainFrame_cp = getContentPane();
		mainFrame_cp.setLayout(new BorderLayout());
		
		createInputPanel();
		createOutputPanel();
		
		mainFrame_cp.add(inputPanel,BorderLayout.WEST);
		mainFrame_cp.add(outputPanel,BorderLayout.CENTER);
		
		dataBtn = new JButton("Data >>");
		dataBtn.setForeground(pink);
		dataBtn.setBackground(new Color(238,238,238));
		
		mainFrame_cp.add(dataBtn,BorderLayout.EAST);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	
	void createInputPanel() {
		
		inputPanel = new JPanel(new GridLayout(3,1));
		inputPanel.setBorder(new LineBorder(pink,4));
		
		JPanel atas = new JPanel(new BorderLayout());
		JLabel title = new JLabel("Daftar Data Barang");
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		title.setHorizontalAlignment(NORMAL);
		title.setForeground(pink);
		title.setBorder(new LineBorder(new Color(238,238,238),10));
		atas.add(title,BorderLayout.CENTER);
		
		JPanel tengah = new JPanel(new FlowLayout());
		JPanel elemen = new JPanel(new GridLayout(3,3));
		elemen.setBorder(new LineBorder(new Color(238,238,238),10));
		
		inputNama = new JLabel ("Nama barang : ");
		inputNama.setForeground(pink);
		inputNamaField = new JTextField(10);
		inputNamaField.setBorder(new LineBorder(new Color(237,8,123)));
		inputNamaField.setText("");
		elemen.add(inputNama);
		elemen.add(inputNamaField);
		
		inputJenis = new JLabel ("Jenis barang : ");
		inputJenis.setForeground(pink);
		inputJenisField = new JTextField(10);
		inputJenisField.setBorder(new LineBorder(new Color(237,8,123)));
		inputJenisField.setText("");
		elemen.add(inputJenis);
		elemen.add(inputJenisField);
		
		inputHarga = new JLabel ("Harga barang : ");
		inputHarga.setForeground(pink);
		inputHargaField = new JTextField(10);
		inputHargaField.setBorder(new LineBorder(new Color(237,8,123)));
		inputHargaField.setText("");
		elemen.add(inputHarga);
		elemen.add(inputHargaField);
		
		tengah.add(elemen);
		
		JPanel bawah = new JPanel(new GridLayout(4,3));
		
		inputBtn = new JButton("INPUT");
		inputBtn.setForeground(new Color(255,255,255));
		inputBtn.setBackground(pink);
		inputBtn.setBorder(new LineBorder(pink));

		
		for (int i = 0; i<=3; i++) {
			bawah.add(new JLabel(""));
		}
		
		bawah.add(inputBtn);
		
		for (int i = 0; i<=1; i++) {
			bawah.add(new JLabel(""));
		}
		
		for (int i = 0; i<=3; i++) {
			bawah.add(new JLabel(""));
		}
		
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nama,jenis;
				int harga;
				
				try {
					nama=inputNamaField.getText();
					jenis=inputJenisField.getText();
					harga = Integer.parseInt(inputHargaField.getText());
					iteration++;
					model.addRow(new Object [] {iteration,nama,jenis,harga});
					JOptionPane.showMessageDialog(null,"Data Terinput");
				} catch (NumberFormatException d) {
					JOptionPane.showMessageDialog(null, "Harga Tidak Diketahui");
				} finally {
					inputNamaField.setText("");
					inputJenisField.setText("");
					inputHargaField.setText("");
				}

			}
		});
		
		inputPanel.add(atas);
		inputPanel.add(tengah);
		inputPanel.add(bawah);

	}
	
	void createOutputPanel() {
		outputPanel = new JPanel(new BorderLayout());
		JPanel atas = new JPanel(new BorderLayout());
		JPanel bawah = new JPanel(new GridLayout(3,5));
		
		JLabel listName = new JLabel("Daftar Barang");
		listName.setHorizontalAlignment(NORMAL);
		listName.setForeground(pink);
		listName.setBorder(new LineBorder(new Color(238,238,238),10));
		atas.add(listName); 
		
		model = new DefaultTableModel(record,kolom);
		tabel = new JTable(model);
		
		tc1 = tabel.getColumnModel().getColumn(0); 
        tc2 = tabel.getColumnModel().getColumn(1);
        tc3 = tabel.getColumnModel().getColumn(2);
        tc4 = tabel.getColumnModel().getColumn(3);
        
        tc1.setPreferredWidth(50); 
        tc2.setPreferredWidth(150);
        tc3.setPreferredWidth(100);
        tc4.setPreferredWidth(100);
        
        JPanel tabelPanel = new JPanel(new FlowLayout());
        tabel.setForeground(pink);
        tabel.setBorder(new LineBorder(pink));
        tabelPanel.add(tabel.getTableHeader());
        tabelPanel.add(tabel);
        
        JButton clearBtn = new JButton("CLEAR");
        clearBtn.setForeground(white);
        clearBtn.setBackground(pink);
        clearBtn.setBorder(new LineBorder(new Color(237,8,123),4));

        JButton hapusBtn = new JButton("HAPUS");
        hapusBtn.setForeground(white);
        hapusBtn.setBackground(pink);
        hapusBtn.setBorder(new LineBorder(new Color(237,8,123),4));
        
        for (int i = 0; i<6; i++) {
			bawah.add(new JLabel(""));
		}
        
        bawah.add(clearBtn);
        bawah.add(new JLabel(""));
        bawah.add(hapusBtn);
        
        for (int i = 0; i<6; i++) {
			bawah.add(new JLabel(""));
		}
        
        clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				JOptionPane.showMessageDialog(null,"Semua Data Dihapus");
				iteration = 0;
			}
		});
        
        hapusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SelectedRowIndex = tabel.getSelectedRow();
				model.removeRow(SelectedRowIndex);
				JOptionPane.showMessageDialog(null,"Data Dihapus");
			}
		});

		outputPanel.add(listName,BorderLayout.NORTH);
		outputPanel.add(tabelPanel, BorderLayout.CENTER);
		outputPanel.add(bawah, BorderLayout.SOUTH);
	}
}