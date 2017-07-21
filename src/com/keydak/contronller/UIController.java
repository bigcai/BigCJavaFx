package com.keydak.contronller;

/**
 * Created by vk on 2017/4/17.
 */
public interface UIController {
    /**
     * 加载Fxml配置
     */
     void loadFxml();

    /**
     * 初始化UI参数
     */
     void initUi();

    /**
     * 注册UI控件的监听器
     */
     void registerListener();
}
