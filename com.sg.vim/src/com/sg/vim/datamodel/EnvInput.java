package com.sg.vim.datamodel;

import com.mongodb.DBObject;
import com.sg.ui.model.input.IInputProvider;
import com.sg.ui.model.input.IInputReceiver;

public class EnvInput implements IInputProvider {

    public EnvInput() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addInputReceiver(IInputReceiver receiver, boolean queryMode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeInputReceiver(IInputReceiver receiver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setInput(Object input) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reload() {
        // TODO Auto-generated method stub

    }

    @Override
    public DBObject insert(DBObject object, int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(Object data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(DBObject data, DBObject updated) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSkipAndLimit(int skip, int limit) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getInputCount(DBObject query) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setQuery(DBObject query) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSort(DBObject sort) {
        // TODO Auto-generated method stub

    }

}
