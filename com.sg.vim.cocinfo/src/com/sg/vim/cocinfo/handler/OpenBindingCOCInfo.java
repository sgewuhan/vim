package com.sg.vim.cocinfo.handler;

import org.bson.types.ObjectId;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mongodb.DBObject;
import com.sg.ui.UIUtils;

public class OpenBindingCOCInfo extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		if(selection==null||selection.isEmpty()){
			return null;
		}
		
		DBObject productCode = (DBObject) selection.getFirstElement();
		
		ObjectId cocinfoId = (ObjectId)productCode.get("cocinfo");
		if(cocinfoId==null){
			MessageBox mb = new MessageBox(HandlerUtil.getActiveShell(event),SWT.ICON_WARNING);
			mb.setText("查看车型一致性信息");
			mb.setMessage("所选择的成品码没有绑定车型一致性信息");
			mb.open();
			return null;
		}
		try {
			UIUtils.open(cocinfoId, "com.sg.vim.editor.cocinfo", false, false, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
