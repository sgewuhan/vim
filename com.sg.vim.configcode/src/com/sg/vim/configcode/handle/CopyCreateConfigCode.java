package com.sg.vim.configcode.handle;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.db.DBActivator;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectCollectionService;
import com.sg.vim.datamodel.ModelTransfer;

public class CopyCreateConfigCode extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		//获得当前选择的记录
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		if(selection == null || selection.isEmpty()){
			return null;
		}
		Object selected = selection.getFirstElement();
		if(!(selected instanceof DBObject)){
			return null;
		}
		
		DBObject srcData = (DBObject)selected;
		
		DataObjectCollectionService dataObjectService = new DataObjectCollectionService();
		DBObject tgtData = ModelTransfer.transfer(srcData, dataObjectService.getReservedKeys());
		
		//准备编辑器的输入数据
		DBCollection collection = DBActivator.getCollection("appportal", "configcodeinfo");
		DataObject editData = new DataObject(collection,tgtData);
		
		
		//使用哪一个编辑器打开
		String editorId = "com.sg.vim.editor.configcode";
		try {
			UIUtils.create(editorId, editData, true, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return null;
	}

}
