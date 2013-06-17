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
            // 检查该COC是否输出了合格证、燃油、COC证书，如果是标记这些为需要更新

            ObjectId id = input.getData().getSystemId();

            boolean isdirty = VimUtils.markCOCDirty(id);

            if (isdirty) {
                UIUtils.showMessage(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "保存",
                        "您有一个或多个合格证、COC证书或燃油标识是由本条车型一致性信息所创建\n。您可以在对应的大英数据中查看到这些不一致的信息，并进一步进行处理？",
                        SWT.ICON_WARNING | SWT.OK);
            }

        }

        return true;

    }

}
