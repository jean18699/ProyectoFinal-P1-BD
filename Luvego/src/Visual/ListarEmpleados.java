package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.glass.events.MouseEvent;

import Logico.Disegnador;
import Logico.Empresa;
import Logico.Jefe;
import Logico.Programador;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import javax.swing.JTextField;

public class ListarEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private static JTable table;
	private int index;
	private JButton btnAceptar;
	private static DefaultTableModel model;
	private static Object[] fila;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblX;
	private JPanel panel_2;
	private JLabel label;
	private JPanel panel_3;
	private JLabel label_1;
	private String select;
	private JPanel panelRegistrados;
	private JTextField txtTotalEmpleados;
	private JLabel lblEmpleadosTotales;
	private JPanel panel_5;
	private JPanel panel_4;
	private JTextField txtBuscarEmpleado;
	private JLabel lblBuscarEmpleado;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ListarEmpleados(){
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Registro de empleados");
		setBounds(100, 100, 797, 642);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		scrollPane.setBounds(0, 0, 700, 216);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPanel.add(scrollPane);
		{
			table = new JTable();
						
			//Clickear en la tabla
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int index = table.getSelectedRow();
					if (index >= 0) {
						select = table.getValueAt(index, 0).toString();

					}
				}
			});
			table.setRowHeight(25);

			table.setFont(new Font("Tahoma", Font.PLAIN, 12));

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			model = new DefaultTableModel();
			String[] columnnames = { "Código", "Nombre", "Apellidos", "Posición", "Proyectos activos", "Evaluacion" };
			model.setColumnIdentifiers(columnnames);
			table.setModel(model);
			scrollPane.setViewportView(table);
			{
				panel = new JPanel();
				panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel.setBounds(699, 0, 93, 216);
				contentPanel.add(panel);
				panel.setLayout(null);
				{
					panel_1 = new JPanel();
					panel_1.setBackground(Color.RED);
					panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					panel_1.setBounds(0, 160, 93, 59);
					panel.add(panel_1);
					panel_1.setLayout(null);
					{
						lblX = new JLabel("X");
						lblX.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(java.awt.event.MouseEvent e) {
								Empresa.getInstance().eliminarEmpleado(select);
								
								cargarEmpleados();

							}
						});
						lblX.setBackground(Color.RED);
						lblX.setToolTipText("Eliminar");
						lblX.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
						lblX.setForeground(Color.WHITE);
						lblX.setHorizontalAlignment(SwingConstants.CENTER);
						lblX.setFont(new Font("Tahoma", Font.BOLD, 48));
						lblX.setBounds(0, 0, 93, 59);
						panel_1.add(lblX);
					}
				}
				{
					panel_2 = new JPanel();
					panel_2.setBackground(Color.WHITE);
					panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					panel_2.setBounds(0, 0, 93, 84);
					panel.add(panel_2);
					panel_2.setLayout(null);
					{
						label = new JLabel("");
						label.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(java.awt.event.MouseEvent e) {
								RegEmpleado reg = new RegEmpleado();
								reg.setLocationRelativeTo(null);
								reg.setModal(true);
								reg.setVisible(true);
								cargarEmpleados();
							}
						});
						label.setToolTipText("Nuevo empleado");
						label.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
						label.setBackground(Color.WHITE);
						label.setBounds(0, 0, 93, 84);
						panel_2.add(label);
						label.setHorizontalAlignment(SwingConstants.CENTER);
						label.setIcon(new ImageIcon(ListarEmpleados.class.getResource("/img/Cliente a color.png")));
					}
				}
				{
					panel_3 = new JPanel();
					panel_3.setBackground(Color.WHITE);
					panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					panel_3.setBounds(0, 83, 93, 78);
					panel.add(panel_3);
					panel_3.setLayout(null);
					{
						label_1 = new JLabel("\u00A1");
						label_1.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(java.awt.event.MouseEvent e) {
								InfoEmpleado info = new InfoEmpleado(Empresa.getInstance().getEmpleadoById(select));
								info.setLocationRelativeTo(null);
								info.setModal(true);
								info.setVisible(true);
								cargarEmpleados();
							}
						});
						label_1.setToolTipText("Informacion");
						label_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
						label_1.setForeground(Color.BLUE);
						label_1.setHorizontalAlignment(SwingConstants.CENTER);
						label_1.setFont(new Font("Tahoma", Font.BOLD, 50));
						label_1.setBounds(0, 0, 93, 78);
						panel_3.add(label_1);
					}
				}
			}
			{
				
				
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				if(Empresa.getInstance().getCantJefes()==0)
				{
					dataset.setValue(Empresa.getInstance().getCantJefes(), "Jefe", "Jefes");
						
				}else
				{
					dataset.setValue(Empresa.getInstance().getCantJefes(), "Jefe", "Jefes");
					
				}
				if(Empresa.getInstance().getCantJefes()==0)
				{
					dataset.setValue(0, "Jefe", "Jefes");
				}
				if(Empresa.getInstance().getCantPlanificadores()==0)
				{
					dataset.setValue(0, "Planificador", "Planificadores");
				}else
				{
					dataset.setValue(Empresa.getInstance().getCantPlanificadores(), "Planificador", "Planificadores");
				}
				if(Empresa.getInstance().getCantProgramadores()==0)
				{
					dataset.setValue(0, "Programador", "Programadores");
				}else
				{
					dataset.setValue(Empresa.getInstance().getCantProgramadores(), "Programador", "Programadores");
				}
				if(Empresa.getInstance().getCantDisegnadores()==0)
				{
					dataset.setValue(0, "Diseñador", "Diseñadores");
				}else
				{
					dataset.setValue(Empresa.getInstance().getCantJefes(), "Diseñador", "Diseñadores");
				}

				
				// Creando el Grafico

				JFreeChart chart = ChartFactory.createBarChart3D("Cantidad de empleados", "Tipo de empleado", "Cantidad", dataset,
						PlotOrientation.VERTICAL, true, true, false);
				chart.setBackgroundPaint(new Color(112, 128, 144));
				chart.getTitle().setPaint(Color.black);

				CategoryPlot p = chart.getCategoryPlot();
				p.setRangeGridlinePaint(Color.red);

				ChartPanel chartPanel = new ChartPanel(chart);
				chartPanel.setBounds(0, 35, 792, 266);
				chartPanel.setLayout(null);

				panelRegistrados = new JPanel();

				panelRegistrados.setBackground(new Color(112, 128, 144));

				panelRegistrados.setBounds(0, 215, 792, 380);

				contentPanel.add(panelRegistrados);
				panelRegistrados.setLayout(null);
				{
					panel_4 = new JPanel();
					panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					panel_4.setBackground(new Color(112, 128, 144));
					panel_4.setBounds(0, 0, 241, 35);
					panelRegistrados.add(panel_4);
					panel_4.setLayout(null);
					{
						lblBuscarEmpleado = new JLabel("Buscar empleado:");
						lblBuscarEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblBuscarEmpleado.setBounds(10, 10, 110, 14);
						lblBuscarEmpleado.setForeground(Color.WHITE);
						panel_4.add(lblBuscarEmpleado);
					}
					{
						txtBuscarEmpleado = new JTextField();
						txtBuscarEmpleado.setBounds(121, 7, 105, 20);
						txtBuscarEmpleado.addKeyListener(new KeyListener() {

							@Override
							public void keyTyped(KeyEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void keyReleased(KeyEvent e) {
								// TODO Auto-generated method stub
								cargarEmpleados();
							}

							@Override
							public void keyPressed(KeyEvent e) {
								// TODO Auto-generated method stub

							}
						});
						panel_4.add(txtBuscarEmpleado);
						txtBuscarEmpleado.setColumns(10);
					}
				}

				panelRegistrados.add(chartPanel);
				{
					panel_5 = new JPanel();
					panel_5.setBackground(new Color(112, 128, 144));
					panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					panel_5.setBounds(0, 326, 263, 35);
					panelRegistrados.add(panel_5);
					{
						lblEmpleadosTotales = new JLabel("Empleados totales:");
						panel_5.add(lblEmpleadosTotales);
						lblEmpleadosTotales.setForeground(Color.WHITE);
						lblEmpleadosTotales.setBackground(new Color(255, 255, 255));
						lblEmpleadosTotales.setFont(new Font("Tahoma", Font.BOLD, 11));
					}
					{
						txtTotalEmpleados = new JTextField();
						txtTotalEmpleados.setEditable(false);
						panel_5.add(txtTotalEmpleados);
						txtTotalEmpleados.setColumns(10);
						txtTotalEmpleados.setText(Integer.toString(Empresa.getInstance().getEmpleados().size()));
					}
				}

			}
			cargarEmpleados();

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(112, 128, 144));
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnAceptar.setEnabled(false);
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
		}
		cargarEmpleados();
	}

	private void cargarEmpleados() {

		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {

				fila[0] = Empresa.getInstance().getEmpleados().get(i).getId();
				fila[1] = Empresa.getInstance().getEmpleados().get(i).getNombre();
				fila[2] = Empresa.getInstance().getEmpleados().get(i).getApellidos();
				
				if (Empresa.getInstance().getEmpleados().get(i) instanceof Jefe) {
					fila[3] = "Jefe de proyecto";
				} else if (Empresa.getInstance().getEmpleados().get(i) instanceof Disegnador) {
					fila[3] = "Diseñador";
				} else if (Empresa.getInstance().getEmpleados().get(i) instanceof Programador) {
					fila[3] = "Programador";
				} else {
					fila[3] = "Planificador";
				}
				fila[4] = Empresa.getInstance().getEmpleados().get(i).getProyectos().size();
				fila[5] = Empresa.getInstance().getEmpleados().get(i).getEvaluacionAnual();
				

				model.addRow(fila);
			
		}

	}

	/*
	 * public void cargarGrafico() { DefaultCategoryDataset dataset = new
	 * DefaultCategoryDataset();
	 * dataset.setValue(Empresa.getInstance().getCantJefes(),"Jefe", "Jefes");
	 * dataset.setValue(Empresa.getInstance().getCantPlanificadores(),
	 * "Planificador", "Planificadores");
	 * dataset.setValue(Empresa.getInstance().getCantProgramadores(),"Programador",
	 * "Programadores");
	 * dataset.setValue(Empresa.getInstance().getCantJefes(),"Diseñador",
	 * "Diseñadores"); // Creando el Grafico
	 * 
	 * JFreeChart chart = ChartFactory.createBarChart3D("Cantidad de empleados",
	 * "Tipo", "Cantidad", dataset, PlotOrientation.VERTICAL, true, true, false);
	 * chart.setBackgroundPaint(new Color(112, 128, 144));
	 * chart.getTitle().setPaint(Color.black);
	 * 
	 * CategoryPlot p = chart.getCategoryPlot(); p.setRangeGridlinePaint(Color.red);
	 * 
	 * ChartPanel chartPanel = new ChartPanel(chart); chartPanel.setBounds(0, 11,
	 * 792, 290); chartPanel.setLayout(null);
	 * 
	 * }
	 */

}
