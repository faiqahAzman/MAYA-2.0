package com.tba.event;

import com.tba.swing.MenuItem;

public interface EventMenuSelected {

    public void menuSelected(int menuIndex, int subMenuIndex);

    public boolean menuPressed(MenuItem aThis, boolean b);
}
