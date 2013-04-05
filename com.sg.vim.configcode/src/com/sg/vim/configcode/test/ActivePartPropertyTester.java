package com.sg.vim.configcode.test;

import org.eclipse.core.expressions.PropertyTester;

import com.sg.util.Utils;
import com.sg.vim.configcode.view.ConfigCodeView;

public class ActivePartPropertyTester extends PropertyTester {
	private static final String ACTIVE_PART= "activePart";

	
	public ActivePartPropertyTester() {
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		if (ACTIVE_PART.equals(property) && !Utils.isNullOrEmpty(args)) {
			if ("canBind".equals(args[0])) {
				if (receiver instanceof ConfigCodeView) {
					boolean canBind = ((ConfigCodeView) receiver).canBind();
					return expectedValue.equals(new Boolean(canBind));
				}
			}
		}
		return false;
	}

}
