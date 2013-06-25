package com.sg.vim.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.DBObject;
import com.sg.vim.model.IVIMFields;

public class LifecycleTesterFuelLabel extends LifecycleTester {

	public LifecycleTesterFuelLabel() {
	}

	@Override
	protected boolean canReUpload(DBObject data) {
		return !canUpload(data, null);
	}

	@Override
	protected boolean canUpload(DBObject data, Object expectedValue) {
		if (IVIMFields.LC_PRINTED.equals(getLifecycle(data))) {

			Object pdate = data.get(IVIMFields.G_34);
			try {
				Date dValue = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’")
						.parse((String) pdate);
				long i = new Date().getTime() - dValue.getTime();
				return i <= 2 * 24 * 60 * 60 * 1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
