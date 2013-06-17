package com.sg.vim.job.savehandler;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.vim.datamodel.util.VimUtils;

public class SaveCOCInfo implements IEditorSaveHandler {

    public SaveCOCInfo() {
    }

    @Override
    public boolean doSaveBefore(DataObjectEditorInput input, IProgressMonitor monitor,
            String operation) throws Exception {
        return true;
    }

    @Override
    public boolean doSaveAfter(DataObjectEditorInput input, IProgressMonitor monitor,
            String operation) throws Exception {
        if (operation.equals(DataObject.UPDATED)) {
            // ����COC�Ƿ�����˺ϸ�֤��ȼ�͡�COC֤�飬����Ǳ����ЩΪ��Ҫ����

            ObjectId id = input.getData().getSystemId();

            boolean isdirty = VimUtils.markCOCDirty(id);

            if (isdirty) {
                UIUtils.showMessage(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "����",
                        "����һ�������ϸ�֤��COC֤���ȼ�ͱ�ʶ���ɱ�������һ������Ϣ������\n���������ڶ�Ӧ�Ĵ�Ӣ�����в鿴����Щ��һ�µ���Ϣ������һ�����д���",
                        SWT.ICON_WARNING | SWT.OK);
            }

        }

        return true;

    }

}
