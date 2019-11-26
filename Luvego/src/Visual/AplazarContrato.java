package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Logico.Contrato;
import Logico.Proyecto;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSpinner.DefaultEditor;

public class AplazarContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFechaAnterior;
	private String patron = "dd/MM/yyyy";
	private JSpinner spnFecha;
	private int caretPosition;

	private Contrato contrato;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public AplazarContrato(Contrato contrato) {
		
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
		
		this.contrato = contrato;
		setResizable(false);
		setType(Type.UTILITY);
		setBounds(100, 100, 356, 151);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBounds(0, 0, 174, 89);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblIngresarNuevaFecha = new JLabel("Ingresar nueva fecha");
				lblIngresarNuevaFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblIngresarNuevaFecha.setHorizontalAlignment(SwingConstants.CENTER);
				lblIngresarNuevaFecha.setBounds(0, 11, 174, 14);
				panel.add(lblIngresarNuevaFecha);
			}
			{
				Date date = new Date();
				SpinnerDateModel sdm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);

				spnFecha = new JSpinner(sdm);
				spnFecha.setBounds(32, 42, 108, 20);
				panel.add(spnFecha);

				JSpinner.DateEditor DateEdit = new JSpinner.DateEditor(spnFecha, patron);
				DateEdit.getTextField().setEditable(true);

				spnFecha.setEditor(DateEdit);

				((DefaultEditor) spnFecha.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
				((JSpinner.DefaultEditor) spnFecha.getEditor()).getTextField().addCaretListener(new CaretListener() {

					@Override
					public void caretUpdate(CaretEvent e) {
						// TODO Auto-generated method stub
						caretPosition = e.getDot();
					}
				});
				;

				((JSpinner.DefaultEditor) spnFecha.getEditor()).getTextField().addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub

						if (e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_LEFT
								&& e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN) {
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

						if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
							e.consume();
						}

					}
				});

			}
		}

		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(170, 0, 180, 90);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				txtFechaAnterior = new JTextField();
				txtFechaAnterior.setBounds(31, 42, 115, 20);
				panel.add(txtFechaAnterior);
				txtFechaAnterior.setEditable(false);
				txtFechaAnterior.setColumns(10);
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				
				txtFechaAnterior.setText(formato.format(contrato.getFechaEntrega()).toString());
			}
			{
				JLabel lblFechaDeEntrega = new JLabel("Fecha de entrega anterior");
				lblFechaDeEntrega.setHorizontalAlignment(SwingConstants.CENTER);
				lblFechaDeEntrega.setBounds(0, 11, 180, 20);
				panel.add(lblFechaDeEntrega);
				lblFechaDeEntrega.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Date fecha = (Date) spnFecha.getValue();
						if (fecha.before(contrato.getFechaEntrega())) {
							JOptionPane.showMessageDialog(null, "Digite una fecha valida", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							contrato.aplazar((Date) spnFecha.getValue());
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
