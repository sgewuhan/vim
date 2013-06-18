package com.sg.vim.trail;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Text;

import com.mongodb.DBObject;
import com.sg.ui.viewer.IEditingSupportor;
import com.sg.ui.viewer.table.CTableViewer;
import com.sg.ui.viewer.table.ColumnConfigurator;

public class CellDisplayEditorSupport implements IEditingSupportor {

    private static final class CellContentDisplaySupport extends EditingSupport {
        private final CellEditor editor;
        private ColumnConfigurator configurator;

        public CellContentDisplaySupport(TableViewer viewer, ColumnConfigurator configurator) {
            super(viewer);
            this.configurator = configurator;
            editor = new TextCellEditor(viewer.getTable());
            ((Text)editor.getControl()).setEditable(false);
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return editor;
        }

        @Override
        protected Object getValue(Object element) {
            if (element instanceof DBObject) {
                return ((DBObject) element).get(configurator.getColumn());
            }
            return null;
        }

        @Override
        protected void setValue(Object element, Object value) {
        }
    }

    public CellDisplayEditorSupport() {
    }

    @Override
    public EditingSupport createEditingSupport(CTableViewer cTableViewer,
            TableViewerColumn viewerColumn, ColumnConfigurator configurator) {
        return new CellContentDisplaySupport(cTableViewer, configurator);
    }

}
