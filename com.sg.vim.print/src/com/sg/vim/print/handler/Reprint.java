package com.sg.vim.print.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mongodb.DBObject;
import com.sg.vim.print.view.CertificateView;

public class Reprint extends AbstractHandler {


    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        // �����Ѵ�ӡ��δ�ϴ��ĺϸ�֤���ټ�����ӡ�Ļ������Ը���ֽ�ʱ�ţ���¼��ӡ���ڣ���������ʷ����
        // 2���������ϴ�������޸��κ�����ֻ��ֽ�ʱ�ŵĻ����Բ���
        IStructuredSelection sel = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
        if (sel == null || sel.isEmpty()) {
            return null;
        }
        
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        DBObject data = (DBObject) sel.getFirstElement();
        if(part instanceof CertificateView){
            ((CertificateView)part).doRePrint(data);
        }
        return null;
    }
        
 

}
