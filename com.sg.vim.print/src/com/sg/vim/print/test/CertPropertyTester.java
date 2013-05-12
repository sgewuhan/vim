package com.sg.vim.print.test;

import org.eclipse.core.expressions.PropertyTester;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.IVIMFields;

public class CertPropertyTester extends PropertyTester {

    public static final String ACT_REPRINT = "reprint";

    public static final String ACT_UPLOAD = "upload";

    public static final String ACT_REUPLOAD = "reupload";

    public static final String ACT_CANCEL = "cancel";

    public static final String ACT_REMOVE = "remove";

    public CertPropertyTester() {
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof DBObject) {
            if (ACT_REPRINT.equals(args[0])) {
                return canReprint((DBObject) receiver);

            } else if (ACT_UPLOAD.equals(args[0])) {
                return canUpload((DBObject) receiver);

            } else if (ACT_REUPLOAD.equals(args[0])) {
                return canReUpload((DBObject) receiver);

            } else if (ACT_CANCEL.equals(args[0])) {
                return canCancel((DBObject) receiver);

            } else if (ACT_REMOVE.equals(args[0])) {
                return canRemove((DBObject) receiver);

            }
        }
        return false;
    }

    private String getLifecycle(DBObject data) {
        return (String) data.get(IVIMFields.LIFECYCLE);
    }

    private boolean canRemove(DBObject data) {
        return IVIMFields.LC_CANCELED.equals(getLifecycle(data));
    }

    private boolean canCancel(DBObject data) {
        return IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

    private boolean canReUpload(DBObject data) {
        return IVIMFields.LC_PRINTED.equals(getLifecycle(data))
                || IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

    private boolean canUpload(DBObject data) {

        return IVIMFields.LC_PRINTED.equals(getLifecycle(data));
    }

    private boolean canReprint(DBObject data) {
        // 对于已打印，未上传的合格证，再继续打印的话，可以更改纸质编号，记录打印日期，并保留历史数据
        // 即状态为已打印。
        // 2、对于已上传如果不修改任何内容只改纸质编号的话可以补打
        return IVIMFields.LC_PRINTED.equals(getLifecycle(data))
                || IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

}
