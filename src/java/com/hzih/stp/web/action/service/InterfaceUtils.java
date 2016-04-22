package com.hzih.stp.web.action.service;

import com.inetec.common.io.IOUtils;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2015/12/2.
 */
public class InterfaceUtils {
    /**
     *
     * @param fwid
     * @param gmsfhm
     * @param yhm
     * @param mm
     * @param url
     * @return
     * @throws Exception
     */
    public static String getMsgForGmsfhm(String fwid, String gmsfhm, String yhm, String mm, String url) throws Exception {
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("FWID",fwid);
        jsonObj.put("GMSFHM", gmsfhm);
        jsonObj.put("YHM", yhm);
        jsonObj.put("MM", mm);
        String paramData = "params="+jsonObj.toString();
        URL localURL = new URL(url);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Charset", "utf-8");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(paramData.length()));
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        byte[]  bytes = null;
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream,"utf-8");
            outputStreamWriter.write(paramData.toString());
            outputStreamWriter.flush();
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            inputStream = httpURLConnection.getInputStream();
            bytes = readInputStream(inputStream);
        } finally {
         if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(bytes,"UTF-8");
    }

    public static void main(String args[])throws Exception {
        String json = InterfaceUtils.getMsgForGmsfhm("ntga2015100030","320621198604176935","064740","111111","http://10.36.11.93:10017/ntgajk/requestData.do");
        System.out.println(json);
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("FWID","ntga2015100029");
        jsonObj.put("GMSFHM", "320621198604176935");
        jsonObj.put("YHM", "064740");
        jsonObj.put("MM", "111111");
        String paramData = "params="+jsonObj.toString();
        URL localURL = new URL("http://10.36.11.93:10017/ntgajk/requestData.do");
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Charset", "utf-8");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(paramData.length()));
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        byte[]  bytes = null;
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream,"utf-8");
            outputStreamWriter.write(paramData.toString());
            outputStreamWriter.flush();
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            inputStream = httpURLConnection.getInputStream();
            bytes = readInputStream(inputStream);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        System.out.println(new String(bytes, "UTF-8"));
    }

    public static byte[] readInputStream(InputStream in){
        byte[] buffer  = new byte[1024];
        int len =-1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            while ((len=in.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
