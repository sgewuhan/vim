package com.sg.vim.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.sg.vim.view.CertificateView;

public class Remove extends AbstractHandler {

    // "1. �ǲ�������ֻ����Դ�ӡ��δ�ϴ��ĺϸ�֤��
    // 2. ��������VIN�������ϵĺϸ�֤�ţ�
    // 3. ���ϵĺϸ�֤���������ϴ��Ͳ�����
    // 4. ��Ȼ�Ѿ���ӡ����ôֻ�ܴ�ӡ�µ���ʲô��˼��
    // 5. �����޸ĵ��ֶ���ʲô��˼������ʱ��Ҫ�޸ĺϸ�֤����Ϣ��"
    // 1����
    // 2���ϸ�֤���ʹ��ԭ���ı��
    // 3�����ϵļ�¼�����ϴ��Ͳ�������Ҫ�ϴ�����VIN��Ӧ���ºϸ�֤���ϸ�֤��ź�ԭ����һ����
    // 4���ǰ�ԭ�ϸ�֤��Ϣ���ϣ�Ȼ�����vin���������µĺϸ�֤��Ϣ���ϸ�֤���ʹ��ԭ���ı��
    // 5�������ɵĺϸ�֤��Ϣ��vin�ͺϸ�֤��ź�ԭ���ı���һ��"

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
        if (sel == null || sel.isEmpty()) {
            return null;
        }
        
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        if(part instanceof CertificateView){
            ((CertificateView)part).doRemove();
        }
        return null;
    }

}
