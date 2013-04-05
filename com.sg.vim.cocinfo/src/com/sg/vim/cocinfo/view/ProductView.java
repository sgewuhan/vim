package com.sg.vim.cocinfo.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
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
//    if(part instanceof ProductView){
//      ((ProductView) part).getViewSite().getActionBars().updateActionBars();
//    }
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
//        IViewPart v = page.showView(getViewSite().getId());
        page.resetPerspective();
      } catch (Exception e) {
        // e.printStackTrace();
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
//    if(part instanceof ProductView){
//      ((ProductView) part).getViewSite().getActionBars().updateActionBars();
//    }
  }

}
