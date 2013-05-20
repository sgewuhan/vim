package com.sg.vim.cocinfo.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bson.types.ObjectId;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.part.FilterPanel;
import com.sg.ui.viewer.filter.ConditionDefinition;
import com.sg.vim.datamodel.IVIMFields;

public class ProductBindTargetViewDP extends ProductBindTargetView {

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (selection.isEmpty()) {
            return;
        }

        // 首先取出绑定的整车
        Set<ObjectId> cocidset = getSelectedCOCInfo((IStructuredSelection) selection);
        if (cocidset.isEmpty()) {
            setCanTransfer(null);
            return;
        }
        // 然后取出整车的底盘ID
        String dpid = getDPIdFromCOC(cocidset);
        if (dpid == null) {
            setCanTransfer(null);
            return;
        }
        // 然后通过底盘ID查询对应产品Id的底盘COC
        navi.createFilterPanel();

        String field = IVIMFields.D_02;
        String type = Utils.TYPE_STRING;
        String name = "产品ID";
        ConditionDefinition conditionDefinition = new ConditionDefinition(field, name, type,
                Utils.OPERATOR_EQUAL, dpid, null);
        setCanTransfer(conditionDefinition);
    }

 

    private void setCanTransfer(ConditionDefinition condition) {
        navi.createFilterPanel();
        FilterPanel dash = (FilterPanel) navi.getCurrentDashPanel();
        if (condition == null) {
            dash.doRemoveAllConditions();
            canTransfer = false;
        } else {
            dash.doSetCondition(new ConditionDefinition[] { condition });
            canTransfer = true;
        }
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
