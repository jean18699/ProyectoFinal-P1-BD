package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Cliente;
import Logico.Disegnador;
import Logico.Empleado;
import Logico.Empresa;
import Logico.Jefe;
import Logico.Planificador;
import Logico.Programador;
import Logico.Proyecto;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.ImageIcon;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class RegProyecto extends JDialog {

	private final JPanel panelProyecto = new JPanel();
	private JTextField txtNombre;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private static DefaultTableModel model;
	private static Object fila[];
	private JTextField txtJefe;
	private JTextField txtIdPlanificador;
	private JTextField txtIdProgramador1;
	private JTextField txtIdProgramador2;
	private JTextField txtIdAdicional;
	private JComboBox<String> cmbOcupacion;
	private JComboBox cmbCategoria;
	private String select;
	private String selectCliente;
	private int index;
	private JLabel lblIdEmpleado;
	private JLabel lblSalarioEmpleado;
	private JPanel panel;
	private JLabel lblNombreDelProyecto;
	private JLabel lblEquipo;
	private JPanel panel_1;
	private JLabel lblProgramador;
	private JLabel lblPlanificador;
	private JLabel lblJefeDelProyecto;
	private JLabel lblProgramador_1;
	private JButton btnCargarJefe;
	private JButton btnCargarPlanificador;
	private JButton btnCargarProgramador;
	private JButton btnCargarProgramador2;
	private JLabel lblAdicional;
	private JLabel lblCategoria;
	private JPanel buttonPane;
	private JButton btnSiguiente;
	private JButton cancelButton;
	private static DefaultTableModel modelClientes;
	private JButton btnAgregarCliente;
	private JButton btnAnterior;
	private JButton btnSiguiente2;
	private JTable tablaClientes;
	private JScrollPane scrollPane_1;
	private JPanel panelClientes;
	private JScrollPane scrollPane;
	private JPanel panel_4;
	private JButton btnInfoJefe;
	private JButton btnInfoPlanificador;
	private JButton btnInfoProgramador;
	private JButton btnInfoProgramador2;
	private JButton btnInfoAdicional;
	private JPanel panel_2;
	private JButton btnResetear;
	private JLabel imagen;
	private JLabel lblLenguaje;
	private JComboBox cmbLenguaje;
	private int indexOcupacionAnterior;

	/**
	 * Launch the application.
	 */

	public RegProyecto() {
		setResizable(false);
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

		indexOcupacionAnterior = 0;
		// Verificando la disponibilidad de los empleados
		for (int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {
			if (!(Empresa.getInstance().getEmpleados().get(i) instanceof Programador)) {
				if (Empresa.getInstance().getEmpleados().get(i).getProyectos().size() < 4) {
					Empresa.getInstance().getEmpleados().get(i).setCondicion("Disponible");
				} else {
					Empresa.getInstance().getEmpleados().get(i).setCondicion("No disponible");
				}
			} else {
				if (Empresa.getInstance().getEmpleados().get(i).getProyectos().size() < 2) {
					Empresa.getInstance().getEmpleados().get(i).setCondicion("Disponible");
				} else {
					Empresa.getInstance().getEmpleados().get(i).setCondicion("No disponible");
				}
			}
		}

		setBounds(100, 100, 1032, 276);
		getContentPane().setLayout(new BorderLayout());

		setLocationRelativeTo(null);

		model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String[] titulos = { "ID", "Nombre", "Ocupacion", "Salario", "Condicion" };
		model.setColumnIdentifiers(titulos);

		modelClientes = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		;

		panelProyecto.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panelProyecto, BorderLayout.CENTER);
		panelProyecto.setLayout(null);

		// TABLA

		String[] cols = { "ID", "Nombre", "Direccion", "Proyectos activos", "Total proyectos solicitados" };
		modelClientes.setColumnIdentifiers(cols);

		btnAgregarCliente = new JButton("Registrar nuevo cliente");
		btnAgregarCliente.setBounds(293, 180, 179, 25);
		panelProyecto.add(btnAgregarCliente);
		btnAgregarCliente.setIcon(null);
		btnAgregarCliente.setVisible(false);
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente reg = new RegCliente();
				reg.setLocationRelativeTo(null);
				reg.setModal(true);
				reg.setVisible(true);
				cargarClientes();
			}
		});
		{
			buttonPane = new JPanel();
			buttonPane.setBounds(0, 213, 1027, 39);
			buttonPane.setBackground(Color.LIGHT_GRAY);
			panelProyecto.add(buttonPane);
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			{
				btnSiguiente = new JButton("Siguiente");
				btnSiguiente.setBounds(855, 7, 77, 23);
				btnSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (txtNombre.getText().equalsIgnoreCase("") || cmbCategoria.getSelectedIndex() == 0 || txtJefe.getText().equalsIgnoreCase("") || txtIdPlanificador.getText().equalsIgnoreCase("") || txtIdProgramador1.getText().equalsIgnoreCase("") || txtIdProgramador2.getText().equalsIgnoreCase("") || txtIdAdicional.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos",
									"Advertencia", JOptionPane.WARNING_MESSAGE);
						}

						else {

							btnAgregarCliente.setVisible(true);
							btnAnterior.setVisible(true);
							btnSiguiente.setVisible(false);
							btnSiguiente2.setVisible(true);
							btnSiguiente2.setEnabled(false);
							panel.setVisible(false);
							panel_1.setVisible(false);
							panel_2.setVisible(true);
							btnResetear.setVisible(false);

							scrollPane.setVisible(false);
							scrollPane_1.setVisible(true);
							panelClientes.setVisible(true);
							cargarClientes();
						}
						/*
						 * Jefe jefe = (Jefe) Empresa.getInstance().getEmpleadoById(txtJefe.getText());
						 * Planificador planificador = (Planificador)
						 * Empresa.getInstance().getEmpleadoById(txtIdPlanificador.getText());
						 * Programador programador1 = (Programador)
						 * Empresa.getInstance().getEmpleadoById(txtIdProgramador1.getText());
						 * Programador programador2 = (Programador)
						 * Empresa.getInstance().getEmpleadoById(txtIdProgramador2.getText());
						 * if(cmbOcupacion.getSelectedItem().toString().equalsIgnoreCase("Planificador")
						 * ) { Planificador planificador2 = (Planificador)
						 * Empresa.getInstance().getEmpleadoById(txtIdAdicional.getText()); Proyecto
						 * proyecto = new
						 * Proyecto("123",txtNombre.getText(),jefe,planificador,programador1,
						 * programador2,planificador2, cmbCategoria.getSelectedItem().toString());
						 * Empresa.getInstance().nuevoProyecto(proyecto);
						 * 
						 * }else
						 * if(cmbOcupacion.getSelectedItem().toString().equalsIgnoreCase("Programador"))
						 * { Programador programador3 = (Programador)
						 * Empresa.getInstance().getEmpleadoById(txtIdAdicional.getText()); Proyecto
						 * proyecto = new
						 * Proyecto("123",txtNombre.getText(),jefe,planificador,programador1,
						 * programador2,programador3, cmbCategoria.getSelectedItem().toString());
						 * Empresa.getInstance().nuevoProyecto(proyecto);
						 * 
						 * }else
						 * if(cmbOcupacion.getSelectedItem().toString().equalsIgnoreCase("Diseñador")) {
						 * Disegnador disegnador = (Disegnador)
						 * Empresa.getInstance().getEmpleadoById(txtIdAdicional.getText()); Proyecto
						 * proyecto = new
						 * Proyecto("123",txtNombre.getText(),jefe,planificador,programador1,
						 * programador2,disegnador, cmbCategoria.getSelectedItem().toString());
						 * Empresa.getInstance().nuevoProyecto(proyecto); }
						 */
					}
				});
				buttonPane.setLayout(null);
				btnSiguiente.setActionCommand("OK");
				buttonPane.add(btnSiguiente);
				getRootPane().setDefaultButton(btnSiguiente);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBounds(942, 7, 75, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

			btnAnterior = new JButton("Anterior");
			btnAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					btnAgregarCliente.setVisible(false);
					btnAnterior.setVisible(false);
					btnSiguiente.setVisible(true);
					btnSiguiente.setEnabled(true);
					btnSiguiente2.setVisible(false);
					panel.setVisible(true);
					panel_1.setVisible(true);
					panel_2.setVisible(false);
					btnResetear.setVisible(true);

					scrollPane.setVisible(true);
					scrollPane_1.setVisible(false);
					panelClientes.setVisible(false);
				}
			});
			btnAnterior.setVisible(false);
			btnAnterior.setBounds(756, 7, 89, 23);
			buttonPane.add(btnAnterior);

			btnSiguiente2 = new JButton("Siguiente");
			btnSiguiente2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ArrayList<Empleado> grupoTrabajo = new ArrayList<>();

					grupoTrabajo.add(Empresa.getInstance().getEmpleadoById(txtJefe.getText()));
					grupoTrabajo.add(Empresa.getInstance().getEmpleadoById(txtIdPlanificador.getText()));
					grupoTrabajo.add(Empresa.getInstance().getEmpleadoById(txtIdProgramador1.getText()));
					grupoTrabajo.add(Empresa.getInstance().getEmpleadoById(txtIdProgramador2.getText()));
					grupoTrabajo.add(Empresa.getInstance().getEmpleadoById(txtIdAdicional.getText()));

					Proyecto proyecto = new Proyecto(txtNombre.getText(), grupoTrabajo,
							cmbCategoria.getSelectedItem().toString(), cmbLenguaje.getSelectedItem().toString());

					Cliente cliente = Empresa.getInstance().getClienteById(select);

					RegContrato reg = new RegContrato(proyecto, cliente);
					reg.setLocationRelativeTo(null);
					reg.setModal(true);
					reg.setVisible(true);
					if (proyecto.isRealizado()) {
						dispose();
					} else {
						btnAnterior.setSelected(true);
					}

				}
			});
			btnSiguiente2.setBounds(855, 7, 77, 23);
			buttonPane.add(btnSiguiente2);

			btnResetear = new JButton("Resetear");
			btnResetear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtJefe.setText("");
					txtIdPlanificador.setText("");
					txtIdProgramador1.setText("");
					txtIdProgramador2.setText("");
					txtIdAdicional.setText("");
				}
			});
			btnResetear.setBounds(10, 7, 89, 23);
			buttonPane.add(btnResetear);
			{
				panel = new JPanel();
				panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel.setBounds(0, 0, 441, 213);
				panelProyecto.add(panel);
				panel.setLayout(null);

				lblNombreDelProyecto = new JLabel("Nombre del proyecto:");
				lblNombreDelProyecto.setBounds(10, 11, 121, 14);
				panel.add(lblNombreDelProyecto);
				{
					txtNombre = new JTextField();
					txtNombre.setBounds(121, 8, 133, 20);
					panel.add(txtNombre);
					txtNombre.setColumns(10);
				}
				{
					lblEquipo = new JLabel("Equipo de trabajo");
					lblEquipo.setBounds(10, 40, 133, 14);
					panel.add(lblEquipo);
				}

				/*
				 * cmbOcupacion = new JComboBox(); cmbOcupacion.addMouseListener(new
				 * MouseAdapter() {
				 * 
				 * @Override public void mouseClicked(MouseEvent e) {
				 * btnCargarAdicional.setEnabled(true); btnCargarJefe.setEnabled(true);
				 * btnCargarProgramador.setEnabled(true);
				 * btnCargarProgramador2.setEnabled(true);
				 * btnCargarPlanificador.setEnabled(true);
				 * 
				 * int indexOcupacion = cmbOcupacion.getSelectedIndex();
				 * System.out.println(indexOcupacion); if(indexOcupacion == 1) {
				 * cargarPlanificadores(); } else if(indexOcupacion == 2) {
				 * cargarProgramadores(); } else if(indexOcupacion == 3) { cargarDisegnadores();
				 * }
				 * 
				 * } });
				 */

				/*
				 * btnCargarAdicional = new JButton("Cargar");
				 * btnCargarAdicional.addActionListener(new ActionListener() { public void
				 * actionPerformed(ActionEvent e) {
				 * 
				 * 
				 * if(cmbOcupacion.getSelectedItem().toString().equalsIgnoreCase("Programador"))
				 * { btnCargarAdicional.setEnabled(false); cargarProgramadores(); }
				 * if(cmbOcupacion.getSelectedItem().toString().equalsIgnoreCase("Planificador")
				 * ) { btnCargarAdicional.setEnabled(false); cargarPlanificadores(); }
				 * if(cmbOcupacion.getSelectedItem().toString().equalsIgnoreCase("Diseñador")) {
				 * btnCargarAdicional.setEnabled(false); cargarDisegnadores(); }
				 * 
				 * } });
				 * 
				 * 
				 * btnCargarAdicional.setBounds(105, 142, 65, 23);
				 * panel_1.add(btnCargarAdicional);
				 */

				lblCategoria = new JLabel("Categoria:");
				lblCategoria.setBounds(269, 11, 64, 14);
				panel.add(lblCategoria);
				
								cmbCategoria = new JComboBox();
								cmbCategoria.setModel(
										new DefaultComboBoxModel(new String[] { "<Seleccione>", "Escritorio", "Web", "Movil" }));
								cmbCategoria.setBounds(328, 8, 98, 20);
								panel.add(cmbCategoria);

				panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBounds(0, 65, 441, 147);
				panel.add(panel_1);
				panel_1.setLayout(null);

				lblProgramador = new JLabel("Programador 1:");
				lblProgramador.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblProgramador.setBounds(24, 61, 93, 14);
				panel_1.add(lblProgramador);

				lblPlanificador = new JLabel("Planificador:");
				lblPlanificador.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblPlanificador.setBounds(42, 36, 75, 14);
				panel_1.add(lblPlanificador);

				{
					lblJefeDelProyecto = new JLabel("Jefe del proyecto:");
					lblJefeDelProyecto.setFont(new Font("Tahoma", Font.PLAIN, 12));
					lblJefeDelProyecto.setBounds(10, 11, 114, 14);
					panel_1.add(lblJefeDelProyecto);
				}

				lblProgramador_1 = new JLabel("Programador 2:");
				lblProgramador_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblProgramador_1.setBounds(24, 86, 93, 14);
				panel_1.add(lblProgramador_1);

				txtJefe = new JTextField();
				txtJefe.setEnabled(false);
				txtJefe.setBounds(117, 9, 140, 20);
				panel_1.add(txtJefe);
				txtJefe.setColumns(10);

				btnCargarJefe = new JButton("Cargar");
				btnCargarJefe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCargarJefe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarJefes();
						btnCargarProgramador.setEnabled(true);
						btnCargarProgramador2.setEnabled(true);
						btnCargarJefe.setEnabled(false);
						btnCargarPlanificador.setEnabled(true);
						// btnCargarAdicional.setEnabled(true);
					}
				});
				btnCargarJefe.setBounds(267, 7, 93, 23);
				panel_1.add(btnCargarJefe);

				txtIdPlanificador = new JTextField();
				txtIdPlanificador.setEnabled(false);
				txtIdPlanificador.setBounds(117, 33, 140, 20);
				panel_1.add(txtIdPlanificador);
				txtIdPlanificador.setColumns(10);

				txtIdProgramador1 = new JTextField();
				txtIdProgramador1.setEnabled(false);
				txtIdProgramador1.setBounds(117, 58, 140, 20);
				panel_1.add(txtIdProgramador1);
				txtIdProgramador1.setColumns(10);

				txtIdProgramador2 = new JTextField();
				txtIdProgramador2.setEnabled(false);
				txtIdProgramador2.setBounds(117, 83, 140, 20);
				panel_1.add(txtIdProgramador2);
				txtIdProgramador2.setColumns(10);

				btnCargarPlanificador = new JButton("Cargar");
				btnCargarPlanificador.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCargarPlanificador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarPlanificadores();
						btnCargarProgramador.setEnabled(true);
						btnCargarProgramador2.setEnabled(true);
						btnCargarJefe.setEnabled(true);
						btnCargarPlanificador.setEnabled(false);
						// btnCargarAdicional.setEnabled(true);
					}
				});
				btnCargarPlanificador.setBounds(267, 32, 93, 23);
				panel_1.add(btnCargarPlanificador);

				btnCargarProgramador = new JButton("Cargar");
				btnCargarProgramador.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCargarProgramador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (cmbLenguaje.getSelectedItem().toString().equalsIgnoreCase("<Seleccione>")) {
							cargarProgramadoresEspecializados();
						} else {
							cargarProgramadoresEspecializados();
						}

						btnCargarProgramador.setEnabled(false);
						btnCargarJefe.setEnabled(true);
						btnCargarPlanificador.setEnabled(true);
						// btnCargarAdicional.setEnabled(true);
						btnCargarProgramador2.setEnabled(true);
					}
				});
				btnCargarProgramador.setBounds(267, 58, 93, 23);
				panel_1.add(btnCargarProgramador);

				btnCargarProgramador2 = new JButton("Cargar");
				btnCargarProgramador2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCargarProgramador2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (cmbLenguaje.getSelectedItem().toString().equalsIgnoreCase("<Seleccione>")) {
							cargarProgramadoresEspecializados();
						} else {
							cargarProgramadoresEspecializados();
						}

						btnCargarProgramador.setEnabled(true);
						btnCargarProgramador2.setEnabled(false);
						btnCargarJefe.setEnabled(true);
						btnCargarPlanificador.setEnabled(true);
						// btnCargarAdicional.setEnabled(true);
					}
				});
				btnCargarProgramador2.setBounds(267, 82, 93, 23);
				panel_1.add(btnCargarProgramador2);

				cmbOcupacion = new JComboBox();
				cmbOcupacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cmbOcupacion.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						// btnCargarAdicional.setEnabled(true);
						btnCargarJefe.setEnabled(true);
						btnCargarProgramador.setEnabled(true);
						btnCargarProgramador2.setEnabled(true);
						btnCargarPlanificador.setEnabled(true);

						
						int indexOcupacionActual = cmbOcupacion.getSelectedIndex();
						if(indexOcupacionActual == 0) {
							indexOcupacionActual = indexOcupacionAnterior;
						}
						//System.out.println(indexOcupacionActual);
						if(indexOcupacionActual != indexOcupacionAnterior) {
							if (indexOcupacionActual == 1) {
								cargarPlanificadores();
							} else if (indexOcupacionActual == 2) {
								cargarProgramadoresEspecializados();
							} else if (indexOcupacionActual == 3) {
								cargarDisegnadores();
							}
							txtIdAdicional.setText("");
						}
						indexOcupacionAnterior = indexOcupacionActual;

					}
				});

				cmbOcupacion.setModel(new DefaultComboBoxModel(
						new String[] { "<Ocupacion>", "Planificador", "Programador", "Dise\u00F1ador" }));
				cmbOcupacion.setBounds(259, 110, 101, 20);
				panel_1.add(cmbOcupacion);

				txtIdAdicional = new JTextField();
				txtIdAdicional.setEnabled(false);
				txtIdAdicional.setBounds(117, 111, 133, 20);
				panel_1.add(txtIdAdicional);
				txtIdAdicional.setColumns(10);

				lblAdicional = new JLabel("Adicional:");
				lblAdicional.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblAdicional.setBounds(59, 111, 58, 14);
				panel_1.add(lblAdicional);

				btnInfoJefe = new JButton("\u00A1");
				btnInfoJefe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!txtJefe.getText().equalsIgnoreCase("")) {
							Empleado emp = Empresa.getInstance().getEmpleadoById(txtJefe.getText());
							InfoEmpleado info = new InfoEmpleado(emp);
							info.setLocationRelativeTo(null);
							info.setModal(true);
							info.setVisible(true);
						}

					}
				});
				btnInfoJefe.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnInfoJefe.setBounds(370, 8, 50, 23);
				panel_1.add(btnInfoJefe);

				btnInfoPlanificador = new JButton("\u00A1");
				btnInfoPlanificador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!txtIdPlanificador.getText().equalsIgnoreCase("")) {
							Empleado emp = Empresa.getInstance().getEmpleadoById(txtIdPlanificador.getText());
							InfoEmpleado info = new InfoEmpleado(emp);
							info.setLocationRelativeTo(null);
							info.setModal(true);
							info.setVisible(true);
						}

					}
				});
				btnInfoPlanificador.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnInfoPlanificador.setBounds(370, 33, 50, 23);
				panel_1.add(btnInfoPlanificador);

				btnInfoProgramador = new JButton("\u00A1");
				btnInfoProgramador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!txtIdProgramador1.getText().equalsIgnoreCase("")) {
							Empleado emp = Empresa.getInstance().getEmpleadoById(txtIdProgramador1.getText());
							InfoEmpleado info = new InfoEmpleado(emp);
							info.setLocationRelativeTo(null);
							info.setModal(true);
							info.setVisible(true);
						}

					}
				});
				btnInfoProgramador.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnInfoProgramador.setBounds(370, 58, 50, 23);
				panel_1.add(btnInfoProgramador);

				btnInfoProgramador2 = new JButton("\u00A1");
				btnInfoProgramador2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!txtIdProgramador2.getText().equalsIgnoreCase("")) {
							Empleado emp = Empresa.getInstance().getEmpleadoById(txtIdProgramador2.getText());
							InfoEmpleado info = new InfoEmpleado(emp);
							info.setLocationRelativeTo(null);
							info.setModal(true);
							info.setVisible(true);
						}

					}
				});
				btnInfoProgramador2.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnInfoProgramador2.setBounds(370, 83, 50, 23);
				panel_1.add(btnInfoProgramador2);

				btnInfoAdicional = new JButton("\u00A1");
				btnInfoAdicional.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!txtIdAdicional.getText().equalsIgnoreCase("")) {
							Empleado emp = Empresa.getInstance().getEmpleadoById(txtIdAdicional.getText());
							InfoEmpleado info = new InfoEmpleado(emp);
							info.setLocationRelativeTo(null);
							info.setModal(true);
							info.setVisible(true);
						}

					}
				});
				btnInfoAdicional.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnInfoAdicional.setBounds(370, 110, 50, 23);
				panel_1.add(btnInfoAdicional);

				panel_4 = new JPanel();
				panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_4.setBackground(Color.LIGHT_GRAY);
				panel_4.setBounds(0, 31, 104, 35);
				panel.add(panel_4);

				lblLenguaje = new JLabel("Lenguaje:");
				lblLenguaje.setBounds(269, 40, 55, 14);
				panel.add(lblLenguaje);

				cmbLenguaje = new JComboBox();
				cmbLenguaje.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!btnCargarProgramador.isEnabled() || !btnCargarProgramador2.isEnabled() || cmbOcupacion.getSelectedIndex()==2) {
							cargarProgramadoresEspecializados();
							if(!txtIdAdicional.getText().equalsIgnoreCase("")) {
								if(!((Programador) Empresa.getInstance().getEmpleadoById(txtIdAdicional.getText())).getEspecialidades().contains(cmbLenguaje.getSelectedItem())) {
									txtIdAdicional.setText("");
								}
							}
						}
						
						if(!txtIdProgramador1.getText().equalsIgnoreCase("")) {
							if(!((Programador) Empresa.getInstance().getEmpleadoById(txtIdProgramador1.getText())).getEspecialidades().contains(cmbLenguaje.getSelectedItem())) {
								txtIdProgramador1.setText("");
							}
						}
						if(!txtIdProgramador2.getText().equalsIgnoreCase("")) {
							if(!((Programador) Empresa.getInstance().getEmpleadoById(txtIdProgramador2.getText())).getEspecialidades().contains(cmbLenguaje.getSelectedItem())) {
								txtIdProgramador2.setText("");
							}
						}
						
					}
				});
				cmbLenguaje.setModel(new DefaultComboBoxModel(
						new String[] {/* "<Seleccione>", */"Java", "C++", "C#", "Python", "HTML/Javascript" }));

				cmbLenguaje.setBounds(328, 37, 98, 20);
				panel.add(cmbLenguaje);
			}

			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel_2.setBounds(0, 0, 279, 213);
			panelProyecto.add(panel_2);
			panel_2.setLayout(null);
			panel_2.setVisible(false);

			imagen = new JLabel("");
			imagen.setBorder(new LineBorder(new Color(0, 0, 0)));
			imagen.setBackground(Color.WHITE);
			imagen.setIcon(new ImageIcon(RegProyecto.class.getResource("/img/Cliente/clientes 3.png")));
			imagen.setBounds(10, 11, 259, 191);
			panel_2.add(imagen);

			scrollPane = new JScrollPane();
			scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(442, 0, 585, 213);
			panelProyecto.add(scrollPane);
			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			table.setShowVerticalLines(false);

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//
					int index = table.getSelectedRow();
					if (index >= 0) {

						select = table.getValueAt(index, 0).toString();

						if (btnCargarJefe.isEnabled() && btnCargarProgramador.isEnabled()
								&& btnCargarProgramador2.isEnabled() && btnCargarPlanificador.isEnabled()) {

							txtIdAdicional.setText(select);
							if(cmbOcupacion.getSelectedIndex() == 1) {
								System.out.println(cmbOcupacion.getSelectedIndex());
								cargarPlanificadores();
							}
							else if(cmbOcupacion.getSelectedIndex() == 2) {
								System.out.println(cmbOcupacion.getSelectedIndex());
								cargarProgramadoresEspecializados();
							}
							else if(cmbOcupacion.getSelectedIndex() == 3) {
								System.out.println(cmbOcupacion.getSelectedIndex());
								cargarDisegnadores();
							}
							
						}

						if (!btnCargarJefe.isEnabled()) {

							txtJefe.setText(select);
							cargarJefes();

						}
						if (!btnCargarProgramador.isEnabled()) {
							txtIdProgramador1.setText(select);
							cargarProgramadoresEspecializados();
						}
						if (!btnCargarProgramador2.isEnabled()) {
							txtIdProgramador2.setText(select);
							cargarProgramadoresEspecializados();
						}
						if (!btnCargarPlanificador.isEnabled()) {
							txtIdPlanificador.setText(select);
							cargarPlanificadores();
						}
					}

				}
			});
			table.setModel(model);

			scrollPane.setViewportView(table);

			panelClientes = new JPanel();
			panelClientes.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panelClientes.setBounds(279, 0, 748, 213);
			panelProyecto.add(panelClientes);
			panelClientes.setLayout(null);
			panelClientes.setVisible(false);

			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			scrollPane_1.setBounds(13, 11, 711, 163);
			panelClientes.add(scrollPane_1);

			tablaClientes = new JTable();
			tablaClientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaClientes.setModel(modelClientes);
			tablaClientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = tablaClientes.getSelectedRow();
					if (index >= 0) {
						select = tablaClientes.getValueAt(index, 0).toString();
						btnSiguiente2.setEnabled(true);
					} else {
						btnSiguiente2.setEnabled(false);
					}
				}
			});

			scrollPane_1.setViewportView(tablaClientes);
			getContentPane()
					.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { panelProyecto, table }));
		}
	}

	private void cargarJefes() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {
			if (Empresa.getInstance().getEmpleados().get(i) instanceof Jefe
					&& !Empresa.getInstance().getEmpleados().get(i).getId().equalsIgnoreCase(txtJefe.getText())
					&& !Empresa.getInstance().getEmpleados().get(i).getId().equalsIgnoreCase(txtIdAdicional.getText())
					&& Empresa.getInstance().getEmpleados().get(i).getCondicion().equalsIgnoreCase("Disponible")) {
				fila[0] = Empresa.getInstance().getEmpleados().get(i).getId();
				fila[1] = Empresa.getInstance().getEmpleados().get(i).getNombre();
				fila[2] = Empresa.getInstance().getEmpleados().get(i).getCargo();
				fila[3] = Empresa.getInstance().getEmpleados().get(i).getSalarioHora();
				fila[4] = Empresa.getInstance().getEmpleados().get(i).getEvaluacionAnual();

				model.addRow(fila);
			}
		}

		table.getColumnModel().getColumn(0).setMinWidth(100);
		// table.getColumnModel().getColumn(0).setMaxWidth(100);
	}

	/*private void cargarProgramadores() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {
			if (Empresa.getInstance().getEmpleados().get(i) instanceof Programador
					&& !Empresa.getInstance().getEmpleados().get(i).getId()
							.equalsIgnoreCase(txtIdProgramador1.getText())
					&& !Empresa.getInstance().getEmpleados().get(i).getId()
							.equalsIgnoreCase(txtIdProgramador2.getText())
					&& !Empresa.getInstance().getEmpleados().get(i).getId().equalsIgnoreCase(txtIdAdicional.getText())
					&& Empresa.getInstance().getEmpleados().get(i).getCondicion().equalsIgnoreCase("Disponible")) {
				fila[0] = Empresa.getInstance().getEmpleados().get(i).getId();
				fila[1] = Empresa.getInstance().getEmpleados().get(i).getNombre();
				fila[2] = Empresa.getInstance().getEmpleados().get(i).getCargo();
				fila[3] = Empresa.getInstance().getEmpleados().get(i).getSalarioHora();
				fila[4] = Empresa.getInstance().getEmpleados().get(i).getEvaluacionAnual();
				model.addRow(fila);
			}
		}

		table.getColumnModel().getColumn(0).setMinWidth(100);
		// table.getColumnModel().getColumn(0).setMaxWidth(100);

	}*/

	private void cargarProgramadoresEspecializados() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {
			if (Empresa.getInstance().getEmpleados().get(i) instanceof Programador && !Empresa.getInstance().getEmpleados().get(i).getId().equalsIgnoreCase(txtIdProgramador1.getText()) && !Empresa.getInstance().getEmpleados().get(i).getId().equalsIgnoreCase(txtIdProgramador2.getText()) && !Empresa.getInstance().getEmpleados().get(i).getId().equalsIgnoreCase(txtIdAdicional.getText()) && Empresa.getInstance().getEmpleados().get(i).getCondicion().equalsIgnoreCase("Disponible")) {
				
				
				/*for (int j = 0; j < ((Programador) Empresa.getInstance().getEmpleados().get(i)).getEspecialidades()
						.size(); j++) {
					if (((Programador) Empresa.getInstance().getEmpleados().get(i)).getEspecialidades().get(j)
							.equalsIgnoreCase(cmbLenguaje.getSelectedItem().toString())) {
						
						System.out.println(Arrays.toString(((Programador) Empresa.getInstance().getEmpleados().get(i)).getEspecialidades().toArray()));
						System.out.println("\n");

						fila[0] = Empresa.getInstance().getEmpleados().get(i).getId();
						fila[1] = Empresa.getInstance().getEmpleados().get(i).getNombre();
						fila[2] = Empresa.getInstance().getEmpleados().get(i).getCargo();
						fila[3] = Empresa.getInstance().getEmpleados().get(i).getSalarioHora();
						fila[4] = Empresa.getInstance().getEmpleados().get(i).getEvaluacionAnual();
						model.addRow(fila);
					}
					
				}*/
				if(((Programador) Empresa.getInstance().getEmpleados().get(i)).getEspecialidades().contains(cmbLenguaje.getSelectedItem())) {
					/*System.out.println(Arrays.toString(((Programador) Empresa.getInstance().getEmpleados().get(i)).getEspecialidades().toArray()));
					System.out.println("\n");*/
					fila[0] = Empresa.getInstance().getEmpleados().get(i).getId();
					fila[1] = Empresa.getInstance().getEmpleados().get(i).getNombre();
					fila[2] = Empresa.getInstance().getEmpleados().get(i).getCargo();
					fila[3] = Empresa.getInstance().getEmpleados().get(i).getSalarioHora();
					fila[4] = Empresa.getInstance().getEmpleados().get(i).getEvaluacionAnual();
					model.addRow(fila);
				}
			}
		}

		table.getColumnModel().getColumn(0).setMinWidth(100);
		// table.getColumnModel().getColumn(0).setMaxWidth(100);

	}

	private void cargarPlanificadores() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {
			if (Empresa.getInstance().getEmpleados().get(i) instanceof Planificador
					&& !Empresa.getInstance().getEmpleados().get(i).getId()
							.equalsIgnoreCase(txtIdPlanificador.getText())
					&& !Empresa.getInstance().getEmpleados().get(i).getId().equalsIgnoreCase(txtIdAdicional.getText())
					&& Empresa.getInstance().getEmpleados().get(i).getCondicion().equalsIgnoreCase("Disponible")) {

				fila[0] = Empresa.getInstance().getEmpleados().get(i).getId();
				fila[1] = Empresa.getInstance().getEmpleados().get(i).getNombre();
				fila[2] = Empresa.getInstance().getEmpleados().get(i).getCargo();
				fila[3] = Empresa.getInstance().getEmpleados().get(i).getSalarioHora();
				fila[4] = Empresa.getInstance().getEmpleados().get(i).getEvaluacionAnual();
				model.addRow(fila);
			}

		}

		table.getColumnModel().getColumn(0).setMinWidth(100);
		// table.getColumnModel().getColumn(0).setMaxWidth(100);

	}

	private void cargarDisegnadores() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {
			if (Empresa.getInstance().getEmpleados().get(i) instanceof Disegnador
					&& !Empresa.getInstance().getEmpleados().get(i).getId().equalsIgnoreCase(txtIdAdicional.getText())
					&& Empresa.getInstance().getEmpleados().get(i).getCondicion().equalsIgnoreCase("Disponible")) {
				fila[0] = Empresa.getInstance().getEmpleados().get(i).getId();
				fila[1] = Empresa.getInstance().getEmpleados().get(i).getNombre();
				fila[2] = Empresa.getInstance().getEmpleados().get(i).getCargo();
				fila[3] = Empresa.getInstance().getEmpleados().get(i).getSalarioHora();
				fila[4] = Empresa.getInstance().getEmpleados().get(i).getEvaluacionAnual();
				model.addRow(fila);

			}
		}

		table.getColumnModel().getColumn(0).setMinWidth(100);
		// table.getColumnModel().getColumn(0).setMaxWidth(100);
	}

	private void cargarClientes() {
		modelClientes.setRowCount(0);

		fila = new Object[modelClientes.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getClientes().size(); i++) {
			fila[0] = Empresa.getInstance().getClientes().get(i).getCedula();
			fila[1] = Empresa.getInstance().getClientes().get(i).getNombre();
			fila[2] = Empresa.getInstance().getClientes().get(i).getDireccion();
			fila[3] = Empresa.getInstance().getClientes().get(i).getContratos().size();
			fila[4] = Empresa.getInstance().getClientes().get(i).getTotalContratos();

			modelClientes.addRow(fila);
		}
	}
}
