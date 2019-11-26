package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Logico.Cliente;
import Logico.Contrato;
import Logico.Empleado;
import Logico.Empresa;
import Logico.Proyecto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegContrato extends JDialog {
	private JTextField txtIdProyecto;
	private JTextField txtNombreProyecto;
	private JTextField txtIdCliente;
	private JTextField txtNombreCliente;
	private String patron = "dd/MM/yyyy";
	private JSpinner spnFecha;
	private Date fechaEntrega;
	private int caretPosition;
	
	
	public RegContrato(Proyecto proyecto, Cliente cliente) {
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setBounds(100, 100, 550, 338);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBounds(0, 0, 546, 270);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			//JSpinner spnFecha = new JSpinner((SpinnerModel) null);
			Date date = new Date();
			SpinnerDateModel sdm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(0, 0, 90, 270);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					
					/*spnFecha.addChangeListener(new ChangeListener() {
						int i = 0;
				        @Override
				        public void stateChanged(ChangeEvent e) {
				            
				            fechaEntrega = (Date) spnFecha.getValue();
							contrato.setProyecto(proyecto);
							contrato.setFechaEntrega(fechaEntrega);
							txtPrecioContrato.setText(Float.toString(contrato.getPrecioFinal()));
							txtIdContrato.setText(contrato.getId());
				            
				        }
				    });*/;
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					panel_2.setBounds(89, 228, 456, 42);
					panel.add(panel_2);
					panel_2.setLayout(null);
					{
						JLabel lblFechaDeEntrega = new JLabel("Fecha de entrega");
						lblFechaDeEntrega.setBounds(10, 11, 98, 14);
						panel_2.add(lblFechaDeEntrega);
						lblFechaDeEntrega.setFont(new Font("Tahoma", Font.BOLD, 11));
					}
					spnFecha = new JSpinner(sdm);
					spnFecha.setBounds(114, 7, 88, 22);
					panel_2.add(spnFecha);
					JSpinner.DateEditor DateEdit = new JSpinner.DateEditor(spnFecha, patron);
					DateEdit.getTextField().setEditable(true);
					
					spnFecha.setEditor(DateEdit);
					
					((DefaultEditor) spnFecha.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
					((JSpinner.DefaultEditor)spnFecha.getEditor()).getTextField().addCaretListener(new CaretListener() {
						
						@Override
						public void caretUpdate(CaretEvent e) {
							// TODO Auto-generated method stub
							caretPosition = e.getDot();				
						}
					});
					
					
					((JSpinner.DefaultEditor)spnFecha.getEditor()).getTextField().addKeyListener(new KeyListener() {
						
						@Override
						public void keyTyped(KeyEvent e) {
							// TODO Auto-generated method stub

							if(e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN) {
								e.consume();
							}
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub


											
						}
						
						@Override
						public void keyPressed(KeyEvent e) {
							// TODO Auto-generated method stub

							if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
								e.consume();
							}

						}
					});
					//fechaEntrega = new Date(spnFecha.getValue().toString());
					
					((JSpinner.DefaultEditor)spnFecha.getEditor()).getTextField().addKeyListener(new KeyListener() {
						
						@Override
						public void keyTyped(KeyEvent e) {
							// TODO Auto-generated method stub

							if(e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN) {
								e.consume();
							}
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub									
						}
						
						@Override
						public void keyPressed(KeyEvent e) {
							// TODO Auto-generated method stub

							if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
								e.consume();
							}

						}
					});
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					panel_2.setBounds(89, 0, 456, 228);
					panel.add(panel_2);
					panel_2.setLayout(null);
					{
						JPanel panel_3 = new JPanel();
						panel_3.setBorder(new TitledBorder(null, "Datos preliminares del proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						panel_3.setBounds(10, 11, 437, 106);
						panel_2.add(panel_3);
						panel_3.setLayout(null);
						{
							JLabel lblIdentificadorDelProyecto = new JLabel("Identificador del proyecto:");
							lblIdentificadorDelProyecto.setBounds(10, 40, 168, 14);
							panel_3.add(lblIdentificadorDelProyecto);
							lblIdentificadorDelProyecto.setFont(new Font("Tahoma", Font.BOLD, 12));
						}
						{
							JLabel lblNombreProyecto = new JLabel("Nombre del proyecto:");
							lblNombreProyecto.setBounds(38, 67, 140, 14);
							panel_3.add(lblNombreProyecto);
							lblNombreProyecto.setFont(new Font("Tahoma", Font.BOLD, 12));
						}
						{
							txtIdProyecto = new JTextField();
							txtIdProyecto.setBounds(188, 38, 124, 20);
							panel_3.add(txtIdProyecto);
							txtIdProyecto.setEnabled(false);
							txtIdProyecto.setColumns(10);
							txtIdProyecto.setText(proyecto.getId());
						}
						{
							txtNombreProyecto = new JTextField();
							txtNombreProyecto.setBounds(188, 65, 124, 20);
							panel_3.add(txtNombreProyecto);
							txtNombreProyecto.setEnabled(false);
							txtNombreProyecto.setColumns(10);
							txtNombreProyecto.setText(proyecto.getNombre());
						}
						{
							JButton btnVerEquipo = new JButton("Ver equipo");
							btnVerEquipo.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									InfoEquipo info = new InfoEquipo(proyecto.getGrupoTrabajo());
									info.setLocationRelativeTo(null);
									info.setModal(true);
									info.setVisible(true);
								}
							});
							btnVerEquipo.setBounds(317, 64, 110, 23);
							panel_3.add(btnVerEquipo);
						}
					}
					{
						JPanel panel_3 = new JPanel();
						panel_3.setBorder(new TitledBorder(null, "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						panel_3.setBounds(10, 118, 437, 99);
						panel_2.add(panel_3);
						panel_3.setLayout(null);
						{
							JLabel lblNombreDelCliente = new JLabel("Nombre del cliente:");
							lblNombreDelCliente.setBounds(58, 63, 124, 14);
							panel_3.add(lblNombreDelCliente);
							lblNombreDelCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
						}
						{
							txtNombreCliente = new JTextField();
							txtNombreCliente.setBounds(192, 61, 124, 20);
							panel_3.add(txtNombreCliente);
							txtNombreCliente.setEnabled(false);
							txtNombreCliente.setColumns(10);
							txtNombreCliente.setText(cliente.getNombre());
						}
						{
							JLabel lblIdentificadorDelCliente = new JLabel("Identificador del cliente:");
							lblIdentificadorDelCliente.setBounds(31, 38, 157, 14);
							panel_3.add(lblIdentificadorDelCliente);
							lblIdentificadorDelCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
						}
						{
							txtIdCliente = new JTextField();
							txtIdCliente.setBounds(192, 37, 124, 18);
							panel_3.add(txtIdCliente);
							txtIdCliente.setEnabled(false);
							txtIdCliente.setColumns(10);
							txtIdCliente.setText(cliente.getId());
						}
						{
							JButton btnNewButton_2 = new JButton("Informacion");
							btnNewButton_2.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
								
									InfoCliente info = new InfoCliente(proyecto.getContrato().getCliente());
									info.setLocationRelativeTo(null);
									info.setModal(true);
									info.setVisible(true);
								}
							});
							btnNewButton_2.setBounds(326, 60, 101, 23);
							panel_3.add(btnNewButton_2);
						}
					}
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBounds(0, 0, 546, 309);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBounds(0, 272, 545, 37);
				panel.add(buttonPane);
				buttonPane.setBackground(Color.LIGHT_GRAY);
				buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				{
					JButton btnAceptar = new JButton("Realizar contrato");
					btnAceptar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							fechaEntrega = (Date)spnFecha.getValue();
							
							Contrato contrato = new Contrato(proyecto,fechaEntrega);
							contrato.setProyecto(null);
							contrato.setFechaEntrega(fechaEntrega);
							contrato.setCliente(cliente);
							proyecto.setContrato(contrato);
							contrato.setId(Integer.toString(Empresa.getInstance().getTotalProyectos()+1));
							
							if(proyecto.getClasificacion().equalsIgnoreCase("Escritorio"))
								Empresa.getInstance().setCantDesktop(Empresa.getInstance().getCantDesktop()+1);
								
							if(proyecto.getClasificacion().equalsIgnoreCase("Web"))
								Empresa.getInstance().setCantDesktop(Empresa.getInstance().getCantWeb()+1);

							if(proyecto.getClasificacion().equalsIgnoreCase("Movil"))
								Empresa.getInstance().setCantDesktop(Empresa.getInstance().getCantMovil()+1);

							
							
							if(proyecto.getLenguaje().equalsIgnoreCase("C++"))
								Empresa.getInstance().setCantCP(Empresa.getInstance().getCantCP()+1);
								
							if(proyecto.getLenguaje().equalsIgnoreCase("Java"))
								Empresa.getInstance().setCantJava(Empresa.getInstance().getCantJava()+1);

							if(proyecto.getLenguaje().equalsIgnoreCase("C#"))
								Empresa.getInstance().setCantCSharp(Empresa.getInstance().getCantCSharp()+1);
							
							if(proyecto.getLenguaje().equalsIgnoreCase("Python"))
								Empresa.getInstance().setCantCSharp(Empresa.getInstance().getCantCSharp()+1);
							
							if(proyecto.getLenguaje().equalsIgnoreCase("HTML/Javascript"))
								Empresa.getInstance().setCantHtml(Empresa.getInstance().getCantHtml()+1);
							
							
							//JOptionPane.showMessageDialog(null, "Id del contrato: "+contrato.getId(), "Confirmacion",

							ConfirmacionContrato confirm = new ConfirmacionContrato(proyecto);
							confirm.setLocationRelativeTo(null);
							confirm.setModal(true);
							confirm.setVisible(true);
						
							if(proyecto.isRealizado())
							{
								Empresa.getInstance().agregarProyecto(proyecto);
								dispose();
							}
							
						}
					});
					btnAceptar.setActionCommand("OK");
					buttonPane.add(btnAceptar);
					getRootPane().setDefaultButton(btnAceptar);
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
	}
}
