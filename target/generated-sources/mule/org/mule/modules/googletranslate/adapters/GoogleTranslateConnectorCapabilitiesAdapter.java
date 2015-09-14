
package org.mule.modules.googletranslate.adapters;

import javax.annotation.Generated;
import org.mule.api.devkit.capability.Capabilities;
import org.mule.api.devkit.capability.ModuleCapability;
import org.mule.modules.googletranslate.GoogleTranslateConnector;


/**
 * A <code>GoogleTranslateConnectorCapabilitiesAdapter</code> is a wrapper around {@link GoogleTranslateConnector } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-09-14T03:17:06-07:00", comments = "Build UNNAMED.2613.77421cc")
public abstract class GoogleTranslateConnectorCapabilitiesAdapter
    extends GoogleTranslateConnector
    implements Capabilities
{


    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(ModuleCapability capability) {
        if (capability == ModuleCapability.LIFECYCLE_CAPABLE) {
            return true;
        }
        return false;
    }

}
