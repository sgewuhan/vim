package com.sg.vim.cocinfo.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.commons.util.ITransferRule;
import com.mobnut.commons.util.ModelTransfer;
import com.mobnut.db.DBActivator;
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
    IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
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
        // 1. ���������ͣ����ֶ���BasicInfo��ΪC22���������¹���תΪCOCInfo�е�M1,N1��Щ����
        // GB 18352.3-2005 M�ǿͳ���M1��9�����£�M2��9�����ϣ�N1��������3500kg���µ��ػ�����
        // ��ȡC22
        Object c22 = src.get(IVIMFields.C_22);
        if ("���ó����ͳ�".equals(c22)) {
          tgt.put(IVIMFields.F_0_4, "M1");
        } else {
          tgt.put(IVIMFields.F_0_4, "N1");
        }
        
        return tgt;
      }

      @Override
      public DBObject doPrepareTransfer(DBObject src) {
        return null;
      }
    };
    DBObject tgtData = ModelTransfer.transfer(srcData, dataObjectService.getReservedKeys(DataObjectCollectionService.CLONE_RESERVED), rule);
    
    tgtData.put("basicinfo_id",srcData.get("_id"));
    

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
