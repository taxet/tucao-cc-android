/**
 * @author zhang.yangyang
 * @create_date 2013-6-24
 */
package cc.tucao.zhang.tools;

import android.util.Log;


/**
 * @name Log пео╒
 * @author zhang.yangyang
 * @create_date 2013-6-24
 * @edit_remark
 */
public class DebugLog {
    public static final int LOG_LEVEL = Log.DEBUG;
    private static String TAG = "TuCao";
    private static boolean sm_debug = true;
    
    //ERROR
    public static void logError(String msg) {
        log(msg, Log.ERROR);
    }
    //DEBUG
    public static void logDebug(String msg) {
        log(msg, Log.DEBUG);
    }
    //WARN
    public static void logWarn(String msg) {
        log(msg, Log.WARN);
    }
    //INFO
    public static void logInfo(String msg) {
        log(msg, Log.INFO);
    }
    //VERBOSE
    public static void logVerbose(String msg) {
        log(msg, Log.VERBOSE);
    }
    //LOG_LEVEL
    private static void log(String msg, int level) {
        if (level < LOG_LEVEL) {
        	return;
        }
        if (sm_debug) {
        	Log.println(level, TAG, msg);
        }
    }
}
