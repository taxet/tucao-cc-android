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
 * @name 文件操作相关类
 * @author zhang.yangyang
 * @create_date 2013-6-25
 * @edit_remark 
 */
public class FileTool {
    /**
     * 创建文件
     * @param fileName 文件名
     * @param dir 目录
     * @return 新创建的文件File对象
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
     * 创建目录
     * @param dir 目录名
     * @return 新创建的目录File对象
     */
    public File CreateSDDir(String dir){
	DebugLog.logDebug("new dir: " + FileFinals.SDCARDROOT+File.separator+dir+File.separator);
	File file = new File(FileFinals.SDCARDROOT+File.separator+dir+File.separator);
	file.mkdirs();
	return file;
    }
    
    /**
     * 检查文件是否存在
     * @param fileName 文件名
     * @param path 路径
     * @return 是否存在
     */
    public boolean IsFileExist(String fileName,String path){
	File file = new File(FileFinals.SDCARDROOT+File.separator+path+File.separator+fileName);
	return file.exists();
    }
    /**
     * 重载 检查文件夹是否存在
     * @param path 路径
     * @return 是否存在
     */
    public boolean IsFileExist(String path){
	File file = new File(FileFinals.SDCARDROOT+File.separator+path);
	return file.exists();
    }
    /**
     * 删除指定目录下所有文件
     * @param dir 目录地址
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
     * 删除指定目录下指定文件
     * @param dir 目录地址
     */
    public static void RmFlie(String filepath,String filename){
	File file = new File(FileFinals.SDCARDROOT+File.separator+filepath+File.separator+filename);
	file.delete();
	DebugLog.logInfo("delete file :" + filename);
    }
    
    /**
     * 保存文件到SDCard
     * @param path 路径
     * @param fileName 文件名
     * @param input inputstream对象
     * @return 保存好的文件File对象
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
     * 保存文件到SDCard
     * @param path 路径
     * @param fileName 文件名
     * @param url 地址
     * @return 保存好的文件File对象
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
