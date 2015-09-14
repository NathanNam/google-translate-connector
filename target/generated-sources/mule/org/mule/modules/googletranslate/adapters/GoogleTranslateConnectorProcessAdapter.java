
package org.mule.modules.googletranslate.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.googletranslate.GoogleTranslateConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>GoogleTranslateConnectorProcessAdapter</code> is a wrapper around {@link GoogleTranslateConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-09-14T03:17:06-07:00", comments = "Build UNNAMED.2613.77421cc")
public abstract class GoogleTranslateConnectorProcessAdapter
    extends GoogleTranslateConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<GoogleTranslateConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, GoogleTranslateConnectorCapabilitiesAdapter> getProcessTemplate() {
        final GoogleTranslateConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,GoogleTranslateConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, GoogleTranslateConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, GoogleTranslateConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
