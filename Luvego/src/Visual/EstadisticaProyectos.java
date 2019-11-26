package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import Logico.Empresa;
import javafx.scene.chart.Chart;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class EstadisticaProyectos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtProyectosActivos;
	private JTextField txtAtrasados;
	private JTextField txtAplazados;
	private JTextField txtCompletados;
	private JTextField txtTotalProyectos;
	private JTextField txtPerdidas;
	private JTextField txtGanancias;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public EstadisticaProyectos() {
		
		
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
		
		setResizable(false);
		setTitle("Estadisticas");
		setBounds(100, 100, 1114, 468);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("C++", Empresa.getInstance().getCantCP());
		data.setValue("C#", Empresa.getInstance().getCantCSharp());
		data.setValue("Java", Empresa.getInstance().getCantJava());
		data.setValue("Python", Empresa.getInstance().getCantPython());
		data.setValue("HTML/Javascript", Empresa.getInstance().getCantHtml());

		// Creando el Grafico
		JFreeChart chart = ChartFactory.createPieChart("Lenguajes de programacion mas solicitados", data, true, true,
				false);
		{
			{
				{
					{
						
					}
				}
			}
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 1108, 403);
			
			contentPanel.add(panel);
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(0, 290, 243, 58);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel.add(panel_1);
				{
					JLabel lblTotalDeProyectos = new JLabel("Total de proyectos realizados:");
					panel_1.add(lblTotalDeProyectos);
				}
				{
					txtTotalProyectos = new JTextField();
					txtTotalProyectos.setEditable(false);
					panel_1.add(txtTotalProyectos);
					txtTotalProyectos.setColumns(10);
					txtTotalProyectos.setText(Integer.toString(Empresa.getInstance().getTotalProyectos()));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(0, 123, 243, 41);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel label_1 = new JLabel("Proyectos aplazados:");
					label_1.setBounds(14, 10, 137, 14);
					panel_1.add(label_1);
				}
				{//dfgdfg
					txtAplazados = new JTextField();
					txtAplazados.setEditable(false);
					txtAplazados.setBounds(140, 7, 91, 18);
					panel_1.add(txtAplazados);
					txtAplazados.setColumns(10);
					
					int contAplazados = 0;
					for(int i = 0; i < Empresa.getInstance().getProyectos().size();i++)
					{
						if(Empresa.getInstance().getProyectos().get(i).getEstado().equalsIgnoreCase("Aplazado"))
						{
							contAplazados++;
						}
					}
					txtAplazados.setText(Integer.toString(contAplazados));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(0, 81, 243, 43);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel label_1 = new JLabel("Proyectos atrasados:");
					label_1.setBounds(14, 10, 131, 14);
					panel_1.add(label_1);
				}
				{
					txtAtrasados = new JTextField();
					txtAtrasados.setEditable(false);
					txtAtrasados.setBounds(140, 10, 92, 22);
					panel_1.add(txtAtrasados);
					txtAtrasados.setColumns(10);
					
					int contAtrasados = 0;
					for(int i = 0; i < Empresa.getInstance().getProyectos().size();i++)
					{
						if(Empresa.getInstance().getProyectos().get(i).getEstado().equalsIgnoreCase("Atrasado"))
						{
							contAtrasados++;
						}
					}
					txtAtrasados.setText(Integer.toString(contAtrasados));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(0, 49, 243, 31);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel label_1 = new JLabel("Proyectos activos:");
					label_1.setBounds(21, 8, 105, 14);
					panel_1.add(label_1);
				}
				{
					txtProyectosActivos = new JTextField();
					txtProyectosActivos.setEditable(false);
					txtProyectosActivos.setBounds(140, 5, 92, 20);
					panel_1.add(txtProyectosActivos);
					txtProyectosActivos.setColumns(10);
					txtProyectosActivos.setText(Integer.toString(Empresa.getInstance().getProyectos().size()));
				}
			}
			JPanel panelGrafico1 = new JPanel();
			panelGrafico1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panelGrafico1.setBounds(243, 11, 442, 379);
			panel.add(panelGrafico1);
			panelGrafico1.setBackground(Color.WHITE);
			panelGrafico1.setLayout(null);

			// Crear el Panel del Grafico con ChartPanel
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBounds(10, 11, 423, 358);
			panelGrafico1.add(chartPanel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBackground(Color.LIGHT_GRAY);
				panel_1.setBounds(0, 349, 243, 41);
				panel.add(panel_1);
				panel_1.setLayout(null);
			}
			{
				JPanel panel_1_1 = new JPanel();
				panel_1_1.setBounds(0, 162, 243, 41);
				panel.add(panel_1_1);
				panel_1_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1_1.setLayout(null);
				{
					JLabel label_1 = new JLabel("Proyectos completados:");
					label_1.setBounds(8, 10, 148, 14);
					panel_1_1.add(label_1);
				}
				{
					txtCompletados = new JTextField();
					txtCompletados.setEditable(false);
					txtCompletados.setBounds(154, 7, 79, 25);
					panel_1_1.add(txtCompletados);
					txtCompletados.setColumns(10);
					txtCompletados.setText(Integer.toString(Empresa.getInstance().getCantProyectosTerminados()));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.LIGHT_GRAY);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBounds(0, 11, 243, 41);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblHistorialDeLa = new JLabel("HISTORIAL DE LA EMPRESA");
					lblHistorialDeLa.setForeground(Color.WHITE);
					lblHistorialDeLa.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblHistorialDeLa.setHorizontalAlignment(SwingConstants.CENTER);
					lblHistorialDeLa.setBounds(0, 0, 243, 41);
					panel_1.add(lblHistorialDeLa);
				}
			}
			JPanel panel_Grafico2 = new JPanel();
			panel_Grafico2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel_Grafico2.setBackground(Color.WHITE);
			panel_Grafico2.setBounds(684, 11, 399, 379);
			panel.add(panel_Grafico2);
			panel_Grafico2.setLayout(null);
			{

				DefaultPieDataset dataCategoria = new DefaultPieDataset();
				dataCategoria.setValue("Escritorio", Empresa.getInstance().getCantDesktop());
				dataCategoria.setValue("Movil", Empresa.getInstance().getCantMovil());
				dataCategoria.setValue("Web", Empresa.getInstance().getCantWeb());

				// Creando el Grafico
				JFreeChart grafico2 = ChartFactory.createPieChart("Tipos de proyectos mas solicitados",
						dataCategoria, true, true, false);
				
				ChartPanel chartPanel_1 = new ChartPanel(grafico2);
				chartPanel_1.setBounds(10, 11, 375, 364);
				panel_Grafico2.add(chartPanel_1);
				chartPanel_1.setLayout(null);
			}
			{
				/*JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBounds(0, 203, 243, 41);
				panel.add(panel_1);
				*/{
					JLabel lblGananciasTotales = new JLabel("Ganancias totales:");
					lblGananciasTotales.setBounds(27, 10, 116, 14);
					panel_Grafico2.add(lblGananciasTotales);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBounds(0, 244, 243, 46);
				panel.add(panel_1);
				{
					JLabel lblPerdidasTotales = new JLabel("Perdidas totales:");
					lblPerdidasTotales.setBounds(36, 13, 137, 14);
					panel_1.add(lblPerdidasTotales);
				}
				{
					txtPerdidas = new JTextField();
					txtPerdidas.setEditable(false);
					txtPerdidas.setColumns(10);
					txtPerdidas.setBounds(141, 10, 92, 25);
					panel_1.add(txtPerdidas);
					txtPerdidas.setText(Float.toString(Empresa.getInstance().getPerdidasTotales()));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_1.setBounds(0, 203, 243, 41);
				panel.add(panel_1);
				{
					JLabel lblGanancias = new JLabel("Ganancias totales:");
					lblGanancias.setBounds(31, 10, 148, 14);
					panel_1.add(lblGanancias);
				}
				{
					txtGanancias = new JTextField();
					txtGanancias.setEditable(false);
					txtGanancias.setColumns(10);
					txtGanancias.setBounds(154, 7, 79, 25);
					panel_1.add(txtGanancias);
					txtGanancias.setText(Float.toString(Empresa.getInstance().getGanancias()));
				}
			}

			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBounds(-29, 401, 1137, 37);
				contentPanel.add(buttonPane);
				buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				buttonPane.setBackground(Color.GRAY);
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				{
					JButton btnAceptar = new JButton("Aceptar");
					btnAceptar.setActionCommand("OK");
					buttonPane.add(btnAceptar);
					getRootPane().setDefaultButton(btnAceptar);
				}
				{
					JButton btnCancelar = new JButton("Cancelar");
					btnCancelar.setActionCommand("Cancel");
					buttonPane.add(btnCancelar);
				}
			}
		}
	}
}
