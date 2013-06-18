package com.sg.vim.handler;

import java.util.Iterator;

import org.bson.types.ObjectId;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
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
import com.sg.vim.model.IVIMFields;
import com.sg.vim.model.ProductCodeInfo;

public class BindingCOCInfoToProductcode2 extends AbstractHandler {

    private static final String info3 = "��ѡ����Ҫ�󶨳���һ������Ϣ�ĳ�Ʒ���¼";
    private static final String info2 = "��ѡ����Ҫ�󶨵���Ʒ��ĵ��̳���һ������Ϣ��¼";
    private static final String info1 = "�Ѿ��ɹ���ɵ��̳���һ������Ϣ�󶨴���";
    private static final String title = "�󶨵��̳���һ������Ϣ����Ʒ��";
    private static final String info5 = "ѡ��ĳ�Ʒ��¼�󶨵�����һ������Ϣû��һ�µĵ���ID";

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
        ObjectId cocinfoId = (ObjectId) cocinfo.get(ProductCodeInfo.FIELD_SYSID);
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
        
        // Ȼ��ȡ�������ĵ���ID
        String dpid = getDPIdFromSelection((IStructuredSelection) prodCodeSelection);
        if (dpid == null) {
            UIUtils.showMessage(activeShell, title, info5, SWT.ICON_INFORMATION);
            return null;
        }
        
        BasicDBList idList = new BasicDBList();
        @SuppressWarnings("rawtypes")
        Iterator iter = prodCodeSelection.iterator();
        while(iter.hasNext()){
            DBObject d = (DBObject) iter.next();
            idList.add(d.get("_id"));
        }
        DBCollection c = DBActivator.getCollection("appportal", "productcodeinfo");
        DBObject query = new BasicDBObject().append(ProductCodeInfo.FIELD_SYSID,
                new BasicDBObject().append("$in", idList));
        DBObject data = new BasicDBObject().append(
                "$set",
                new BasicDBObject().append(IVIMFields.COC_ID2, cocinfoId).append(
                        IVIMFields.COC_NAME2, cocinfoName));
        c.update(query, data, false, true);

        iter = prodCodeSelection.iterator();
        while (iter.hasNext()) {
            DBObject productCodeData = (DBObject) iter.next();
            productCodeData.put(IVIMFields.COC_ID2, cocinfoId);
            productCodeData.put(IVIMFields.COC_NAME2, cocinfoName);
            viewer.update(productCodeData, null);
        }
        UIUtils.showMessage(activeShell, title, info1, SWT.ICON_INFORMATION);
        return null;
    }
    
    private String getDPIdFromSelection(IStructuredSelection selection) {
        String dpid = null;
        @SuppressWarnings("rawtypes")
        Iterator iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject data = (DBObject) iter.next();
            Object itm = data.get(IVIMFields.C_12);
            if(dpid==null){
                dpid = (String) itm;
            }else{
                if(!dpid.equals(itm)){
                    return null;
                }
            }
        }
        return dpid;
    }

}
