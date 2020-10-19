package org.camel.alipay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101100661109";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZ1AC+edzCU1Je40Dy+WFR5Z12AbxO0E1fv1fq2c7TNmYLLxaaz0UMs1KNAoDO1t6F/rjlDxNcX/4icmYcs3cOdvMWyWkS+Mm4sYnaZFKEhezOoW6gJX8QT3WafDjIXCaULnQ0OxIntua6wYHi9SC7IS9jTyYvW63MNHWOohINFhyvu4BfbZd30u8yxcRTzr4TaTJBIj2n+sEAnsjnx/Ab8AQV7HUWYlaeVX5qATr6Nd05U+HBciQSwV+OuxRWbIA9SF3hP89ViS2vrcacG4UrMwdgrgxwXrTEcL4whGB8o0HiUyEghjmRPtNObyamqDqoRmq8QusAqm+Q7qjnUudPAgMBAAECggEAUNz8QyTmYpTQYScPVmq3uQh+PLFu3vNrKSD5w3AZ5DfdbpWjSWAZyTJe+9dMBO847LFZWrgREmc3vb3rZb0FoenvoZPhE2MLD3bso4xHQHjyYjDEaZUsdVN/2+TfKJwgf5bn6SkAQzZHlfbxI3B+c0Bfm9HYnd6Qvx74iTEzUgLxkY9IL59rkr8L9OEmOsrc2fQUKU9Yfe/nrgZExlUhf3UdLrg3dvKcOUzIMGZY6VRa3NBG8AC+arFu8q4qYSF0qN5J06kzo+kBE9Gdw8PXeeK2Ztpt3+X2v9hBigc+eIW/RM87gHyKTGIKn2c75jBnwV8CXDIp2dObdCzPz6hiwQKBgQDnm7qD9DKzBx2gj9k8oH1a6odTHIIzrbXZ9d8bs+QGdQwa84lN7B0wSgpIfR4O7Vs18dnXCtYEIBNPexsuMwaf0SiXGIoigmux6Qh5ePZwnFFhNe/vdQb++waJ3WY0G5wzNDDUw57UdItCIsKGFOyGJsQOUvFft4WsEQu5VDN1LwKBgQCqB0iKHGRH2zW8lW+pT4N5T/n74BB9zNG0ecIswY9dpoOhxQ2ORFCOBwFrMhoKAw0ErR1hjjXylFTlNRAD3eu4+h7Zk7ZyFyeVBRT5olG5GAnev6aLaqSbaofGu4PxRpv6/eSXGrteauD6Fz98+O42hIFvIrbVZMK5+Y6gJn1n4QKBgQCBAWTqiuKShawi+vztoBcyxN+Lk52SW3enaCRVseWZEyki+fPIy3OWqicAKXDMr7jWW1gTR53dvmADLXb+TL2r1zzcrHaEslB6UGq//niGUQujMf/rSSwUao5BzGhZLefbPX7VhAC8Jg5LUEE5nf4A2ybpM5nuzrJOHVCCcjwXUQKBgFn2iJcq9pRelEw39WDsRXBGtMpkf9VQLhUD542uzKCMnlFV5hpEYysC4elKFST/AEBdKJKZoEm92j18mgCxxyuUjAYrdflf7v2WNsbu77az1WUampBh2L6y2yx3wxRCe/2NoQmih0kp2+f3rQv+2mvWKHYZNAwsyCuGs8RhGTGBAoGAeLg57/AGU63bGeKHaIid0AGPujGQ6zU6HB/UM3UJ2gNJyXg6bqD5VXcQpaglIEzFXvQWkngClm+UKmoFVvsYaYCiP9JrADUBK1B6EsA025Yq+4WheI2KDMwQs//jo5FT9SBAbfrQStwPO79uT16VoT3zO1TbmET+DHNE6ymkmNY=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjUNg8aTxR5RbEuEPXHPQyzP2B83bsMccsj4EzH3CnpgqIZh+u9rrxTrmGXDPnjrM2UxWFLxyXZ/2jKUZw0hAN1H+IxjtiKtyg7hcRCdToNCXF4UufT3piz3sMwRta5MFaf8Khp1MUQwpt+QRdd6TEgVE8r0jnro1jSFbRcxVOmWWIzwPeI73dric/6F1n3OVfIPUdUO0v009+RY3ETUBeQX2j5P2TW5h19/gp5c+GR+gl8mMv9MtikQ6Rq9joqkEy9ElwmeOT6TERvxAeztCQL6cOl+06OvoriW9KwCC+xSUKBjLKxUGNpfywWhvCYD6/CfhYxL4fduqazehW7xVqQIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/mecs.web.front/finishRecharge.action";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/mecs.web.front/finishRecharge.action";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	//public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

