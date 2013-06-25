package com.sg.vim.test;

import java.util.Date;

import org.eclipse.core.expressions.PropertyTester;

import com.mongodb.DBObject;
import com.sg.vim.VimUtils;
import com.sg.vim.model.IVIMFields;

public class LifecycleTester extends PropertyTester {

    public static final String ACT_REPRINT = "reprint";

    public static final String ACT_UPLOAD = "upload";

    public static final String ACT_REUPLOAD = "reupload";

    public static final String ACT_CANCEL = "cancel";

    public static final String ACT_REMOVE = "remove";

    public static final String ACT_EDIT = "edit";

    public static final String ACT_PRINT = "print";
    
    public static final String ACT_REASSY = "reassembly";

    public LifecycleTester() {
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof DBObject) {
            if (ACT_REPRINT.equals(args[0])) {
                return canReprint((DBObject) receiver);

            } else if (ACT_UPLOAD.equals(args[0])) {
                return canUpload((DBObject) receiver, expectedValue);

            } else if (ACT_REUPLOAD.equals(args[0])) {
                return canReUpload((DBObject) receiver);

            } else if (ACT_CANCEL.equals(args[0])) {
                return canCancel((DBObject) receiver);

            } else if (ACT_REMOVE.equals(args[0])) {
                return canRemove((DBObject) receiver);

            } else if (ACT_EDIT.equals(args[0])) {
                return canEdit((DBObject) receiver);

            } else if (ACT_PRINT.equals(args[0])) {
                return canPrint((DBObject) receiver, expectedValue);

            } else if(ACT_REASSY.equals(args[0])){
                return canReassembly((DBObject) receiver);
            }
        }
        return false;
    }

    private boolean canReassembly(DBObject data) {
        //�Ѿ������Ŀ���
        return IVIMFields.LC_CANCELED.equals(getLifecycle(data));
    }

    private boolean canPrint(DBObject data, Object type) {
        boolean b = IVIMFields.LC_ABANDON.equals(getLifecycle(data));
        if (!b) {
            return false;
        }

        // �������˿����ظ���ӡʱ���������Ϊ����ʱ�������ٴδ�ӡ
        if ("cocRePrintable".equals(type)) {
            return VimUtils.COC_REPRINT;
        } else if ("flRePrintable".equals(type)) {
            return VimUtils.FL_REPRINT;
        }

        return false;

    }

    private String getLifecycle(DBObject data) {
        return (String) data.get(IVIMFields.LIFECYCLE);
    }

    private boolean canRemove(DBObject data) {
        // ����Ѵ�ӡ��δ�ϴ������ݣ�������ʷ����
        // ����VIN�������ϵĺϸ�֤��ţ������ϴ���Ҳ���ܲ���
        // ���ݴ�ӡʱ�������ֻ�ܴ�ӡ�µģ���Ҫ��д����ԭ��
        // ����������ʷ��¼�������޸ĵ��ֶ��У��ϸ�֤��ţ�vin��

        return IVIMFields.LC_PRINTED.equals(getLifecycle(data));
    }

    private boolean canCancel(DBObject data) {
        return IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

    private boolean canReUpload(DBObject data) {
        // ���ڴ�ӡ��2����δ��ʱ�ϴ�������Ϊ�����������������ݣ����������������ʾ��
        // ���ڲ������ݣ�Ҫ����д����ԭ��

        if (IVIMFields.LC_PRINTED.equals(getLifecycle(data))) {
            Object pdate = data.get(IVIMFields.MANUFACTUREDATE);
            if (pdate instanceof Date) {
                long i = new Date().getTime() - ((Date) pdate).getTime();
                return i > 2 * 24 * 60 * 60 * 1000;
            }
        }

        return false;
    }

    private boolean canUpload(DBObject data, Object expectedValue) {
        if (IVIMFields.LC_PRINTED.equals(getLifecycle(data))) {
            Object pdate = data.get(IVIMFields.MANUFACTUREDATE);
            if (pdate instanceof Date) {
                long i = new Date().getTime() - ((Date) pdate).getTime();
                return i <= 2 * 24 * 60 * 60 * 1000;
            }
        }

        return false;
    }

    private boolean canReprint(DBObject data) {
        // �����Ѵ�ӡ��δ�ϴ��ĺϸ�֤���ټ�����ӡ�Ļ������Ը���ֽ�ʱ�ţ���¼��ӡ���ڣ���������ʷ����
        // ��״̬Ϊ�Ѵ�ӡ��
        // 2���������ϴ�������޸��κ�����ֻ��ֽ�ʱ�ŵĻ����Բ���
        return IVIMFields.LC_PRINTED.equals(getLifecycle(data))
                || IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

    private boolean canEdit(DBObject data) {
        return IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

}
