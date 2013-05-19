package com.sg.vim.datamodel.option;

import com.mobnut.commons.util.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.editor.field.value.IFieldOptionProvider;
import com.sg.vim.datamodel.BasicInfo;
import com.sg.vim.datamodel.IVIMFields;

public class FC6_Option implements IFieldOptionProvider {

	  private BasicInfo service;

	public FC6_Option() {
		  service = new BasicInfo();
		  
	  }

	  /**
	   * 读取c_02用字符串分割用于选项
	   */
	  @Override
	  public Enumerate getOption(Object input, Object data, String key, Object value) {
	    Object srcValue = ((DataObject)data).getValue(IVIMFields.F_0_2C1);
	    //
	    try {
			DBCursor fc6 = service.find(new BasicDBObject().append(IVIMFields.F_0_2C1, srcValue), new BasicDBObject().append(IVIMFields.F_C6, 1));
			if(fc6.hasNext()){
				DBObject bdata = fc6.next();
				String fc6Value = (String) bdata.get(IVIMFields.F_C6);
				if(!Utils.isNullOrEmpty(fc6Value)){
				      String[] values = ((String)fc6Value).split(",");
				      Enumerate[] children = new Enumerate[values.length];
				      Enumerate root = new Enumerate("root","", "", children);
				      for (int i = 0; i < children.length; i++) {
				        children[i] = new Enumerate(values[i], values[i]);
				      }
				      return root;
					
				}
			}
	    } catch (Exception e) {
		}
	    return new Enumerate("root");
	  }

	}
