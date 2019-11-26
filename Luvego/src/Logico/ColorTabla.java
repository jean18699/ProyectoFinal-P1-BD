package Logico;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


@SuppressWarnings("serial")
public class ColorTabla extends JTable
{
	
	private static ArrayList <Integer> arrayList = new ArrayList<>();
	public Component prepareRenderer(TableCellRenderer renderer, int indexRow, int indexCol)
	{
		Component comp = super.prepareRenderer(renderer, indexRow, indexCol);
			int p =0;
		for(Integer x : arrayList) {
			if(x==indexRow) {
				comp.setBackground(Color.red);
				comp.setForeground(Color.white);
				p=1;
			}
		}
		if(p==0) {
			comp.setBackground(Color.WHITE);
			comp.setForeground(Color.BLACK);
		}
		
		return comp;
		
	}

	
	public static void indice(int ind)
	{
		arrayList.add(ind);
		
	}
	
	
}	
	
	
	
