package com.sg.vim.print.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.mobnut.commons.util.Utils;
import com.sg.ui.UIUtils;
import com.sg.vim.datamodel.util.VimUtils;

public class NumberInput extends Shell {

    protected int number;

    public NumberInput(Shell shell) {
        super(shell, SWT.APPLICATION_MODAL | SWT.BORDER);
        createContent(this);
    }

    private void createContent(Composite parent) {
        setLayout(new FormLayout());

        Label label = new Label(parent, SWT.NONE);
        label.setText("请输入新的合格证纸张编号:");
        FormData fd = new FormData();
        label.setLayoutData(fd);
        fd.top = new FormAttachment(0, 10);
        fd.left = new FormAttachment(0, 10);
        // 创建一个文本输入框
        final Text zzbnInput = new Text(parent, SWT.BORDER);
        fd = new FormData();
        zzbnInput.setLayoutData(fd);
        fd.top = new FormAttachment(label, 10);
        fd.left = new FormAttachment(0, 10);
        fd.width = 240;
        fd.right = new FormAttachment(100, -10);

        // 取出当前的纸张编号值
        int num = VimUtils.getMaxPaperOfCert();
        String s = String.format("%" + 0 + 7 + "d", num);
        zzbnInput.setText(s);

        // 创建补打按钮
        Button printButton = new Button(parent, SWT.PUSH);
        printButton.setText("确定");
        fd = new FormData();
        printButton.setLayoutData(fd);
        fd.top = new FormAttachment(zzbnInput, 10);
        fd.left = new FormAttachment(0, 10);
        fd.bottom = new FormAttachment(100, -10);
        printButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // 设置纸张编号
                String st = zzbnInput.getText();
                if (Utils.isNumbers(st)) {
                    try {
                        int n = Integer.parseInt(st);
                        NumberInput.this.number = n;
                        NumberInput.this.dispose();
                        return;
                    } catch (Exception e2) {
                    }
                }
                UIUtils.showMessage(NumberInput.this, "合格证补打", "需要输入合法的数字", SWT.ICON_ERROR | SWT.OK);

            }
        });

        Button closeButton = new Button(parent, SWT.PUSH);
        closeButton.setText("取消");
        fd = new FormData();
        closeButton.setLayoutData(fd);
        fd.top = new FormAttachment(zzbnInput, 10);
        fd.left = new FormAttachment(printButton, 10);
        fd.bottom = new FormAttachment(100, -10);
        closeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                NumberInput.this.dispose();
            }
        });
    }

    public int getNumber() {
        return number;
    }

}
