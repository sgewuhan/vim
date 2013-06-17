package com.sg.vim.datamodel;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class BasicInfo extends AuthCollectionService {

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
        return "basicinfo";
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

    public String[] getFullFields() {
        return new String[] { IVIMFields.C_23, IVIMFields.D_23, IVIMFields.D_18, IVIMFields.D_02,
                IVIMFields.D_01, IVIMFields.C_22, IVIMFields.F_C0_2, IVIMFields.F_0_2C1,
                IVIMFields.F_0_2_1, IVIMFields.D_19, IVIMFields.D_20, IVIMFields.C_21,
                IVIMFields.C_14, IVIMFields.F_0_1, IVIMFields.D_03, IVIMFields.D_04,
                IVIMFields.F_0_5b, IVIMFields.D_06, IVIMFields.D_05, IVIMFields.C_17,
                IVIMFields.C_18, IVIMFields.F_6_1, IVIMFields.F_8, IVIMFields.F_7_1,
                IVIMFields.F_C7_1, IVIMFields.F_C7_2, IVIMFields.F_C7_3, IVIMFields.F_42_1,
                IVIMFields.C_02, IVIMFields.D_17, IVIMFields.F_14_1, IVIMFields.C_09,
                IVIMFields.C_08, IVIMFields.D_07, IVIMFields.C_10, IVIMFields.C_04,
                IVIMFields.F_C5, IVIMFields.F_C34, IVIMFields.F_3, IVIMFields.C_13, IVIMFields.F_1,
                IVIMFields.F_1_1, IVIMFields.F_C1, IVIMFields.F_11, IVIMFields.F_C2,
                IVIMFields.F_C3, IVIMFields.F_5A, IVIMFields.F_5B, IVIMFields.F_C6,
                IVIMFields.D_21, IVIMFields.D_22, IVIMFields.C_06, IVIMFields.F_32S,
                IVIMFields.F_44, IVIMFields.C_19, IVIMFields.C_20, IVIMFields.C_15,
                IVIMFields.C_16, IVIMFields.D_08, IVIMFields.C_12, IVIMFields.CCC_04,
                IVIMFields.CCC_05, IVIMFields.CCC_06, IVIMFields.F_C4, IVIMFields.F_20,
                IVIMFields.C_01, IVIMFields.F_24, IVIMFields.D_14, IVIMFields.D_15,
                IVIMFields.D_16, IVIMFields.D_11, IVIMFields.D_12, IVIMFields.D_13,
                IVIMFields.C_05, IVIMFields.C_11 };
    }

}
