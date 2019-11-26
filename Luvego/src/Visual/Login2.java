package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
//import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
//import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Logico.Empresa;

public class Login2 extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField txtContrasena;
	private JLabel lblFotoLlaves;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login2 frame = new Login2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/*private void leerArchivo() throws IOException, ClassNotFoundException {
		File archivoBiblioteca = new File("registro.dat");
		if(!archivoBiblioteca.exists()) {
			FileOutputStream f = new FileOutputStream(new File("registro.dat"));
			f.close();
		}
		FileInputStream fi = new FileInputStream(new File("registro.dat"));
		if(archivoBiblioteca.length() > 0) {
			ObjectInputStream oi = new ObjectInputStream(fi);
			Empresa.getInstance().setEmpresa((Empresa) oi.readObject());	
			oi.close();
		}
	}
*/

	public Login2() throws ClassNotFoundException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
	//	leerArchivo();

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPane = new JPanel();
		
		{
			JLabel lbl_Id = new JLabel("Id: ");
			lbl_Id.setBounds(26, 46, 56, 16);
			contentPanel.add(lbl_Id);
		}
		{
			txtId = new JTextField();
			txtId.setText("");
			txtId.setBounds(26, 75, 116, 22);
			contentPanel.add(txtId);
			txtId.setColumns(10);
		}
		{
			JLabel lblContrasena = new JLabel("Contrase\u00F1a");
			lblContrasena.setBounds(26, 110, 86, 16);
			contentPanel.add(lblContrasena);
		}
		{
			txtContrasena = new JPasswordField();
			txtContrasena.setEchoChar('*');
			txtContrasena.setBounds(26, 139, 116, 22);
			contentPanel.add(txtContrasena);
			txtContrasena.setColumns(10);
		}
		{
			JButton btnIngresar = new JButton("Ingresar");
			btnIngresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				    String tipo = Empresa.getInstance().getLoginUser().getTipo();
					String pass = Empresa.getInstance().getLoginUser().getPass();
					if(txtId.getText().equalsIgnoreCase(tipo) && contrasenaCorrecta(txtContrasena.getPassword())) {
					//Empresa.getInstance();
					//String id = Empresa.getLoginUser().getId();
					//Empresa.getInstance();
					//String pass = Empresa.getPass();
					if(txtId.getText().equals(tipo) && String.valueOf(txtContrasena.getPassword()).equals(pass)) {
						Principal frame = new Principal();
						dispose();
						frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Id o contraseña", "Advertencia", JOptionPane.WARNING_MESSAGE);
					
				}
			}
			}
			});
			//btnEntrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnIngresar);
			btnIngresar.setBounds(155, 186, 97, 25);
			contentPanel.add(btnIngresar);
		}
		{
			lblFotoLlaves = new JLabel("");
			lblFotoLlaves.setLayout(new BorderLayout());
			lblFotoLlaves.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/llaves.png")).getImage().getScaledInstance(432, 253, Image.SCALE_SMOOTH)));
			lblFotoLlaves.setBounds(0, 0, 432, 253);
			contentPanel.add(lblFotoLlaves);
		}
	}
	
	public boolean contrasenaCorrecta(char[] intento) {
		boolean esCorrecto = true;
		char[] contrasenaCorrecta = Empresa.getInstance().getLoginUser().getPass().toCharArray();
		
		if(intento.length != contrasenaCorrecta.length) {
			esCorrecto = false;
		}
		else {
			esCorrecto = Arrays.equals(intento, contrasenaCorrecta);
		}
		return esCorrecto;
	}
}
