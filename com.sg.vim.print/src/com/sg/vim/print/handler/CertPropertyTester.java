package com.sg.vim.print.handler;

import java.util.Date;

import org.eclipse.core.expressions.PropertyTester;

import com.mongodb.DBObject;
import com.sg.vim.datamodel.IVIMFields;

public class CertPropertyTester extends PropertyTester {

    public static final String ACT_REPRINT = "reprint";

    public static final String ACT_UPLOAD = "upload";

    public static final String ACT_REUPLOAD = "reupload";

    public static final String ACT_CANCEL = "cancel";

    public static final String ACT_REMOVE = "remove";
    
    public static final String ACT_EDIT = "edit";


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

            } else if (ACT_EDIT.equals(args[0])) {
                return canEdit((DBObject) receiver);

            }
        }
        return false;
    }


    private String getLifecycle(DBObject data) {
        return (String) data.get(IVIMFields.LIFECYCLE);
    }

    private boolean canRemove(DBObject data) {
        //针对已打印，未上传的数据，作废历史数据
        //根据VIN生成作废的合格证编号，正常上传，也可能补传
        //根据打印时间而定，只能打印新的，需要填写作废原因。
        //保留作废历史记录（不能修改的字段有：合格证编号，vin）

        return IVIMFields.LC_PRINTED.equals(getLifecycle(data));
    }

    private boolean canCancel(DBObject data) {
        return IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

    private boolean canReUpload(DBObject data) {
        // 对于打印后2天内未及时上传的数据为补传，对于正常数据，点击补传，弹出提示框，
        // 对于补传数据，要求填写补传原因

        if (IVIMFields.LC_PRINTED.equals(getLifecycle(data))) {
            Object pdate = data.get(IVIMFields.PRINTDATE);
            if (pdate instanceof Date) {
                long i = new Date().getTime() - ((Date) pdate).getTime();
                return i > 2 * 24 * 60 * 60 * 1000;
            }
        }

        return false;
    }

    private boolean canUpload(DBObject data) {

        if (IVIMFields.LC_PRINTED.equals(getLifecycle(data))) {
            Object pdate = data.get(IVIMFields.PRINTDATE);
            if (pdate instanceof Date) {
                long i = new Date().getTime() - ((Date) pdate).getTime();
                return i <= 2 * 24 * 60 * 60 * 1000;
            }
        }

        return false;
    }

    private boolean canReprint(DBObject data) {
        // 对于已打印，未上传的合格证，再继续打印的话，可以更改纸质编号，记录打印日期，并保留历史数据
        // 即状态为已打印。
        // 2、对于已上传如果不修改任何内容只改纸质编号的话可以补打
        return IVIMFields.LC_PRINTED.equals(getLifecycle(data))
                || IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }
    

    private boolean canEdit(DBObject data) {
        return IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

}
