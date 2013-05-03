package com.sg.vim.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import com.sg.vim.print.control.PrintContent;

public class PrintView extends ViewPart {

    private PrintContent content;
    private Form form;
//    private Menu messagePopup;


    public PrintView() {
    }

    @Override
    public void createPartControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit( parent.getDisplay() );
        form = toolkit.createForm( parent );
//        addFormMessageLisener();
        
        ManagedForm mform = new ManagedForm(form);
        Composite body = form.getBody();
        body.setLayout(new FillLayout());
        content = new PrintContent(mform,form,SWT.NONE);
    }

    @Override
    public void setFocus() {
        content.setFocus();
    }
    
//    private void addFormMessageLisener() {
//
//        form.addMessageHyperlinkListener(new HyperlinkAdapter() {
//
//            public void linkActivated(HyperlinkEvent e) {
//
//                showMessagePopup(e);
//            }
//        });
//    }
//
//    private void showMessagePopup(HyperlinkEvent e) {
//
//        if (messagePopup != null && !messagePopup.isDisposed()) {
//            messagePopup.dispose();
//        }
//        messagePopup = new Menu(form.getShell(), SWT.POP_UP);
//        IMessage[] messages = form.getChildrenMessages();
//        for (int i = 0; i < messages.length; i++) {
//            final IMessage message = messages[i];
//            MenuItem item = new MenuItem(messagePopup, SWT.PUSH);
//            String text = message.getPrefix() + " " + message.getMessage();
////          text = Utils.getPlainText(text);
//            item.setText(text);
//            item.setImage(getMessageImage(message.getMessageType()));
//            item.addSelectionListener(new SelectionAdapter() {
//
//                @Override
//                public void widgetSelected(SelectionEvent e) {
//
//                    Control c = message.getControl();
//                    if (c != null && !c.isDisposed()) {
//                        c.setFocus();
//                    }
//                }
//            });
//        }
//        Point hl = ((Control) e.widget).toDisplay(0, 0);
//        hl.y += 16;
//        messagePopup.setLocation(hl);
//        messagePopup.setVisible(true);
//
//    }
//
//    /**
//     * 根据类型获取图标
//     * 
//     * @param type
//     * @return
//     */
//    private Image getMessageImage(int type) {
//
//        Display display = form.getShell().getDisplay();
//        switch (type) {
//        case IMessageProvider.ERROR:
//            return display.getSystemImage(SWT.ICON_ERROR);
//        case IMessageProvider.WARNING:
//            return display.getSystemImage(SWT.ICON_WARNING);
//        case IMessageProvider.INFORMATION:
//            return display.getSystemImage(SWT.ICON_INFORMATION);
//        }
//        return null;
//    }

}
