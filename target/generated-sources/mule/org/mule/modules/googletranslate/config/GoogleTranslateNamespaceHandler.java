
package org.mule.modules.googletranslate.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/google-translate</code>.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-09-14T03:17:06-07:00", comments = "Build UNNAMED.2613.77421cc")
public class GoogleTranslateNamespaceHandler
    extends NamespaceHandlerSupport
{

    private static Logger logger = LoggerFactory.getLogger(GoogleTranslateNamespaceHandler.class);

    private void handleException(String beanName, String beanScope, NoClassDefFoundError noClassDefFoundError) {
        String muleVersion = "";
        try {
            muleVersion = MuleManifest.getProductVersion();
        } catch (Exception _x) {
            logger.error("Problem while reading mule version");
        }
        logger.error(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [google-translate] is not supported in mule ")+ muleVersion));
        throw new FatalBeanException(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [google-translate] is not supported in mule ")+ muleVersion), noClassDefFoundError);
    }

    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        try {
            this.registerBeanDefinitionParser("config", new GoogleTranslateConnectorConfigDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("config", "@Config", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-translate", new GetTranslateDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-translate", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("post-translate", new PostTranslateDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("post-translate", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-supported-languages", new GetSupportedLanguagesDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-supported-languages", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-detect-language", new GetDetectLanguageDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-detect-language", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("post-detect-language", new PostDetectLanguageDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("post-detect-language", "@Processor", ex);
        }
    }

}
