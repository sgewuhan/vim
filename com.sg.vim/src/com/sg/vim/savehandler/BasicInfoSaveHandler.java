package com.sg.vim.savehandler;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.vim.VimUtils;
import com.sg.vim.model.COCInfo;
import com.sg.vim.model.IVIMFields;

public class BasicInfoSaveHandler implements IEditorSaveHandler {

    private COCInfo cocService;

    public BasicInfoSaveHandler() {
        cocService = new COCInfo();

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
            // 检查该公告车型是否绑定了COC信息

            ObjectId id = input.getData().getSystemId();
            DBCursor cur = cocService.find(new BasicDBObject().append(IVIMFields.BASICINFO_ID, id),
                    null);
            if (!cur.hasNext()) {
                return true;
            }

            int ok = UIUtils.showMessage(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                    "保存", "您有一个或多个车型一致性信息是由本条公告车型信息所创建\n。您需要同步更改关联的车型一致性信息码？", SWT.ICON_QUESTION
                            | SWT.YES | SWT.NO);
            if(SWT.YES!=ok){
                return true;
            }
            while(cur.hasNext()){
                DBObject cocInfo = cur.next();
                VimUtils.syncPublicDataToCOC(cocInfo);
            }
        }

        return true;
    }
}
