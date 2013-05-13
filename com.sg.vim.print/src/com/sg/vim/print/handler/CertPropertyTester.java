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
        //����Ѵ�ӡ��δ�ϴ������ݣ�������ʷ����
        //����VIN�������ϵĺϸ�֤��ţ������ϴ���Ҳ���ܲ���
        //���ݴ�ӡʱ�������ֻ�ܴ�ӡ�µģ���Ҫ��д����ԭ��
        //����������ʷ��¼�������޸ĵ��ֶ��У��ϸ�֤��ţ�vin��

        return IVIMFields.LC_PRINTED.equals(getLifecycle(data));
    }

    private boolean canCancel(DBObject data) {
        return IVIMFields.LC_UPLOADED.equals(getLifecycle(data));
    }

    private boolean canReUpload(DBObject data) {
        // ���ڴ�ӡ��2����δ��ʱ�ϴ�������Ϊ�����������������ݣ����������������ʾ��
        // ���ڲ������ݣ�Ҫ����д����ԭ��

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
