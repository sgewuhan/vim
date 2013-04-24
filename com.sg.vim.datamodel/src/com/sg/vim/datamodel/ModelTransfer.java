package com.sg.vim.datamodel;

import java.util.Iterator;

import com.mobnut.commons.util.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ModelTransfer {

  public static DBObject transfer(DBObject src, String[] reserved) {
    return transfer(src, reserved, null);
  }

  public static DBObject transfer(DBObject src, String[] reserved, ITransferRule rule) {
    BasicDBObject tgt = new BasicDBObject();

    Iterator<String> iter = src.keySet().iterator();
    while (iter.hasNext()) {
      String key = iter.next();
      if (Utils.inArray(key, reserved)) {
        continue;
      }
      Object value = null;
      if (rule != null) {
        value = rule.getValue(src, key);
      } else {
        value = src.get(key);
      }
      tgt.put(key, value);
    }
    return tgt;
  }
}
