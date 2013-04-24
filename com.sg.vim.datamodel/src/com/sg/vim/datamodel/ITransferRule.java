package com.sg.vim.datamodel;

import com.mongodb.DBObject;

public interface ITransferRule {

  Object getValue(DBObject src, String key);

}
