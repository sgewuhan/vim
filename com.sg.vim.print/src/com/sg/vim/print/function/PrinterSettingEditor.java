package com.sg.vim.print.function;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
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
import com.sg.vim.datamodel.util.VimUtils;

public class PrinterSettingEditor extends EditorPart {

    public static final String[] functionsNameList = new String[] { "��ӡ�ϸ�֤", "��ӡ����һ����֤��", "��ӡȼ�ͱ�ʶ" };

    public class ComboEditingSpport extends StringEditingSpport {

        protected ComboEditingSpport(String properties) {
            super(properties, true);

        }

        @Override
        protected CellEditor getEditor() {
            ComboBoxCellEditor editor = new ComboBoxCellEditor(viewer.getTable(),
                    functionsNameList, SWT.READ_ONLY);
            return editor;
        }

        @Override
        protected Object getValue(Object element) {
            PrinterItem printerItem = (PrinterItem) element;
            String text = (String) printerItem.getValue(properties);
            for (int i = 0; i < functionsNameList.length; i++) {
                if (functionsNameList[i].equals(text)) {
                    return i;
                }
            }
            return 0;
        }

        @Override
        protected void setValue(Object element, Object value) {
            int i = ((Integer) value).intValue();
            super.setValue(element, functionsNameList[i]);
        }
    }

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
                        return "����ҪΪ��������ֵ";
                    }
                    if ((value instanceof String) && (Utils.isNumbers((String) value))) {
                        return null;
                    } else {
                        return "����Ҫ��������";
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
                            return "����ҪΪ��������ֵ";
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
            return !(element instanceof AddActionPrintItem);
        }

        @Override
        protected Object getValue(Object element) {
            PrinterItem printerItem = (PrinterItem) element;
            return printerItem.getValue(properties);
        }

        @Override
        protected void setValue(Object element, Object value) {
            PrinterItem printerItem = (PrinterItem) element;
            printerItem.setValue(properties, value);
            viewer.update(printerItem, null);
        }

    }

    public class AddActionPrintItem extends PrinterItem {

        public AddActionPrintItem() {
        }

        public String getText(String parameter) {
            if (VimUtils.mVeh_PrinterName.equals(parameter)) {
                String image = "<img src='"+FileUtil.getImageURL(ImageResource.ADD_16, UI.PLUGIN_ID)+"'  width='16' height='16'/>";

                return "<small>"+image+"<a href=\"create\" target=\"_rwt\">ע���´�ӡ��</a></small>";
            } else {
                return "";
            }
        }

    }

    public class PrinterItem {

        private DBObject data;

        public PrinterItem(DBObject next) {
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

        public PrinterItem() {
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
            if (parameter.equals(VimUtils.mVeh_PrinterName)) {
                if (!Utils.isNullOrEmpty(text)) {
                    String image = "<img src='"+FileUtil.getImageURL(ImageResource.DELETE_16, UI.PLUGIN_ID)+"'  width='16' height='16'/>";
                    text = "     <span  style='float:right;padding:0px'><small>"+ image+"<a href=\"remove@"
                            + index + "\" target=\"_rwt\">ɾ��</a></small></span>" + text;
                }
            }
            return text;
        }
    }

    private TableViewer viewer;
    private List<PrinterItem> input;
    private DBCollection collection;
    private boolean isDirty;

    public PrinterSettingEditor() {
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        ArrayList<DBObject> dataList = new ArrayList<DBObject>();
        for (int i = 0; i < input.size(); i++) {
            PrinterItem item = input.get(i);
            if (item instanceof AddActionPrintItem) {
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
        collection = DBActivator.getCollection("appportal", "printers");

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
        // ��ѯ��ϵͳ��ӡ���б������ṩ����ɾ���ĵĹ���
        viewer = new TableViewer(parent, SWT.BORDER);
        viewer.getTable().setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.detail == RWT.HYPERLINK) {
                    if (e.text.equals("create")) {
                        DBObject data = new BasicDBObject();
                        data.put(VimUtils.mVeh_PrintPosLeft, "15");
                        data.put(VimUtils.mVeh_PrintPosTop, "15");
                        data.put(VimUtils.mVeh_Connect, "COM1");
                        data.put(VimUtils.mVeh_Baud, "9600");
                        data.put(VimUtils.mVeh_Parity, "N");
                        data.put(VimUtils.mVeh_Databits, "8");
                        data.put(VimUtils.mVeh_Stopbits, "1");
                        input.add(input.size() - 1, new PrinterItem(data));
                        viewer.refresh();
                        isDirty = true;
                        firePropertyChange(PROP_DIRTY);
                    } else if (e.text.startsWith("remove@")) {
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

        TableViewerColumn col = createEditableColumn("��ӡ����", VimUtils.mVeh_PrinterName, 180);
        col.setEditingSupport(new StringEditingSpport(VimUtils.mVeh_PrinterName, true));

        col = createEditableColumn("����", VimUtils.mVeh_A_PrinterFunction, 180);
        col.setEditingSupport(new ComboEditingSpport(VimUtils.mVeh_A_PrinterFunction));

        col = createEditableColumn("˵��", VimUtils.mVeh_A_PrinterDesc, 180);
        col.setEditingSupport(new StringEditingSpport(VimUtils.mVeh_A_PrinterDesc, false));

        col = createEditableColumn("�ϸ�֤\n��߾�", VimUtils.mVeh_PrintPosLeft, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_PrintPosLeft, true));

        col = createEditableColumn("�ϸ�֤\n�ϱ߾�", VimUtils.mVeh_PrintPosTop, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_PrintPosTop, true));

        col = createEditableColumn("����", VimUtils.mVeh_Connect, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Connect, true));

        col = createEditableColumn("������", VimUtils.mVeh_Baud, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Baud, true));

        col = createEditableColumn("��ż\nУ��", VimUtils.mVeh_Parity, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Parity, true));

        col = createEditableColumn("����λ", VimUtils.mVeh_Databits, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Databits, true));

        col = createEditableColumn("ֹͣλ", VimUtils.mVeh_Stopbits, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Stopbits, true));

        viewer.setContentProvider(ArrayContentProvider.getInstance());

        input = new ArrayList<PrinterItem>();
        DBCursor cur = collection.find();
        while (cur.hasNext()) {
            PrinterItem printerItem = new PrinterItem(cur.next());
            input.add(printerItem);
        }
        input.add(new AddActionPrintItem());

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
                PrinterItem printerItem = (PrinterItem) element;
                return printerItem.getText(properties);
            }
        });

        return col;
    }

    @Override
    public void setFocus() {

    }

}
