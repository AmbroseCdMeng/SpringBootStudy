package org.sang.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * 添加一个 Listener
 */
@WebListener
public class MyListener implements ServletRequestListener {//也可以是其他 Listener
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("MyListener >>>>> requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("MyListener >>>>> requestInitialized");
    }
}
