package Visual;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Cliente;
import Logico.Empresa;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.omg.CosNaming.NamingContextExtPackage.URLStringHelper;

import Connection.DBConnection;

import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;


@SuppressWarnings("serial")
public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtCedula;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtTelefono2;
	private JTextField txtCorreo;
	private JRadioButton rdbtnHombre;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public RegCliente() {
		setResizable(false);
		setType(Type.UTILITY);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setBackground(Color.BLACK);
		
		
		setTitle("Registro de cliente");
		setBounds(100, 100, 432, 334);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(220, 220, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.GREEN);
		panel.setBackground(new Color(245, 245, 245));
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(0, 0, 428, 264);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 47, 59, 14);
		lblNombre.setBackground(new Color(0, 0, 0));
		lblNombre.setForeground(new Color(0, 0, 0));
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(79, 44, 180, 20);
		txtNombre.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(314, 166, 73, 14);
		lblDireccion.setForeground(new Color(0, 0, 0));
		panel.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(271, 192, 145, 56);
		txtDireccion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(12, 19, 55, 16);
		panel.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtCedula.setBounds(79, 17, 180, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 82, 55, 16);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtApellido.setBounds(79, 76, 180, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(20, 110, 45, 16);
		panel.add(lblSexo);
		
		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setSelected(true);
		buttonGroup.add(rdbtnHombre);
		rdbtnHombre.setBounds(79, 104, 88, 24);
		panel.add(rdbtnHombre);
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		buttonGroup.add(rdbtnMujer);
		rdbtnMujer.setBounds(173, 104, 88, 24);
		panel.add(rdbtnMujer);
		
		JLabel lblTelefono = new JLabel("Telefono 1:");
		lblTelefono.setBounds(10, 141, 75, 16);
		panel.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTelefono.setBounds(79, 139, 180, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblTelefono_1 = new JLabel("Telefono 2:");
		lblTelefono_1.setBounds(10, 165, 75, 16);
		panel.add(lblTelefono_1);
		
		txtTelefono2 = new JTextField();
		txtTelefono2.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTelefono2.setBounds(79, 163, 180, 20);
		panel.add(txtTelefono2);
		txtTelefono2.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(30, 193, 55, 16);
		panel.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtCorreo.setBounds(79, 191, 180, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(271, 11, 145, 143);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setEnabled(false);
		label.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(RegCliente.class.getResource("/img/Cliente/Cliente 1 124pix.png")));
		label.setBounds(0, 0, 145, 142);
		panel_1.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setBounds(0, 263, 428, 32);
			contentPanel.add(buttonPane);
			buttonPane.setBackground(new Color(112, 128, 144));
			{
				buttonPane.setLayout(null);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBorder(new LineBorder(new Color(112, 128, 144)));
				cancelButton.setBounds(353, 5, 63, 26);
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.setBackground(new Color(128, 128, 128));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("btnCancelar");
				buttonPane.add(cancelButton);
			}
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					String genero  = null;
					if(rdbtnHombre.isSelected())
					{
						//genero = "Hombre";
						genero = "H";
						
					}else if(rdbtnMujer.isSelected())
					{
						//genero = "Mujer";
						genero = "M";
					}
					
					String cedula = txtCedula.getText();
					String nombre = txtNombre.getText();
					String apellido = txtApellido.getText();
					String tel1 = txtTelefono.getText();
					String tel2 = txtTelefono2.getText();
					String correo = txtCorreo.getText();
					
					if(!Empresa.getInstance().ValidadorCedula(cedula) || !Empresa.getInstance().ValidadorNombre(nombre) || !Empresa.getInstance().ValidadorNombre(apellido) || !ValidadorTelefono(tel1) || !ValidadorTelefono(tel2) || !ValidadorCorreo(correo) || txtDireccion.getText().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "Por favor, llene correctamente todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
						if(!Empresa.getInstance().ValidadorCedula(cedula)) {
							txtCedula.setForeground(Color.RED);
						}
						else {
							txtCedula.setForeground(Color.BLACK);
						}
						if(!Empresa.getInstance().ValidadorNombre(nombre)) {
							txtNombre.setForeground(Color.RED);
						}
						else {
							txtNombre.setForeground(Color.BLACK);
						}
						if(!Empresa.getInstance().ValidadorNombre(apellido)) {
							txtApellido.setForeground(Color.BLACK);
						}
						else {
							txtApellido.setBackground(Color.WHITE);
						}
						if(!ValidadorTelefono(tel1)) {
							txtTelefono.setForeground(Color.RED);
						}
						else {
							txtTelefono.setForeground(Color.BLACK);
						}
						if(!ValidadorTelefono(tel2)) {
							txtTelefono2.setForeground(Color.RED);
						}
						else {
							txtTelefono2.setForeground(Color.BLACK);
						}
						if(!ValidadorCorreo(correo)) {
							txtCorreo.setForeground(Color.RED);
						}
						else {
							txtCorreo.setForeground(Color.BLACK);
						}
						if(txtDireccion.getText().equalsIgnoreCase("")) {
							txtDireccion.setForeground(Color.RED);
						}
						else {
							txtDireccion.setForeground(Color.BLACK);
						}
					}
					
					else {
						/*Cliente cliente = new Cliente(txtCedula.getText(),txtNombre.getText(),txtCorreo.getText(),txtTelefono.getText(),txtTelefono2.getText(),
								txtDireccion.getText(),genero);
						Empresa.getInstance().nuevoCliente(cliente);*/
						try {
							DBConnection.getInstance().agregarCliente(txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), genero, txtTelefono.getText(), txtTelefono2.getText(), txtCorreo.getText(), txtDireccion.getText());
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						dispose();
					}
				}
				
				
				
			});
			btnAceptar.setBorder(new LineBorder(new Color(112, 128, 144)));
			btnAceptar.setBounds(269, 5, 72, 26);

			btnAceptar.setForeground(new Color(0, 0, 0));
			btnAceptar.setBackground(new Color(105, 105, 105));
			
			btnAceptar.setActionCommand("btnAceptar");
			buttonPane.add(btnAceptar);
			getRootPane().setDefaultButton(btnAceptar);
		}
	}
	
	public boolean ValidadorTelefono(String str) {
		boolean verdad = true;
		
		int i = 0;
		int cantGuiones = 0;
		if(str.equalsIgnoreCase("")) {
			return false;
		}
		while(i < str.length() && verdad != false) {
			if(Integer.valueOf(str.charAt(i)) == 45) {
				cantGuiones++;
			}
			if((Integer.valueOf(str.charAt(i)) != 45 && Integer.valueOf(str.charAt(i)) < 48 || Integer.valueOf(str.charAt(i)) > 57) || cantGuiones > 2) {
				verdad = false;
				return verdad;
			}
			i++;

		}
		return verdad;
	}
	
	public boolean ValidadorCorreo(String str) {
		boolean arrobaEncontrado = false;
		boolean puntoEncontrado = false;

		int i = 0;
		if (str.equalsIgnoreCase("") || Integer.valueOf(str.charAt(0)) == 32) {
			return false;
		}

		while (i < str.length() && (arrobaEncontrado != true || puntoEncontrado != true)) {
			
			if(Integer.valueOf(str.charAt(i)) == 64) {
				arrobaEncontrado = true;
			}
			if(Integer.valueOf(str.charAt(i)) == 46) {
				puntoEncontrado = true;
			}

			i++;
		}
		
		System.out.println(arrobaEncontrado);
		System.out.println(puntoEncontrado);
		System.out.println("\n");
		
		if(arrobaEncontrado == true && puntoEncontrado == true) {
			return true;
		}
		
		return false;
	}
}
