package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Logico.Cliente;
import Logico.Empleado;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Window.Type;

public class InfoCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	private JTextField txtProyectos;
	private JTextField txtCedula;
	private JTextField txtTelefono2;
	
	public InfoCliente(Cliente cliente) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Informacion");
		setBounds(100, 100, 447, 187);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(20, 62, 59, 14);
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel.add(lblNombre);
			
			txtNombre = new JTextField(cliente.getNombre());
			txtNombre.setEnabled(false);
			txtNombre.setBounds(79, 59, 143, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			txtNombre.setText(cliente.getNombre());
			
			JLabel lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(30, 90, 59, 14);
			lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel.add(lblCorreo);
			
			txtCorreo = new JTextField();
			txtCorreo.setEnabled(false);
			txtCorreo.setBounds(79, 87, 143, 20);
			panel.add(txtCorreo);
			txtCorreo.setColumns(10);
			txtCorreo.setText(cliente.getCorreo());

			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(243, 31, 68, 14);
			lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel.add(lblDireccion);
			
			txtDireccion = new JTextField();
			txtDireccion.setEnabled(false);
			txtDireccion.setBounds(304, 28, 118, 20);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
			txtDireccion.setText(cliente.getDireccion());
			
			JLabel lblCantidadDeProyectos = new JLabel("Telefono:");
			lblCantidadDeProyectos.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCantidadDeProyectos.setBounds(243, 62, 77, 14);
			panel.add(lblCantidadDeProyectos);
			
			txtProyectos = new JTextField();
			txtProyectos.setEnabled(false);
			txtProyectos.setBounds(304, 59, 118, 20);
			panel.add(txtProyectos);
			txtProyectos.setColumns(10);
			txtProyectos.setText(cliente.getTelefono());
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCedula.setBounds(20, 31, 59, 14);
			panel.add(lblCedula);
			
			txtCedula = new JTextField();
			txtCedula.setEnabled(false);
			txtCedula.setEditable(false);
			txtCedula.setBounds(80, 28, 142, 20);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			txtCedula.setText(cliente.getCedula());
			
			JLabel lblTelefono = new JLabel("Telefono 2:");
			lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTelefono.setBounds(243, 90, 68, 14);
			panel.add(lblTelefono);
		
			txtTelefono2 = new JTextField();
			txtTelefono2.setEnabled(false);
			txtTelefono2.setEditable(false);
			txtTelefono2.setBounds(314, 87, 108, 20);
			panel.add(txtTelefono2);
			txtTelefono2.setColumns(10);
			txtTelefono2.setText(cliente.getTelefono2());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
					}
				});
				btnCerrar.setActionCommand("OK");
				buttonPane.add(btnCerrar);
				getRootPane().setDefaultButton(btnCerrar);
			}
		}
	}
}
