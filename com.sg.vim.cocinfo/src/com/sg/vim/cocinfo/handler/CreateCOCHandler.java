package com.sg.vim.cocinfo.handler;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.sg.ui.UIUtils;
import com.sg.ui.part.ICreateHandler;
import com.sg.ui.part.editor.IEditorSaveHandler;

public class CreateCOCHandler implements ICreateHandler {

//	private BasicInfo service;
//	private DataObjectCollectionService dataObjectService;
//	private COCInfo cocService;

	public CreateCOCHandler() {
//		service = new BasicInfo();
//		dataObjectService = new DataObjectCollectionService();
//		cocService = new COCInfo();
	}

	@Override
	public void create(IStructuredSelection selection, String editorId,
			IEditorSaveHandler saveHandler) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        UIUtils.showMessage(shell, "创建COC", "功能暂不可用,请选择公告车型创建", SWT.ICON_INFORMATION);
        return;
//		// 显示选择公告车型的对话框
//		try {
//			DBCursor cur;
//			cur = service.find(null, null);
//			Enumerate[] selectionList = new Enumerate[cur.count()];
//			int i = 0;
//			while (cur.hasNext()) {
//				DBObject data = cur.next();
//				String desc = (String) data.get(IVIMFields.F_0_2C1);
//				selectionList[i] = new Enumerate(data.get("_id").toString(),
//						desc.toString(), data, null);
//				i++;
//			}
//
//			Enumerate option = FilteredOptionsSelector.openSelector(Display
//					.getCurrent().getActiveShell(), "请选择公告车型",
//					selectionList);
//
//			if (option != null) {
//				DBObject src = (DBObject) option.getValue();
//
//				DBObject tgt = ModelTransfer.transfer(src,
//				        dataObjectService.getReservedKeys(DataObjectCollectionService.CLONE_RESERVED));
//				tgt.put("basicinfo_id",src.get("_id"));
//
//				DataObject data = new DataObject(cocService.getC(), tgt);
//				UIUtils.create(editorId, data, false, saveHandler);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
