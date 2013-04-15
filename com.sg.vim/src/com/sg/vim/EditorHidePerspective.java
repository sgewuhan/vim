package com.sg.vim;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class EditorHidePerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);

	}

}
