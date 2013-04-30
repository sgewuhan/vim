package com.sg.vim.cocinfo.handler;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;

import com.mobnut.commons.util.ModelTransfer;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectCollectionService;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.ICreateHandler;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.ui.part.editor.field.actions.FilteredOptionsSelector;
import com.sg.vim.datamodel.BasicInfo;
import com.sg.vim.datamodel.ConfigCodeInfo;
import com.sg.vim.datamodel.IVIMFields;

public class CreateCFGHandler implements ICreateHandler {

	private BasicInfo service;
	private DataObjectCollectionService dataObjectService;
	private ConfigCodeInfo configService;

	public CreateCFGHandler() {
		service = new BasicInfo();
		dataObjectService = new DataObjectCollectionService();
		configService = new ConfigCodeInfo();
	}

	@Override
	public void create(IStructuredSelection selection, String editorId,
			IEditorSaveHandler saveHandler) {

		// 显示选择公告车型的对话框
		try {
			DBCursor cur;
			cur = service.find(null, null);
			Enumerate[] selectionList = new Enumerate[cur.count()];
			int i = 0;
			while (cur.hasNext()) {
				DBObject data = cur.next();
				String desc = (String) data.get(IVIMFields.F_0_2C);
				selectionList[i] = new Enumerate(data.get("_id").toString(),
						desc.toString(), data, null);
				i++;
			}

			Enumerate option = FilteredOptionsSelector.openSelector(Display
					.getCurrent().getActiveShell(), "请选择公告车型",
					selectionList);

			if (option != null) {
				DBObject src = (DBObject) option.getValue();

				DBObject tgt = ModelTransfer.transfer(src,
				        dataObjectService.getReservedKeys(DataObjectCollectionService.CLONE_RESERVED));
                tgt.put("basicinfo_id",src.get("_id"));

				DataObject data = new DataObject(configService.getC(), tgt);
				UIUtils.create(editorId, data, false, saveHandler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
