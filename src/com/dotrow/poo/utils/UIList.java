/*
 * Copyright (C) 2013.  sergio
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dotrow.poo.utils;

import com.dotrow.poo.listeners.ItemListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: JCase
 * Project Url: http://www.dotrow.com/projects/java/jcase
 * Author: Sergio Ceron
 * Version: 1.0
 * Date: 21/04/13 02:26 PM
 * Desc:
 */

public class UIList<T> extends JPanel implements MouseListener {

	private List<ItemListener> listeners = new ArrayList<ItemListener>();

	private DefaultTableModel dm = new DefaultTableModel() {
		@Override
		public boolean isCellEditable( int row, int column ) {
			return false;
		}
	};
	private JTable table;

	public UIList() {
		setLayout(new GridBagLayout());
		setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		dm.addColumn("Title");
		table = new JTable(dm);
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(Color.cyan);
		table.setOpaque(false);

		( (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class) ).setOpaque(false);
		table.setSelectionBackground(Color.darkGray);
		table.setSelectionForeground(Color.white);

		table.addMouseListener(this);

		add(table, c);
	}

	public void addItem( T item ) {
		dm.addRow(new Object[]{ item });
	}

	public void updateItem( T item, int index ) {
		dm.removeRow(index);
		dm.insertRow(index, new Object[]{ item });
	}

	public void removeItem( int index ) {
		dm.removeRow(index);
	}

	public List<ItemListener> getListeners() {
		return listeners;
	}

	public void addListener( ItemListener listener ) {
		this.listeners.add(listener);
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		if( e.getClickCount() == 2 ) {
			JTable target = (JTable) e.getSource();
			int row = target.getSelectedRow();
			Object value = target.getModel().getValueAt(row, 0);
			for( ItemListener il : listeners ) {
				il.onItemDoubleClick(this, value, row);
			}
		}
		if( SwingUtilities.isRightMouseButton(e) ) {
			JTable target = (JTable) e.getSource();
			Point p = e.getPoint();
			int row = target.rowAtPoint(p);
			Object value = target.getModel().getValueAt(row, 0);
			for( ItemListener il : listeners ) {
				il.onItemRightClick(this, value, row);
			}
		}
	}

	@Override
	public void mousePressed( MouseEvent e ) {
	}

	@Override
	public void mouseReleased( MouseEvent e ) {
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
	}

	@Override
	public void mouseExited( MouseEvent e ) {
	}
}


