package com.sg.vim.view;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;

import com.sg.ui.ImageResource;
import com.sg.ui.UI;
import com.sg.ui.part.Navigator;

public class ShortcutFilterAction extends Action {

    private Navigator navigatable;
    private ShortcutFilterMenu menuManager;

    public ShortcutFilterAction(Navigator navigatable, String id) {
        this.navigatable = navigatable;
        setId(id);
        menuManager = new ShortcutFilterMenu(navigatable);

        setImageDescriptor(UI.getImageDescriptor(ImageResource.SEARCH2_32));
        setText("‘§∂®“Â≤È—Ø");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        ToolBarManager tm = (ToolBarManager) navigatable.getPart()
                .getToolBarManager();

        int index = tm.indexOf(this.getId()) - 1;
        ToolBar control = tm.getControl();
        Menu menu = menuManager.createContextMenu(control);

        Point hl = control.toDisplay(0, 0);
        hl.y += control.getBounds().height;
        hl.x += index * 42 + 50;

        menu.setLocation(hl);
        menu.setVisible(true);
    }

}
