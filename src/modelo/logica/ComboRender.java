
package modelo.logica;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ComboRender extends DefaultTableCellRenderer  {
    
    JComboBox<String> combo = new JComboBox<>();
    JSpinner js;    
   
    MiJTextField mt = new MiJTextField();
    
    Font fuente = new Font("Noto Sans", Font.BOLD, 14);
    
    public ComboRender(){
        combo = new JComboBox<>();
        combo.setMaximumRowCount(4); 
        
        combo.addItem("A gusto");
        combo.addItem("Cabeza/s");
        combo.addItem("Cucharada/s");
        combo.addItem("Cucharadita/s");
        combo.addItem("Diente/s");
        combo.addItem("Gramo/s");
        combo.addItem("Hoja/s");
        combo.addItem("Kilo/s");
        combo.addItem("Litro/s");
        combo.addItem("MiliLitro/s");
        combo.addItem("Pizca");
        combo.addItem("Planta/s");
        combo.addItem("Puñado/s");
        combo.addItem("Taza/s");
        combo.addItem("Unidad/es");
                                
        js = new JSpinner();
               
        combo.setFont(fuente);
        js.setFont(fuente);
        
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                                        
        boolean tilde = (boolean)table.getValueAt(row,0);
        
        if(tilde){
        combo.setSelectedItem(value);              
        return combo;        
        }
        combo.setSelectedIndex(-1);
        return combo;
      
    }
    
        
    
    
}
