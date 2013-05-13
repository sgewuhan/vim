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

        // ��õ�ǰѡ��ļ�¼
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
                // �ӹ��泵���в�����Ӧ��COC��Ϣ
                // 2. �������ƴ�����Щ
                // 3. ����ǰ���־�ϲ�
                return src.get(key);
            }

            @Override
            public DBObject doPostTransfer(DBObject src, DBObject tgt) {
                //*********************************************************************************
                // 1. ���������ͣ����ֶ���BasicInfo��ΪC22���������¹���תΪCOCInfo�е�M1,N1��Щ����
                // GB 18352.3-2005 M�ǿͳ���M1��9�����£�M2��9�����ϣ�N1��������3500kg���µ��ػ�����
                // ��ȡC22
                Object c22 = src.get(IVIMFields.C_22);
                if ("���ó����ͳ�".equals(c22)) {
                    tgt.put(IVIMFields.F_0_4, "M1");
                } else {
                    tgt.put(IVIMFields.F_0_4, "N1");
                }
                //*********************************************************************************
                //2. ����ȼ������
                StringBuffer sb = new StringBuffer();
                Object f251 = src.get(IVIMFields.F_25_1);
                sb.append(f251);
                Object f252 = src.get(IVIMFields.F_25_2);
                if(!Utils.isNullOrEmptyString(f252)){
                    sb.append("/");//ʹ��/�ָ�
                    sb.append(f252);
                }
                Object f253 = src.get(IVIMFields.F_25_3);
                if(!Utils.isNullOrEmptyString(f253)){
                    sb.append("/");//ʹ��/�ָ�
                    sb.append(f253);
                }
                tgt.put(IVIMFields.F_25, sb.toString());
                //*********************************************************************************
                //3. ����ȼ������
                BasicDBList table = new BasicDBList();
                Object d14 = src.get(IVIMFields.D_14);//����
                table.add(new BasicDBObject().append("location", "����").append("co2", "").append("fuelqty", d14));
                Object d15 = src.get(IVIMFields.D_15);//�н�
                table.add(new BasicDBObject().append("location", "�н�").append("co2", "").append("fuelqty", d15));
                Object d16 = src.get(IVIMFields.D_16);//�ۺ�
                table.add(new BasicDBObject().append("location", "�ۺ�").append("co2", "").append("fuelqty", d16));
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

        // ׼���༭������������
        DBCollection collection = DBActivator.getCollection("appportal", "cocinfo");
        DataObject editData = new DataObject(collection, tgtData);

        // ʹ����һ���༭����
        String editorId = "com.sg.vim.editor.cocinfo";
        try {
            UIUtils.create(editorId, editData, true, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
