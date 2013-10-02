/**
 * @author zhang.yangyang
 * @create_date 2013-6-25
 * @last_edit_author 
 * @last_edit_date 
 * @edit_remark
 */
package cc.tucao.zhang.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cc.tucao.zhang.finals.FileFinals;

/**
 * @name �ļ����������
 * @author zhang.yangyang
 * @create_date 2013-6-25
 * @edit_remark 
 */
public class FileTool {
    /**
     * �����ļ�
     * @param fileName �ļ���
     * @param dir Ŀ¼
     * @return �´������ļ�File����
     * @throws IOException
     */
    public File CreateFileInSDCard(String fileName,String dir) throws IOException{
	String filePath = FileFinals.SDCARDROOT+File.separator+dir+File.separator+fileName;
	String filedir = FileFinals.SDCARDROOT+File.separator+dir;
	File myDir = new File(filedir);
	
	if (!myDir.exists()) {
	    boolean bMkdirs = myDir.mkdirs();
	    DebugLog.logDebug("bMkdirs : " + bMkdirs);
	}
	File file = new File(filePath);

	DebugLog.logDebug("new file : " + filePath);

	file.createNewFile();
	return file;
    }

    /**
     * ����Ŀ¼
     * @param dir Ŀ¼��
     * @return �´�����Ŀ¼File����
     */
    public File CreateSDDir(String dir){
	DebugLog.logDebug("new dir: " + FileFinals.SDCARDROOT+File.separator+dir+File.separator);
	File file = new File(FileFinals.SDCARDROOT+File.separator+dir+File.separator);
	file.mkdirs();
	return file;
    }
    
    /**
     * ����ļ��Ƿ����
     * @param fileName �ļ���
     * @param path ·��
     * @return �Ƿ����
     */
    public boolean IsFileExist(String fileName,String path){
	File file = new File(FileFinals.SDCARDROOT+File.separator+path+File.separator+fileName);
	return file.exists();
    }
    /**
     * ���� ����ļ����Ƿ����
     * @param path ·��
     * @return �Ƿ����
     */
    public boolean IsFileExist(String path){
	File file = new File(FileFinals.SDCARDROOT+File.separator+path);
	return file.exists();
    }
    /**
     * ɾ��ָ��Ŀ¼�������ļ�
     * @param dir Ŀ¼��ַ
     */
    public static void RmFliesForDir(String dir){
	File file = new File(FileFinals.SDCARDROOT+File.separator+dir+File.separator);
	if (!file.exists()) {
	    return;
	}
	File[] files = file.listFiles();
	for (int i = 0; i < files.length; i++) {
	    File f = files[i];
	    f.delete();
	    DebugLog.logInfo("delete all files");
	}
	
    }
    /**
     * ɾ��ָ��Ŀ¼��ָ���ļ�
     * @param dir Ŀ¼��ַ
     */
    public static void RmFlie(String filepath,String filename){
	File file = new File(FileFinals.SDCARDROOT+File.separator+filepath+File.separator+filename);
	file.delete();
	DebugLog.logInfo("delete file :" + filename);
    }
    
    /**
     * �����ļ���SDCard
     * @param path ·��
     * @param fileName �ļ���
     * @param input inputstream����
     * @return ����õ��ļ�File����
     */
    public File Write2SDFromInput(String path,String fileName,InputStream input){
	File file = null;
	OutputStream output = null;
	
	try {
	    CreateSDDir(path);
	    file = CreateFileInSDCard(fileName, path);
	    output = new FileOutputStream(file);
	    byte buffer [] = new byte[4*1024];
	    int temp;
	    while((temp = input.read(buffer))!=-1){
		output.write(buffer,0,temp);
	    }
	    output.flush();
	} catch (Exception e) {
	    DebugLog.logError("e=" + e.getMessage().toString());
	}finally{
	    try {
		output.close();
	    } catch (IOException e) {
		DebugLog.logError("e=" + e.getMessage().toString());
	    }
	}
	return file;
    }
    
    /**
     * �����ļ���SDCard
     * @param path ·��
     * @param fileName �ļ���
     * @param url ��ַ
     * @return ����õ��ļ�File����
     */
    public File downLoadFile(String path,String fileName, String url) {
	File file = null;
	
	try {
	    InputStream is = HTMLTool.GetHttpConnection(url).getInputStream();
	    file = Write2SDFromInput(path, fileName, is);	
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return file;
    }
}
