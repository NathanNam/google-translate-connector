
package org.mule.modules.googletranslate.adapters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.registry.RegistrationException;
import org.mule.api.registry.ResolverException;
import org.mule.api.registry.TransformerResolver;
import org.mule.api.transformer.DataType;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.mule.modules.googletranslate.GoogleTranslateConnector;
import org.mule.registry.TypeBasedTransformerResolver;
import org.mule.transformer.simple.ObjectToString;
import org.mule.transformer.types.DataTypeFactory;
import org.mule.transport.http.HttpMuleMessageFactory;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-09-14T03:17:06-07:00", comments = "Build UNNAMED.2613.77421cc")
public class GoogleTranslateConnectorRestClientAdapter
    extends GoogleTranslateConnectorProcessAdapter
    implements MuleContextAware, Disposable, Initialisable
{

    private int responseTimeout;
    private MuleContext muleContext;
    private volatile HttpClient httpClient;
    private HttpMuleMessageFactory httpMuleMessageFactory;
    private volatile MultiThreadedHttpConnectionManager connectionManager;

    private MuleMessage getMuleMessage(HttpMethod method, String encoding) {
        try {
            MuleMessage muleMessage = httpMuleMessageFactory.create(method, encoding);
            muleMessage.getPayloadAsString();
            return muleMessage;
        } catch (Exception e) {
            throw new RuntimeException("Couldn't transform http response to MuleMessage", e);
        }
    }

    public void setMuleContext(MuleContext context) {
        muleContext = context;
        httpMuleMessageFactory = new HttpMuleMessageFactory(muleContext);
    }

    private Transformer getPayloadTransformer(DataType inputDataType, DataType outputDataType) {
        try {
            TransformerResolver typeBasedResolver = muleContext.getRegistry().lookupObject(TypeBasedTransformerResolver.class);
            Transformer typeResolverTransformer = typeBasedResolver.resolve(inputDataType, outputDataType);
            if ((typeResolverTransformer == null)||(typeResolverTransformer instanceof ObjectToString)) {
                Transformer transformer = muleContext.getRegistry().lookupTransformer(inputDataType, outputDataType);
                if (transformer!= null) {
                    return transformer;
                }
            }
            return typeResolverTransformer;
        } catch (ResolverException rese) {
            throw new RuntimeException(rese.getMessage(), rese);
        } catch (RegistrationException re) {
            throw new RuntimeException(re.getMessage(), re);
        } catch (TransformerException te) {
            throw new RuntimeException(te.getMessage(), te);
        }
    }

    @Override
    public void initialise()
        throws InitialisationException
    {
        connectionManager = new MultiThreadedHttpConnectionManager();
        httpClient = new HttpClient(connectionManager);
        httpClient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
        httpClient.getParams().setParameter("http.socket.timeout", responseTimeout);
        httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    }

    /**
     * Sets responseTimeout
     * 
     * @param value Value to set
     */
    public void setResponseTimeout(int value) {
        this.responseTimeout = value;
    }

    public String getTranslate(String apiKey, String sourceLanguage, String targetLanguage, String text)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://www.googleapis.com/language/translate/v2?key={key}&";
        uri = uri.replace("{key}", String.valueOf(apiKey));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("source").append("=").append(String.valueOf(sourceLanguage));
        queryString.append("&").append("target").append("=").append(String.valueOf(targetLanguage));
        queryString.append("&").append("q").append("=").append(String.valueOf(text));
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = GoogleTranslateConnector.class.getMethod("getTranslate", String.class, String.class, String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getTranslate", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String postTranslate(String header, String apiKey, String sourceLanguage, String targetLanguage, String text)
        throws IOException
    {
        HttpMethod method = null;
        method = new PostMethod();
        String uri = "https://www.googleapis.com/language/translate/v2?key={key}&";
        uri = uri.replace("{key}", String.valueOf(apiKey));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("source").append("=").append(String.valueOf(sourceLanguage));
        queryString.append("&").append("target").append("=").append(String.valueOf(targetLanguage));
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        method.addRequestHeader("X-HTTP-Method-Override", String.valueOf(header));
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new NameValuePair("q", String.valueOf(text)));
        ((PostMethod) method).addParameters(postParameters.toArray(new NameValuePair[] {}));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = GoogleTranslateConnector.class.getMethod("postTranslate", String.class, String.class, String.class, String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named postTranslate", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getSupportedLanguages(String apiKey, String targetLanguage)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://www.googleapis.com/language/translate/v2/languages?key={key}&";
        uri = uri.replace("{key}", String.valueOf(apiKey));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("target").append("=").append(String.valueOf(targetLanguage));
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = GoogleTranslateConnector.class.getMethod("getSupportedLanguages", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getSupportedLanguages", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String getDetectLanguage(String apiKey, String text)
        throws IOException
    {
        HttpMethod method = null;
        method = new GetMethod();
        String uri = "https://www.googleapis.com/language/translate/v2/detect?key={key}&";
        uri = uri.replace("{key}", String.valueOf(apiKey));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        queryString.append("&").append("q").append("=").append(String.valueOf(text));
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = GoogleTranslateConnector.class.getMethod("getDetectLanguage", String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named getDetectLanguage", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

    public String postDetectLanguage(String header, String apiKey, String text)
        throws IOException
    {
        HttpMethod method = null;
        method = new PostMethod();
        String uri = "https://www.googleapis.com/language/translate/v2/detect?key={key}&";
        uri = uri.replace("{key}", String.valueOf(apiKey));
        method.setURI(new URI(uri, false));
        StringBuilder queryString = new StringBuilder(((method.getQueryString()!= null)?method.getQueryString():""));
        if ((queryString.length()> 0)&&(queryString.charAt(0) == '&')) {
            queryString.deleteCharAt(0);
        }
        method.setQueryString(URIUtil.encodeQuery(queryString.toString()));
        method.addRequestHeader("X-HTTP-Method-Override", String.valueOf(header));
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new NameValuePair("q", String.valueOf(text)));
        ((PostMethod) method).addParameters(postParameters.toArray(new NameValuePair[] {}));
        httpClient.executeMethod(method);
        MuleMessage muleMessage = getMuleMessage(method, "UTF-8");
        String output = ((String) muleMessage.getPayload());
        if ((output!= null)&&(!String.class.isAssignableFrom(String.class))) {
            DataType payloadOutputDataType = null;
            try {
                Method reflectedMethod = GoogleTranslateConnector.class.getMethod("postDetectLanguage", String.class, String.class, String.class);
                payloadOutputDataType = DataTypeFactory.createFromReturnType(reflectedMethod);
                DataType payloadInputDataType = DataTypeFactory.create(String.class, ((String) muleMessage.getOutboundProperty("Content-Type")));
                Transformer transformer = getPayloadTransformer(payloadInputDataType, payloadOutputDataType);
                return ((String) transformer.transform(output));
            } catch (TransformerException te) {
                throw new RuntimeException(("Unable to transform output from String to "+ payloadOutputDataType.toString()), te);
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException("Unable to find method named postDetectLanguage", nsme);
            }
        } else {
            return ((String)((Object) output));
        }
    }

}
