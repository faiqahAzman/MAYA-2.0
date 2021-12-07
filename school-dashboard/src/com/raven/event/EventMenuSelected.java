package com.raven.event;

import com.raven.swing.MenuItem;

public interface EventMenuSelected {

    public void menuSelected(int menuIndex, int subMenuIndex);

    public boolean menuPressed(MenuItem aThis, boolean b);
}
