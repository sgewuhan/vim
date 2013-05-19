package com.sg.vim.datamodel.option;

import java.util.ArrayList;
import java.util.List;

import com.mobnut.commons.util.Utils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.editor.field.value.IFieldOptionProvider;
import com.sg.vim.datamodel.IVIMFields;

public class F421_Option implements IFieldOptionProvider {

	public F421_Option() {
	}

	/**
	 * 读取c_02用字符串分割用于选项
	 */
	@Override
	public Enumerate getOption(Object input, Object data, String key,
			Object value) {
		Object srcValue1 = ((DataObject) data).getValue(IVIMFields.F_42_1b);
		List<Enumerate> children1 = getChildren(srcValue1);
		Object srcValue2 = ((DataObject) data).getValue(IVIMFields.C_02);
		children1.addAll(getChildren(srcValue2));
		Enumerate root = new Enumerate("root", "", "", children1.toArray(new Enumerate[]{}));
		return root;
	}

	private List<Enumerate> getChildren(Object srcValue1) {
		List<Enumerate> children = new ArrayList<Enumerate>();
		if ((srcValue1 instanceof String)
				&& (Utils.isPatternMatched(srcValue1, "(\\d+)(,\\d+)*"))) {
			String[] values = ((String) srcValue1).split(",");
			for (int i = 0; i < values.length; i++) {
				children.add(new Enumerate(values[i], values[i]));
			}
		}
		return children;
	}

}
