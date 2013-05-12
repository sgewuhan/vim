package com.sg.vim.print.control;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.mobnut.commons.util.Utils;
import com.sg.ui.UIUtils;
import com.sg.vim.datamodel.util.VimUtils;
import com.sg.vim.print.module.PrintModule;

public class CertPrintModuleSeqEditingSupport extends EditingSupport {

    private TextCellEditor editor;
    private Text text;

    public CertPrintModuleSeqEditingSupport(TreeViewer viewer) {
        super(viewer);
        editor = new TextCellEditor(viewer.getTree(), SWT.BORDER);
        text = (Text) editor.getControl();
        text.setTextLimit(7);
        text.setData(RWT.CUSTOM_VARIANT, "big");
    }

    protected void checkInput(String value) throws Exception {
        int i;
        try {
            i = Integer.parseInt(value);
        } catch (Exception e) {
            throw new Exception("����������");
        }

        int id = VimUtils.getCurrentMaxPaperOfCert();
        if (id > i) {
            throw new Exception("�����õ���ʼ˳��Ų���С�ڵ�ǰ��˳��š�");
        }

    }

    @Override
    protected void setValue(Object element, Object value) {
        if (Utils.isNullOrEmptyString(value)) {
            return;
        }
        try {
            checkInput((String) value);
        } catch (Exception e) {
            UIUtils.showMessage(getViewer().getControl().getShell(), "����ֽ�ű��", e.getMessage(),
                    SWT.ICON_ERROR | SWT.CLOSE);
            return;
        }
        int i = Integer.parseInt((String) value);
        ((PrintModule) element).setInputPaperNumber(i);
        getViewer().update(element, null);
        PrintModule[] sub = ((PrintModule) element).getSubModules();
        getViewer().update(sub, null);
    }

    @Override
    protected Object getValue(Object element) {
        return ((PrintModule) element).getInputPaperNumber();
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return editor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return ((PrintModule) element).canInputPaperNumber();
    }
}