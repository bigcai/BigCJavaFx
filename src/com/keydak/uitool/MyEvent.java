package com.keydak.uitool;

import javafx.scene.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: vk
 * Date: 2017/4/17
 * Time: 14:33
 * Description:
 *
 * 回调接口的上下文事件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEvent {
    private Parent root;
}
