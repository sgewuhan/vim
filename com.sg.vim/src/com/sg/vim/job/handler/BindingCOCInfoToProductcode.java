package com.sg.vim.job.handler;

import java.util.Iterator;

import org.bson.types.ObjectId;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.part.view.TableNavigator;
import com.sg.ui.viewer.table.CTableViewer;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.ProductCodeInfo;
import com.sg.vim.datamodel.util.VimUtils;

public class BindingCOCInfoToProductcode extends AbstractHandler {

    private static final String info4 = "选定的公告车型和成品码公告车型不一致，不能执行绑定";
    private static final String info3 = "请选择需要绑定车型一致性信息的成品码记录";
    private static final String info2 = "请选择需要绑定到成品码的整车车型一致性信息记录";
    private static final String info1 = "已经成功完成车型一致性信息绑定处理";
    private static final String title = "绑定整车车型一致性信息到成品码";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil
                .getCurrentSelection(event);
        Shell activeShell = HandlerUtil.getActiveShell(event);
        if (selection.isEmpty()) {
            UIUtils.showMessage(activeShell, title, info2, SWT.ICON_INFORMATION);
            return null;
        }

        DBObject cocinfo = (DBObject) selection.getFirstElement();
        Object f_0_2C1_coc = cocinfo.get(IVIMFields.F_0_2C1);

        ObjectId cocinfoId = (ObjectId) cocinfo.get(ProductCodeInfo.FIELD_SYSID);
        String dpId = (String) cocinfo.get(IVIMFields.C_12);

        String cocinfoName = (String) cocinfo.get(IVIMFields.F_0_2_1) + " "
                + cocinfo.get(IVIMFields.F_0_2C1) + " " + cocinfo.get(IVIMFields.F_0_2a);

        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        TableNavigator part = (TableNavigator) window.getActivePage().findView(
                "com.sg.vim.productcode");
        if (part == null) {
            return null;
        }
        CTableViewer viewer = part.getNavigator().getViewer();
        IStructuredSelection prodCodeSelection = viewer.getSelection();
        if (prodCodeSelection == null || prodCodeSelection.isEmpty()) {
            UIUtils.showMessage(activeShell, title, info3, SWT.ICON_INFORMATION);
        }
        BasicDBList idList = new BasicDBList();
        @SuppressWarnings("rawtypes")
        Iterator iter = prodCodeSelection.iterator();
        while (iter.hasNext()) {
            DBObject productCodeData = (DBObject) iter.next();
            Object f_0_2C1_pc = productCodeData.get(IVIMFields.F_0_2C1);
            if (!Util.equals(f_0_2C1_coc, f_0_2C1_pc)) {
                UIUtils.showMessage(activeShell, title, info4, SWT.ICON_ERROR);
                return null;
            }
            //同步MES
            try {
                VimUtils.mntMesProductInfo(productCodeData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            idList.add(productCodeData.get(ProductCodeInfo.FIELD_SYSID));
        }

        DBCollection c = DBActivator.getCollection("appportal", "productcodeinfo");
        DBObject query = new BasicDBObject().append(ProductCodeInfo.FIELD_SYSID,
                new BasicDBObject().append("$in", idList));
        DBObject data = new BasicDBObject().append(
                "$set",
                new BasicDBObject().append(IVIMFields.COC_ID, cocinfoId).append(
                        IVIMFields.COC_NAME, cocinfoName).append(
                                IVIMFields.C_12, dpId));
        c.update(query, data, false, true);

        iter = prodCodeSelection.iterator();
        while (iter.hasNext()) {
            DBObject productCodeData = (DBObject) iter.next();
            productCodeData.put(IVIMFields.COC_ID, cocinfoId);
            productCodeData.put(IVIMFields.COC_NAME, cocinfoName);
            productCodeData.put(IVIMFields.C_12, dpId);
            viewer.update(productCodeData, null);
        }
        UIUtils.showMessage(activeShell, title, info1, SWT.ICON_INFORMATION);
        return null;
    }

}
