package com.sg.vim.cocinfo.handler;

import java.util.Iterator;

import org.bson.types.ObjectId;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sg.ui.part.view.TableNavigator;
import com.sg.ui.viewer.table.CTableViewer;

public class BindingCOCInfoToProductcode extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getCurrentSelection(event);
		if (selection.isEmpty()) {
			MessageBox mb = new MessageBox(HandlerUtil.getActiveShell(event),
					SWT.ICON_INFORMATION);
			mb.setText("绑定车型一致性信息到成品码");
			mb.setMessage("请选择需要绑定到成品码的车型一致性信息记录");
			mb.open();
			return null;
		}

		DBObject cocinfo = (DBObject) selection.getFirstElement();
		ObjectId cocinfoId = (ObjectId) cocinfo.get("_id");

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		TableNavigator part = (TableNavigator) window.getActivePage().findView(
				"com.sg.vim.productcode");
		if (part == null) {
			return null;
		}
		CTableViewer viewer = part.getNavigator().getViewer();
		IStructuredSelection prodCodeSelection = viewer.getSelection();
		if (prodCodeSelection == null || prodCodeSelection.isEmpty()) {
			MessageBox mb = new MessageBox(HandlerUtil.getActiveShell(event),
					SWT.ICON_INFORMATION);
			mb.setText("绑定车型一致性信息到成品码");
			mb.setMessage("请选择需要绑定车型一致性信息的成品码记录");
			mb.open();
			return null;
		}
		BasicDBList idList = new BasicDBList();
		@SuppressWarnings("rawtypes")
		Iterator iter = prodCodeSelection.iterator();
		while (iter.hasNext()) {
			DBObject productCodeData = (DBObject) iter.next();
			idList.add(productCodeData.get("_id"));
		}

		DBCollection c = DBActivator.getCollection("appportal",
				"productcodeinfo");
		DBObject query = new BasicDBObject().append("_id",
				new BasicDBObject().append("$in", idList));
		DBObject data = new BasicDBObject().append("$set",
				new BasicDBObject().append("cocinfo", cocinfoId));
		c.update(query, data, false, true);

		iter = prodCodeSelection.iterator();
		while (iter.hasNext()) {
			DBObject productCodeData = (DBObject) iter.next();
			productCodeData.put("cocinfo", cocinfoId);
			viewer.update(productCodeData, null);
		}

		MessageBox mb = new MessageBox(HandlerUtil.getActiveShell(event),
				SWT.ICON_INFORMATION);
		mb.setText("绑定车型一致性信息到成品码");
		mb.setMessage("已经成功完成车型一致性信息绑定处理");
		mb.open();
		return null;
	}

}
