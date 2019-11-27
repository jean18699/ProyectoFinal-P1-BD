package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import Logico.ColorTabla;
import Logico.Disegnador;
import Logico.Empleado;
import Logico.Empresa;
import Logico.Jefe;
import Logico.MySQLConexion;
import Logico.Planificador;
import Logico.Programador;
import Logico.Proyecto;

import java.awt.event.WindowEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995139449296392536L;
	private JPanel contentPane;
	private Dimension dim;
	private JTable tablaProyectos;
	private DefaultTableModel model;
	private static Object[] fila;
	private JTextField txtCantidadProyectos;
	private JTextField txtPerdidas;
	private JTextField txtIdCliente;
	private JTextField txtIngresos;
	private JTextField txtIdProyecto;
	private JTextField txtNombreProyecto;
	private JTextField txtNombreCliente;
	private JTextField txtFecha;
	private JTextField txtFechaEntrega;


	//BASE DE DATOS
	MySQLConexion conexion = new MySQLConexion("Luvego");
	java.sql.Connection conn = conexion.getConexion();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//

	public Principal() {

		setTitle("LuvegoSoft");

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					FileOutputStream fichero = new FileOutputStream("registro.bin");
					ObjectOutputStream guardar = new ObjectOutputStream(fichero);
					guardar.writeObject(Empresa.getInstance().getEmpresa());
					fichero.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);

			}
		});

		
		  try { File arch = new File("registro.bin");
		  
		  if(!arch.exists()) { arch.createNewFile(); }
		  
		  FileInputStream archivo = new FileInputStream(arch);
		  
		  ObjectInputStream cargar = new ObjectInputStream(archivo);
		  Empresa.getInstance().setEmpresa((Empresa)cargar.readObject());
		  
		  archivo.close();
		 
		  }
		  catch (FileNotFoundException e1) {
			  e1.printStackTrace(); }
		  catch
		  (IOException e1) { e1.printStackTrace(); } catch (ClassNotFoundException e1)
		   { e1.printStackTrace(); }
		 

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = super.getToolkit().getScreenSize();
		super.setSize((dim.width), (dim.height - 50));
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(176, 196, 222));
		menuBar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setJMenuBar(menuBar);

		JMenu mnProyectos = new JMenu("Proyectos");
		mnProyectos.setIcon(new ImageIcon(Principal.class.getResource("/img/Nuevo Proyecto 32x32.png")));
		menuBar.add(mnProyectos);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo proyecto");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegProyecto regProyecto = new RegProyecto();
				regProyecto.setModal(true);
				regProyecto.setVisible(true);
				cargarProyectos();
				

				repaint();
			}
		});
		mnProyectos.add(mntmNuevo);

		JMenuItem mntmRegistro = new JMenuItem("Registro de proyectos");
		mntmRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarProyectos listar = new ListarProyectos();
				listar.setLocationRelativeTo(null);
				listar.setModal(true);
				listar.setVisible(true);
				cargarProyectos();
				repaint();
			}
		});
		mnProyectos.add(mntmRegistro);

		JMenuItem mntmEstadisticas = new JMenuItem("Estadisticas");
		mntmEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadisticaProyectos stats = new EstadisticaProyectos();
				stats.setLocationRelativeTo(null);
				stats.setModal(true);
				stats.setVisible(true);
				cargarProyectos();
				repaint();
			}
		});
		mnProyectos.add(mntmEstadisticas);

		JMenu mnEmpleados = new JMenu("Empleados");
		mnEmpleados.setIcon(new ImageIcon(Principal.class.getResource("/img/empleados.png")));
		menuBar.add(mnEmpleados);

		JMenuItem mntmNuevo_1 = new JMenuItem("Registrar empleado");
		mntmNuevo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEmpleado reg = new RegEmpleado();
				reg.setLocationRelativeTo(null);
				reg.setModal(true);
				reg.setVisible(true);
				cargarProyectos();
				repaint();
			}
		});
		mnEmpleados.add(mntmNuevo_1);

		JMenuItem mntmGestionar = new JMenuItem("Gestionar");
		mntmGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListarEmpleados list = new ListarEmpleados(conn);
				list.setLocationRelativeTo(null);
				list.setModal(true);
				list.setVisible(true);
				cargarProyectos();
				repaint();
			}
		});
		mnEmpleados.add(mntmGestionar);

		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setIcon(new ImageIcon(Principal.class.getResource("/img/cliente a color 32x32.png")));
		menuBar.add(mnClientes);

		JMenuItem mntmRegistrar = new JMenuItem("Registrar cliente");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RegCliente reg = new RegCliente();
				reg.setLocationRelativeTo(null);
				reg.setModal(true);
				reg.setVisible(true);
				cargarProyectos();
				repaint();
			}
		});
		mnClientes.add(mntmRegistrar);

		JMenuItem mntmListadoDeClientes = new JMenuItem("Listado de clientes");
		mntmListadoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarClientes list = new ListarClientes();
				list.setLocationRelativeTo(null);
				list.setModal(true);
				list.setVisible(true);
				cargarProyectos();
				repaint();
			}
		});
		mnClientes.add(mntmListadoDeClientes);

		JMenu mnContratos = new JMenu("Contratos");
		mnContratos.setIcon(new ImageIcon(Principal.class.getResource("/img/Contratos/reanudar.png")));
		menuBar.add(mnContratos);

		JMenuItem mntmContratos = new JMenuItem("Contratos realizados");
		mntmContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarContratos list = new ListarContratos();
				list.setLocationRelativeTo(null);
				list.setModal(true);
				list.setModal(true);
				list.setVisible(true);
				cargarProyectos();
				repaint();
			}
		});
		mnContratos.add(mntmContratos);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		model = new DefaultTableModel();
		String[] cols = { "ID", "Nombre del proyecto", "Estado" };
		model.setColumnIdentifiers(cols);
		cargarProyectos();
		contentPane.setLayout(null);

		JPanel panelProyectosSolicitados = new JPanel();
		panelProyectosSolicitados.setBounds(895, 211, 452, 447);
		panelProyectosSolicitados.setBackground(Color.WHITE);
		contentPane.add(panelProyectosSolicitados);

		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Escritorio", Empresa.getInstance().getCantDesktop());
		data.setValue("Web", Empresa.getInstance().getCantWeb());
		data.setValue("Movil", Empresa.getInstance().getCantMovil());

		// Creando el Grafico
		JFreeChart chart = ChartFactory.createPieChart("Tipos de proyectos mas solicitados", data, true, true, false);

		ChartPanel chartPanel = new ChartPanel(chart);

		panelProyectosSolicitados.add(chartPanel);

		JPanel panel = new JPanel();
		panel.setBounds(878, 0, 489, 206);
		panel.setBackground(new Color(112, 128, 144));
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEstadoDeProyectos = new JLabel("Estado de proyectos");
		lblEstadoDeProyectos.setForeground(new Color(255, 255, 255));
		lblEstadoDeProyectos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstadoDeProyectos.setBounds(20, 11, 160, 14);
		panel.add(lblEstadoDeProyectos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 452, 112);
		panel.add(scrollPane);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane.setBackground(Color.WHITE);

		tablaProyectos = new JTable();
		scrollPane.setViewportView(tablaProyectos);
		tablaProyectos.setModel(model);

		JButton btnGestionar = new JButton("Gestionar");
		btnGestionar.setBounds(20, 159, 89, 23);
		panel.add(btnGestionar);

		txtCantidadProyectos = new JTextField();
		txtCantidadProyectos.setEditable(false);
		txtCantidadProyectos.setBounds(379, 175, 86, 20);
		panel.add(txtCantidadProyectos);
		txtCantidadProyectos.setColumns(10);
		txtCantidadProyectos.setText(Integer.toString(Empresa.getInstance().getProyectos().size()));

		JLabel lblTotalDeProyectos = new JLabel("Total de proyectos:");
		lblTotalDeProyectos.setForeground(new Color(255, 255, 255));
		lblTotalDeProyectos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalDeProyectos.setBounds(263, 178, 115, 14);
		panel.add(lblTotalDeProyectos);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(878, 205, 489, 464);
		panel_1.setBackground(new Color(112, 128, 144));
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(0, 363, 363, 306);
		contentPane.add(panel_2);

		txtPerdidas = new JTextField();
		txtPerdidas.setEditable(false);
		txtPerdidas.setColumns(10);
		txtPerdidas.setBounds(10, 262, 154, 20);
		txtPerdidas.setText(Float.toString(Empresa.getInstance().getUltimasPerdida()));
		panel_2.add(txtPerdidas);

		JLabel label = new JLabel("Ingresos adquiridos");
		label.setBounds(188, 243, 97, 14);
		panel_2.add(label);

		txtIdCliente = new JTextField();
		txtIdCliente.setEditable(false);
		txtIdCliente.setColumns(10);
		txtIdCliente.setBounds(10, 118, 154, 20);
		txtIdCliente.setText(Empresa.getInstance().getUltimoIdCliente());
		panel_2.add(txtIdCliente);
		/*
		 * if(Empresa.getInstance().getUltimoProyecto().getContrato().getCliente().getId
		 * ()!=null) {
		 * txtIdCliente.setText(Empresa.getInstance().getUltimoProyecto().getContrato().
		 * getCliente().getId());
		 * 
		 * }
		 */

		JLabel label_1 = new JLabel("Perdidas estimadas");
		label_1.setBounds(10, 243, 121, 14);
		panel_2.add(label_1);

		txtIngresos = new JTextField();
		txtIngresos.setEditable(false);
		txtIngresos.setColumns(10);
		txtIngresos.setBounds(188, 262, 154, 20);
		txtIngresos.setText(Float.toString(Empresa.getInstance().getUltimaGanancia()));
		panel_2.add(txtIngresos);

		JLabel label_2 = new JLabel("ID del proyecto:");
		label_2.setBounds(10, 36, 88, 14);
		panel_2.add(label_2);

		txtIdProyecto = new JTextField();
		txtIdProyecto.setEditable(false);
		txtIdProyecto.setColumns(10);
		txtIdProyecto.setBounds(10, 61, 154, 20);
		panel_2.add(txtIdProyecto);
		txtIdProyecto.setText(Empresa.getInstance().getUltimoId());

		// txtIdProyecto.setText(Empresa.getInstance().getUltimoProyecto().getClasificacion());

		JLabel label_3 = new JLabel("ID del cliente:");
		label_3.setBounds(10, 93, 88, 14);
		panel_2.add(label_3);

		JLabel label_4 = new JLabel("Nombre del proyecto");
		label_4.setBounds(188, 36, 121, 14);
		panel_2.add(label_4);

		txtNombreProyecto = new JTextField();
		txtNombreProyecto.setEditable(false);
		txtNombreProyecto.setColumns(10);
		txtNombreProyecto.setBounds(188, 61, 154, 20);
		panel_2.add(txtNombreProyecto);
		txtNombreProyecto.setText(Empresa.getInstance().getUltimoNombreProyecto());

		JLabel label_5 = new JLabel("Nombre del cliente");
		label_5.setBounds(188, 93, 97, 14);
		panel_2.add(label_5);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setColumns(10);
		txtNombreCliente.setBounds(188, 118, 154, 20);
		txtNombreCliente.setText(Empresa.getInstance().getUltimoNombreCliente());
		panel_2.add(txtNombreCliente);

		JLabel label_6 = new JLabel("Fecha de solicitud");
		label_6.setBounds(10, 154, 88, 14);
		panel_2.add(label_6);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(10, 179, 154, 20);
		panel_2.add(txtFecha);

		JLabel label_7 = new JLabel("Fecha de entrega");
		label_7.setBounds(188, 154, 97, 14);
		panel_2.add(label_7);

		txtFechaEntrega = new JTextField();
		txtFechaEntrega.setEditable(false);
		txtFechaEntrega.setColumns(10);
		txtFechaEntrega.setBounds(188, 179, 154, 20);
		panel_2.add(txtFechaEntrega);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(0, 236, 363, 70);
		panel_2.add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(0, 0, 363, 25);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblInformacionSobreLa = new JLabel("Informacion sobre la ultima venta");
		lblInformacionSobreLa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformacionSobreLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacionSobreLa.setBounds(0, 0, 363, 25);
		panel_4.add(lblInformacionSobreLa);
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarProyectos list = new ListarProyectos();
				list.setLocationRelativeTo(null);
				list.setModal(true);
				list.setVisible(true);
				cargarProyectos();
				repaint();
			}
		});

		Proyecto ultimo = Empresa.getInstance().getUltimoProyecto();
		if (ultimo != null) {
			String patron = "dd/MM/yyyy";
			DateFormat df = new SimpleDateFormat(patron);

			txtNombreProyecto.setText(ultimo.getNombre());
			txtIdProyecto.setText(ultimo.getId());
			txtIdCliente.setText(ultimo.getContrato().getCliente().getCedula());
			txtNombreCliente.setText(ultimo.getContrato().getCliente().getNombre());
			txtFechaEntrega.setText(df.format(ultimo.getContrato().getFechaEntrega()));
			txtFecha.setText(df.format(ultimo.getContrato().getFechaInicio()));
			txtPerdidas.setText(Float.toString(Empresa.getInstance().getUltimasPerdida()));
			txtIngresos.setText(Float.toString(Empresa.getInstance().getUltimaGanancia()));
		}

	}

	private void cargarProyectos() {
//<<<<<<< HEAD

	
		Date fecha = new Date();
		// Empresa.getInstance().eliminarContratoCliente("dasdasd", "1");
		// System.out.println(Empresa.getInstance().getContratos().size());
		// System.out.println(Empresa.getInstance().getContratos().remove(0));
		for (int i = 0; i < Empresa.getInstance().getProyectos().size(); i++) {
			if (Empresa.getInstance().getProyectos().get(i).getContrato().getFechaEntrega().before(fecha)) {

				Empresa.getInstance().getProyectos().get(i).setEstado("Atrasado");

				Empresa.getInstance().getProyectos().get(i).setAtrasado(true);
			} else {
				Empresa.getInstance().getProyectos().get(i).setEstado("Normal");
			}
		}

		for (int i = 0; i < Empresa.getInstance().getProyectos().size(); i++) {
			if (Empresa.getInstance().getProyectos().get(i).getEstado().equalsIgnoreCase("Atrasado")) {
				for (int j = 0; j < Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().size(); j++) {
					Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().get(j).setProyectosAtrasados(
							Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().get(j).getProyectosAtrasados()
									+ 1);
					if (Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().get(j)
							.getProyectosAtrasados() == 2) {
						Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().get(j)
								.setEvaluacionAnual("Bueno");
					}
					if (Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().get(j)
							.getProyectosAtrasados() > 2) {
						Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().get(j).setEvaluacionAnual("Malo");
					}
					if (Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().get(j)
							.getProyectosAtrasados() < 2) {
						Empresa.getInstance().getProyectos().get(i).getGrupoTrabajo().get(j)
								.setEvaluacionAnual("Excelente");
					}
				}
			}
		}
 
//=======
//>>>>>>> branch 'master' of https://github.com/jean18699/ProyectoFinalP1.git

		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (int i = 0; i < Empresa.getInstance().getProyectos().size(); i++) {

			fila[0] = Empresa.getInstance().getProyectos().get(i).getId();
			fila[1] = Empresa.getInstance().getProyectos().get(i).getNombre();
			fila[2] = Empresa.getInstance().getProyectos().get(i).getEstado();

			model.addRow(fila);
		}

	}

}
