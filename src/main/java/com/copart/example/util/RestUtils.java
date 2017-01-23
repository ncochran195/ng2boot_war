package com.copart.example.util;

/**
 * Created by Kashah on 1/23/2017.
 */

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.solr.core.query.result.FacetPage;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RestUtils
{
    private final Logger _logger = LoggerFactory.getLogger(this.getClass());
    private static final String ATTR_RETURN_CODE = "returnCode";
    private static final String ATTR_RETURN_CODE_DESC = "returnCodeDesc";
    private static final String ATTR_ERROR_MSG = "errorMsg";
    private static final String ATTR_DATA = "data";
    private final String REQ_PARAM_DELIMITER = "&";
    private final String QUERY = "query";
    private final String RESULTS = "results";
    private final String FIELD_NAME = "fieldName";
    private final String FACET_RESULTS = "facetResults";
    private final String PARAM_DELIMITER = "$";
    private static ObjectMapper mapper = new ObjectMapper();

    public enum Codes {
        RETURN_CODE_SUCCESS(1, "Success"),
        RETURN_CODE_ERROR(-1, "Error occured");

        private Integer statusCode;
        private String statusMsg;

        Codes(Integer statusCode, String statusMsg) {
            this.statusCode = statusCode;
            this.statusMsg = statusMsg;
        }
    }

    public static HashMap<String, Object> getRestfulView(HashMap<String, Object> dataMap) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_SUCCESS.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC, Codes.RETURN_CODE_SUCCESS.statusMsg);
        resultMap.put(ATTR_DATA, dataMap);

        return resultMap;
    }

    public static Map<String, Object> getRestfulView(Map<String, Object> dataMap) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_SUCCESS.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC, Codes.RETURN_CODE_SUCCESS.statusMsg);
        resultMap.put(ATTR_DATA, dataMap);

        return resultMap;
    }

    public static HashMap<String, Object> getSuccessRestfulView(HashMap<String, Object> dataMap) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_SUCCESS.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC, Codes.RETURN_CODE_SUCCESS.statusMsg);
        resultMap.put(ATTR_DATA, dataMap);

        return resultMap;
    }

    public static HashMap<String, Object> getSuccessRestfulView() {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_SUCCESS.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC, Codes.RETURN_CODE_SUCCESS.statusMsg);
        return resultMap;
    }

    public static HashMap<String, Object> getErrorRestfulView(HashMap<String, Object> dataMap) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_ERROR.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC, Codes.RETURN_CODE_ERROR.statusMsg);
        resultMap.put(ATTR_DATA, dataMap);

        return resultMap;
    }

    public static HashMap<String, Object> getErrorRestfulView(String msg) {
        return getErrorRestfulView(ATTR_ERROR_MSG, msg);
    }

    public static HashMap<String, Object> getErrorRestfulView(String key, String msg) {
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(key, msg);
        return getErrorRestfulView(dataMap);
    }

    public static String getErrorResponseObject(String errorMsg)
            throws IOException {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_ERROR.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC, errorMsg);
        return new ObjectMapper().writeValueAsString(resultMap);
    }

    public static String getErrorResponseObject(String errorMsg, Object data)
            throws IOException {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_ERROR.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC, errorMsg);
        resultMap.put(ATTR_DATA, data);
        return new ObjectMapper().writeValueAsString(resultMap);
    }

    public static String getSuccessResponseObject(Object data)
            throws IOException {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_SUCCESS.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC,
                Codes.RETURN_CODE_SUCCESS.statusMsg);
        resultMap.put(ATTR_DATA, data);

        return new ObjectMapper().writeValueAsString(resultMap);
    }

    public static HashMap<String, Object> getSuccessResponseMapObject(Object data)
            throws IOException {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_SUCCESS.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC,
                Codes.RETURN_CODE_SUCCESS.statusMsg);
        resultMap.put(ATTR_DATA, data);
        return resultMap;

    }

    public static HashMap<String, Object> getErrorResponseMapObject(Object data)
            throws IOException {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(ATTR_RETURN_CODE, Codes.RETURN_CODE_ERROR.statusCode);
        resultMap.put(ATTR_RETURN_CODE_DESC,
                Codes.RETURN_CODE_ERROR.statusMsg);
        resultMap.put(ATTR_DATA, data);
        return resultMap;

    }


    /*public static HttpResponse restRequest(URIBuilder builder,
        CloseableHttpClient httpClient, HttpClientContext context)
            throws ClientProtocolException, IOException, URISyntaxException
    {
        URI uri = builder.build();

        HttpGet getReq = new HttpGet(uri);
        getReq.addHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
        getReq.addHeader("X-GDC-CHECK-DOMAIN", "false");
        HttpResponse getResponse = httpClient.execute(getReq, context);
        return getResponse;
    }*/

    public static Object mapJSONtoObject(String json, Class c)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object pojoObject = mapper.readValue(json, c);
        return pojoObject;

    }

    public static Object mapJSONtoObject(HttpServletRequest request, Class c)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object pojoObject = null;

        BufferedReader reader = null;

        try {
            reader = request.getReader();
            pojoObject = mapper.readValue(reader, c);
            return pojoObject;

        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    /**
     * Method convertLookupItemJsonListResponse.
     * Desc: JSON parsing request to Hashmap
     *
     * @param request HttpServletRequest
     * @return HashMap<String,Object>
     * @throws JsonGenerationException Exception
     * @throws JsonMappingException    Exception
     * @throws IOException             Exception
     */
    public static HashMap<String, Object> convertJsonToHashMap(HttpServletRequest request) throws IOException {

        BufferedReader reader = null;
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        HashMap<String, Object> requestParams = new HashMap<String, Object>();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            TypeReference<HashMap<String, Object>> typeRef
                    = new TypeReference<HashMap<String, Object>>() {
            };
            reader = request.getReader();

            //Mapping Json object into Hashmap object
            requestParams = mapper.readValue(reader, typeRef);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return requestParams;
    }

    /*public static <T> T getReducedJsonPayload(T returnObj, Class clazz, String[] excludes, String includes[])
    {
        return JsonResult.instance().use(JsonView.with(returnObj)
                .onClass(clazz, Match.match()
                        .exclude(excludes)
                        .include(includes)))
                .returnValue();
    }

    public static <T> T getReducedJsonPayload(T returnObj, Class clazz, String... includes)
    {
        return JsonResult.instance().use(JsonView.with(returnObj)
                .onClass(clazz, Match.match()
                        .exclude(new String[]{"*"})
                        .include(includes)))
                .returnValue();
    }*/

    public static Map<String, Object> postRequest(HttpClient httpClient, String uri, List<NameValuePair> nvps) throws IOException, URISyntaxException {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        HttpResponse response = httpClient.execute(httpPost);
        Map<String, Object> carMap = mapper.readValue(EntityUtils.toString(response.getEntity()), new TypeReference<Map<String, Object>>() {
        });
        return carMap;
    }

    public static HttpResponse postRequestForResponse(HttpClient httpClient, String uri, List<NameValuePair> nvps, String contentType) throws IOException, URISyntaxException {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Accept", contentType);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        HttpResponse response;
        try {
            response = httpClient.execute(httpPost);
        } finally {
            httpPost.releaseConnection();
        }
        return response;
    }

    public static HttpResponse putRequestForResponse(HttpClient httpClient, String uri, StringEntity stringEntity, String accessToken) throws IOException, URISyntaxException {
        HttpPut httpPut = new HttpPut(uri);
        httpPut.addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        httpPut.addHeader("Authorization", "Bearer " + accessToken);
        httpPut.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(httpPut);
        BasicHttpResponse httpResponse = new BasicHttpResponse(response.getStatusLine());
        return httpResponse;
    }

    public static String convertToJsonString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    public static <T> T convertFromJsonString(String str, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(str, clazz);
    }

    public static <T> T convertFromJsonString(String str, TypeReference<T> clazz) throws IOException
    {
        return new ObjectMapper().readValue(str, clazz);
    }

    public <T> Map<String, Object> getSolrQueryReturnMap(String fieldName, String q, String fq, FacetPage<T> data) {
        HashMap<String, Object> dataMap = new HashMap<>();
        if (fieldName != null) {
            dataMap.put(FIELD_NAME, fieldName);
        }
        dataMap.put(QUERY, q + PARAM_DELIMITER + fq);
        dataMap.put(RESULTS, data.getContent());
        dataMap.put(FACET_RESULTS, data.getFacetResultPages().stream().flatMap(page -> page.getContent().stream())
                .collect(Collectors.groupingBy(content -> content.getField().getName())));
        return getSuccessRestfulView(dataMap);
    }

}