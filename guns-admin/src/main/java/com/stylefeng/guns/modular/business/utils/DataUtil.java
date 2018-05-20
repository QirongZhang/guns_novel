package com.stylefeng.guns.modular.business.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/********************************************************************************
 * @program: DataUtil
 * @description: 获取reuqest payload 里的数据
 * @copyright: Copyright © 2018-2019
 * @author: Zhang Qirong
 * @version: 1.0
 * @create: 2018/5/15 Created
 *******************************************************************************/
public class DataUtil {

    /** 
    * @Description:  获取reuqest payload 里的数据
    * @Param: [request] 
    * @return: java.lang.String 
    * @Author: Zhang Qirong 
    * @Date: 2018/5/15 
    */
    public static String getBody(HttpServletRequest request) throws IOException {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
