package com.sg.vim.print.handler;

import org.eclipse.core.expressions.PropertyTester;

import com.mobnut.commons.util.Utils;
import com.mongodb.DBObject;
import com.sg.vim.datamodel.IVIMFields;

public class LifecycleTester2 extends PropertyTester {

    public static final String ACT_UPLOAD = "upload";
    
    public static final String ACT_CANCEL = "cancel";

    public LifecycleTester2() {
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof DBObject) {
            if (ACT_UPLOAD.equals(args[0])) {
                return canUpload((DBObject) receiver);

            } else if (ACT_CANCEL.equals(args[0])) {
                return canCancel((DBObject) receiver);
            }
        }
        return false;
    }

    private boolean canCancel(DBObject receiver) {
        String lc = getLifecycle(receiver);
        return IVIMFields.LC_UPLOADED.equals(lc);
    }

    private boolean canUpload(DBObject receiver) {
        String lc = getLifecycle(receiver);
        return Utils.isNullOrEmpty(lc)||IVIMFields.LC_CANCELED.equals(lc);
    }

    
    private String getLifecycle(DBObject data) {
        return (String) data.get(IVIMFields.LIFECYCLE);
    }
}
