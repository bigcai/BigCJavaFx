package com.keydak.uitool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: vk
 * Date: 2017/4/21
 * Time: 10:45
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    String text;
    Object value;

    @Override
    public String toString() {
        return text;
    }
}
