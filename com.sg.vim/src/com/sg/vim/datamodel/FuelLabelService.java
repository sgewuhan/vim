package com.sg.vim.datamodel;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class FuelLabelService extends AuthCollectionService {

    @Override
    public DB getDB() {
      return Portal.getBasicDB();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mobnut.db.collection.CollectionService#getCollectionName()
     */
    @Override
    protected String getCollectionName() {
      return "fuellabel";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mobnut.db.collection.AuthCollectionService#getDefaultSearchColumns()
     */
    @Override
    public DBObject getDefaultSearchColumns() {
      return null;
    }


}
