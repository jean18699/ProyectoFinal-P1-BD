package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


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

public class InfoEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtSalario;
	private JTextField txtDireccion;
	private JTextField textOcupacion;
	private JTextField txtEvaluacion;
	private JTextField txtProyectos;
	private JTextField txtCedula;
	
	public InfoEmpleado(Empleado empleado) {
		
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
		setBounds(100, 100, 486, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion de empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(20, 62, 59, 14);
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel.add(lblNombre);
			
			txtNombre = new JTextField(empleado.getNombre());
			txtNombre.setEnabled(false);
			txtNombre.setBounds(79, 59, 143, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			txtNombre.setText(empleado.getNombre());
			
			JLabel lbl = new JLabel("Apellido:");
			lbl.setBounds(20, 90, 59, 14);
			lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel.add(lbl);
			
			txtApellido = new JTextField(empleado.getApellidos());
			txtApellido.setEnabled(false);
			txtApellido.setBounds(79, 87, 143, 20);
			panel.add(txtApellido);
			txtApellido.setColumns(10);
			txtApellido.setText(empleado.getApellidos());
			
			JLabel lblSalarioHora = new JLabel("Salario/Hora:");
			lblSalarioHora.setBounds(257, 47, 88, 14);
			lblSalarioHora.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel.add(lblSalarioHora);
			
			txtSalario = new JTextField();
			txtSalario.setEnabled(false);
			txtSalario.setBounds(342, 44, 118, 20);
			panel.add(txtSalario);
			txtSalario.setColumns(10);
			txtSalario.setText(Float.toString(empleado.getSalarioHora()));
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(268, 78, 68, 14);
			lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel.add(lblDireccion);
			
			txtDireccion = new JTextField();
			txtDireccion.setEnabled(false);
			txtDireccion.setBounds(342, 75, 118, 20);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
			txtDireccion.setText(empleado.getDireccion());
			
			JLabel lblOcupacion = new JLabel("Ocupacion:");
			lblOcupacion.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblOcupacion.setBounds(10, 144, 83, 14);
			panel.add(lblOcupacion);
			
			textOcupacion = new JTextField();
			textOcupacion.setEnabled(false);
			textOcupacion.setBounds(79, 141, 143, 20);
			panel.add(textOcupacion);
			textOcupacion.setColumns(10);
			textOcupacion.setText(empleado.getCargo());
			//txtGenero.setText();
			
			JLabel lblEdads = new JLabel("Edad:");
			lblEdads.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblEdads.setBounds(33, 119, 46, 14);
			panel.add(lblEdads);
			
			JLabel lblEdad = new JLabel("");
			lblEdad.setBounds(79, 118, 46, 14);
			lblEdad.setText(Integer.toString(empleado.getEdad()));
			panel.add(lblEdad);
			
			JLabel lblEvaluacion = new JLabel("Evaluacion:");
			lblEvaluacion.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblEvaluacion.setBounds(268, 108, 68, 14);
			panel.add(lblEvaluacion);
			
			txtEvaluacion = new JTextField();
			txtEvaluacion.setEnabled(false);
			txtEvaluacion.setBounds(342, 106, 118, 20);
			panel.add(txtEvaluacion);
			txtEvaluacion.setColumns(10);
			txtEvaluacion.setText(empleado.getEvaluacionAnual());
			
			JLabel lblCantidadDeProyectos = new JLabel("Cantidad de proyectos:");
			lblCantidadDeProyectos.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCantidadDeProyectos.setBounds(243, 144, 143, 14);
			panel.add(lblCantidadDeProyectos);
			
			txtProyectos = new JTextField();
			txtProyectos.setEnabled(false);
			txtProyectos.setBounds(383, 141, 77, 20);
			panel.add(txtProyectos);
			txtProyectos.setColumns(10);
			txtProyectos.setText(Integer.toString(empleado.getProyectos().size()));
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCedula.setBounds(20, 31, 59, 14);
			panel.add(lblCedula);
			
			txtCedula = new JTextField();
			txtCedula.setEditable(false);
			txtCedula.setBounds(80, 28, 142, 20);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			txtCedula.setText(empleado.getCedula());
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
