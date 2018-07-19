
package modelo.logica;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel{
    
    public void modeloPrincipal(JTable tabla){
        
        DefaultTableModel modelo= new DefaultTableModel(){
               
            Class[] types = new Class [] {
                   java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
             };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
        
            }
        };
                        
            modelo.addColumn("Imprimir");
            modelo.addColumn("Título");            
            modelo.addColumn("Tipo");
            modelo.addColumn("fecha");
                        
            tabla.setRowHeight(40);
            
            tabla.setModel(modelo);
                                        
            tabla.getTableHeader().setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));             
            tabla.getTableHeader().setForeground(Color.black);
            DefaultTableCellRenderer renderCabecera = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();
            renderCabecera.setHorizontalAlignment(0);
                                    
            tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(250);
                                                                    
    }
    
   public void modeloIngredientes(JTable tabla){
       
       DefaultTableModel modelo= new DefaultTableModel(){
               
            Class[] types = new Class [] {
                 java.lang.String.class, java.lang.String.class
             };
            boolean[] canEdit = new boolean [] {
                 false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
        
            }
        };
                        
            modelo.addColumn("Ingrediente");
            modelo.addColumn("Cantidad");            
                                   
            tabla.setRowHeight(28);
            
            tabla.getTableHeader().setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));             
            tabla.getTableHeader().setForeground(Color.black);
            DefaultTableCellRenderer renderCabecera = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();
            renderCabecera.setHorizontalAlignment(0);
                                    
            tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
                        
            tabla.setModel(modelo);
                               
            tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(250);
   }
   
    
        
     public void modeloCantidadIngredientes(JTable tabla){
       
       DefaultTableModel modelo= new DefaultTableModel(){
               
            Class[] types = new Class [] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
            boolean[] canEdit = new boolean [] {
                 true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                
                boolean[] editar = new boolean[]{true, false, true, true};
                boolean[] noEditar = new boolean[]{true,false,false,false};
                boolean tilde = (boolean) tabla.getValueAt(rowIndex, 0);
                
                if(tilde){
                    
                    return editar[columnIndex];
                    }
                    return noEditar[columnIndex];                                                                                        
                }
            
        };
            modelo.addColumn("Seleccionar");                              
            modelo.addColumn("Ingrediente");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Unidad");
         
                                   
            tabla.setRowHeight(28);
            
            tabla.getTableHeader().setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));             
            tabla.getTableHeader().setForeground(Color.black);
            DefaultTableCellRenderer renderCabecera = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();
            renderCabecera.setHorizontalAlignment(0);
            tabla.setModel(modelo);           
                                   
            tabla.getColumnModel().getColumn(3).setCellEditor(new ComboEditor());           
            tabla.getColumnModel().getColumn(3).setCellRenderer(new ComboRender());
                                               
            DefaultCellEditor unClick = new DefaultCellEditor(new MiJTextField());
            
            unClick.setClickCountToStart(1);
            
                        
            tabla.setDefaultEditor(tabla.getColumnClass(2), unClick);

            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);                                                                 
     }
                               
      public void modeloEditarIngredientes(JTable tabla){
       
       DefaultTableModel modelo= new DefaultTableModel(){
               
            Class[] types = new Class [] {
                 java.lang.String.class, java.lang.String.class
             };
            boolean[] canEdit = new boolean [] {
                 false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
        
            }
        };
                        
            modelo.addColumn("Ingrediente");
            modelo.addColumn("Cantidad");            
                                   
            tabla.setRowHeight(28);
            
            tabla.getTableHeader().setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));             
            tabla.getTableHeader().setForeground(Color.black);
            DefaultTableCellRenderer renderCabecera = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();
            renderCabecera.setHorizontalAlignment(0);
                                    
            
                        
            tabla.setModel(modelo);
                               
           
   }
     
}
    
    

