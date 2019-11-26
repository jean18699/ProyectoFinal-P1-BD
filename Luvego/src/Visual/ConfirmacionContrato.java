package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Contrato;
import Logico.Proyecto;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmacionContrato extends JDialog {
 
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdContrato;
	private JTextField txtPrecioFinal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfirmacionContrato dialog = new ConfirmacionContrato(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfirmacionContrato(Proyecto proyecto) {
		
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
		
		setTitle("Confirmar");
		setResizable(false);
		setBounds(100, 100, 287, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblIdDelContrato = new JLabel("Id del contrato:");
			lblIdDelContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblIdDelContrato.setBounds(10, 30, 111, 14);
			contentPanel.add(lblIdDelContrato);
		}
		{
			txtIdContrato = new JTextField();
			txtIdContrato.setEditable(false);
			txtIdContrato.setBounds(116, 28, 155, 20);
			contentPanel.add(txtIdContrato);
			txtIdContrato.setColumns(10);
			txtIdContrato.setText(proyecto.getContrato().getId());
		}
		{
			JLabel lblPrecioFinal = new JLabel("Precio final:");
			lblPrecioFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPrecioFinal.setBounds(32, 55, 89, 14);
			contentPanel.add(lblPrecioFinal);
		}
		{
			txtPrecioFinal = new JTextField();
			txtPrecioFinal.setEditable(false);
			txtPrecioFinal.setBounds(116, 55, 155, 20);
			contentPanel.add(txtPrecioFinal);
			txtPrecioFinal.setColumns(10);
			txtPrecioFinal.setText(Float.toString(proyecto.getContrato().getPrecioFinal()));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setBackground(new Color(112, 128, 144));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						proyecto.setRealizado(true);
						dispose();
						//fsdfsdfda
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
