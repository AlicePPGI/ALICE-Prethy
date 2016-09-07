/**
 * 
 */
package presentation;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import repository.ODP;

/**
 * @author wsantos
 *
 */
public class ODPTable extends AbstractTableModel {

	private static final long serialVersionUID = -7830947396394384577L;

	private static final int COL_NAME = 0;
	private static final int COL_DESCRIPTION = 1;
	
	private List<ODP> lines;
	private String[] columns = new String[]{"Name", "Description"};
	
	public ODPTable(List<ODP> odpsFound){
		this.lines = odpsFound;
	}

	@Override
	public int getColumnCount() {
		return this.columns.length;
	}

	@Override
	public int getRowCount() {
		return this.lines.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		ODP odp = this.lines.get(row);
		if(column == COL_NAME){
			return odp.getName();
		}
		if(column == COL_DESCRIPTION){
			return odp.getDescription();
		}
		return "";
	}

	@Override
	public String getColumnName(int columnIndex){
		return this.columns[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex){
		return String.class;
	}

	@Override
	public boolean isCellEditable(int row, int column){
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column){
		ODP odp = this.lines.get(row);
		if(column == COL_NAME){
			odp.setName((String) aValue);
		}
		if(column == COL_DESCRIPTION){
			odp.setDescription((String) aValue);
		}
	}
}
