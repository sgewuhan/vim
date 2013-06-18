package com.sg.vim.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.sg.vim.view.CertificateView;

public class Remove extends AbstractHandler {

    // "1. 是不是作废只是针对打印但未上传的合格证？
    // 2. 怎样根据VIN生成作废的合格证号？
    // 3. 作废的合格证可以正常上传和补传？
    // 4. 既然已经打印，那么只能打印新的是什么意思？
    // 5. 不能修改的字段是什么意思，作废时需要修改合格证的信息吗？"
    // 1、是
    // 2、合格证编号使用原来的编号
    // 3、作废的记录不能上传和补传，需要上传的是VIN对应的新合格证（合格证编号和原来的一样）
    // 4、是把原合格证信息作废，然后根据vin重新生成新的合格证信息，合格证编号使用原来的编号
    // 5、新生成的合格证信息中vin和合格证编号和原来的保持一致"

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
