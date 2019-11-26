package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import Logico.Empleado;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdJefe;
	private JTextField txtNombreJefe;
	private JTextField txtProgramador1;
	private JTextField txtNombreProgramador1;
	private JTextField txtIdPlanificador;
	private JTextField txtNombrePlanificador;
	private JTextField txtIdProgramador2;
	private JTextField txtNombreProgramador2;
	private JTextField txtIdAdicional;
	private JTextField txtNombreAdicional;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public InfoEquipo(ArrayList<Empleado> equipo) {
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Equipo a cargo del desarolllo");
		setBounds(100, 100, 531, 433);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBounds(10, 11, 250, 107);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblIdentificador = new JLabel("Identificador:");
				lblIdentificador.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblIdentificador.setBounds(10, 39, 80, 14);
				panel.add(lblIdentificador);
			}
			{
				txtIdJefe = new JTextField(equipo.get(0).getId());
				txtIdJefe.setEditable(false);
				txtIdJefe.setBounds(100, 36, 118, 20);
				panel.add(txtIdJefe);
				txtIdJefe.setColumns(10);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNombre.setBounds(42, 67, 56, 14);
				panel.add(lblNombre);
			}
			{
				txtNombreJefe = new JTextField(equipo.get(0).getNombre() + " " + equipo.get(0).getApellidos());
				txtNombreJefe.setEditable(false);
				txtNombreJefe.setBounds(100, 64, 118, 20);
				panel.add(txtNombreJefe);
				txtNombreJefe.setColumns(10);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(128, 0, 128));
				panel_1.setBounds(0, 0, 250, 23);
				panel.add(panel_1);
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				{
					JLabel lblJefeDelEquipo = new JLabel("Jefe del equipo");
					lblJefeDelEquipo.setForeground(new Color(255, 255, 255));
					lblJefeDelEquipo.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel_1.add(lblJefeDelEquipo);
					lblJefeDelEquipo.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBounds(10, 129, 250, 107);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Identificador:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 36, 80, 14);
				panel.add(label);
			}
			{
				txtProgramador1 = new JTextField(equipo.get(2).getId());
				txtProgramador1.setEditable(false);
				txtProgramador1.setColumns(10);
				txtProgramador1.setBounds(100, 33, 118, 20);
				panel.add(txtProgramador1);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(37, 67, 56, 14);
				panel.add(label);
			}
			{
				txtNombreProgramador1 = new JTextField(equipo.get(2).getNombre() + " " + equipo.get(2).getApellidos());
				txtNombreProgramador1.setEditable(false);
				txtNombreProgramador1.setColumns(10);
				txtNombreProgramador1.setBounds(99, 64, 118, 20);
				panel.add(txtNombreProgramador1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(0, 128, 128));
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_1.setBounds(0, 0, 250, 23);
				panel.add(panel_1);
				{
					JLabel lblProgramador = new JLabel("Programador");
					lblProgramador.setForeground(new Color(255, 255, 255));
					lblProgramador.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblProgramador.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(lblProgramador);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBounds(270, 11, 250, 107);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Identificador:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 39, 80, 14);
				panel.add(label);
			}
			{
				txtIdPlanificador = new JTextField(equipo.get(1).getId());
				txtIdPlanificador.setEditable(false);
				txtIdPlanificador.setColumns(10);
				txtIdPlanificador.setBounds(98, 36, 118, 20);
				panel.add(txtIdPlanificador);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(39, 67, 56, 14);
				panel.add(label);
			}
			{
				txtNombrePlanificador = new JTextField(equipo.get(3).getNombre() + " " + equipo.get(3).getApellidos());
				txtNombrePlanificador.setEditable(false);
				txtNombrePlanificador.setColumns(10);
				txtNombrePlanificador.setBounds(98, 64, 118, 20);
				panel.add(txtNombrePlanificador);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setForeground(new Color(255, 255, 255));
				panel_1.setBackground(new Color(0, 128, 0));
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_1.setBounds(0, 0, 250, 23);
				panel.add(panel_1);
				{
					JLabel lblPlanificador = new JLabel("Planificador");
					lblPlanificador.setForeground(new Color(255, 255, 255));
					lblPlanificador.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblPlanificador.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(lblPlanificador);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBounds(270, 129, 250, 107);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Identificador:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 39, 80, 14);
				panel.add(label);
			}
			{
				txtIdProgramador2 = new JTextField(equipo.get(3).getId());
				txtIdProgramador2.setEditable(false);
				txtIdProgramador2.setColumns(10);
				txtIdProgramador2.setBounds(100, 36, 118, 20);
				panel.add(txtIdProgramador2);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(42, 67, 56, 14);
				panel.add(label);
			}
			{
				txtNombreProgramador2 = new JTextField(equipo.get(1).getNombre() + " " + equipo.get(1).getApellidos());
				txtNombreProgramador2.setEditable(false);
				txtNombreProgramador2.setColumns(10);
				txtNombreProgramador2.setBounds(100, 64, 118, 20);
				panel.add(txtNombreProgramador2);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(0, 128, 128));
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_1.setBounds(0, 0, 250, 23);
				panel.add(panel_1);
				{
					JLabel lblProgramador2 = new JLabel("Programador");
					lblProgramador2.setForeground(new Color(255, 255, 255));
					lblProgramador2.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblProgramador2.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(lblProgramador2);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBounds(10, 247, 250, 107);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Identificador:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 39, 80, 14);
				panel.add(label);
			}
			{
				txtIdAdicional = new JTextField(equipo.get(4).getId());
				txtIdAdicional.setEditable(false);
				txtIdAdicional.setColumns(10);
				txtIdAdicional.setBounds(100, 36, 118, 20);
				panel.add(txtIdAdicional);
			}
			{
				JLabel label = new JLabel("Nombre:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(40, 67, 56, 14);
				panel.add(label);
			}
			{
				txtNombreAdicional = new JTextField(equipo.get(4).getNombre() + " " + equipo.get(4).getApellidos());
				txtNombreAdicional.setEditable(false);
				txtNombreAdicional.setColumns(10);
				txtNombreAdicional.setBounds(100, 64, 118, 20);
				panel.add(txtNombreAdicional);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(128, 128, 0));
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_1.setBounds(0, 0, 250, 23);
				panel.add(panel_1);
				{
					JLabel lblAdicional = new JLabel("Adicional");
					lblAdicional.setForeground(new Color(255, 255, 255));
					lblAdicional.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblAdicional.setHorizontalAlignment(SwingConstants.CENTER);
					panel_1.add(lblAdicional);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, null, null, null));
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
		}
	}

}
