package com.sg.vim.cocinfo.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.commons.util.ITransferRule;
import com.mobnut.commons.util.ModelTransfer;
import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectCollectionService;
import com.sg.vim.datamodel.IVIMFields;

public class CopyCreateCOCinfo extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // 获得当前选择的记录
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil
                .getCurrentSelection(event);
        if (selection == null || selection.isEmpty()) {
            return null;
        }
        Object selected = selection.getFirstElement();
        if (!(selected instanceof DBObject)) {
            return null;
        }

        DBObject srcData = (DBObject) selected;

        DataObjectCollectionService dataObjectService = new DataObjectCollectionService();
        ITransferRule rule = new ITransferRule() {

            @Override
            public Object getValue(DBObject src, String key) {
                // 从公告车型中产生对应的COC信息
                // 2. 处理名称代号这些
                // 3. 处理前后轮距合并
                return src.get(key);
            }

            @Override
            public DBObject doPostTransfer(DBObject src, DBObject tgt) {
                //*********************************************************************************
                // 1. 处理车辆类型，该字段在BasicInfo中为C22，根据以下规则转为COCInfo中的M1,N1这些东西
                // GB 18352.3-2005 M是客车，M1是9座以下；M2是9座以上；N1是总质量3500kg以下的载货汽车
                // 读取C22
                Object c22 = src.get(IVIMFields.C_22);
                if ("乘用车及客车".equals(c22)) {
                    tgt.put(IVIMFields.F_0_4, "M1");
                } else {
                    tgt.put(IVIMFields.F_0_4, "N1");
                }
                //*********************************************************************************
                //2. 处理燃料种类
                StringBuffer sb = new StringBuffer();
                Object f251 = src.get(IVIMFields.F_25_1);
                sb.append(f251);
                Object f252 = src.get(IVIMFields.F_25_2);
                if(!Utils.isNullOrEmptyString(f252)){
                    sb.append("/");//使用/分割
                    sb.append(f252);
                }
                Object f253 = src.get(IVIMFields.F_25_3);
                if(!Utils.isNullOrEmptyString(f253)){
                    sb.append("/");//使用/分割
                    sb.append(f253);
                }
                tgt.put(IVIMFields.F_25, sb.toString());
                //*********************************************************************************
                //3. 处理燃油消耗
                BasicDBList table = new BasicDBList();
                Object d14 = src.get(IVIMFields.D_14);//市区
                table.add(new BasicDBObject().append("location", "市区").append("co2", "").append("fuelqty", d14));
                Object d15 = src.get(IVIMFields.D_15);//市郊
                table.add(new BasicDBObject().append("location", "市郊").append("co2", "").append("fuelqty", d15));
                Object d16 = src.get(IVIMFields.D_16);//综合
                table.add(new BasicDBObject().append("location", "综合").append("co2", "").append("fuelqty", d16));
                tgt.put(IVIMFields.F_46_3, table);
                return tgt;
            }

            @Override
            public DBObject doPrepareTransfer(DBObject src) {
                return null;
            }
        };
        DBObject tgtData = ModelTransfer
                .transfer(srcData, dataObjectService
                        .getReservedKeys(DataObjectCollectionService.CLONE_RESERVED), rule);

        tgtData.put("basicinfo_id", srcData.get("_id"));

        // 准备编辑器的输入数据
        DBCollection collection = DBActivator.getCollection("appportal", "cocinfo");
        DataObject editData = new DataObject(collection, tgtData);

        // 使用哪一个编辑器打开
        String editorId = "com.sg.vim.editor.cocinfo";
        try {
            UIUtils.create(editorId, editData, true, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
