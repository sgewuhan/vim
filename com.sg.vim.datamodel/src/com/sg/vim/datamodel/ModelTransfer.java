package com.sg.vim.datamodel;

import java.util.Iterator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sg.util.Utils;

public class ModelTransfer {
	
	

	public static DBObject transfer(DBObject src,String[] reserved){
		BasicDBObject tgt = new BasicDBObject();

		Iterator<String> iter = src.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			if(Utils.inArray(key, reserved)){
				continue;
			}
			tgt.put(key, src.get(key));
		}
		return tgt;
	}
}
