package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Connection.DBConnection;
import Logico.Disegnador;
import Logico.Empleado;
import Logico.Empresa;
import Logico.Jefe;
import Logico.Planificador;
import Logico.Programador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.LineBorder;
import java.awt.Component;

public class RegEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDireccion;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JLabel lblEspecialidades;
	private JCheckBox chckbxJava;
	private JCheckBox chckbxHtml;
	private JCheckBox chckbxCpp;
	private JLabel lblFrecuencia;
	private JSpinner spnFrecuencia;
	private JRadioButton rdbtnJefe;
	private JRadioButton rdbtnProgramador;
	private JRadioButton rdbtnPlanificador;
	private JRadioButton rdbtnDisegnador;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JTextField txtSalarioHora;
	private JSpinner spnEdad;
	private JLabel lblFotoMujer;
	private JLabel lblFotoHombre;
	private JLabel lblOcupacion;
	private JLabel lblInformacionPersonal;
	private JLabel lblNewLabel;
	private JLabel lblTelefono;
	private JTextField txtTelefono2;
	private JTextField txtCorreo;
	private JLabel lblCedula;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JCheckBox chckbxCSharp; 
	private JCheckBox chckbxPython;
	private JSpinner spnSalario;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public RegEmpleado() {
		setBounds(100, 100, 747, 533);
		setResizable(false);
		setTitle("Registrar empleado");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JPanel panel_InfoPersonal = new JPanel();
		panel_InfoPersonal.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_InfoPersonal.setBounds(173, 11, 560, 246);
		contentPanel.add(panel_InfoPersonal);
		panel_InfoPersonal.setLayout(null);
		{
			lblInformacionPersonal = new JLabel("Informacion personal");
			lblInformacionPersonal.setBackground(Color.WHITE);
			lblInformacionPersonal.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblInformacionPersonal.setHorizontalAlignment(SwingConstants.CENTER);
			lblInformacionPersonal.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblInformacionPersonal.setBounds(0, 0, 560, 28);
			panel_InfoPersonal.add(lblInformacionPersonal);
		}
		{
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(22, 89, 56, 16);
			panel_InfoPersonal.add(lblNombre);
		}
		{
			JLabel lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(22, 119, 56, 16);
			panel_InfoPersonal.add(lblApellidos);
		}
		{
			JLabel lblSexo = new JLabel("Genero:");
			lblSexo.setBounds(32, 167, 46, 16);
			panel_InfoPersonal.add(lblSexo);
		}
		{
			JLabel lblEdad = new JLabel("Edad: ");
			lblEdad.setBounds(33, 203, 56, 16);
			panel_InfoPersonal.add(lblEdad);
		}
		{
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(273, 179, 70, 16);
			panel_InfoPersonal.add(lblDireccion);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.setBounds(339, 179, 172, 43);
			panel_InfoPersonal.add(txtDireccion);
			txtDireccion.setColumns(10);
		}
		{
			
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(80, 82, 172, 22);
			panel_InfoPersonal.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.setBounds(80, 116, 172, 22);
			panel_InfoPersonal.add(txtApellidos);
			txtApellidos.setColumns(10);
		}
		
		rdbtnHombre = new JRadioButton("");
		rdbtnHombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rdbtnMujer.setSelected(false);
				
				if(!rdbtnHombre.isSelected()) {
					rdbtnHombre.setSelected(true);
				}
				
			}
		});
		rdbtnHombre.setSelected(true);
		rdbtnHombre.setBounds(84, 167, 25, 25);
		panel_InfoPersonal.add(rdbtnHombre);
		
		rdbtnMujer = new JRadioButton("");
		rdbtnMujer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnHombre.setSelected(false);
				
				if(!rdbtnMujer.isSelected()) {
					rdbtnMujer.setSelected(true);
				}
			}
		});
		rdbtnMujer.setBounds(159, 167, 25, 25);
		panel_InfoPersonal.add(rdbtnMujer);
		
		spnEdad = new JSpinner();
		spnEdad.setModel(new SpinnerNumberModel(new Integer(18), new Integer(18), null, new Integer(1)));
		spnEdad.setBounds(80, 200, 46, 22);
		panel_InfoPersonal.add(spnEdad);
		{
			lblFotoMujer = new JLabel("");
			lblFotoMujer.setIcon(new ImageIcon(RegEmpleado.class.getResource("/img/empleada.png")));
			lblFotoMujer.setBounds(190, 138, 62, 54);
			panel_InfoPersonal.add(lblFotoMujer);
		}
		{
			lblFotoHombre = new JLabel("");
			lblFotoHombre.setIcon(new ImageIcon(RegEmpleado.class.getResource("/img/empleado.png")));
			lblFotoHombre.setBounds(107, 138, 46, 54);
			panel_InfoPersonal.add(lblFotoHombre);
		}
		{
			lblTelefono = new JLabel("Telefono 1:");
			lblTelefono.setBounds(275, 53, 68, 14);
			panel_InfoPersonal.add(lblTelefono);
		}
		
		JLabel lblTelefono_1 = new JLabel("Telefono 2:");
		lblTelefono_1.setBounds(274, 86, 69, 14);
		panel_InfoPersonal.add(lblTelefono_1);
		
		txtTelefono2 = new JTextField();
		txtTelefono2.setBounds(339, 83, 172, 22);
		panel_InfoPersonal.add(txtTelefono2);
		txtTelefono2.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(287, 120, 46, 14);
		panel_InfoPersonal.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(339, 117, 172, 21);
		panel_InfoPersonal.add(txtCorreo);
		txtCorreo.setColumns(10);
		{
			lblCedula = new JLabel("Cedula:");
			lblCedula.setBounds(22, 53, 46, 14);
			panel_InfoPersonal.add(lblCedula);
		}
		{
			txtCedula = new JTextField();
			txtCedula.setBounds(80, 50, 172, 20);
			panel_InfoPersonal.add(txtCedula);
			txtCedula.setColumns(10);
		}
		{
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(339, 50, 172, 22);
			panel_InfoPersonal.add(txtTelefono);
		}
		{
			JPanel panelOcupacion = new JPanel();
			panelOcupacion.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panelOcupacion.setBounds(173, 256, 560, 109);
			contentPanel.add(panelOcupacion);
			{
				rdbtnJefe = new JRadioButton("Jefe");
				rdbtnJefe.setBounds(64, 43, 90, 25);
				rdbtnJefe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						rdbtnProgramador.setSelected(false);
						rdbtnPlanificador.setSelected(false);
						rdbtnDisegnador.setSelected(false);
						
						lblEspecialidades.setVisible(false);
						chckbxJava.setVisible(false);
						chckbxHtml.setVisible(false);
						chckbxCpp.setVisible(false);
						chckbxCSharp.setVisible(false);
						chckbxPython.setVisible(false);
						
						
						lblFrecuencia.setVisible(false);
						spnFrecuencia.setVisible(false);
						
						if(!rdbtnJefe.isSelected()) {
							rdbtnJefe.setSelected(true);
						}
						
					}
				});
				panelOcupacion.setLayout(null);
				rdbtnJefe.setSelected(true);
				panelOcupacion.add(rdbtnJefe);
			}
			{
				rdbtnProgramador = new JRadioButton("Programador");
				rdbtnProgramador.setBounds(156, 43, 127, 25);
				rdbtnProgramador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					
						rdbtnJefe.setSelected(false);
						rdbtnPlanificador.setSelected(false);
						rdbtnDisegnador.setSelected(false);
						
						lblEspecialidades.setVisible(true);
						chckbxJava.setVisible(true);
						chckbxHtml.setVisible(true);
						chckbxCpp.setVisible(true);
						chckbxCSharp.setVisible(true);
						chckbxPython.setVisible(true);
						
						lblFrecuencia.setVisible(false);
						spnFrecuencia.setVisible(false);
						
						if(!rdbtnProgramador.isSelected()) {
							rdbtnProgramador.setSelected(true);
						}
					}
				});
				rdbtnProgramador.setSelected(false);
				panelOcupacion.add(rdbtnProgramador);
			}
			{
				rdbtnPlanificador = new JRadioButton("Planificador");
				rdbtnPlanificador.setBounds(285, 43, 127, 25);
				rdbtnPlanificador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						
						rdbtnJefe.setSelected(false);
						rdbtnProgramador.setSelected(false);
						rdbtnDisegnador.setSelected(false);
						
						lblEspecialidades.setVisible(false);
						chckbxJava.setVisible(false);
						chckbxHtml.setVisible(false);
						chckbxCpp.setVisible(false);
						chckbxCSharp.setVisible(false);
						chckbxPython.setVisible(false);
						
						lblFrecuencia.setVisible(true);
						spnFrecuencia.setVisible(true);
						
						if(!rdbtnPlanificador.isSelected()) {
							rdbtnPlanificador.setSelected(true);
						}
					}
				});
				panelOcupacion.add(rdbtnPlanificador);
			}
			{
				rdbtnDisegnador = new JRadioButton("Dise\u00F1ador");
				rdbtnDisegnador.setBounds(414, 43, 127, 25);
				rdbtnDisegnador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						
						rdbtnJefe.setSelected(false);
						rdbtnProgramador.setSelected(false);
						rdbtnPlanificador.setSelected(false);

						lblEspecialidades.setVisible(false);
						chckbxJava.setVisible(false);
						chckbxHtml.setVisible(false);
						chckbxCpp.setVisible(false);
						chckbxCSharp.setVisible(false);
						chckbxPython.setVisible(false);
						
						lblFrecuencia.setVisible(false);
						spnFrecuencia.setVisible(false);
						
						if(!rdbtnDisegnador.isSelected()) {
							rdbtnDisegnador.setSelected(true);
						}
					}
				});
				panelOcupacion.add(rdbtnDisegnador);
			}
			{
				lblOcupacion = new JLabel("Puesto de trabajo a ocupar");
				lblOcupacion.setBorder(new LineBorder(new Color(0, 0, 0)));
				lblOcupacion.setHorizontalAlignment(SwingConstants.CENTER);
				lblOcupacion.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblOcupacion.setBounds(0, 0, 560, 25);
				panelOcupacion.add(lblOcupacion);
			}
		}
		{
			JPanel panel_InfoAdicional = new JPanel();
			panel_InfoAdicional.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel_InfoAdicional.setBounds(173, 364, 560, 92);
			contentPanel.add(panel_InfoAdicional);
			panel_InfoAdicional.setLayout(null);
			{
				JLabel lblSalariohora = new JLabel("Salario/hora:");
				lblSalariohora.setBounds(12, 48, 85, 16);
				panel_InfoAdicional.add(lblSalariohora);
			}
			{
				lblEspecialidades = new JLabel("Especialidades:");
				lblEspecialidades.setVisible(false);
				lblEspecialidades.setBounds(232, 48, 99, 16);
				panel_InfoAdicional.add(lblEspecialidades);
			}
			
			chckbxJava = new JCheckBox("Java");
			chckbxJava.setVisible(false);
			chckbxJava.setBounds(339, 44, 69, 25);
			panel_InfoAdicional.add(chckbxJava);
			
			chckbxHtml = new JCheckBox("HTML/Javascript");
			chckbxHtml.setVisible(false);
			chckbxHtml.setBounds(412, 44, 106, 25);
			panel_InfoAdicional.add(chckbxHtml);
			
			chckbxCpp = new JCheckBox("C++");
			chckbxCpp.setVisible(false);
			chckbxCpp.setBounds(410, 68, 49, 25);
			panel_InfoAdicional.add(chckbxCpp);
			{
				lblFrecuencia = new JLabel("Frecuencia:");
				lblFrecuencia.setVisible(false);
				lblFrecuencia.setBounds(232, 48, 85, 16);
				panel_InfoAdicional.add(lblFrecuencia);//
			}
			
			spnFrecuencia = new JSpinner();
			spnFrecuencia.setVisible(false);
			spnFrecuencia.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnFrecuencia.setBounds(343, 45, 69, 22);
			panel_InfoAdicional.add(spnFrecuencia);
			
			spnSalario = new JSpinner();
			
			Float value = new Float(1.0);
			Float step = new Float(0.1);
			SpinnerNumberModel model = new SpinnerNumberModel(value, new Float(1), null, step);
			spnSalario.setModel(model);
			spnSalario.setBounds(95, 45, 64, 22);
			panel_InfoAdicional.add(spnSalario);
			{
				txtSalarioHora = new JTextField();
				txtSalarioHora.setBounds(95, 45, 99, 22);
				//panel_InfoAdicional.add(txtSalarioHora);
				txtSalarioHora.setColumns(10);
			}
			{
				lblNewLabel = new JLabel("Informacion adicional");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
				lblNewLabel.setBounds(0, 0, 560, 25);
				panel_InfoAdicional.add(lblNewLabel);
			}
			
			chckbxCSharp = new JCheckBox("C#");
			chckbxCSharp.setVisible(false);
			chckbxCSharp.setBounds(339, 69, 69, 23);
			panel_InfoAdicional.add(chckbxCSharp);
			
			chckbxPython = new JCheckBox("Python");
			chckbxPython.setVisible(false);
			chckbxPython.setBounds(463, 69, 97, 23);
			panel_InfoAdicional.add(chckbxPython);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 172, 445);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Empleados/empleados_ventana.jpg")).getImage().getScaledInstance(320, 350, Image.SCALE_SMOOTH)));
		//lblNewLabel_1.setIcon(new ImageIcon(RegEmpleado.class.getResource("/img/Empleados/empleados_ventana.jpg")));
		lblNewLabel_1.setBounds(-58, 0, 230, 445);
		panel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String cedula = txtCedula.getText();
						String telefono = txtTelefono.getText();
						String telefono2 = txtTelefono2.getText();
						String correo = txtCorreo.getText();
						String nombre = txtNombre.getText();
						String apellidos = txtApellidos.getText();
						String sexo;
						
						
						if(rdbtnHombre.isSelected()) {
							sexo = "H";
						}
						else {
							sexo = "M";
						}
						int edad = (int) spnEdad.getValue();
						String direccion = txtDireccion.getText();
						float salarioHora = Float.valueOf(spnSalario.getValue().toString());
						
						if(Empresa.getInstance().ValidadorFlotantes(txtSalarioHora.getText())) {
							salarioHora = (Float)spnSalario.getValue();//Float.valueOf(txtSalarioHora.getText());
						}
						
						ArrayList<String> especialidades = new ArrayList<>();
						if(chckbxJava.isSelected()) {
							especialidades.add("Java");
						}
						if(chckbxHtml.isSelected()) {
							especialidades.add("HTML");
						}
						if(chckbxCpp.isSelected()) {
							especialidades.add("C++");
						}
						if(chckbxCSharp.isSelected())
						{
							especialidades.add("C#");
						}
						if(chckbxPython.isSelected())
						{
							especialidades.add("Python");
						}
						
						int frecuencia = (int) spnFrecuencia.getValue();
						
