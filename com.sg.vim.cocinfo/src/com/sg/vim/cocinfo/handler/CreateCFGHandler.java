package com.sg.vim.cocinfo.handler;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.ICreateHandler;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.ui.part.editor.field.actions.FilteredOptionsSelector;
import com.sg.vim.datamodel.BasicInfo;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.util.VimUtils;

public class CreateCFGHandler implements ICreateHandler {

    private BasicInfo service;

    // private DataObjectCollectionService dataObjectService;
    // private ConfigCodeInfo configService;

    public CreateCFGHandler() {
        service = new BasicInfo();
        // dataObjectService = new DataObjectCollectionService();
        // configService = new ConfigCodeInfo();
    }

    @Override
    public void create(IStructuredSelection selection, String editorId,
            IEditorSaveHandler saveHandler) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        // 显示选择公告车型的对话框
        try {
            DBCursor cur;
            cur = service.find(null, null);
            Enumerate[] selectionList = new Enumerate[cur.count()];
            int i = 0;
            while (cur.hasNext()) {
                DBObject data = cur.next();
                String desc = (String) data.get(IVIMFields.F_0_2C);
                selectionList[i] = new Enumerate(data.get("_id").toString(), desc.toString(), data,
                        null);
                i++;
            }

            Enumerate option = FilteredOptionsSelector
                    .openSelector(shell, "请选择公告车型", selectionList);

            if (option != null) {
                DBObject src = (DBObject) option.getValue();

                VimUtils.copyCreateCFG(src);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
