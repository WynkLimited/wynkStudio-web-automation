package in.wynk.API;

import in.wynk.common.Utils;
import in.wynk.framework.Driver.HashMapNew;
import in.wynk.framework.DriverFactory;
import in.wynk.framework.Reporting;
import in.wynk.pages.CommonPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.SignatureException;

public class TvUtknGenerator extends CommonPage {

  private static final Logger log = LoggerFactory.getLogger(TvUtknGenerator.class);

  public TvUtknGenerator(Reporting Reporter, in.wynk.framework.Assert Assert,
                         in.wynk.framework.SoftAssert SoftAssert,
                         ThreadLocal<HashMapNew> sTestDetails) {
    super(Reporter, Assert, SoftAssert, sTestDetails);
  }

  public static String getSecret() {
    Utils utils = new Utils(null);
    HashMapNew env = utils.getEnvValues();
    String secret = null;
    if (getConfigXMLValue("env").equalsIgnoreCase("PROD")) {
      secret = "2d7db9";
    } else if (getConfigXMLValue("env").equalsIgnoreCase("DEV") || env.get("env").equalsIgnoreCase("LOCAL") || getConfigXMLValue("env").equalsIgnoreCase("PREPROD")) {
      secret = "blabla";
    }
    return secret;
  }


//  public static String getUtkn(String phoneNumber, String method, String requestUri) {
//    String token = "";
//    String payload = "";
//    String uid = GenerateUid.getUidMsisdn(phoneNumber);
//    if (method.trim().equalsIgnoreCase("GET")) {
//      token = generateSignatureGET(requestUri, uid);
//    } else if (method.trim().equalsIgnoreCase("POST")) {
//      token = generateSignaturePOST(requestUri, payload, uid);
//    } else if (method.trim().equalsIgnoreCase("DELETE")) {
//      token = generateSignatureDELETE(requestUri, uid);
//    }
//    log.info("Utkn token for \nenvironment :- " + DriverFactory.getTestDetails().get("env") + "\nuid :- " + uid
//            + "\nutkn :- " + token);
//    return token;
//  }
public static String getUtkn(String phoneNumber, String method, String requestUri) {
  String token = "";
  String payload = "";
  String uid = GenerateUid.getUidMsisdn(phoneNumber);
  if (method.trim().equalsIgnoreCase("GET")) {
    token = generateSignatureGET(requestUri, uid);
  } else if (method.trim().equalsIgnoreCase("POST")) {
    token = generateSignaturePOST(requestUri, payload, uid);
  }
  log.info("Utkn token for \nenvironment :- " + getConfigXMLValue("env") + "\nuid :- " + uid
          + "\nutk :- " + token);
  return token;
}

  public static String getUtkn(String phoneNumber, String method, String requestUri,String payload) {
    String token = "";
    String uid = GenerateUid.getUidMsisdn(phoneNumber);
    if (method.trim().equalsIgnoreCase("GET")) {
      token = generateSignatureGET(requestUri, uid);
    } else if (method.trim().equalsIgnoreCase("POST")) {
      token = generateSignaturePOST(requestUri, payload, uid);
    }
    log.info("Utkn token for \nenvironment :- " + getConfigXMLValue("env") + "\nuid :- " + uid
        + "\nutk :- " + token);
    return token;
  }

  public static String generateSignatureGET(String url1, String uid) {
    String token;
    String signature = null;
    String digestStr = null;
    try {
      token = calculateRFC2104HMAC(uid, getSecret());
      URL uri = new URL(URLDecoder.decode(url1, "UTF-8"));
      if (uri.getQuery() != null && !uri.getQuery().isEmpty()) {
        digestStr = uri.getPath() + "?" + uri.getQuery();
      } else {
        digestStr = uri.getPath();
      }
      signature = generateHMACSignature("GET", digestStr, token);
    } catch (SignatureException e) {
      log.error(e.getMessage());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return getSignatureInFormat(uid, signature);
  }

  public static String generateSignaturePOST(String url1, String payload, String uid) {
    String signature = null;
    String digestStr = null;
    try {
      String token = calculateRFC2104HMAC(uid, getSecret());
      URL uri = new URL(URLDecoder.decode(url1, "UTF-8"));
      if(uri.getQuery() != null && !uri.getQuery().isEmpty())
        digestStr=uri.getPath()  + "?" + uri.getQuery();
      else
        digestStr=uri.getPath();
      if(StringUtils.isEmpty(payload))
        signature = generateHMACSignature("POST", digestStr, token);
      else {
        signature = generateHMACSignature("POST", digestStr, payload,
            token);
      }
    } catch (SignatureException | MalformedURLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return getSignatureInFormat(uid, signature);
  }

  public static String generateHMACSignature(String httpVerb, String requestUri, String secret)
      throws SignatureException {
    String signature = StringUtils.EMPTY;
    String digestString = new StringBuilder(httpVerb).append(requestUri).toString();
    try {
      signature = calculateRFC2104HMAC(digestString, secret);
    } catch (SignatureException e) {
      log.error(e.getMessage());
      throw e;
    }
    return signature;
  }

  public static String generateHMACSignature(String httpVerb, String requestUri, String payload,
      String secret) throws SignatureException {
    String signature = StringUtils.EMPTY;
    String digestString = new StringBuilder(httpVerb).append(requestUri).append(payload).toString();
    try {
      signature = calculateRFC2104HMAC(digestString, secret);
    } catch (SignatureException e) {
      log.error(e.getMessage());
      throw e;
    }
    return signature;
  }
  public static String generateSignatureDELETE(String url1, String uid) {
    String token;
    String signature = null;
    String digestStr = null;
    try {
      token = calculateRFC2104HMAC(uid, getSecret());
      URL uri = new URL(URLDecoder.decode(url1, "UTF-8"));
      if (uri.getQuery() != null && !uri.getQuery().isEmpty()) {
        digestStr = uri.getPath() + "?" + uri.getQuery();
      } else {
        digestStr = uri.getPath();
      }
      signature = generateHMACSignature("DELETE", digestStr, token);
    } catch (SignatureException e) {
      log.error(e.getMessage());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return getSignatureInFormat(uid, signature);
  }
  public static String getSignatureInFormat(String uid, String signature) {
    return uid + ":" + signature;
  }

  public static String calculateRFC2104HMAC(String data, String secretKey)
      throws SignatureException {
    String result;
    try {
      Mac mac = Mac.getInstance("HmacSHA1");
      SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA1");
      mac.init(key);
      byte[] authentication = mac.doFinal(data.getBytes());
      result = new String(org.apache.commons.codec.binary.Base64.encodeBase64(authentication));
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
    }
    return result;
  }

  public static String getConfigXMLValue(String key) {
    Utils utils = new Utils(null);
    HashMapNew env = utils.getEnvValues();
    String envValue = env.get(key);
    return envValue;
  }
}