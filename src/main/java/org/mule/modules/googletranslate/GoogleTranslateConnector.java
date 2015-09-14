package org.mule.modules.googletranslate;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;

import java.io.IOException;

import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestHeaderParam;
import org.mule.api.annotations.rest.RestPostParam;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.annotations.rest.RestUriParam;

/**
 * Google Translate Anypoint Connector
 * @author Nathan (Dae Hyun) Nam
 * 	
 */

@Connector(name="google-translate", friendlyName="GoogleTranslate")
public abstract class GoogleTranslateConnector {


    /**
     * Get a translated text based on a target language
     * @param apiKey: key to identify your google translate application
     * @param sourceLanguage: the language of the source text
     * @param targetLanguage: the language to translate the source text into
     * @param text: the text to be translated (less than 2K characters)
     * @return
     * @throws IOException
     */

    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://www.googleapis.com/language/translate/v2?key={key}&", method=HttpMethod.GET)
    public abstract String getTranslate(
    		@RestUriParam("key") String apiKey,
    		@RestQueryParam("source") String sourceLanguage,
    		@RestQueryParam("target") String targetLanguage,
    		@RestQueryParam("q") String text
    		) throws IOException;  
    
    /**
     * Get a translated text based on a target language (for a longer text)
     * @param header: X-HTTP-Method-Override header should be set as "GET" to tell the translate api to treat the request as a GET.
     * @param apiKey: key to identify your google translate application
     * @param sourceLanguage: the language of the source text
     * @param targetLanguage: the language to translate the source text into
     * @param text: the text to be translated (less than 5K characters)
     * @return
     * @throws IOException
     */

    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://www.googleapis.com/language/translate/v2?key={key}&", method=HttpMethod.POST)
    public abstract String postTranslate(
    	    @Default(value = "GET")
    	    @RestHeaderParam ("X-HTTP-Method-Override") String header,
    		@RestUriParam("key") String apiKey,
    		@RestQueryParam("source") String sourceLanguage,
    		@RestQueryParam("target") String targetLanguage,
    		@RestPostParam("q") String text
    		) throws IOException;  
    /**
     * Get a list of supported languages by Google Translate.
     * @param apiKey: key to identify your google translate application
     * @param targetLanguage: language code will be paired with a full name in a language specified in targetLanguage.
     * @return
     * @throws IOException
     */
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://www.googleapis.com/language/translate/v2/languages?key={key}&", method=HttpMethod.GET)
    public abstract String getSupportedLanguages(
    		@RestUriParam("key") String apiKey,
    	    @Default(value = "en")
    		@RestQueryParam("target") String targetLanguage
    		) throws IOException;  
    
    /**
     * Detect in which language a text is written.
     * @param apiKey: key to identify your google translate application
     * @param text: the text to be detected (less than 2K characters)
     * @return
     * @throws IOException
     */
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://www.googleapis.com/language/translate/v2/detect?key={key}&", method=HttpMethod.GET)
    public abstract String getDetectLanguage(
    		@RestUriParam("key") String apiKey,
    		@RestQueryParam("q") String text
    		) throws IOException;  

    /**
     * Detect in which language a text is written. (less than 5K characters)
     * @param header: X-HTTP-Method-Override header should be set as "GET" to tell the translate api to treat the request as a GET.
     * @param apiKey
     * @param text
     * @return
     * @throws IOException
     */
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(uri="https://www.googleapis.com/language/translate/v2/detect?key={key}&", method=HttpMethod.POST)
    public abstract String postDetectLanguage(
    	    @Default(value = "GET")
    	    @RestHeaderParam ("X-HTTP-Method-Override") String header,
    		@RestUriParam("key") String apiKey,
    		@RestPostParam("q") String text
    		) throws IOException;  
     
}









