package configuration;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class IntruderConfigurator extends JDialog  
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private final ButtonGroup autoPilotAlgorithmGroup = new ButtonGroup();
	private final ButtonGroup collisionAvoidanceAlgorithmGroup = new ButtonGroup();
	private final ButtonGroup selfSeparationAlgorithmGroup = new ButtonGroup();

	public IntruderConfigurator(String intruderAlias) 
	{
		final IntruderConfig intruderConfig = Configuration.getInstance().intrudersConfig.get(intruderAlias);		
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));		
		contentPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Configuring "+intruderAlias);
		lblTitle.setBounds(10, 0, 240, 33);
		contentPanel.add(lblTitle);
		
		{
			JPanel positionPanel = new JPanel();
			positionPanel.setBorder(new TitledBorder(null, "Position", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			positionPanel.setBounds(10, 62, 292, 91);
			contentPanel.add(positionPanel);
			positionPanel.setLayout(null);
			
			JLabel lblOffsetX = new JLabel("OffsetX");
			lblOffsetX.setBounds(10, 21, 44, 15);
			positionPanel.add(lblOffsetX);
			
			final JLabel offsetXLabel = new JLabel(""+intruderConfig.intruderOffsetX);
			offsetXLabel.setBounds(234, 21, 48, 15);
			positionPanel.add(offsetXLabel);
			
			JSlider selfOffsetXSlider = new JSlider();
			selfOffsetXSlider.setBounds(64, 21, 160, 16);
			positionPanel.add(selfOffsetXSlider);
			selfOffsetXSlider.setSnapToTicks(true);
			selfOffsetXSlider.setPaintLabels(true);		
			selfOffsetXSlider.setMaximum(30000);
			selfOffsetXSlider.setMinimum(-10000);
			selfOffsetXSlider.setValue((int)(intruderConfig.intruderOffsetX));
			selfOffsetXSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderOffsetX = source.getValue();
					offsetXLabel.setText(""+intruderConfig.intruderOffsetX);
				}
			});
			
			JLabel lblOffsetY = new JLabel("OffsetY");
			lblOffsetY.setBounds(10, 42, 44, 15);
			positionPanel.add(lblOffsetY);
			
			final JLabel offsetYLabel = new JLabel(""+intruderConfig.intruderOffsetY);
			offsetYLabel.setBounds(234, 42, 48, 15);
			positionPanel.add(offsetYLabel);
			
			JSlider selfOffsetYSlider = new JSlider();
			selfOffsetYSlider.setBounds(64, 42, 160, 16);
			positionPanel.add(selfOffsetYSlider);
			selfOffsetYSlider.setSnapToTicks(true);
			selfOffsetYSlider.setPaintLabels(true);		
			selfOffsetYSlider.setMaximum(500);
			selfOffsetYSlider.setMinimum(-500);
			selfOffsetYSlider.setValue((int)(intruderConfig.intruderOffsetY));
			selfOffsetYSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderOffsetY = source.getValue();
					offsetYLabel.setText(""+intruderConfig.intruderOffsetY);
				}
			});
			
			JLabel lblOffsetZ = new JLabel("OffsetZ");
			lblOffsetZ.setBounds(10, 65, 44, 15);
			positionPanel.add(lblOffsetZ);
			
			final JLabel offsetZLabel = new JLabel(""+intruderConfig.intruderOffsetZ);
			offsetZLabel.setBounds(234, 65, 48, 15);
			positionPanel.add(offsetZLabel);
			
			JSlider selfOffsetZSlider = new JSlider();
			selfOffsetZSlider.setBounds(64, 64, 160, 16);
			positionPanel.add(selfOffsetZSlider);
			selfOffsetZSlider.setSnapToTicks(true);
			selfOffsetZSlider.setPaintLabels(true);		
			selfOffsetZSlider.setMaximum(5000);
			selfOffsetZSlider.setMinimum(-5000);
			selfOffsetZSlider.setValue((int)(intruderConfig.intruderOffsetZ));
			selfOffsetZSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderOffsetZ = source.getValue();
					offsetZLabel.setText(""+intruderConfig.intruderOffsetZ);
				}
			});
			
		}
		
			
		{
			JPanel sensorSelectionPanel = new JPanel();
			sensorSelectionPanel.setBorder(new TitledBorder(null, "Sensor Selection", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			sensorSelectionPanel.setBounds(10, 164, 290, 47);
			contentPanel.add(sensorSelectionPanel);
			sensorSelectionPanel.setLayout(null);
			
			JCheckBox chckbxPerfectSensor = new JCheckBox("Perfect");
			chckbxPerfectSensor.setBounds(8, 20, 61, 23);
			sensorSelectionPanel.add(chckbxPerfectSensor);
			chckbxPerfectSensor.setSelected((intruderConfig.intruderSensorSelection&0B10000) == 0B10000);
			chckbxPerfectSensor.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(((JCheckBox)e.getSource()).isSelected())
					{
						intruderConfig.intruderSensorSelection |= 0B10000;
					}
				}
			});
			
			
			JCheckBox chckbxAdsb = new JCheckBox("ADS-B");
			chckbxAdsb.setBounds(71, 20, 55, 23);
			sensorSelectionPanel.add(chckbxAdsb);
			chckbxAdsb.setSelected((intruderConfig.intruderSensorSelection&0B01000) == 0B01000);
			chckbxAdsb.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(((JCheckBox)e.getSource()).isSelected())
					{
						intruderConfig.intruderSensorSelection |= 0B01000;
					}
				}
			});
			
			JCheckBox chckbxTcas = new JCheckBox("TCAS");
			chckbxTcas.setBounds(127, 20, 55, 23);
			sensorSelectionPanel.add(chckbxTcas);
			chckbxTcas.setSelected((intruderConfig.intruderSensorSelection&0B00100) == 0B00100);
			chckbxTcas.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(((JCheckBox)e.getSource()).isSelected())
					{
						intruderConfig.intruderSensorSelection |= 0B00100;
					}
				}
			});
			
			JCheckBox chckbxRadar = new JCheckBox("Radar");
			chckbxRadar.setBounds(178, 20, 55, 23);
			sensorSelectionPanel.add(chckbxRadar);
			chckbxRadar.setSelected((intruderConfig.intruderSensorSelection&0B00010) == 0B00010);
			chckbxRadar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(((JCheckBox)e.getSource()).isSelected())
					{
						intruderConfig.intruderSensorSelection |= 0B00010;
					}
				}
			});
			
			JCheckBox chckbxEoir = new JCheckBox("EO/IR");
			chckbxEoir.setBounds(229, 20, 55, 23);
			sensorSelectionPanel.add(chckbxEoir);
			chckbxEoir.setSelected((intruderConfig.intruderSensorSelection&0B00001) == 0B00001);
			chckbxEoir.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(((JCheckBox)e.getSource()).isSelected())
					{
						intruderConfig.intruderSensorSelection |= 0B00001;
					}
				}
			});
		}
				
		
		{
			JPanel autoPilotAlgorithmSelectionPanel = new JPanel();
			
			autoPilotAlgorithmSelectionPanel.setBorder(new TitledBorder(null, "Auto-Pilot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			autoPilotAlgorithmSelectionPanel.setBounds(10, 210, 290, 47);
			contentPanel.add(autoPilotAlgorithmSelectionPanel);
			autoPilotAlgorithmSelectionPanel.setLayout(null);
			
			
			JRadioButton rdbtnWhitenoise = new JRadioButton("WhiteNoise");
			rdbtnWhitenoise.setSelected(intruderConfig.intruderAutoPilotAlgorithmSelection=="WhiteNoise");
			rdbtnWhitenoise.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(((JRadioButton)e.getSource()).isSelected())
					{
						intruderConfig.intruderAutoPilotAlgorithmSelection="WhiteNoise";
					}
				}
			});
			rdbtnWhitenoise.setBounds(6, 19, 110, 23);
			autoPilotAlgorithmGroup.add(rdbtnWhitenoise);
			autoPilotAlgorithmSelectionPanel.add(rdbtnWhitenoise);
			
			
			JRadioButton rdbtnSpecific = new JRadioButton("Specific");
			rdbtnSpecific.setSelected(intruderConfig.intruderAutoPilotAlgorithmSelection=="Specific");
			rdbtnSpecific.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(((JRadioButton)e.getSource()).isSelected())
					{
						intruderConfig.intruderAutoPilotAlgorithmSelection="Specific";
					}
				}
			});			
			rdbtnSpecific.setBounds(203, 19, 77, 23);
			autoPilotAlgorithmGroup.add(rdbtnSpecific);
			autoPilotAlgorithmSelectionPanel.add(rdbtnSpecific);
			

			
		}		
	
		
		
		{
			JPanel collisionAvoidanceAlgorithmSelectionPanel = new JPanel();
			collisionAvoidanceAlgorithmSelectionPanel.setBorder(new TitledBorder(null, "CAA Selection", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			collisionAvoidanceAlgorithmSelectionPanel.setBounds(10, 256, 290, 47);
			contentPanel.add(collisionAvoidanceAlgorithmSelectionPanel);
			collisionAvoidanceAlgorithmSelectionPanel.setLayout(null);			
			
			JRadioButton rdbtnACASXAvoidanceAlgorithm = new JRadioButton("ACASX");
			rdbtnACASXAvoidanceAlgorithm.setBounds(6, 17, 94, 23);
			rdbtnACASXAvoidanceAlgorithm.setSelected(intruderConfig.intruderCollisionAvoidanceAlgorithmSelection == "ACASXAvoidanceAlgorithm");
			collisionAvoidanceAlgorithmSelectionPanel.add(rdbtnACASXAvoidanceAlgorithm);
			collisionAvoidanceAlgorithmGroup.add(rdbtnACASXAvoidanceAlgorithm);
			rdbtnACASXAvoidanceAlgorithm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(((JRadioButton)e.getSource()).isSelected())
					{
						intruderConfig.intruderCollisionAvoidanceAlgorithmSelection = "ACASXAvoidanceAlgorithm";
					}
				}
			});
			
			JRadioButton rdbtnACASX3DAvoidanceAlgorithm = new JRadioButton("ACASX3D");
			rdbtnACASX3DAvoidanceAlgorithm.setBounds(102, 17, 94, 23);
			rdbtnACASX3DAvoidanceAlgorithm.setSelected(intruderConfig.intruderCollisionAvoidanceAlgorithmSelection == "ACASX3DAvoidanceAlgorithm");
			collisionAvoidanceAlgorithmSelectionPanel.add(rdbtnACASX3DAvoidanceAlgorithm);
			collisionAvoidanceAlgorithmGroup.add(rdbtnACASX3DAvoidanceAlgorithm);
			rdbtnACASX3DAvoidanceAlgorithm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(((JRadioButton)e.getSource()).isSelected())
					{
						intruderConfig.intruderCollisionAvoidanceAlgorithmSelection = "ACASX3DAvoidanceAlgorithm";
					}
				}
			});
			
			JRadioButton rdbtnNone = new JRadioButton("None");
			rdbtnNone.setBounds(206, 17, 62, 23);
			rdbtnNone.setSelected(intruderConfig.intruderCollisionAvoidanceAlgorithmSelection == "None");
			collisionAvoidanceAlgorithmSelectionPanel.add(rdbtnNone);
			collisionAvoidanceAlgorithmGroup.add(rdbtnNone);
			rdbtnNone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(((JRadioButton)e.getSource()).isSelected())
					{
						intruderConfig.intruderCollisionAvoidanceAlgorithmSelection = "None";
					}
				}
			});
		}
		
		
		{
			JPanel selfSeparationAlgorithmSelectionPanel = new JPanel();
			selfSeparationAlgorithmSelectionPanel.setBounds(10, 304, 290, 47);
			contentPanel.add(selfSeparationAlgorithmSelectionPanel);
			selfSeparationAlgorithmSelectionPanel.setBorder(new TitledBorder(null, "SSA Selection", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			selfSeparationAlgorithmSelectionPanel.setLayout(null);			
			
			JRadioButton rdbtnNASAChorusAlgorithm = new JRadioButton("Chorus");
			rdbtnNASAChorusAlgorithm.setBounds(6, 17, 94, 23);
			rdbtnNASAChorusAlgorithm.setSelected(intruderConfig.intruderSelfSeparationAlgorithmSelection == "NASAChorusAlgorithm");
			selfSeparationAlgorithmSelectionPanel.add(rdbtnNASAChorusAlgorithm);
			selfSeparationAlgorithmGroup.add(rdbtnNASAChorusAlgorithm);
			rdbtnNASAChorusAlgorithm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(((JRadioButton)e.getSource()).isSelected())
					{
						intruderConfig.intruderSelfSeparationAlgorithmSelection = "NASAChorusAlgorithm";
					}
				}
			});
			
				
			JRadioButton rdbtnNone_1 = new JRadioButton("None");
			rdbtnNone_1.setBounds(206, 17, 62, 23);
			rdbtnNone_1.setSelected(intruderConfig.intruderSelfSeparationAlgorithmSelection == "None");
			selfSeparationAlgorithmSelectionPanel.add(rdbtnNone_1);
			selfSeparationAlgorithmGroup.add(rdbtnNone_1);
			rdbtnNone_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(((JRadioButton)e.getSource()).isSelected())
					{
						intruderConfig.intruderSelfSeparationAlgorithmSelection = "None";
					}
				}
			});
		}
	
		{
			JPanel otherPanel = new JPanel();
			otherPanel.setBackground(Color.LIGHT_GRAY);
			otherPanel.setBounds(12, 362, 290, 133);
			contentPanel.add(otherPanel);
			otherPanel.setLayout(null);
			
			JLabel lblVx = new JLabel("VX");
			lblVx.setBounds(10, 11, 27, 15);
			otherPanel.add(lblVx);
			
			final JLabel vxLabel = new JLabel(""+intruderConfig.intruderVx);
			vxLabel.setBounds(232, 11, 58, 15);
			otherPanel.add(vxLabel);
			
			final JComboBox comboBox = new JComboBox();			
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"+", "-"}));
			comboBox.setSelectedIndex(intruderConfig.intruderVx>0?0:1);
			comboBox.setBounds(33, 8, 34, 20);
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					intruderConfig.intruderVx = (comboBox.getSelectedIndex()==0?1:-1)*Math.abs(intruderConfig.intruderVx);
					vxLabel.setText(""+intruderConfig.intruderVx);
				}
			});
			otherPanel.add(comboBox);
			
		
			
			JSlider selfVxSlider = new JSlider();
			selfVxSlider.setBounds(64, 11, 161, 16);
			otherPanel.add(selfVxSlider);
			selfVxSlider.setSnapToTicks(true);
			selfVxSlider.setPaintLabels(true);		
			selfVxSlider.setMaximum(304);
			selfVxSlider.setMinimum(169);
			selfVxSlider.setValue(Math.abs((int)(intruderConfig.intruderVx)));
			selfVxSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderVx = (intruderConfig.intruderVx>0?1:-1)*source.getValue();
					vxLabel.setText(""+intruderConfig.intruderVx);
				}
			});
			
			JLabel lblVy = new JLabel("VY");
			lblVy.setBounds(10, 31, 37, 15);
			otherPanel.add(lblVy);
			
			final JLabel vyLabel = new JLabel(""+intruderConfig.intruderVy);
			vyLabel.setBounds(232, 31, 58, 15);
			otherPanel.add(vyLabel);
		
			JSlider selfVySlider = new JSlider();
			selfVySlider.setBounds(64, 31, 161, 16);
			otherPanel.add(selfVySlider);
			selfVySlider.setSnapToTicks(true);
			selfVySlider.setPaintLabels(true);		
			selfVySlider.setMaximum(58);
			selfVySlider.setMinimum(-67);
			selfVySlider.setValue((int)(intruderConfig.intruderVy));
			selfVySlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderVy = source.getValue();
					vyLabel.setText(""+intruderConfig.intruderVy);

				}
			});
			
			JLabel lblVz = new JLabel("VZ");
			lblVz.setBounds(10, 51, 37, 15);
			otherPanel.add(lblVz);
			
			final JLabel vzLabel = new JLabel(""+intruderConfig.intruderVz);
			vzLabel.setBounds(232, 51, 58, 15);
			otherPanel.add(vzLabel);
		
			JSlider selfVzSlider = new JSlider();
			selfVzSlider.setBounds(64, 51, 161, 16);
			otherPanel.add(selfVzSlider);
			selfVzSlider.setSnapToTicks(true);
			selfVzSlider.setPaintLabels(true);		
			selfVzSlider.setMaximum(58);
			selfVzSlider.setMinimum(-67);
			selfVzSlider.setValue((int)(intruderConfig.intruderVz));
			selfVzSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderVz = source.getValue();
					vzLabel.setText(""+intruderConfig.intruderVz);

				}
			});
			
			JLabel lblStdDevX = new JLabel("SDX");
			lblStdDevX.setBounds(10, 71, 37, 15);
			otherPanel.add(lblStdDevX);
			
			final JLabel stdDevXLabel = new JLabel(""+intruderConfig.intruderStdDevX);
			stdDevXLabel.setBounds(232, 71, 58, 15);
			otherPanel.add(stdDevXLabel);
			
			JSlider headOnStdDevXSlider = new JSlider();
			headOnStdDevXSlider.setBounds(64, 71, 161, 16);
			otherPanel.add(headOnStdDevXSlider);
			headOnStdDevXSlider.setSnapToTicks(true);
			headOnStdDevXSlider.setPaintLabels(true);		
			headOnStdDevXSlider.setMaximum(15);
			headOnStdDevXSlider.setMinimum(0);
			headOnStdDevXSlider.setValue((int)(intruderConfig.intruderStdDevX));
			headOnStdDevXSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderStdDevX = source.getValue();
					stdDevXLabel.setText(""+intruderConfig.intruderStdDevX);
				}
			});
			
			JLabel lblStdDevY = new JLabel("SDY");
			lblStdDevY.setBounds(10, 91, 37, 15);
			otherPanel.add(lblStdDevY);
			
			final JLabel stdDevYLabel = new JLabel(""+intruderConfig.intruderStdDevY);
			stdDevYLabel.setBounds(232, 91, 58, 15);
			otherPanel.add(stdDevYLabel);
			
			JSlider headOnStdDevYSlider = new JSlider();
			headOnStdDevYSlider.setBounds(64, 91, 161, 16);
			otherPanel.add(headOnStdDevYSlider);
			headOnStdDevYSlider.setSnapToTicks(true);
			headOnStdDevYSlider.setPaintLabels(true);		
			headOnStdDevYSlider.setMaximum(15);
			headOnStdDevYSlider.setMinimum(0);
			headOnStdDevYSlider.setValue((int)(intruderConfig.intruderStdDevY));
			headOnStdDevYSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderStdDevY = source.getValue();
					stdDevYLabel.setText(""+intruderConfig.intruderStdDevY);
				}
			});
		
			
			JLabel lblStdDevZ = new JLabel("SDZ");
			lblStdDevZ.setBounds(10, 111, 37, 15);
			otherPanel.add(lblStdDevZ);
			
			final JLabel stdDevZLabel = new JLabel(""+intruderConfig.intruderStdDevZ);
			stdDevZLabel.setBounds(232, 111, 58, 15);
			otherPanel.add(stdDevZLabel);
			
			JSlider headOnStdDevZSlider = new JSlider();
			headOnStdDevZSlider.setBounds(64, 111, 161, 16);
			otherPanel.add(headOnStdDevZSlider);
			headOnStdDevZSlider.setSnapToTicks(true);
			headOnStdDevZSlider.setPaintLabels(true);		
			headOnStdDevZSlider.setMaximum(15);
			headOnStdDevZSlider.setMinimum(0);
			headOnStdDevZSlider.setValue((int)(intruderConfig.intruderStdDevZ));
			headOnStdDevZSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					intruderConfig.intruderStdDevZ = source.getValue();
					stdDevZLabel.setText(""+intruderConfig.intruderStdDevZ);
				}
			});
		}
		
		
		{			
			JPanel performancePanel = new JPanel();
			performancePanel.setBackground(Color.LIGHT_GRAY);
			performancePanel.setBounds(10, 506, 290, 166);
			contentPanel.add(performancePanel);
			performancePanel.setLayout(null);
			JLabel lblMaxspeed = new JLabel("MaxSpeed");
			lblMaxspeed.setBounds(12, 14, 82, 15);
			performancePanel.add(lblMaxspeed);
			
			
			JTextField maxSpeedTextField_1 = new JTextField();
			maxSpeedTextField_1.setBounds(170, 14, 114, 19);
			performancePanel.add(maxSpeedTextField_1);
			maxSpeedTextField_1.setText(String.valueOf(intruderConfig.intruderMaxSpeed));
			maxSpeedTextField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					JTextField maxSpeedTextField = (JTextField) e.getSource();
					intruderConfig.intruderMaxSpeed = new Double(maxSpeedTextField.getText());
				}
			});
			maxSpeedTextField_1.setColumns(10);
			
			
			JLabel lblMinspeed = new JLabel("MinSpeed");
			lblMinspeed.setBounds(12, 43, 70, 19);
			performancePanel.add(lblMinspeed);
			
			
			JTextField minSpeedTextField_1 = new JTextField();
			minSpeedTextField_1.setBounds(170, 45, 114, 19);
			performancePanel.add(minSpeedTextField_1);
			minSpeedTextField_1.setText(String.valueOf(intruderConfig.intruderMinSpeed));
			minSpeedTextField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					JTextField minSpeedTextField = (JTextField) e.getSource();
					intruderConfig.intruderMinSpeed = new Double(minSpeedTextField.getText());
				}
			});
			minSpeedTextField_1.setColumns(10);
			
			JLabel lblMaxClimb = new JLabel("MaxClimb");
			lblMaxClimb.setBounds(11, 75, 70, 19);
			performancePanel.add(lblMaxClimb);
			
			
			JTextField maxClimbTextField_1 = new JTextField();
			maxClimbTextField_1.setBounds(169, 73, 114, 19);
			performancePanel.add(maxClimbTextField_1);
			maxClimbTextField_1.setText(String.valueOf(intruderConfig.intruderMaxClimb));
			maxClimbTextField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					JTextField maxClimbTextField = (JTextField) e.getSource();
					intruderConfig.intruderMaxClimb = new Double(maxClimbTextField.getText());
				}
			});
			maxClimbTextField_1.setColumns(10);
			
			JLabel lblMaxDescent = new JLabel("MaxDescent");
			lblMaxDescent.setBounds(11, 105, 101, 19);
			performancePanel.add(lblMaxDescent);
			
			
			JTextField maxDescentTextField_1 = new JTextField();
			maxDescentTextField_1.setBounds(169, 107, 114, 19);
			performancePanel.add(maxDescentTextField_1);
			maxDescentTextField_1.setText(String.valueOf(intruderConfig.intruderMaxDescent));
			maxDescentTextField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					JTextField maxDescentTextField = (JTextField) e.getSource();
					intruderConfig.intruderMaxDescent = new Double(maxDescentTextField.getText());
				}
			});
			maxDescentTextField_1.setColumns(10);
			
			JLabel lblMaxturning = new JLabel("MaxTurning");
			lblMaxturning.setBounds(11, 136, 82, 15);
			performancePanel.add(lblMaxturning);
			
			{
				
				JTextField maxTurningTextField_1 = new JTextField();
				maxTurningTextField_1.setBounds(170, 138, 114, 19);
				performancePanel.add(maxTurningTextField_1);
				maxTurningTextField_1.setText(String.valueOf(Math.round(Math.toDegrees(intruderConfig.intruderMaxTurning)*100)/100.0));
				maxTurningTextField_1.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						JTextField maxTurningTextField = (JTextField) e.getSource();
						intruderConfig.intruderMaxTurning = Math.toRadians(new Double(maxTurningTextField.getText()));
					}
				});
				maxTurningTextField_1.setColumns(10);
			}
		}
		
		setModal(true);
		setBounds(1240, 380, 340, 784); // for windows: setBounds(1160, 380, 347, 474); 
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		btnOk.setBounds(213, 700, 89, 23);
		contentPanel.add(btnOk);
		this.setVisible(true);
								
	}
}
