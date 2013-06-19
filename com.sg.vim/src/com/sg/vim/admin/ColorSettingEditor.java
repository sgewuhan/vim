package com.sg.vim.admin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.mobnut.commons.util.Utils;
import com.mobnut.commons.util.file.FileUtil;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.ImageResource;
import com.sg.ui.UI;
import com.sg.ui.UIUtils;
import com.sg.vim.model.IVIMFields;

public class ColorSettingEditor extends EditorPart {


    protected class StringIntegerEditingSupport extends StringEditingSpport {

        public StringIntegerEditingSupport(String properties, boolean noEmpty) {
            super(properties, noEmpty);

        }

        protected CellEditor getEditor() {
            TextCellEditor editor = new TextCellEditor(viewer.getTable());
            editor.setValidator(new ICellEditorValidator() {

                @Override
                public String isValid(Object value) {
                    if (noEmpty && Utils.isNullOrEmptyString(value)) {
                        return "您需要为属性设置值";
                    }
                    if ((value instanceof String) && (Utils.isNumbers((String) value))) {
                        return null;
                    } else {
                        return "您需要输入数字";
                    }
                }
            });

            return editor;
        }

    }

    protected class StringEditingSpport extends EditingSupport {

        protected String properties;
        private CellEditor editor;
        protected boolean noEmpty;

        public StringEditingSpport(String properties, boolean noEmpty) {
            super(viewer);
            this.properties = properties;
            this.noEmpty = noEmpty;
            editor = getEditor();

        }

        protected CellEditor getEditor() {
            TextCellEditor editor = new TextCellEditor(viewer.getTable());
            if (noEmpty) {
                editor.setValidator(new ICellEditorValidator() {

                    @Override
                    public String isValid(Object value) {
                        if (Utils.isNullOrEmptyString(value)) {
                            return "您需要为属性设置值";
                        } else {
                            return null;
                        }
                    }
                });
            }
            return editor;
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return editor;
        }

        @Override
        protected boolean canEdit(Object element) {
            return !(element instanceof AddColorItem);
        }

        @Override
        protected Object getValue(Object element) {
            ColorItem printerItem = (ColorItem) element;
            return printerItem.getValue(properties);
        }

        @Override
        protected void setValue(Object element, Object value) {
            ColorItem printerItem = (ColorItem) element;
            printerItem.setValue(properties, value);
            viewer.update(printerItem, null);
        }

    }

    public class AddColorItem extends ColorItem {

        public AddColorItem() {
        }

        public String getText(String parameter) {
            if (IVIMFields.color_name.equals(parameter)) {
                String image = "<img src='"+FileUtil.getImageURL(ImageResource.ADD_16, UI.PLUGIN_ID)+"'  width='16' height='16'/>";

                return "<small>"+image+"<a href=\"create\" target=\"_rwt\">注册新的颜色</a></small>";
            } else {
                return "";
            }
        }

    }

    public class ColorItem {

        private DBObject data;

        public ColorItem(DBObject next) {
            data = next;
        }

        public void setValue(String properties, Object value) {
            Object oldValue = data.get(properties);
            if (!Util.equals(oldValue, value)) {
                data.put(properties, value);
                isDirty = true;
                firePropertyChange(PROP_DIRTY);
            }
        }

        public ColorItem() {
        }

        public Object getValue(String parameter) {
            Object value = data.get(parameter);
            if (value == null) {
                return "";
            } else {
                return value;
            }
        }

        public String getText(String parameter) {
            int index = input.indexOf(this);
            String text = (String) getValue(parameter);
            if (parameter.equals(IVIMFields.color_name)) {
                if (!Utils.isNullOrEmpty(text)) {
                    String image = "<img src='"+FileUtil.getImageURL(ImageResource.DELETE_16, UI.PLUGIN_ID)+"'  width='16' height='16'/>";
                    text = "     <span  style='float:right;padding:0px'><small>"+ image+"<a href=\"remove@"
                            + index + "\" target=\"_rwt\">删除</a></small></span>" + text;
                }
            }
            return text;
        }
    }

    private TableViewer viewer;
    private List<ColorItem> input;
    private DBCollection collection;
    private boolean isDirty;

    public ColorSettingEditor() {
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        ArrayList<DBObject> dataList = new ArrayList<DBObject>();
        for (int i = 0; i < input.size(); i++) {
            ColorItem item = input.get(i);
            if (item instanceof AddColorItem) {
                continue;
            }
            dataList.add(item.data);
        }

        collection.remove(new BasicDBObject());
        collection.insert(dataList);
        isDirty = false;
        firePropertyChange(PROP_DIRTY);
    }

    @Override
    public void doSaveAs() {
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        setSite(site);
        setInput(input);
        collection = DBActivator.getCollection("appportal", "colors");

    }

    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void createPartControl(Composite parent) {
        viewer = new TableViewer(parent, SWT.BORDER);
        viewer.getTable().setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.detail == RWT.HYPERLINK) {
                    if (e.text.endsWith("create")) {
                        DBObject data = new BasicDBObject();
                        data.put(IVIMFields.color_name, "");
                        data.put(IVIMFields.color_code, "");
                        input.add(input.size() - 1, new ColorItem(data));
                        viewer.refresh();
                        isDirty = true;
                        firePropertyChange(PROP_DIRTY);
                    } else if (e.text.contains("remove@")) {
                        int index = Integer.parseInt(e.text.split("@")[1]);
                        input.remove(index);
                        viewer.refresh();
                        isDirty = true;
                        firePropertyChange(PROP_DIRTY);
                    }
                }
            }
        });

        UIUtils.enableTableViewerEditing(viewer);

        TableViewerColumn col = createEditableColumn("颜色名称", IVIMFields.color_name, 180);
        col.setEditingSupport(new StringEditingSpport(IVIMFields.color_name, true));

        col = createEditableColumn("颜色代码", IVIMFields.color_code, 180);
        col.setEditingSupport(new StringEditingSpport(IVIMFields.color_code, true));

        viewer.setContentProvider(ArrayContentProvider.getInstance());

        input = new ArrayList<ColorItem>();
        DBCursor cur = collection.find();
        while (cur.hasNext()) {
            input.add(new ColorItem(cur.next()));
        }
        input.add(new AddColorItem());

        viewer.setInput(input);
    }

    private TableViewerColumn createEditableColumn(String text, final String properties, int width) {
        TableViewerColumn col = new TableViewerColumn(viewer, SWT.LEFT);
        col.getColumn().setText(text);
        col.getColumn().setWidth(width);
        col.getColumn().setResizable(true);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((ColorItem) element).getText(properties);
            }
        });

        return col;
    }

    @Override
    public void setFocus() {

    }

}