//<<<<<<< HEAD
						/*if(!Empresa.getInstance().ValidadorNombre(nombre) || !Empresa.getInstance().ValidadorNombre(apellidos) ||!Empresa.getInstance().ValidadorNumeros(cedula)  ||!Empresa.getInstance().ValidadorNumeros(telefono)
//<<<<<<< HEAD
								||!Empresa.getInstance().ValidadorNumeros(telefono2) || direccion.equals("")) {
							//
//=======
=======
						if(!Empresa.getInstance().ValidadorNombre(nombre) || !Empresa.getInstance().ValidadorNombre(apellidos) ||!Empresa.getInstance().ValidadorNumeros(cedula)  ||!Empresa.getInstance().ValidadorNumeros(telefono)
>>>>>>> branch 'master' of https://github.com/jean18699/ProyectoFinalP1.git
								||!Empresa.getInstance().ValidadorNombre(nombre)|| salarioHora == 0 || direccion.equals("")) {
							
//>>>>>>> branch 'master' of https://github.com/jean18699/ProyectoFinalP1.git
							JOptionPane.showMessageDialog(null, "Por favor, llene correctamente todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
							if(!Empresa.getInstance().ValidadorNombre(nombre)) {
								txtNombre.setForeground(Color.RED);
							}
							if(!Empresa.getInstance().ValidadorNombre(apellidos)) {
								txtApellidos.setForeground(Color.RED);
							}
							/*if(direccion.equals("")) {
								txtDireccion.setForeground(Color.RED);
<<<<<<< HEAD
<<<<<<< HEAD
							}
							else {
								txtDireccion.setForeground(Color.BLACK);
							}
							if(salarioHora == 0) {//
								txtSalarioHora.setForeground(Color.RED);
=======
>>>>>>> branch 'master' of https://github.com/jean18699/ProyectoFinalP1.git
=======
							}*/
						
						if(Empresa.getInstance().ValidadorNombre(nombre) && Empresa.getInstance().ValidadorNombre(apellidos) && Empresa.getInstance().getInstance().ValidadorNumeros(telefono) && Empresa.getInstance().ValidadorNumeros(telefono2)) {
						
							if(rdbtnProgramador.isSelected() && !chckbxJava.isSelected() && !chckbxHtml.isSelected() && !chckbxCpp.isSelected() && !chckbxCSharp.isSelected() && !chckbxPython.isSelected()) {
								JOptionPane.showMessageDialog(null, "Por favor, seleccione una o más especialidades", "Advertencia", JOptionPane.WARNING_MESSAGE);
							}
							
							else {
														
									if(rdbtnJefe.isSelected()) {
										Empleado jefe = new Jefe(cedula,nombre, apellidos, sexo, edad,telefono,telefono2, direccion, salarioHora);
										Empresa.getInstance().nuevoEmpleado(jefe);
										//JOptionPane.showMessageDialog(null, "Identificador asignado: "+jefe.getId(), "Registro completo", JOptionPane.INFORMATION_MESSAGE);
										try {
											DBConnection.getInstance().agregarJefe(jefe.getId(),cedula, nombre, apellidos, sexo, telefono, telefono2, correo, direccion, edad, salarioHora);					} catch (ClassNotFoundException | SQLException e1) {
											e1.printStackTrace();
										}
									
										
									}
									else if(rdbtnProgramador.isSelected()) {
										Empleado programador = new Programador(cedula,nombre, apellidos, sexo, edad,telefono,telefono2, direccion, salarioHora, especialidades);
										Empresa.getInstance().nuevoEmpleado(programador);
										try {
											DBConnection.getInstance().agregarProgramador(programador.getId(),cedula, nombre, apellidos, sexo, telefono, telefono2, correo, direccion, edad, salarioHora);
											for(int i = 0; i < especialidades.size();i++)
											{
												DBConnection.getInstance().agregarEspecialidadProgramador(programador.getId(),especialidades.get(i));	
											}
											
										} catch (ClassNotFoundException | SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
										//JOptionPane.showMessageDialog(null, "Identificador asignado: "+identifi, "Registro completo", JOptionPane.INFORMATION_MESSAGE);
									}
									else if(rdbtnPlanificador.isSelected()) {
										Empleado planificador = new Planificador(cedula,nombre, apellidos, sexo, edad,telefono,telefono2, direccion, salarioHora, frecuencia);
										Empresa.getInstance().nuevoEmpleado(planificador);
										try {
											DBConnection.getInstance().agregarPlanificador(planificador.getId(),cedula, nombre, apellidos, sexo, telefono, telefono2, correo, direccion, edad, salarioHora, frecuencia);
										} catch (ClassNotFoundException | SQLException e1) {
											e1.printStackTrace();
										}
										
										//JOptionPane.showMessageDialog(null, "Identificador asignado: "+planificador.getId(), "Registro completo", JOptionPane.INFORMATION_MESSAGE);
									}
									else if(rdbtnDisegnador.isSelected()) {
										Empleado Disegnador = new Disegnador(cedula,nombre, apellidos, sexo, edad,telefono,telefono2, direccion, salarioHora);
										try {
											DBConnection.getInstance().agregarDisegnador(Disegnador.getId(),cedula, nombre, apellidos, sexo, telefono, telefono2, correo, direccion, edad, salarioHora);
										} catch (ClassNotFoundException | SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
										/*Empleado disegnador = new Disegnador(cedula,nombre, apellidos, sexo, edad,telefono,telefono2, direccion, salarioHora);
										Empresa.getInstance().nuevoEmpleado(disegnador);
										JOptionPane.showMessageDialog(null, "Identificador asignado: "+disegnador.getId(), "Registro completo", JOptionPane.INFORMATION_MESSAGE);
									*/}
								cleanFields();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Por favor, llene correctamente todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	private void cleanFields() {
		
		txtCedula.setText("");
		txtTelefono.setText("");
		txtTelefono2.setText("");
		txtCorreo.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		rdbtnHombre.setSelected(true);
		rdbtnMujer.setSelected(false);
		spnEdad.setValue(18);
		txtDireccion.setText("");
		/*rdbtnJefe.setSelected(true);
		
		rdbtnProgramador.setSelected(false);
		rdbtnPlanificador.setSelected(false);
		rdbtnDisegnador.setSelected(false);*/
		
		txtSalarioHora.setText("");
		spnFrecuencia.setValue(1);
		spnSalario.setValue(1);
		chckbxJava.setSelected(false);
		chckbxHtml.setSelected(false);
		chckbxCpp.setSelected(false);
		
		
		
		txtNombre.setForeground(Color.BLACK);
		txtApellidos.setForeground(Color.BLACK);
		txtSalarioHora.setForeground(Color.BLACK);
		txtCorreo.setForeground(Color.black);
		txtCedula.setForeground(Color.BLACK);
		txtTelefono.setForeground(Color.BLACK);
		txtTelefono2.setForeground(Color.BLACK);
	}
}
