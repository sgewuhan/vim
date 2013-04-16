package com.sg.vim.cocinfo.view;

import org.eclipse.jface.internal.provisional.action.ToolBarManager2;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.sg.ui.part.view.TableNavigator;

public class ProductView extends TableNavigator implements IPartListener {

  public ProductView() {
  }

  @Override
  public void createPartControl(Composite parent) {
    //
    getViewSite().getPage().addPartListener(this);

    super.createPartControl(parent);
  }

  @Override
  public void partActivated(IWorkbenchPart part) {

  }

  @Override
  public void partBroughtToTop(IWorkbenchPart part) {

  }

  @Override
  public void partClosed(IWorkbenchPart part) {
    IWorkbenchPage page = getViewSite().getPage();
    IEditorReference[] er = page.getEditorReferences();
    if (er == null || er.length == 0) {
      try {
        page.resetPerspective();
      } catch (Exception e) {
      }
    }
  }

  @Override
  public void partDeactivated(IWorkbenchPart part) {

  }

  @Override
  public void partOpened(IWorkbenchPart part) {
    // 当编辑器打开时，隐藏
    if (part instanceof EditorPart) {
      getViewSite().getPage().activate(part);
      getViewSite().getPage().hideView(this);
    }

  }

}
