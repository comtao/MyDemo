package owen.cn.com.demo.support.http;

import java.util.Map;

import owen.cn.com.demo.support.file.FileDownloaderHttpHelper;
import owen.cn.com.demo.support.file.FileUploaderHttpHelper;

public class HttpUtil {
	
	private static HttpUtil httpUtil;

	private HttpUtil() {
	}

	public static HttpUtil getInstance() {
		if (httpUtil == null) {
			httpUtil = new HttpUtil();
		}
		return httpUtil;
	}


	/**
	 * token值放入 param中
	 * @param httpMethod
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String executeNormalTask(HttpMethod httpMethod, String url,
			Map<String, String> param) throws Exception {
		return new JavaHttpUtil().executeNormalTask(httpMethod,url,param);
	}
	
	public String executePostTask(String token,String url,String paramsObj){
		return JavaHttpUtil.doPost(token, url, paramsObj);
	}

	public boolean executeDownloadTask(String url, String path,
			FileDownloaderHttpHelper.DownloadListener downloadListener) {
		return !Thread.currentThread().isInterrupted()
				&& new JavaHttpUtil()
						.doGetSaveFile(url, path, downloadListener);
	}

	public String executeUploadTask(String url, Map<String, String> param,
			String path, String imageParamName,
			FileUploaderHttpHelper.ProgressListener listener) throws Exception {
		if(!Thread.currentThread().isInterrupted()){
			return new JavaHttpUtil().doUploadFile(url, param, path,
					imageParamName, listener);
		}
		return null;
	}

}
