package com.sg.vim;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

public class COCPerspective implements IPerspectiveFactory, IPartListener {

	private static final String COM_SG_VIM_PRODUCTCODE = "com.sg.vim.productcode";
	private static final String COM_SG_VIM_COCINFO = "com.sg.vim.cocinfo";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addView(COM_SG_VIM_COCINFO, SWT.LEFT, .7f,
				"org.eclipse.ui.editorss");
		layout.addPlaceholder(COM_SG_VIM_PRODUCTCODE, SWT.RIGHT, .7f,
				COM_SG_VIM_COCINFO);

		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.addPartListener(this);
	}

	@Override
	public void partActivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		if (part instanceof EditorPart) {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			try {
				page.showView(COM_SG_VIM_PRODUCTCODE);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		if (part instanceof EditorPart) {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			IViewPart view = page.findView(COM_SG_VIM_PRODUCTCODE);
			if (view != null) {
				page.hideView(view);
			}
		}
	}

}
