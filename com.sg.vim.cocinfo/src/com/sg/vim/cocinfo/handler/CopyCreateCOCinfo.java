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

    // 获得当前选择的记录
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
        // 从公告车型中产生对应的COC信息
        // 2. 处理名称代号这些
        // 3. 处理前后轮距合并
        return src.get(key);
      }

      @Override
      public DBObject doPostTransfer(DBObject src, DBObject tgt) {
        // 1. 处理车辆类型，该字段在BasicInfo中为C22，根据以下规则转为COCInfo中的M1,N1这些东西
        // GB 18352.3-2005 M是客车，M1是9座以下；M2是9座以上；N1是总质量3500kg以下的载货汽车
        // 读取C22
        Object c22 = src.get(IVIMFields.C_22);
        if ("乘用车及客车".equals(c22)) {
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
