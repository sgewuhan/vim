package com.sg.vim.cocinfo.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.part.view.TableNavigator;
import com.sg.ui.viewer.table.CTableViewer;
import com.sg.vim.datamodel.IVIMFields;
import com.sg.vim.datamodel.ProductCodeInfo;

public class BindingCOCInfoToProductcode2 extends AbstractHandler {

    private static final String info3 = "请选择需要绑定车型一致性信息的成品码记录";
    private static final String info2 = "请选择需要绑定到成品码的底盘车型一致性信息记录";
    private static final String info1 = "已经成功完成底盘车型一致性信息绑定处理";
    private static final String title = "绑定底盘车型一致性信息到成品码";
    private static final String info4 = "选择的成品记录没有绑定整车一致性信息";
    private static final String info5 = "选择的成品记录绑定的整车一致性信息没有一致的底盘ID";

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
        Set<ObjectId> cocidset = getSelectedCOCInfo((IStructuredSelection) prodCodeSelection);
        if (cocidset.isEmpty()) {
            UIUtils.showMessage(activeShell, title, info4, SWT.ICON_INFORMATION);
            return null;
        }
        
        // 然后取出整车的底盘ID
        String dpid = getDPIdFromCOC(cocidset);
        if (dpid == null) {
            UIUtils.showMessage(activeShell, title, info5, SWT.ICON_INFORMATION);
            return null;
        }
        
        BasicDBList idList = new BasicDBList();
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
    
    private Set<ObjectId> getSelectedCOCInfo(IStructuredSelection selection) {
        Set<ObjectId> result = new HashSet<ObjectId>();
        @SuppressWarnings("rawtypes")
        Iterator iter = selection.iterator();
        while (iter.hasNext()) {
            DBObject data = (DBObject) iter.next();
            Object itm = data.get(IVIMFields.COC_ID);
            result.add((ObjectId) itm);
        }
        return result;
    }
    
    private String getDPIdFromCOC(Set<ObjectId> cocidset) {
        ArrayList<ObjectId> list = new ArrayList<ObjectId>();
        list.addAll(cocidset);
        DBCollection c = DBActivator.getCollection(IVIMFields.DB_NAME, IVIMFields.COL_COCINFO);
        DBCursor cur = c.find(new BasicDBObject().append("_id", new BasicDBObject().append("$in", list)),
                new BasicDBObject().append(IVIMFields.C_12, 1));
        String dpId = null;
        while(cur.hasNext()){
          Object d = cur.next().get(IVIMFields.C_12);
          if(dpId==null){
              dpId = (String) d;
          }else{
              if(!dpId.equals(d)){
                  return null;
              }
          }
        }
        
//        if (d != null) {
//            return (String) d.get(IVIMFields.C_12);
//        }
        return dpId;
    }

}
