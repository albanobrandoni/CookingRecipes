
package modelo.logica;

import java.awt.Component;
import java.awt.Font;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ComboEditor extends AbstractCellEditor implements TableCellEditor{
    
    JComboBox<String> combo = new JComboBox<>();    
    Font fuente = new Font("Noto Sans", Font.BOLD, 14);
    
    ComboEditor(){
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
                
        combo.setFont(fuente);
    }
    
    @Override
    public Object getCellEditorValue() {
        return combo.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                                                          
              
        combo.setSelectedItem("A gusto");                    
        combo.setSelectedItem(value);        
                                                      
        return combo;
        
    }
}
      
            
          
