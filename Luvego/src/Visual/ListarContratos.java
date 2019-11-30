package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.MouseEvent;

import Connection.DBConnection;
import Logico.Cliente;
import Logico.ColorTabla;
import Logico.Contrato;
import Logico.Empleado;
import Logico.Empresa;
import Logico.Proyecto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

public class ListarContratos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private static JTable table;
	private int index;
	private static DefaultTableModel model;
	private static Object[] fila;
	private JTextField txtNombre;
	private JTextField txtId;
	private String select;
	private JButton btnAplazar;
	private Date fechaActual;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ListarContratos() {
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
		
		fechaActual = new Date();
		
		setTitle("Contratos pendientes");
		setBounds(100, 100, 943, 359);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		scrollPane.setBounds(160, 11, 690, 281);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPanel.add(scrollPane);
		{
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
				
					int index = table.getSelectedRow();
					if(index >= 0)
					{
						select = table.getValueAt(index, 0).toString();
						btnAplazar.setEnabled(true);
						//btnContratoInfo.setEnabled(true);
						
					}else
					{
						btnAplazar.setEnabled(false);
						//btnContratoInfo.setEnabled(false);
					}
				}
			});
			table.setRowHeight(25);
			
			
			table.setFont(new Font("Tahoma", Font.PLAIN, 12));
			table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			model = new DefaultTableModel();
			
			
			try {
				table.setModel(DBConnection.getInstance().mostrarContratos());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scrollPane.setViewportView(table);
		
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
			panel.setBackground(new Color(211, 211, 211));
			panel.setBounds(0, 10, 155, 166);
			contentPanel.add(panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBackground(new Color(220, 220, 220));
				panel_1.setBounds(0, 0, 155, 78);
				panel.add(panel_1);
				{
					txtNombre = new JTextField();
					txtNombre.setColumns(10);
					txtNombre.setBounds(10, 20, 135, 20);
					panel_1.add(txtNombre);
				}
				{
					JButton btnCargarPorNombre = new JButton("Consultar");
					btnCargarPorNombre.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cargarPorNombre(txtNombre.getText());
						}
					});
					btnCargarPorNombre.setBackground(new Color(220, 220, 220));
					btnCargarPorNombre.setBounds(38, 45, 79, 23);
					panel_1.add(btnCargarPorNombre);
				}
				{
					JLabel lblBuscarPorCliente = new JLabel("Buscar por cliente");
					lblBuscarPorCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblBuscarPorCliente.setBounds(27, 5, 124, 14);
					panel_1.add(lblBuscarPorCliente);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBackground(new Color(220, 220, 220));
				panel_1.setBounds(0, 78, 155, 86);
				panel.add(panel_1);
				{
					txtId = new JTextField();
					txtId.setColumns(10);
					txtId.setBounds(10, 20, 135, 20);
					panel_1.add(txtId);
				}
				{
					JButton btnCargarPorId = new JButton("Consultar");
					btnCargarPorId.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cargarPorId(txtId.getText());
						}
					});
					btnCargarPorId.setBackground(new Color(220, 220, 220));
					btnCargarPorId.setBounds(38, 45, 79, 23);
					panel_1.add(btnCargarPorId);
				}
				{
					JLabel lblBuscarPorId = new JLabel("Buscar por ID");
					lblBuscarPorId.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblBuscarPorId.setBounds(38, 6, 86, 14);
					panel_1.add(lblBuscarPorId);
				}
			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, null, null, null));
			buttonPane.setBounds(0, 299, 937, 34);
			contentPanel.add(buttonPane);
			{
				buttonPane.setLayout(null);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnSalir.setBounds(874, 5, 53, 23);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
			{
				JButton btnCancelarContrato = new JButton("Cancelar contrato");
				btnCancelarContrato.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Empresa.getInstance().cancelarContrato(select);
						cargarContratos();
						
					}
				});
				btnCancelarContrato.setBounds(736, 5, 128, 23);
				buttonPane.add(btnCancelarContrato);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(112, 128, 144));
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBounds(0, 0, 969, 300);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(851, 215, 91, 78);
				panel.add(panel_2);
				panel_2.setBackground(Color.LIGHT_GRAY);
				panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_2.setLayout(null);
				{
					btnAplazar = new JButton("");
					btnAplazar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Contrato contrato = null;
							for(int i = 0; i < Empresa.getInstance().getContratos().size();i++)
							{
								if(Empresa.getInstance().getContratos().get(i).getId().equalsIgnoreCase(select))
								{
									contrato = Empresa.getInstance().getContratos().get(i);
								}
							}
							AplazarContrato aplazo = new AplazarContrato(contrato);
							aplazo.setLocationRelativeTo(null);
							aplazo.setModal(true);
							aplazo.setVisible(true);
							cargarContratos();
							
						}
					});
					btnAplazar.setEnabled(false);
					btnAplazar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btnAplazar.setToolTipText("Aplazar proyecto");
					btnAplazar.setBackground(Color.WHITE);
					btnAplazar.setBounds(0, 0, 87, 78);
					panel_2.add(btnAplazar);
					btnAplazar.setIcon(
							new ImageIcon(ListarContratos.class.getResource("/img/Contratos/Aplazo de contratos.png")));
					btnAplazar.setFont(new Font("Tahoma", Font.BOLD, 11));
					btnAplazar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				}
			}
		}
		cargarContratos();
	}
		private void cargarContratos() {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		model.setRowCount(0);
		fechaActual = new Date();
		fila = new Object[model.getColumnCount()];
		
		for(int i = 0; i < Empresa.getInstance().getContratos().size();i++)
		{
			fila[0] = Empresa.getInstance().getContratos().get(i).getId();
			fila[1] = Empresa.getInstance().getContratos().get(i).getCliente().getNombre();
			fila[2] = format.format(Empresa.getInstance().getContratos().get(i).getFechaInicio());
		
			if(Empresa.getInstance().getContratos().get(i).isAplazado())
			{
				fila[4] =  Empresa.getInstance().getContratos().get(i).getPrecioAntes();					
			}else
			{
				fila[4] =  Empresa.getInstance().getContratos().get(i).getPrecioFinal();
			}

			fila[3] = format.format(Empresa.getInstance().getContratos().get(i).getFechaEntrega());


			model.addRow(fila);
		}
		
		/*for (int i = 0; i < Empresa.getInstance().getContratos().size(); i++) {
			fila[0] = Empresa.getInstance().getContratos().get(i).getId();
			fila[1] = Empresa.getInstance().getContratos().get(i).getCliente().getNombre();

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			fila[2] = format.format(Empresa.getInstance().getContratos().get(i).getFechaInicio());

			fila[3] = format.format(Empresa.getInstance().getContratos().get(i).getFechaEntrega());
			fila[4] =  Empresa.getInstance().getContratos().get(i).getPrecioFinal();

			
			
			/*if(Empresa.getInstance().getContratos().get(i).getFechaInicio().before(fechaActual))
			{
				//Empresa.getInstance().getProyectoById(Empresa.getInstance().getContratos().get(i).getProyecto().getId()).setEstado("Atrasado");
				//ColorTabla.indice(i);
			
			
			model.addRow(fila);
		}*/
	}
	
	private void cargarPorNombre(String nombre) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getProyectos().size(); i++) {
			if (Empresa.getInstance().getProyectos().get(i).getNombre().equalsIgnoreCase(nombre)) {
				fila[0] = Empresa.getInstance().getProyectos().get(i).getId();
				fila[1] = Empresa.getInstance().getProyectos().get(i).getNombre();
				fila[2] = Empresa.getInstance().getProyectos().get(i).getJefeProyecto().getApellidos();
				fila[3] = Empresa.getInstance().getProyectos().get(i).getEstado();

				model.addRow(fila);
			}
		}

	}
	
	private void cargarPorId(String id) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < Empresa.getInstance().getProyectos().size(); i++) {
			if (Empresa.getInstance().getProyectos().get(i).getId().equalsIgnoreCase(id)) {
				fila[0] = Empresa.getInstance().getProyectos().get(i).getId();
				fila[1] = Empresa.getInstance().getProyectos().get(i).getNombre();
				fila[2] = Empresa.getInstance().getProyectos().get(i).getJefeProyecto().getApellidos();
				fila[3] = Empresa.getInstance().getProyectos().get(i).getEstado();

				model.addRow(fila);
			}
		}

	}
	
}
