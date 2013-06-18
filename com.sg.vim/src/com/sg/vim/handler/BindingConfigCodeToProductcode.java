package com.sg.vim.handler;

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
import com.sg.vim.model.ConfigCodeInfo;
import com.sg.vim.model.IVIMFields;
import com.sg.vim.model.ProductCodeInfo;

public class BindingConfigCodeToProductcode extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil
                .getCurrentSelection(event);
        Shell activeShell = HandlerUtil.getActiveShell(event);
        if (selection.isEmpty()) {

            UIUtils.showMessage(activeShell, "绑定配置序列号到成品码", "请选择需要绑定到成品码的配置序列号",
                    SWT.ICON_INFORMATION);

            return null;
        }

        DBObject configcode = (DBObject) selection.getFirstElement();
        Object f_0_2C1_cc = configcode.get(IVIMFields.F_0_2C1);

        ObjectId configcodeId = (ObjectId) configcode.get(ConfigCodeInfo.FIELD_SYSID);
        String configcodeName = (String) configcode.get(IVIMFields.H_03);

        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        TableNavigator part = (TableNavigator) window.getActivePage().findView(
                "com.sg.vim.productcode");
        if (part == null) {
            return null;
        }
        CTableViewer viewer = part.getNavigator().getViewer();
        IStructuredSelection prodCodeSelection = viewer.getSelection();
        if (prodCodeSelection == null || prodCodeSelection.isEmpty()) {
            UIUtils.showMessage(activeShell, "绑定配置序列号到成品码", "请选择需要绑定配置序列号的成品码记录",
                    SWT.ICON_INFORMATION);

            return null;
        }
        BasicDBList idList = new BasicDBList();
        @SuppressWarnings("rawtypes")
        Iterator iter = prodCodeSelection.iterator();
        while (iter.hasNext()) {
            DBObject productCodeData = (DBObject) iter.next();
            Object f_0_2C1_pc = productCodeData.get(IVIMFields.F_0_2C1);
            if(!Util.equals(f_0_2C1_cc, f_0_2C1_pc)){
                UIUtils.showMessage(activeShell, "绑定车型一致性信息到成品码", "选定的公告车型和成品码公告车型不一致，不能执行绑定",
                        SWT.ICON_ERROR);
                return null;
            }
            
            idList.add(productCodeData.get(ProductCodeInfo.FIELD_SYSID));
        }

        DBCollection c = DBActivator.getCollection("appportal", "productcodeinfo");
        DBObject query = new BasicDBObject().append(ProductCodeInfo.FIELD_SYSID,
                new BasicDBObject().append("$in", idList));
        DBObject data = new BasicDBObject().append(
                "$set",
                new BasicDBObject().append(IVIMFields.CFG_ID, configcodeId).append(
                        IVIMFields.CFG_NAME, configcodeName));
        c.update(query, data, false, true);

        iter = prodCodeSelection.iterator();
        while (iter.hasNext()) {
            DBObject productCodeData = (DBObject) iter.next();
            productCodeData.put(IVIMFields.CFG_ID, configcodeId);
            productCodeData.put(IVIMFields.CFG_NAME, configcodeName);
            viewer.update(productCodeData, null);
        }

        UIUtils.showMessage(activeShell, "绑定配置序列号到成品码", "已经成功将配置序列号绑定处理", SWT.ICON_INFORMATION);
        return null;
    }
}
