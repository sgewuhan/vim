package com.sg.vim.configcode.handle;

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

public class OpenBindingConfigCodeInfo extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		if(selection==null||selection.isEmpty()){
			return null;
		}
		
		DBObject productCode = (DBObject) selection.getFirstElement();
		
		ObjectId cocinfoId = (ObjectId)productCode.get("configcodeinfo_id");
		if(cocinfoId==null){
			MessageBox mb = new MessageBox(HandlerUtil.getActiveShell(event),SWT.ICON_WARNING);
			mb.setText("查看配置序列号信息");
			mb.setMessage("所选择的成品码没有绑定配置序列号");
			mb.open();
			return null;
		}
		try {
			UIUtils.open(cocinfoId, "com.sg.vim.editor.configcode", false, false, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
