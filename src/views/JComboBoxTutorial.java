package views;

import javax.swing.*;

import main.JSONretriever;
import services.RExecuterService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class JComboBoxTutorial {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JComboBox<String> comboBox = new JComboBox<String>();
	static DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	JTextField txtAdd = new JTextField(15);
	JLabel labelJSONS = new JLabel();
	JLabel labelGetData = new JLabel();
	JLabel labelGetTrees = new JLabel();
	JLabel labelNormalize = new JLabel();
	JLabel labelOk = new JLabel();
	JButton btnRunJsons = new JButton("Run");
	JButton btnRunGetData = new JButton("RunGetData");
	JButton btnRunGetTrees = new JButton("RunGetTrees");
	JButton btnRunNormalize = new JButton("RunNormalize");
	String selectedValue;
	RExecuterService service = new RExecuterService();

	public JComboBoxTutorial() {
		comboBox.setModel(model);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectedValue = model.getSelectedItem().toString();
				}
			}
		});

		btnRunJsons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (selectedValue.compareToIgnoreCase("all")!=0) {
						if (JSONretriever.extractJSONFrom(Integer.parseInt(selectedValue))) {
							panel.add(labelOk);
						}
					} else {
						JSONretriever.execAll();
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRunGetData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (service.Run(Integer.parseInt(selectedValue))) {
						service.runCSVLoad(service.getNumberOfFiles(),Integer.parseInt(selectedValue));
						panel.add(labelOk);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			
		});
		btnRunGetTrees.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (service.getTrees(Integer.parseInt(selectedValue))) {
						panel.add(labelOk);
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRunNormalize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (service.normalize(Integer.parseInt(selectedValue)))
						panel.add(labelOk);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		labelJSONS.setText("Download JSONS: ");
		labelOk.setText("----OK!");
		panel.add(labelJSONS);
		panel.add(comboBox);
		panel.add(btnRunJsons);
//		panel.add(labelGetData);
//		panel.add(btnRunGetData);
//		panel.add(labelGetTrees);
//		panel.add(btnRunGetTrees);
//		panel.add(labelNormalize);
//		panel.add(btnRunNormalize);
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		for (int i = 1; i <= 114; i++)
			model.addElement(i + "");
		model.addElement("all");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new JComboBoxTutorial();
			}
		});
	}
}